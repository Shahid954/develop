package com.zenwork.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zenwork.library.AppLibrary;

public class ManageFormPage {

	AppLibrary appLibrary;
	private WebDriver driver;
	public String manageForm = "xpath:-://h3[text()='Manage Forms']";
	public String referenceNumberVal = "xpath:-://td[@role='gridcell'][4]";
	public String changeScheduleDateButton = "xpath:-://input[@id='btnScheduleAll']";
	public String payerDropdown = "xpath:-://body[1]/div[4]/div[1]/div[3]/div[2]/div[1]/div[1]/ul[1]/li[1]/div[2]/div[1]/ul[1]/li[1]/div[1]/div[1]/div[1]/p[1]/span[1]/span[1]/span[2]/span[1]";
	public String selectYear = "xpath:-:(//span[text()='Select Tax Year...'])[1]";
	public String selectSingleRecipient = "xpath:-:(//input[@id='checkbox'])[1]";
	public String selecAllCheckbox = "id:-:selectall";

	public String payerList = "xpath:-://li[@role='menuitem']//div//div//span[@class='k-input'][normalize-space()='Select payer...']";
	public String payer = "xpath:-://div[div[text()='Select payer...']]//div//ul[@id='Payers_listbox']//li";
//	public String payer = "xpath:-://div//ul[@id='Payers_listbox']";
	
	public String yearList = "xpath:-://div//ul[@id='TaxYear_listbox']//li";
	public String uspsMailToRecipientButton = "id:-:btnSendMailCopy";
	public String uspsMailConfirmationOkButton = "id:-:alertify-ok";
	public String uspsMailConfirmationCancelButton = "id:-:alertify-cancel";
	public String prepayPaymentOkButton = "id:-:alertify-ok";
	public String prepayPaymentCancelButton = "id:-:alertify-cancel";
	public String paymentSuccessfulOkButton = "id:-:alertify-ok";
	public String sendPdfToPortalButton = "id:-:btnSendPDFToPortal";
	public String confirmMessagePdfOkButton = "id:-:alertify-ok";
	public String confirmPdfText = "xpath:-://p[text()='Do you want to Request send PDF to Portal ? ']";
	public String OkButton = "id:-:alertify-ok";
	public String stateFilingCheckbox = "id:-:btnBulkSelectedStateFilingReference";
	public String clickOnSelectFormDropdown = "xpath:-:(//span[text()='Select Form...'])[2]";
	public String selectFormDropdown = "xpath:-://div//ul[@id='BulkStateFormName_listbox']//li[@class='k-item']";
	public String selectReferencenumberDropdown = "xpath:-://span[text()='Select Reference Numbers...']";
	public String selectReferenceCheckbox = "xpath:-://div[@class='k-list-scroller']//ul//li//input[@id='chkref']";
	public String submitButton = "xpath:-:(//div//button[text()='Submit'])[12]";
	public String selectAllReferenceNumber = "xpath:-://label[text()=' Select All']";
	public String successPopupText = "xpath:-://p[text()='Successfully sent PDF to portal.']";
	public String uspsRequestedText = "id:-:USPSid";
	public String uspsMailForSelectedPayerPopup = "xpath:-://button[text()='Selected' and @id='alertify-cancel']";
	public String confirmPdfOkButton="xpath:-://button[text()='OK' and @id='alertify-ok']";
	public String confirmSelectedPayerButton="xpath:-:(//button[text()='Selected'])[2]";
	public String usePrepayAmountOfSendPdfPortalOkButton="xpath:-://article[p[contains(text(),'Do you want to use prepay amount for this?')]]//button[text()='OK']";

	public ManageFormPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void verifyManageFormPageUi() throws Exception {
		AppLibrary.verifyElement(driver, manageForm, true);
		AppLibrary.verifyElement(driver, referenceNumberVal, true);
	}

	public String verifyLatestRecord() throws Exception {
		String refNo = AppLibrary.GetTextForVerification(driver, referenceNumberVal);
		return refNo;
	}

	public void manageFormPayerDropdown(String option) throws Exception {
		AppLibrary.clickElement(driver, payerDropdown);
		AppLibrary.waitTillElementClickable(driver, payer);
		List<WebElement> payerOptions = AppLibrary.findElements(driver, payer);
		for (WebElement e : payerOptions) {
			String er = e.getText();
			if (er.equalsIgnoreCase(option)) {
				e.click();
				break;
			}
		}

	}

	public void manageFormTaxYearDropdown(String year) throws Exception {
		AppLibrary.clickElement(driver, selectYear);
		List<WebElement> yearOptions = AppLibrary.findElements(driver, yearList);
		for (WebElement yr : yearOptions) {
			String s = yr.getText();
			if (s.equalsIgnoreCase(year)) {
				yr.click();
				break;
			}
		}

	}

	public void uspsMailMultipleRecipient() throws Exception {
		AppLibrary.clickElement(driver, selecAllCheckbox);
		AppLibrary.clickElement(driver, uspsMailToRecipientButton);
		AppLibrary.clickElement(driver, uspsMailConfirmationOkButton);
		AppLibrary.clickElement(driver, prepayPaymentOkButton);
		AppLibrary.clickElement(driver, paymentSuccessfulOkButton);
	}

