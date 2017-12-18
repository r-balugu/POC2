package com.kony.appiumtests.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kony.appiumtests.forms.FrmFBLogin;
import com.kony.appiumtests.forms.FrmLogin;

public class FrmFBLoginTest extends BaseTest {

                private final String FB_USER_NAME = "platformqa123@gmail.com";
                private final String FB_PASSWORD = "fbTest@1234";
                
                private FrmLogin loginfrm;
                private FrmFBLogin fbloginfrm;
                                
               
                
                @BeforeTest
                @Override
                public void setUpPage() {
                	 loginfrm = new FrmLogin(driver);                                               
                     fbloginfrm = new FrmFBLogin(driver); 
                }

                // Test to login app using Facebook credentials
                @Test
                public void testfblogin(){                             
                                loginfrm.loginwithFB();  
                                fbloginfrm.fblogin(FB_USER_NAME,FB_PASSWORD);
                                                 
                }
 
            	@AfterMethod
            	public void afterMethod() {
            		System.out.println("In After Method");
            		loginfrm.clickbackbutton();
            	}
                
}
