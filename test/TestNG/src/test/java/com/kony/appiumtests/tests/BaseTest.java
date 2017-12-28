package com.kony.appiumtests.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.AppiumDriver;

import java.net.URL;
import org.openqa.selenium.By;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


public abstract class BaseTest {

	/**
	 * A base constructor that sets the page's driver
	 * 
	 * The page structure is being used within this test in order to separate
	 * the page actions from the tests.
	 * 
	 * @BeforeTest block will be before the @Test is going to execute.
	 * 
	 * @AfterTest block will be executed after all the Test's are executed.
	 * 
	 * @AfterSuite will be executed after all the tests executed, the mechanisms
	 *             like Closing DB connection, Quitting from the app are written
	 *             in this section.
	 */
	public static String platformName;
	public static AndroidDriver<MobileElement> androiddriver;
	public static IOSDriver<MobileElement> iosdriver;
	public static RemoteWebDriver driver; // AppiumDriver class contains all
											// methods shared by iOS and Android

	
	@BeforeSuite
	// To Launch App on device.
	public void beforeSuite() throws Exception {

		System.out.println("initializing is starting..........");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		// The commented section is used when try to work in local Environment.

	/*	 
		  capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Kony(GT-I9300");					  
		  capabilities.setCapability(MobileCapabilityType.UDID, "4d00bb04432b403d");	 //Give Device ID of your mobile phone 	  
		  capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			capabilities.setCapability("appPackage", "com.orgname.Sample");
			capabilities.setCapability("appActivity", "com.orgname.Sample.Sample");
			capabilities.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
			
			*/	  
		if (iosdriver == null) {

			// Comm with Appium server
			iosdriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		}

		System.out.println("Getting platform name from capabilities.........."
				+ iosdriver.getCapabilities().getPlatform().toString());
		/**
		 * getPlatform() is used to know the platform on which the app is
		 * running
		 */
		platformName = iosdriver.getCapabilities().getPlatform().toString();

		if ("MAC".equalsIgnoreCase(platformName)) {
			System.out.println("Inside platform MAC............");
			driver = iosdriver;
		} else {
			if (driver != null) {
				driver.quit();
				driver = null;
			}
			if (androiddriver != null) {
				androiddriver.quit();
				androiddriver = null;
			}
			if (iosdriver != null) {
				iosdriver.quit();
				iosdriver = null;
			}

			System.out.println("Inside platform ANDROID............");
			androiddriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver = androiddriver;
		
			System.out.println("Inbasetest beforetest");
			driver.findElement(By.name("Cancel")).click();
		}

	}

	@BeforeClass
	public void navigateTo() {

	}

	@BeforeTest
	public abstract void setUpPage();

	@AfterTest
	public void afterTest() {

	}
	
	   @BeforeMethod
	   public void beforeMethod() {
	     
	   }
	
	@AfterMethod
	public void afterMethod() {

	}

	@AfterSuite
	// App close
	public void tearDownAppium() {
		if (driver != null)
			driver.quit();
		if (androiddriver != null)
			androiddriver.quit();
		if (iosdriver != null)
			iosdriver.quit();
	}
	
    public void bclickbackbutton()
    {
    	driver.navigate().back();    	
    }

}
