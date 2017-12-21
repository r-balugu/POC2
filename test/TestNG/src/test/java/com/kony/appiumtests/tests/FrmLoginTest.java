
package com.kony.appiumtests.tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kony.appiumtests.forms.FrmLogin;

public class FrmLoginTest extends BaseTest {

	private final String CORRECT_USER_NAME = "raju.balugu@kony.com";
	private final String CORRECT_PASSWORD = "Default@1234";

	private FrmLogin loginfrm;


	/**
	 * Creates FrmLogin page
	 * 
	 * @Override we are overriding the abstract methods (setUpPage()), and and
	 *           customizing the implementation in the inherited classes.
	 * 
	 * @Test annotation is used for writing the test scripts. We can execute the
	 *       required tests only with the help of Group test mechanism, which is
	 *       offered by testNG.
	 * 
	 */

	@BeforeTest
	@Override
	public void setUpPage() {
		loginfrm = new FrmLogin(driver);
	}

	@BeforeClass
	@Override
	public void navigateTo() {

	}
	
	   @BeforeMethod
	   public void beforeMethod() {
	      System.out.println("in beforeMethod");
	   }

	@Test(groups = "Login")
	public void testLogin() throws InterruptedException {
			System.out.println("Login Starts");
			loginfrm.loginIn(CORRECT_USER_NAME, CORRECT_PASSWORD);
			System.out.println("Login Ends");
	}



	
	@AfterMethod
	public void afterMethod() {
		System.out.println("In After Method");
		loginfrm.clickbackbutton();
		WebDriverWait wait = new WebDriverWait(driver, 3000);
	}
		
	
	
}