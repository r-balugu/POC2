/**************************************************************
Project Name			:	POC2 Automation Sample
Package Name			:	Forms
Class Name				:	FrmMain
Purpose of the Class	:	To maintain the repository for the locators 
 **************************************************************/
package com.kony.appiumtests.forms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * extends is the keyword used to inherit the properties of a class. In this we
 * are using extends to inherit the properties from BaseForm class.
 */

public class FrmMain extends BaseForm {

	public FrmMain(RemoteWebDriver driver) {
		super(driver);		// super() is used to invoke immediate parent class
								// constructor.
	}
//Locators on Main page
	
	@FindBy(name="Logout")
	public WebElement btnlogout;
	
	@FindBy(name="You are successfully logged in.")
	public WebElement lbl_welcome;
	
	@FindBy(name="No")
	public WebElement btnnologout;
	
	@FindBy(name="Yes")
	public WebElement btnyeslogout;
	
	
// Method for logging out of the app	
	public void logOut()
	{
		this.btnlogout.click();
		this.btnyeslogout.click();
		
	}
	
}
