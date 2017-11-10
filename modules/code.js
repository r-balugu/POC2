gblClaimToken = "";
passwordCheck = false;
//Type your code here
mobileFabricConfiguration = 
  {
  appKey:"f872e665079e2e588156aa46b48a284e", 
  appSecret:"7c1c65441ef0f2963ef1ec154c2830b5", 
  serviceURL:"https://100011509.auth.konycloud.com/appconfig",
  identityServices: 
  [
    {
      service:"userstore",
      username:"Novartis.Europe@kony.com",
      password: "Default@1234"
    },
    {
      service:"FacebookIdentity"
    }
  ],
  integrationServices: [{
    service: "Store",
    operations: ["CreateUser"]
  },{
    service: "KonyAccounts",
    operations: ["accountLogin"]
  }],
  konysdkObject: null,
  authClient: null,
  integrationObj: null,
  isKonySDKObjectInitialized:false,
  isMFAuthenticated: false
};

function initializeMobileFabric()
{
  kony.print (" ********** Entering into initializeMobileFabric ********** ");
  if (kony.net.isNetworkAvailable(constants.NETWORK_TYPE_ANY))
  {
    //    kony.application.showLoadingScreen("loadskin","Initializing the app !!!",constants.LOADING_SCREEN_POSITION_FULL_SCREEN , true,true,{enableMenuKey:true,enableBackKey:true, progressIndicatorColor : "ffffff77"});
    kony.application.showLoadingScreen("", "", constants.LOADING_SCREEN_POSITION_ONLY_CENTER, true, true, null);

    mobileFabricConfiguration.konysdkObject = new kony.sdk();
    mobileFabricConfiguration.konysdkObject.init(mobileFabricConfiguration.appKey,mobileFabricConfiguration.appSecret,mobileFabricConfiguration.serviceURL,initializeMobileFabricSuccess,initializeMobileFabricFailure);  
  }
  else
    alert ("Network unavailable. Please check your network settings. ");
  kony.print (" ********** Exiting out of initializeMobileFabric ********** ");
}

function initializeMobileFabricSuccess(response)
{
  kony.print (" ********** Entering into initializeMobileFabricSuccess ********** ");
  kony.print (" ********** Success initializeMobileFabricSuccess response : " + JSON.stringify(response) + " ********** ");
  mobileFabricConfiguration.isKonySDKObjectInitialized=true;
  kony.application.dismissLoadingScreen();
  //authenticateMFUsingUserStore();
  kony.print (" ********** Exiting out of initializeMobileFabricSuccess ********** ");
}

function initializeMobileFabricFailure(error)
{
  kony.print (" ********** Entering into initializeMobileFabricFailure ********** ");
  kony.print (" ********** Failure in initializeMobileFabric: " + JSON.stringify(error) + " ********** ");
  kony.application.dismissLoadingScreen();
  alert (" Unable to initialize the application. Please try again. ");
  kony.print (" ********** Exiting out of initializeMobileFabricFailure ********** ");
}

function authenticateMFUsingUserStore(){
  kony.print (" ********** Entering into authenticateMFUsingUserStore ********** ");
  //  kony.application.showLoadingScreen("loadskin","Fetching news !!!",constants.LOADING_SCREEN_POSITION_FULL_SCREEN , false,true,{enableMenuKey:true,enableBackKey:true, progressIndicatorColor : "ffffff77"});
  kony.application.showLoadingScreen("", "", constants.LOADING_SCREEN_POSITION_ONLY_CENTER, true, true, null);
  mobileFabricConfiguration.authClient = mobileFabricConfiguration.konysdkObject.getIdentityService(mobileFabricConfiguration.identityServices[0].service);
  var authParams = {"userid": frmLogin.txtUsername.text, 
                    "password": frmLogin.txtPwd.text};
  mobileFabricConfiguration.authClient.login(authParams, loginMFSuccess, loginMFFailure);
  kony.print (" ********** Exiting out of authenticateMFUsingUserStore ********** ");

}

function loginMFSuccess(response){
  kony.print (" ********** Entering into loginMFSuccess ********** ");
  kony.print (" ********** Success loginMFSuccess response : " + JSON.stringify(response) + " ********** ");
  mobileFabricConfiguration.isMFAuthenticated = true;
  kony.application.dismissLoadingScreen();
  frmMain.show();
  kony.print (" ********** Exiting out of loginMFSuccess ********** ");
}

function loginMFFailure(error)
{
  kony.print (" ********** Entering into loginMFFailure ********** ");
  kony.print (" ********** Failure in loginMFFailure: " + JSON.stringify(error) + " ********** ");
  kony.application.dismissLoadingScreen();
  if(error.details.errmsg != undefined && error.details.errmsg != null){
    alert(error.details.errmsg);
  }else{
    alert("Something went wrong. Please try again");
  }
}

function authenticateMFUsingFacebook(){
  kony.print (" ********** Entering into authenticateMFUsingFacebook ********** ");
  mobileFabricConfiguration.authClient = mobileFabricConfiguration.konysdkObject.getIdentityService(mobileFabricConfiguration.identityServices[1].service);
  var authParams = {};
  mobileFabricConfiguration.authClient.login(authParams, loginMF_FBSuccess, loginMF_FBFailure);
  kony.print (" ********** Exiting out of authenticateMFUsingFacebook ********** ");

}

function loginMF_FBSuccess(response){
  kony.print (" ********** Entering into loginMF_FBSuccess ********** ");
  kony.print (" ********** Success loginMF_FBSuccess response : " + JSON.stringify(response) + " ********** ");
  mobileFabricConfiguration.isMFAuthenticated = true;
  frmMain.show();
  kony.print (" ********** Exiting out of loginMF_FBSuccess ********** ");
}

