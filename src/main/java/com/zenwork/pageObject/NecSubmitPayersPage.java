package com.zenwork.pageObject;

import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zenwork.library.AppLibrary;

public class NecSubmitPayersPage {

	private AppLibrary appLibrary;
	private WebDriver driver;
	public String selectAll = "id:-:selectallGlobal";
	public String uspsMail = "id:-:selectallUspsGlobal";
	public String emailRecipient = "id:-:selectallRecipientGlobal";
	public String tinMatch = "id:-:selectallTinMatchGlobal";
	public String submitForThisPayerButton = "id:-:btnSubmit";
	public String scheduleDateAlertText = "xpath:-://p[contains(text(),'The Scheduled date is the date when the form/s is/are submitted to the IRS/SSA.')]";
	public String scheduleDateAlertCanelButton = "id:-:alertify-cancel";
	public String scheduleDateAlertOkButton = "id:-:alertify-ok";
	public String agreePopUpText = "xpath:-://p[text()=' I have reviewed and verified the data for submission']";
	public String agreePopUpCheckBox = "id:-:AgreeChkBoxId";
	public String OkButton = "id:-:alertify-ok";
	public String saveAndContinue = "id:-:btnSaveContinue";
	public String singlePayerSelectCheckbox = "xpath:-://td//input[@class='chkbxq']";
	public String stateFilingCheckbox = "id:-:selectallStatesGlobal";
	public String changeScheduleDateForAll = "id:-:btnScheduleAll";
	public String scheduleDateButton = "xpath:-://span//span[@class='k-icon k-i-calendar']";
	public String futureDate = "xpath:-:(//tbody//tr//td[7]//a[text()='6'])";
	public String okButtonOfScheduleForm = "xpath:-://div[@class='ui-dialog-buttonset']//button[text()='OK']";
	public String okButtonOfPlanNotify = "xpath:-://article//nav//button[text()='OK']";
	public String agreePopUpOkButton = "id:-:alertify-ok";
	public String futureDateLocator="xpath:-://td[@class='k-today k-state-selected k-state-focused']";

	public NecSubmitPayersPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void verifysubmitPayersPageUi() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, selectAll);
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
		AppLibrary.waitTillElementLoaded(driver, selectAll);
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
		AppLibrary.verifyElement(driver, scheduleDateAlertCanelButton, true);
		AppLibrary.verifyElement(driver, scheduleDateAlertOkButton, true);
	}

	public void acceptScheduleDateAlert() throws Exception {
		AppLibrary.clickElement(driver, scheduleDateAlertOkButton);
	}

	public void verifyAgreementAlertUi() {
		AppLibrary.verifyElement(driver, agreePopUpText, true);
	}

	public PaymentPage acceptAgreementAlert() throws Exception {
		AppLibrary.clickByJavascript(driver, submitForThisPayerButton);
		AppLibrary.waitTillElementLoaded(driver, OkButton);
		AppLibrary.clickElement(driver, OkButton);
		AppLibrary.clickElement(driver, agreePopUpCheckBox);
		AppLibrary.clickByJavascript(driver, OkButton);
		return new PaymentPage(appLibrary);
	}

	public NecSubmitPayersPage saveAndContinue() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, saveAndContinue);
		AppLibrary.clickElement(driver, saveAndContinue);
		return new NecSubmitPayersPage(appLibrary);
	}

	public void submitSinglePayerForm() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, singlePayerSelectCheckbox);
		AppLibrary.clickElement(driver, singlePayerSelectCheckbox);
		AppLibrary.clickElement(driver, stateFilingCheckbox);
		AppLibrary.clickElement(driver, uspsMail);
		AppLibrary.clickElement(driver, emailRecipient);
		AppLibrary.clickByJavascript(driver, tinMatch);
	}

	public void dateSelection() throws Exception {
		AppLibrary.clickByJavascript(driver, changeScheduleDateForAll);
		AppLibrary.waitTillElementClickable(driver, scheduleDateButton);
		AppLibrary.clickElement(driver, scheduleDateButton);
		AppLibrary.waitTillElementLoaded(driver, futureDate);
		AppLibrary.clickElement(driver, futureDate);
		AppLibrary.clickElement(driver, okButtonOfScheduleForm);
	}
	public void DynamicdateSelection() throws Exception {
		AppLibrary.clickByJavascript(driver, changeScheduleDateForAll);
		AppLibrary.waitTillElementClickable(driver, scheduleDateButton);
		AppLibrary.clickElement(driver, scheduleDateButton);
        Calendar calendar = Calendar.getInstance();
        int future = calendar.getFirstDayOfWeek();
        String futureDateAsString = Integer.toString(future);
        WebElement ele = driver.findElement(By.xpath("//table[@class='k-content k-month']//td//a[contains(text(),'"+futureDateAsString+"')]"));
		ele.click();
		AppLibrary.clickElement(driver, okButtonOfScheduleForm);
	}

	public PaymentPage stateFilingSubmitForThisPayer() throws Exception {
		AppLibrary.clickElement(driver, submitForThisPayerButton);
		AppLibrary.waitTillElementClickable(driver, okButtonOfPlanNotify);
		AppLibrary.clickElement(driver, okButtonOfPlanNotify);
		AppLibrary.waitTillElementLoaded(driver, agreePopUpCheckBox);
		AppLibrary.clickElement(driver, agreePopUpCheckBox);
		AppLibrary.clickElement(driver, agreePopUpOkButton);
		return new PaymentPage(appLibrary);
	}

	public void submitMultipleStateFilingForm() throws Exception {
        //AppLibrary.clickElement(driver, selectMultipleFormCheckbox);
        AppLibrary.clickElement(driver, stateFilingCheckbox);
        AppLibrary.clickElement(driver, changeScheduleDateForAll);
        
    }
}
