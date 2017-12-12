package com.kony.appiumtests.forms;


import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.remote.HideKeyboardStrategy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FrmLogin extends BaseForm {

	/**
	 * The page structure is being used within this test in order to separate
	 * the page actions from the tests
	 * 
	 * Locators are saved with the help of @FindBy annotation, and can be used
	 * in the corresponding tests by extending the FrmHome class.
	 * 
	 * LoginIn() method is used to Login into the application. we can pass the
	 * user name, and password.
	 */

	public FrmLogin(RemoteWebDriver driver) {
		super(driver); // super() is used to invoke immediate parent class
						// constructor.
	}

	// Locators_PreLogin Screen
	/**
	 * @FindBy is just an alternate way of finding elements. It is better used
	 *         to support the PageObject pattern.
	 */

	
	@FindBy(name = "Login")
	public WebElement btnLogin;

	private WebElement username;

	private WebElement pass_word;
	
	@FindBy(name = "Register")
	public WebElement btnRegister;
	
	//@FindBy(xpath= "//android.widget.Button[@index='1']")	
	public WebElement btnLoginFB;

	private void initiaizeElements(){
		System.out.println("textBoxClass for the platform .."+platformName+".. is ... "+textBoxClass);
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(textBoxClass)));
		List<WebElement> textBoxList = driver.findElements(By.className(textBoxClass));
		this.username = textBoxList.get(0);
		this.pass_word = textBoxList.get(1);
		List<WebElement> buttonList = driver.findElements(By.className("android.widget.Button"));
		this.btnLoginFB = buttonList.get(1);
	}


	public void loginIn(String userName, String password) {
		this.initiaizeElements();
		this.username.sendKeys(userName);
		if ("MAC".equalsIgnoreCase(platformName)) {
			iosdriver.hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Done");
		} else {
			androiddriver.hideKeyboard();
		}
		this.pass_word.sendKeys(password);
		if ("MAC".equalsIgnoreCase(platformName)) {
			iosdriver.hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Done");
		} else {
			androiddriver.hideKeyboard();
		}
		this.btnLogin.click();
	}
	
	public void registernew()
	{
		this.btnRegister.click();		
	}

	public void loginwithFB()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@index='1' and @text='Login WithFacebook']")));
		this.initiaizeElements();
		this.btnLoginFB.click();	
		
	}
	
    public boolean checkIfBackAtLogin() {
        return btnLogin.isDisplayed() && username.isDisplayed() && pass_word.isDisplayed();
    }
}