function loginMF_FBFailure(error){
  kony.print (" ********** Entering into loginMF_FBFailure ********** ");
  kony.print (" ********** Failure in loginMF_FBFailure: " + JSON.stringify(error) + " ********** ");
  alert (" Unable to authenticate to Server, Login failed. Please try again. ");
  kony.print (" ********** Exiting out of loginMF_FBFailure ********** ");
}



function logoutOnClick()
{
  kony.print (" ********** Entering into initializeMobileFabric ********** ");
  if (kony.net.isNetworkAvailable(constants.NETWORK_TYPE_ANY))
  {
    frmLogin.show();
  }
  else
    alert ("Network unavailable. Please check your network settings. ");
}

function showLogoutAlert(){
  kony.ui.Alert({
    "alertType": constants.ALERT_TYPE_CONFIRMATION,
    "alertTitle": "",
    "yesLabel": "Yes",
    "noLabel": "No",
    "message": "Are you sure you want to logout?",
    "alertIcon": "",
    "alertHandler": logoutHandler
  }, {

  });
}

function logoutHandler(decision){
  if(decision){
    logoutOnClick();
  }
}

function btn_logout () {
  if (kony.net.isNetworkAvailable(constants.NETWORK_TYPE_ANY))
  {
    kony.print (" ********** Entering into authenticateMFUsingFacebook ********** ");
    mobileFabricConfiguration.authClient = mobileFabricConfiguration.konysdkObject.getIdentityService(mobileFabricConfiguration.identityServices[0].service);
    var authParams = {};
    mobileFabricConfiguration.authClient.logout(authParams, logOutMFSuccess, logOutMFFailure);
    //kony.application.showLoadingScreen("", "", constants.LOADING_SCREEN_POSITION_ONLY_CENTER, true, true, null);
  } else {
    alert("Network unavailable. Please check your internet connection");
  }
} 

function logOutMFSuccess(response){
  //alert(" ********** Entering into loginMF_FBSuccess ********** ");
  kony.print (" ********** Success loginMF_FBSuccess response : " + JSON.stringify(response) + " ********** ");
  mobileFabricConfiguration.isMFAuthenticated = false;
  kony.application.dismissLoadingScreen();
  frmLogin.show();
}

function logOutMFFailure(error){
  //alert (" Unable to Logout from Server, Logout failed. Please try again. ");
  kony.application.dismissLoadingScreen();
  kony.print (" ********** Exiting out of logoutMF_FBFailure ********** ");
}

function resetData(){
  frmAddUser.txtEmail.text = "";
  frmAddUser.txtFirstName.text = "";
  frmAddUser.txtLastName.text = "";
  frmAddUser.txtPhone.text = "";
  frmAddUser.txtPassword.text = "";
  frmAddUser.txtRePwd.text = "";
  frmLogin.show();
}

function createUser() {
  if (kony.net.isNetworkAvailable(constants.NETWORK_TYPE_ANY)) {
    kony.application.showLoadingScreen("", "", constants.LOADING_SCREEN_POSITION_ONLY_CENTER, true, true, null);

    mobileFabricConfiguration.integrationObj = mobileFabricConfiguration.konysdkObject.getIntegrationService(mobileFabricConfiguration.integrationServices[0].service);

    var operationName = mobileFabricConfiguration.integrationServices[0].operations[0];
    var headers = {
      "X-Kony-Authorization":"",
    };
    var data = {
      "username": frmAddUser.txtEmail.text,
      "userpassword": frmAddUser.txtPassword.text,
      "firstname":frmAddUser.txtFirstName.text,
      "lastname":frmAddUser.txtLastName.text,
      "phone":frmAddUser.txtPhone.text==""?"":frmAddUser.txtPhone.text
    };
    mobileFabricConfiguration.integrationObj.invokeOperation(operationName, headers, data, createUserSuccessCallback, createUserErrorCallback);
  }
  else
  {
    alert("Network unavailable. Please check your network settings. ");
  }
}

function createUserSuccessCallback(response){
  kony.application.dismissLoadingScreen();
  if(response != null && response != undefined){
    alert("You have been successfully registered. Please login to continue.");
    resetData();
  }
}

function createUserErrorCallback(response){
  alert("Unable to create user.");
  kony.application.dismissLoadingScreen();
}

function preshowData(){
  frmAddUser.txtEmail.text = "";
  frmAddUser.txtFirstName.text = "";
  frmAddUser.txtLastName.text = "";
  frmAddUser.txtPhone.text = "";
  frmAddUser.txtPassword.text = "";
  frmAddUser.txtRePwd.text = "";
}

function validateAddUser(){
  if(frmAddUser.txtEmail.text == "" || frmAddUser.txtPassword.text == "" ||
     frmAddUser.txtFirstName.text == "" ||frmAddUser.txtLastName.text == "" || !passwordCheck){
    alert("Please enter all details correctly.");
  } else {
    createUser();
  }
}

function checkPasswordStrength(password){
  var passwordRegex = new RegExp(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/);
  if(passwordRegex.test(password)){
    passwordCheck = true;
  } else {
    alert('Password must contain minimum 8 characters, 1 uppercase, 1 lowercase, 1 special character and 1 digit');
    passwordCheck = false;
  }
}

function checkConfirmPassword(confirmPassword){
  if(frmAddUser.txtPassword.text != null && frmAddUser.txtPassword.text != ""){
    if(frmAddUser.txtPassword.text !== confirmPassword){
      alert('Passwords do not match.')
      passwordCheck = false;
    }else{
      passwordCheck = true;
    }
  }
}
