package com.zenwork.smoke;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.zenwork.library.AppLibrary;
import com.zenwork.library.TestBase;
import com.zenwork.pageObject.AddPayerForm;
import com.zenwork.pageObject.AddRecipientPage;
import com.zenwork.pageObject.DashboardPage;
import com.zenwork.pageObject.Forms;
import com.zenwork.pageObject.HomePage;
import com.zenwork.pageObject.LoginPage;
import com.zenwork.pageObject.ManagePayerPage;
import com.zenwork.pageObject.ManageRecipient;
import com.zenwork.pageObject.People;
import com.zenwork.pageObject.SideNavigation;

public class ValidateBusinessTypeAddRecipient extends TestBase {
	public static String payerBusinessName;

	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("ValidateBusinessTypeAddRecipient");
	}

	@DataProvider(name = "PayerData")
	public Object[][] getData() throws Exception {
		String filePath = "Resources" + File.separator + "AddNewPayerData.xls";
		Object object[][] = AppLibrary.readExcel(filePath, 0);
		return object;
	}

	@DataProvider(name = "AddRecipientData")
	public Object[][] getRecipientData() throws Exception {
		String filePath = "Resources" + File.separator + "BusinessTypeAddRecipient.xls";
		Object object[][] = AppLibrary.readExcel(filePath, 0);
		return object;
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

	@Test(dataProvider = "AddRecipientData", dependsOnMethods = "businessTypeAddPayer")
	public void addBusinessTypeRecipients(String businessName, String address, String city, String state,
			String zipCode, String country, String phone, String email, String exeIndicator) throws Exception {

		if (exeIndicator.equalsIgnoreCase("Yes")) {
			String recipientEinNum = AppLibrary.generateRandomNumber(9);
			String recipientBusinessName = businessName + " " + AppLibrary.getFormattedDate().replace("_", "");
			String recipientAddress = AppLibrary.randIntDigits(1111, 9999) + " " + address;

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
			AddRecipientPage arp = mr.addRecipient();
			arp.fillAddRecipientForm(recipientEinNum, recipientBusinessName, recipientAddress, city, state, zipCode,
					country, email);
			arp.addRecipientDetails();
			arp.VerifyRecipientConfirmation();
		}

	}
}
