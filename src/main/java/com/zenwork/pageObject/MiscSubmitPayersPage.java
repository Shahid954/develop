package com.zenwork.pageObject;

import org.openqa.selenium.WebDriver;

import com.zenwork.library.AppLibrary;

public class MiscSubmitPayersPage {

	private AppLibrary appLibrary;
	private WebDriver driver;
	public String selectAll = "id:-:selectallGlobal";
	public String uspsMail = "id:-:selectallUspsGlobal";
	public String emailRecipient = "id:-:selectallRecipientGlobal";
	public String tinMatch = "id:-:selectallTinMatchGlobal";
	public String submitForThisPayerButton = "id:-:btnSubmit";
	public String scheduleDateAlertText = "xpath:-://p[contains(text(),'The Scheduled date is the date when the form/s is/are submitted to the IRS/SSA.')]";
	public String scheduleDateAlertCancelButton = "id:-:alertify-cancel";
	public String scheduleDateAlertOkButton = "id:-:alertify-ok";
	public String agreePopUpText = "xpath:-://p[text()=' I have reviewed and verified the data for submission']";
	public String agreePopUpCheckBox = "id:-:AgreeChkBoxId";
	public String agreePopUpOkButton = "id:-:alertify-ok";

	public MiscSubmitPayersPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void verifyMiscSubmitPayersPageUi() throws Exception {
		AppLibrary.verifyElement(driver, selectAll, true);
		AppLibrary.verifyElement(driver, uspsMail, true);
		AppLibrary.verifyElement(driver, emailRecipient, true);
		AppLibrary.verifyElement(driver, tinMatch, true);
	}

	public void selectAllDetails() throws Exception {
		AppLibrary.clickElement(driver, selectAll);
		AppLibrary.clickElement(driver, uspsMail);
		AppLibrary.clickElement(driver, emailRecipient);
		AppLibrary.clickElement(driver, tinMatch);
	}
	
	public void selectDetailsWithoutUsps() throws Exception {
		AppLibrary.clickElement(driver, selectAll);
		AppLibrary.clickElement(driver, emailRecipient);
		AppLibrary.clickElement(driver, tinMatch);
	}

	public PaymentPage submitForThisPayer() throws Exception {
		AppLibrary.clickElement(driver, submitForThisPayerButton);
		return new PaymentPage(appLibrary);
	}

	public void verifyScheduleDateAlert() throws Exception {
		AppLibrary.verifyElement(driver, scheduleDateAlertText, true);
		AppLibrary.verifyElement(driver, scheduleDateAlertCancelButton, true);
		AppLibrary.verifyElement(driver, scheduleDateAlertOkButton, true);
	}

	public void acceptScheduleDateAlert() throws Exception {
		AppLibrary.clickElement(driver, scheduleDateAlertOkButton);
	}
	
	public void verifyAgreementAlertUi() {
		AppLibrary.verifyElement(driver, agreePopUpText, true);
	}
	
}
