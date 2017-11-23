/**
 * Purpose: This function is to authenticate facebook authentication.
 * 
 */
function authenticateMFUsingFacebook() {
    kony.print(" ********** Entering into authenticateMFUsingFacebook ********** ");
    mobileFabricConfiguration.authClient = mobileFabricConfiguration.konysdkObject.getIdentityService(mobileFabricConfiguration.identityServices[1].service);
    var authParams = {};
    mobileFabricConfiguration.authClient.login(authParams, loginMF_FBSuccess, loginMF_FBFailure);
    kony.print(" ********** Exiting out of authenticateMFUsingFacebook ********** ");
}

/**
 * Purpose: This function is success call back of login to Facebook.
 * @param {string} response - has details of login response.
 * 
 */
function loginMF_FBSuccess(response) {
    kony.print(" ********** Success loginMF_FBSuccess response : " + JSON.stringify(response) + " ********** ");
    mobileFabricConfiguration.isMFAuthenticated = true;
    frmMain.show();
    kony.print(" ********** Exiting out of loginMF_FBSuccess ********** ");
}

/**
 * Purpose: This function is Failure call back of login to Facebook.
 * @param {string} error - has details of error details.
 * 
 */
function loginMF_FBFailure(error) {
    kony.print(" ********** Failure in loginMF_FBFailure: " + JSON.stringify(error) + " ********** ");
    alert(" Unable to authenticate to Server, Login failed. Please try again. ");
    kony.print(" ********** Exiting out of loginMF_FBFailure ********** ");
}