	public void uspsMailSingleRecipient() throws Exception {
		AppLibrary.clickElement(driver, selectSingleRecipient);
		AppLibrary.clickElement(driver, uspsMailToRecipientButton);
		AppLibrary.waitTillElementClickable(driver, uspsMailConfirmationOkButton);
		AppLibrary.clickElement(driver, uspsMailConfirmationOkButton);
		AppLibrary.waitTillElementLoaded(driver, prepayPaymentOkButton);
		AppLibrary.clickByJavascript(driver, prepayPaymentOkButton);
		AppLibrary.clickElement(driver, paymentSuccessfulOkButton);
	}

	public void verifyUspsMailForSingleRecipient(String verificationText) {

		List<WebElement> uspsText = AppLibrary.findElements(driver, uspsRequestedText);
		for (WebElement yr : uspsText) {
			String s = yr.getText();
			if (s.equalsIgnoreCase(verificationText)) {
				AppLibrary.verifyElement(driver, uspsRequestedText, true);
				break;
			}
		}
	}
	
	public void verifyUspsMailForMultipleRecipient(String verificationText) {

		List<WebElement> uspsText = AppLibrary.findElements(driver, uspsRequestedText);
		for (WebElement yr : uspsText) {
			String s = yr.getText();
			if (s.equalsIgnoreCase(verificationText)) {
				AppLibrary.verifyElement(driver, uspsRequestedText, true);
			}
			
			else {
				break;
			}
		}
	}

	public void sendPdfToPortalSingleRecipient() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, selectSingleRecipient);
		AppLibrary.clickElement(driver, selectSingleRecipient);
		AppLibrary.clickElement(driver, sendPdfToPortalButton);
		AppLibrary.clickElement(driver, confirmMessagePdfOkButton);
		AppLibrary.clickElement(driver, OkButton);
		AppLibrary.clickElement(driver, OkButton);
		AppLibrary.clickByJavascript(driver, usePrepayAmountOfSendPdfPortalOkButton);
		AppLibrary.waitTillElementLoaded(driver, successPopupText);
		AppLibrary.verifyElement(driver, successPopupText, true);
		AppLibrary.clickElement(driver, OkButton);
	}

	public StateFilingPaymentPage stateFilingFormForSingle(String formOption) throws Exception {
		AppLibrary.clickElement(driver, selectSingleRecipient);
		AppLibrary.clickElement(driver, stateFilingCheckbox);
		AppLibrary.clickElement(driver, clickOnSelectFormDropdown);
		List<WebElement> formOptions = AppLibrary.findElements(driver, selectFormDropdown);
		for (WebElement webElement : formOptions) {
			String f = webElement.getText();
			if (f.equalsIgnoreCase(formOption)) {
				webElement.click();
				break;
			}

		}
		AppLibrary.clickElement(driver, selectReferencenumberDropdown);
		AppLibrary.clickElement(driver, selectReferenceCheckbox);
		AppLibrary.clickElement(driver, submitButton);
		return new StateFilingPaymentPage(appLibrary);
	}

	public StateFilingPaymentPage stateFilingFormForMultiple(String formOption) throws Exception {
		AppLibrary.clickElement(driver, selecAllCheckbox);
		AppLibrary.clickElement(driver, stateFilingCheckbox);
		AppLibrary.clickElement(driver, clickOnSelectFormDropdown);
		List<WebElement> formOptions = AppLibrary.findElements(driver, selectFormDropdown);
		for (WebElement webElement : formOptions) {
			String f = webElement.getText();
			if (f.equalsIgnoreCase(formOption)) {
				webElement.click();
				break;
			}

		}
		AppLibrary.clickElement(driver, selectReferencenumberDropdown);
		AppLibrary.clickElement(driver, selectAllReferenceNumber);
		AppLibrary.clickElement(driver, submitButton);
		return new StateFilingPaymentPage(appLibrary);

	}

	public void multipleUspsMail() throws Exception {
		AppLibrary.clickElement(driver, selecAllCheckbox);
		AppLibrary.clickElement(driver, uspsMailToRecipientButton);
		AppLibrary.waitTillElementClickable(driver, uspsMailForSelectedPayerPopup);
		AppLibrary.clickByJavascript(driver, uspsMailForSelectedPayerPopup);
		AppLibrary.clickElement(driver, uspsMailConfirmationOkButton);
		// AppLibrary.clickElement(driver, prepayPaymentOkButton);
		AppLibrary.clickByJavascript(driver, paymentSuccessfulOkButton);

	}
	
	public void multipleSendPdfPortal() throws Exception {
		AppLibrary.clickElement(driver, selecAllCheckbox);
		AppLibrary.clickElement(driver, sendPdfToPortalButton);
		AppLibrary.clickElement(driver, confirmPdfOkButton);
		AppLibrary.clickElement(driver, confirmSelectedPayerButton);
		AppLibrary.clickByJavascript(driver, usePrepayAmountOfSendPdfPortalOkButton);
		AppLibrary.verifyElement(driver, successPopupText, true);
		AppLibrary.clickElement(driver, confirmPdfOkButton);
		
	}
}
