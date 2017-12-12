package com.kony.appiumtests.forms;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.remote.HideKeyboardStrategy;

/**
 * extends is the keyword used to inherit the properties of a class. In this we
 * are using extends to inherit the properties from BaseForm class.
 */

public class FrmRegister extends BaseForm {

	public FrmRegister(RemoteWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// Locators_Register Screen
	
	@FindBy(name="CANCEL") 
	public WebElement btncancel;
	
	@FindBy(name="ADD USER") 
	public WebElement btnadduser;
	
	public WebElement newEmailtxtbox;	
	
	public WebElement newFirstNametxtbox;	
	
	public WebElement newLastNametxtbox;
	
	public WebElement newPhonetxtbox;	
	
	public WebElement newpwdtxtbox;	
	
	public WebElement newrepwdtxtbox;
	
	
	//Initialize Textbox elements
	
	private void initiaizeElements(){
		System.out.println("textBoxClass for the platform .."+platformName+".. is ... "+textBoxClass);
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(textBoxClass)));
		List<WebElement> textBoxList = driver.findElements(By.className(textBoxClass));
		this.newEmailtxtbox = textBoxList.get(0);
		this.newFirstNametxtbox = textBoxList.get(1);
		this.newLastNametxtbox = textBoxList.get(2);
		this.newPhonetxtbox = textBoxList.get(3);
		this.newpwdtxtbox = textBoxList.get(4);
		this.newrepwdtxtbox = textBoxList.get(5);
	}
	
	//Method to register new user
	
	public void registeruser(String email, String firstname, String lastname, String phoneno, String password, String repassword) {
		
		this.initiaizeElements();
		this.newEmailtxtbox.sendKeys(email);		
		if ("MAC".equalsIgnoreCase(platformName)) {
			iosdriver.hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Done");
		} else {
			androiddriver.hideKeyboard();
		}
		this.newFirstNametxtbox.sendKeys(firstname);		
		if ("MAC".equalsIgnoreCase(platformName)) {
			iosdriver.hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Done");
		} else {
			androiddriver.hideKeyboard();
		}
		 this.newLastNametxtbox.sendKeys(lastname);		
		if ("MAC".equalsIgnoreCase(platformName)) {
			iosdriver.hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Done");
		} else {
			androiddriver.hideKeyboard();
		}
		this.newPhonetxtbox.sendKeys(phoneno);		
		if ("MAC".equalsIgnoreCase(platformName)) {
			iosdriver.hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Done");
		} else {
			androiddriver.hideKeyboard();
		}
		this.newpwdtxtbox.sendKeys(password);		
		if ("MAC".equalsIgnoreCase(platformName)) {
			iosdriver.hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Done");
		} else {
			androiddriver.hideKeyboard();
		}
		this.newrepwdtxtbox.sendKeys(repassword);		
		if ("MAC".equalsIgnoreCase(platformName)) {
			iosdriver.hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Done");
		} else {
			androiddriver.hideKeyboard();
		}
		
		this.btnadduser.click(); 
	}
	
	//Method to cancel the registration of new user
	
	public void cancelregistration()
	{
		this.btncancel.click();	
		
	}
	

	
}
