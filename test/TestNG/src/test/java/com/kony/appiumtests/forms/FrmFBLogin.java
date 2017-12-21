package com.kony.appiumtests.forms;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.remote.HideKeyboardStrategy;

public class FrmFBLogin extends BaseForm {

	public FrmFBLogin(RemoteWebDriver driver) {
		super(driver);		
	}

	@FindBy(name="Email address or phone number")
	public WebElement fbusername;
	
	@FindBy(name="Facebook password")
	public WebElement fbpswd;
	
	@FindBy(name="Log In ")
	public WebElement fbLogin;	


	//Method to login using facebook
	public void fblogin(String userName, String password){
		WebDriverWait wait = new WebDriverWait(driver, 10000);										
		try{
			if(fbLogin.isDisplayed()){			
				this.fbusername.sendKeys(userName);	
				if ("MAC".equalsIgnoreCase(platformName)) {
					iosdriver.hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Done");
				} else {
					androiddriver.hideKeyboard();
				}
				this.fbpswd.sendKeys(password);
				if ("MAC".equalsIgnoreCase(platformName)) {
					iosdriver.hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Done");
				} else {
					androiddriver.hideKeyboard();
				}
				this.fbLogin.click();
			
				wait.until(ExpectedConditions.elementToBeClickable(By.name("Logout")));
			}
		}catch(NoSuchElementException e){
			wait.until(ExpectedConditions.elementToBeClickable(By.name("Logout")));
			System.out.println("in catch block");
		}		
	}

	
	
}
