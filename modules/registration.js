gblClaimToken = "";
passwordCheck = false;

/**
 * Purpose: This function is used to reset the registration form.
 * 
 */
function resetData() {
    frmAddUser.txtEmail.text = "";
    frmAddUser.txtFirstName.text = "";
    frmAddUser.txtLastName.text = "";
    frmAddUser.txtPhone.text = "";
    frmAddUser.txtPassword.text = "";
    frmAddUser.txtRePwd.text = "";
    frmLogin.show();
}

/**
 * Purpose: This function is to create the user in Kony user store.
 * 
 */
function createUser() {
    if (kony.net.isNetworkAvailable(constants.NETWORK_TYPE_ANY)) {
        kony.application.showLoadingScreen("", "", constants.LOADING_SCREEN_POSITION_ONLY_CENTER, true, true, null);
        mobileFabricConfiguration.integrationObj = mobileFabricConfiguration.konysdkObject.getIntegrationService(mobileFabricConfiguration.integrationServices[0].service);
        var operationName = mobileFabricConfiguration.integrationServices[0].operations[0];
        var headers = {
            "X-Kony-Authorization": "",
        };
        var data = {
            "username": frmAddUser.txtEmail.text,
            "userpassword": frmAddUser.txtPassword.text,
            "firstname": frmAddUser.txtFirstName.text,
            "lastname": frmAddUser.txtLastName.text,
            "phone": frmAddUser.txtPhone.text === "" ? "" : frmAddUser.txtPhone.text
        };
        mobileFabricConfiguration.integrationObj.invokeOperation(operationName, headers, data, createUserSuccessCallback, createUserErrorCallback);
    } else {
        alert("Network unavailable. Please check your network settings. ");
    }
}

/**
 * Purpose: This is a success callback of create user to in Kony user store.
 * 
 */

function createUserSuccessCallback(response) {
    kony.application.dismissLoadingScreen();
    if (response !== null && response !== undefined) {
        alert("You have been successfully registered. Please login to continue.");
        resetData();
    }
}

/**
 * Purpose: This is a failure callback of create user to in Kony user store.
 * 
 */
function createUserErrorCallback(response) {
    alert("Unable to create user.");
    kony.application.dismissLoadingScreen();
}

/**
 * Purpose: This function to pre load the data with empty values.
 * 
 */
function preshowData() {
    frmAddUser.txtEmail.text = "";
    frmAddUser.txtFirstName.text = "";
    frmAddUser.txtLastName.text = "";
    frmAddUser.txtPhone.text = "";
    frmAddUser.txtPassword.text = "";
    frmAddUser.txtRePwd.text = "";
}

/**
 * Purpose: This function is to validate the user details before login.
 * 
 */
function validateAddUser() {
    if (frmAddUser.txtEmail.text === "" || frmAddUser.txtPassword.text === "" ||
        frmAddUser.txtFirstName.text === "" || frmAddUser.txtLastName.text === "" || !passwordCheck) {
        alert("Please enter all details correctly.");
    } else {
        createUser();
    }
}

/**
 * Purpose: This function is to check the password stregth while filling the registration form.
 * @param {string} password - The password to check tge
 * 
 */
function checkPasswordStrength(password) {
    var passwordRegex = new RegExp(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/);
    if (passwordRegex.test(password)) {
        passwordCheck = true;
    } else {
        alert('Password must contain minimum 8 characters, 1 uppercase, 1 lowercase, 1 special character and 1 digit');
        passwordCheck = false;
    }
}

/**
 * Purpose: This function is to check the password match with confirmed password.
 * @param {string} confirmPassword - checking password 
 * 
 */
function checkConfirmPassword(confirmPassword) {
    if (frmAddUser.txtPassword.text !== null && frmAddUser.txtPassword.text !== "") {
        if (frmAddUser.txtPassword.text !== confirmPassword) {
            alert('Passwords do not match.');
            passwordCheck = false;
        } else {
            passwordCheck = true;
        }
    }
}