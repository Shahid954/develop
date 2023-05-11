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
import com.zenwork.pageObject.NECFormSingleFiling;
import com.zenwork.pageObject.NecSubmitPayersPage;
import com.zenwork.pageObject.NewForm;
import com.zenwork.pageObject.PaymentPage;
import com.zenwork.pageObject.SideNavigation;

public class ScheduleDynamicFutureDate extends TestBase{
	
	
	
	public static String payerBusinessName;

	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("ValidateSingleFormStateFiling");
	}

	@DataProvider(name = "AddPayerData")
	public Object[][] getData() throws Exception {
		String filePath = "Resources" + File.separator + "BusinessTypeAddRecipient.xls";
		Object object[][] = AppLibrary.readExcel(filePath, 0);
		return object;
	}

	@Test(dataProvider = "AddPayerData")
	public void addPayerAndRecipient(String businessName, String address, String city, String state, String zipCode,
			String country, String phone, String email, String exeIndicator) throws Exception {
		String payerEinNum = AppLibrary.generateRandomNumber(9);
		String recipientEinNum = AppLibrary.generateRandomNumber(9);
		payerBusinessName = businessName + " " + AppLibrary.getFormattedDate().replace("_", "");
		String recipientBusinessName = businessName + " " + AppLibrary.getFormattedDate().replace("_", "");
		String disregardedEntity = AppLibrary.generateRandomNumber(9);
		String payerAddress = AppLibrary.randIntDigits(1111, 9999) + " " + address;
		String recipientAddress = AppLibrary.randIntDigits(1111, 9999) + " " + address;
		String payerPhoneNumber = phone + AppLibrary.generateRandomNumber(8);

		appLibrary.getDriverInstance();
		appLibrary.launchApp();// launching the application in browser
		HomePage ap = new HomePage(appLibrary);
		DashboardPage dp = new DashboardPage(appLibrary);
		SideNavigation sn = new SideNavigation(appLibrary);
		LoginPage lp = ap.navigateToLoginPage();
		lp.login(getUserID(), getPassword());
		dp.plansPopupCancel();
		Forms f = sn.selectForms();
		NewForm fr = f.selectNewForm();
		fr.selectYear();
		NECFormSingleFiling nc = fr.selectNecForm();
		AddPayerForm anp = nc.addNewPayer();
		anp.fillAddPayerForm(payerEinNum, payerBusinessName, disregardedEntity, payerAddress, city, state, zipCode,
				country, payerPhoneNumber);
		anp.addPayerDetails();
		anp.alertifyOk();
		AddRecipientPage arp = nc.addNewRecipient();
		arp.fillAddRecipientForm(recipientEinNum, recipientBusinessName, recipientAddress, city, state, zipCode,
				country, email);
		arp.addRecipientDetails();
		arp.VerifyRecipientConfirmation();
		nc.enterNoneEmployeeCompensation("1000");
		nc.enterFederalIncomeTaxWithheld("150");
		nc.enterStateTaxWithHeld("595");
		NecSubmitPayersPage nec = nc.saveAndContinueNec();
		nec.submitSinglePayerForm();
		nec.DynamicdateSelection();
		PaymentPage pay = nec.stateFilingSubmitForThisPayer();
		
	}

}
