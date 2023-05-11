package com.zenwork.smoke;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.zenwork.library.AppLibrary;
import com.zenwork.library.TestBase;
import com.zenwork.pageObject.HomePage;
import com.zenwork.pageObject.LoginPage;


public class Tax1099LoginFunctionalityTest extends TestBase {

	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("Tax1099LoginFuctionalityTest");
	}
	
	ExtentReports report;
	ExtentTest logger;

	@Test
	public void loginFunctioalityTest() throws Exception {
		appLibrary.getDriverInstance();
		appLibrary.launchApp();// launching the application in browser
		HomePage ap = new HomePage(appLibrary);
		ap.verifyHomePageUi();
		LoginPage lp = ap.navigateToLoginPage();
		lp.verifyLoginPageUi();
		lp.login(getUserID(),getPassword());
	}
	
}
