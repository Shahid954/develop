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
import com.zenwork.pageObject.People;
import com.zenwork.pageObject.SideNavigation;

public class ValidateIndividualTypePayerAdded extends TestBase {
	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("ValidateIndividualTypeAddPayer");
	}

	@DataProvider(name = "PayerData")
	public Object[][] getData() throws Exception {
		String filePath = "Resources" + File.separator + "IndividualAddNewPayerData.xls";
		Object object[][] = AppLibrary.readExcel(filePath, 0);
		return object;
	}

	@Test(dataProvider = "PayerData")
	public void individualTypeAddPayer(String firstName, String lastName, String address, String city, String state,
			String zipCode, String country, String phone, String exeIndicator) throws Exception {

		String payerSsnNum = AppLibrary.generateRandomNumber(9);
		// String payerBusinessName = businessName + " " +
		// AppLibrary.getFormattedDate().replace("_", "");
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
		apf.fillIndividualAddPayerForm(payerSsnNum, firstName, lastName, disregardedEntity, payerAddress, city, state,
				zipCode, country, payerPhoneNumber);
		apf.addPayerDetails();
		apf.verifyAlertMessage();
		apf.alertifyOk();

	}
}
