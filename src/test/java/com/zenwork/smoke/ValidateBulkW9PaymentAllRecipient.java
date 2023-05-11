package com.zenwork.smoke;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.zenwork.library.AppLibrary;
import com.zenwork.library.TestBase;
import com.zenwork.pageObject.AddPayerForm;
import com.zenwork.pageObject.DashboardPage;
import com.zenwork.pageObject.HomePage;
import com.zenwork.pageObject.LoginPage;
import com.zenwork.pageObject.ManagePayerPage;
import com.zenwork.pageObject.ManageRecipient;
import com.zenwork.pageObject.People;
import com.zenwork.pageObject.SideNavigation;

public class ValidateBulkW9PaymentAllRecipient extends TestBase {
	public static String payerBusinessName;

	@DataProvider(name = "PayerData")
	public Object[][] getData() throws Exception {
		String filePath = "Resources" + File.separator + "AddNewPayerData.xls";
		Object object[][] = AppLibrary.readExcel(filePath, 0);
		return object;
	}

	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("ValidateBulkW9PaymentAllRecipient");
	}

	@Test(dataProvider = "PayerData")
	public void businessTypeAddPayer(String businessName, String address, String city, String state, String zipCode,
			String country, String phone, String exeIndicator) throws Exception {

		String payerEinNum = AppLibrary.generateRandomNumber(9);
		payerBusinessName = businessName + " " + AppLibrary.getFormattedDate().replace("_", "");
		String disregardedEntity = AppLibrary.generateRandomNumber(9);
		String payerAddress = AppLibrary.randIntDigits(1111, 9999) + " " + address;
		String payerPhoneNumber = phone + AppLibrary.generateRandomNumber(8);

		appLibrary.getDriverInstance();
		appLibrary.launchApp();// launching the application in browser
		HomePage ap = new HomePage(appLibrary);
		DashboardPage dp = new DashboardPage(appLibrary);
		SideNavigation sn = new SideNavigation(appLibrary);
		LoginPage lp = ap.navigateToLoginPage();
		lp.login(getUserID(), getPassword());
		dp.plansPopupCancel();
		People p = sn.selectPeople();
		ManagePayerPage mpp = p.selectManagePayer();
		AddPayerForm apf = mpp.addPayerButton();
		apf.fillAddPayerForm(payerEinNum, payerBusinessName, disregardedEntity, payerAddress, city, state, zipCode,
				country, payerPhoneNumber);
		apf.addPayerDetails();
		apf.verifyAlertMessage();
		apf.alertifyOk();

	}

	@Test(dependsOnMethods = "businessTypeAddPayer")
	public void validateRequestW9AllRecipient() throws Exception {
		appLibrary.getDriverInstance();
		appLibrary.launchApp();// launching the application in browser
		HomePage ap = new HomePage(appLibrary);
		DashboardPage dp = new DashboardPage(appLibrary);
		SideNavigation sn = new SideNavigation(appLibrary);
		LoginPage lp = ap.navigateToLoginPage();
		lp.login(getUserID(), getPassword());
		dp.plansPopupCancel();
		People p = sn.selectPeople();
		ManageRecipient mr = p.selectManageRecipient();
		mr.selectPayerFromDropdown(payerBusinessName);
		mr.uploadFile();
		mr.sendBulkW9RequestForAllRecipient();

	}
}
