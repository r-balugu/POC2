/**************************************************************
Project Name			:	POC2 Test Automation Sample
Package Name			:	tests
Class Name				:	FrmMainTest
Purpose of the Class	:	Validating the Functionality of logout on the Form Main.  

 **************************************************************/

package com.kony.appiumtests.tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kony.appiumtests.forms.FrmLogin;
import com.kony.appiumtests.forms.FrmMain;

public class FrmMainTest extends BaseTest {
	
	private final String CORRECT_USER_NAME = "raju.balugu@kony.com";
	private final String CORRECT_PASSWORD = "Default@1234";
	
	private FrmLogin loginfrm;
	private FrmMain mainfrm;

	/**
	 * Creates FrmLogin page,FrmMain page
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
	 mainfrm = new FrmMain(driver);
	}

	@BeforeClass
	@Override
	public void navigateTo() {
	/*	try {
		loginfrm.loginIn(CORRECT_USER_NAME, CORRECT_PASSWORD);		
			Thread.sleep(2000);
		} catch (InterruptedException e) {
						e.printStackTrace();
		}*/
	}
	
	
	@Test
	public void testLogin() throws InterruptedException {
			System.out.println("Login Starts");
			loginfrm.loginIn(CORRECT_USER_NAME, CORRECT_PASSWORD);
			System.out.println("Login Ends");
	}
	
	@Test
	public void testlogOut()
	{
		mainfrm.logOut();
		loginfrm.checkIfBackAtLogin();
	}
}
