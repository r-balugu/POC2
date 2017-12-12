package com.kony.appiumtests.tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kony.appiumtests.forms.FrmLogin;
import com.kony.appiumtests.forms.FrmRegister;

public class FrmRegisterTest extends BaseTest {

	private FrmLogin loginfrm;
	private FrmRegister registerfrm;
	

	@BeforeTest
	@Override
	public void setUpPage() {
		loginfrm = new FrmLogin(driver);
		registerfrm= new FrmRegister(driver);	
		
	}
	
	@BeforeClass
	@Override
	public void navigateTo() {
		//loginfrm.registernew();			
	}
	
	   /**
     * Test to register new user
     */
	
	@Test
	public void testregisteruser()
	{
		loginfrm.registernew();
		System.out.println("Register Starts");
		registerfrm.registeruser("lalitha.chadalavada@kony.com", "lalitha", "p", "9866188536", "Default@123", "Default@123");
	//	WebDriverWait wait = new WebDriverWait(driver, 5000);
	//	wait.until(arg0)
		System.out.println("User Added");
	}
	
	   /**
     * Test to cancel the registration of new user
     */
	
	@Test
	public void testregistercancel()
	{
		loginfrm.registernew();
		System.out.println("Cancelling Registration");
		registerfrm.cancelregistration();
		loginfrm.checkIfBackAtLogin();
	}
	
}
