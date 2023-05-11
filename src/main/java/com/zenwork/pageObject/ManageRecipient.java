package com.zenwork.pageObject;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.zenwork.library.AppLibrary;

public class ManageRecipient {
	private WebDriver driver;
	private AppLibrary appLibrary;
	public String payersDropdown = "id:-:ddlPayer_chosen";
	public String searchPayer = "xpath:-://input[@class='chosen-search-input']";
	public String requestW8Button = "id:-:requestW8Button";
	public String requestW9Button = "id:-:requestW9Button";
	public String selection = "xpath:-://li[contains(@class,'active-result')]";
	public String selectFiles = "xpath:-://input[@type='file' and @id='upload']";
	public String bulkUpload = "id:-:Next";
	public String uploadSuccessMsgText = "xpath:-://p[text()='Spread Sheet Uploaded Successfully']";
	public String uploadSucessPopupButton = "xpath:-://button[text()='OK' and @id='alertify-ok']";
	public String bulkW8request = "id:-:btnBulkW8Request";
	public String BulkW8confirmationText = "xpath:-://p[text()='Do you want to W8 Request all recipients under selected payer or W8 Request only selected recipients ? ']";
	public String W8requestAllRecipients = "xpath:-://button[text()='W8 Request All Recipients']";
	public String W8requestSelectedRecipients = "xpath:-://button[text()='W8 Request selected Recipients']";
	public String selectAllCheckbox = "id:-:selectall";
	public String removeFirstRecipient = "xpath:-:(//tr//td//input[@id='checkbox'])[1]";
	public String selectFirstRecipient = "xpath:-:(//tr//td//input[@id='checkbox'])[1]";
	public String bulkW9request = "id:-:btnBulkW9Request";
	public String W9requestAllRecipients = "xpath:-://button[text()='W9 Request All Recipients']";
	public String W9requestSelectedRecipients = "xpath:-://button[text()='W9 Request selected Recipients']";
	public String BulkW9confirmationText = "xpath:-://p[text()='Do you want to W9 Request all recipients under selected payer or W9 Request only selected recipients ? ']";
	public String secondConfiramtionText = "xpath:-://p[text()='Click on proceed to continue sending bulk W9/W8 requests']";
	public String secondConfiramtionProceedButton = "xpath:-://button[text()='Proceed' and @id='alertify-ok']";
	public String secondConfiramtionCancelButton = "xpath:-://button[text()='Cancel' and @id='alertify-cancel']";
	public String successConfirmationPopup = "xpath:-://p[text()='Request sent successfully']";
	public String successConfirmationOkButton = "xpath:-://button[text()='ok' and @id='alertify-ok']";
	public String recipientCheckBox = "id:-:checkbox";
	public String requestTinMatchButton = "xpath:-:(//a[@id='requestTINMatch'])[1]";
	public String clickOnOkRequestTinMatchButton = "xpath:-://article[p[text()='Click on OK to request TIN match']]//button[text()='OK']";
	public String deleteRecipientConfirmationOkButton = "xpath:-://button[text()='OK' and @id='alertify-ok']";
	public String successfullyDeletedOkButton = "xpath:-://button[text()='OK' and @id='alertify-ok']";
	public String deleteRecipientButton = "id:-:btnDeletePayee";
	public String spreadSheetUploadedSuccessfullyOkButton = "xpath:-://button[text()='OK' and @id='alertify-ok']";
	public String doYouWantToUsePrepayAmountOkButton = "xpath:-://button[text()='OK' and @id='alertify-ok']";
	public String tinMatchSuccessfullyOkButton = "id:-:alertify-ok";
	public String tinMatchSuccessfullyMessage = "xpath:-://p[text()='TIN Match request Successful.']";
	public String bulkRequestTinMatchButton="xpath:-://input[@id='btnBulkTinMatch']";
    public String tinMatchAllRecipientConfirmationButton="xpath:-://button[text()='TIN MATCH All Recipients']";
    public String tinMatchSelectedRecipientConfirmationButton="xpath:-://button[text()='TIN MATCH selected Recipients']";
    public String proceedConfirmationButton="xpath:-://button[text()='Proceed' and @id='alertify-ok']";
    public String submittedForTenNewRecipientProceedButton="xpath:-://article[p[contains(text(),'A TIN match request has been submitted for ')]]//button[text()='Proceed']";
    public String usePrepayAmountForThisProceedButton="xpath:-://article[p[contains(text(),'TIN matching requests : 10')]]//button[text()='Proceed']";
    public String addRecipientButton="id:-:btnAddPayee";
    public String planTypeOkButton="xpath:-://article[p[span[contains(text(),'Plan Type')]]]//button[text()='OK']";
    public String prepayAmountOkButton="xpath:-://article[p[text()='Do you want to use prepay amount for this?']]//button[text()='OK']";
    public String paymentSuccessfullOkButton="xpath:-://article[p[text()='Payment successful']]//button[text()='OK']";
    public String bulkW8RequestDropdown="id:-:ddlBulkW8List";
    public String w8RequestDropdown="id:-:ddlW8List";
  
    public String sendW8Request="id:-:btnSendW8";
    public String proceedPopupText="xpath:-://p[text()='Click on proceed to continue sending bulk W8 requests']";
    public String proceedButton="id:-:alertify-ok";
    public String successPopupText="xpath:-://p[text()='Request sent successfully']";
    public String successPopupOk="id:-:alertify-ok";
    
    public String w8RequestButtonSingleRecipient="xpath:-:(//a[@id='requestW8Button'])[1]";
    public String emailSentText="xpath:-://p[text()='Email sent to Recipient']";
    public String emailSentPopupOk="id:-:alertify-ok";
    public String okButton="id:-:alertify-ok";
    public String successUploadSheetPopupText="xpath:-://p[contains(text(),'Spread Sheet Uploaded Successfully')]";
  

	public ManageRecipient(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void verifyMiscFormPageUi() throws Exception {
		AppLibrary.verifyElement(driver, payersDropdown, true);
		AppLibrary.verifyElement(driver, requestW8Button, true);
		AppLibrary.verifyElement(driver, requestW9Button, true);
		AppLibrary.verifyElement(driver, bulkW8request, true);
	}

	public void selectPayerFromDropdown(String payerName) throws Exception {
		AppLibrary.waitTillElementLoaded(driver, payersDropdown);
		AppLibrary.clickElement(driver, payersDropdown);
		AppLibrary.enterText(driver, searchPayer, payerName);
		AppLibrary.waitTillElementLoaded(driver, selection);
		AppLibrary.clickElement(driver, selection);

	}

	public void validateW8() throws Exception {
		AppLibrary.clickElement(driver, selectFirstRecipient);
		AppLibrary.clickElement(driver, requestW8Button);
		WebElement W8options = AppLibrary.findElement(driver, w8RequestDropdown);
		Select sel= new Select(W8options);
		sel.selectByIndex(1);		
		AppLibrary.clickElement(driver, sendW8Request);
		AppLibrary.waitTillElementLoaded(driver, emailSentText);
		AppLibrary.verifyElement(driver, emailSentText, true);
		AppLibrary.clickElement(driver, emailSentPopupOk);
		
	}

	public void validateW9() throws Exception {
		AppLibrary.clickElement(driver, selectFirstRecipient);
		AppLibrary.clickElement(driver, requestW9Button);
		AppLibrary.waitTillElementLoaded(driver, okButton);
		AppLibrary.clickElement(driver,okButton);
		AppLibrary.waitTillElementLoaded(driver, emailSentText);
		AppLibrary.verifyElement(driver, emailSentText, true);
		AppLibrary.clickElement(driver,okButton);
	}

	public void uploadFile() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, selectFiles);
		WebElement file = AppLibrary.findElement(driver, selectFiles);
		String filePath = System.getProperty("user.dir")+File.separator+"Resources"+File.separator+"Recipient_Data_Import_Templatew8w9.xlsx";
		file.sendKeys(filePath);
		AppLibrary.waitTillElementLoaded(driver, bulkUpload);
		AppLibrary.clickElement(driver, bulkUpload); 
		AppLibrary.waitTillElementLoaded(driver, successUploadSheetPopupText);
		AppLibrary.verifyElement(driver, successUploadSheetPopupText, true);
		AppLibrary.clickByJavascript(driver, uploadSucessPopupButton);
	
	}
	

	public void sendBulkW8RequestForSelectedRecipient() throws Exception {
		AppLibrary.clickElement(driver, selectAllCheckbox);
		AppLibrary.clickElement(driver, removeFirstRecipient);
//		AppLibrary.scroll(driver, bulkW8request);
		AppLibrary.clickElement(driver, bulkW8request);
		AppLibrary.waitTillElementLoaded(driver, W8requestSelectedRecipients);
		AppLibrary.clickElement(driver, W8requestSelectedRecipients);
		AppLibrary.clickByJavascript(driver, planTypeOkButton);
		AppLibrary.clickElement(driver, prepayAmountOkButton);
		AppLibrary.clickByJavascript(driver, paymentSuccessfullOkButton);
		AppLibrary.waitTillElementLoaded(driver, bulkW8RequestDropdown);
		WebElement W8options = AppLibrary.findElement(driver, bulkW8RequestDropdown);	
		Select sel= new Select(W8options);
		sel.selectByIndex(1);		
		AppLibrary.clickElement(driver, sendW8Request);
		AppLibrary.clickElement(driver, proceedButton);
		AppLibrary.waitTillElementLoaded(driver, successPopupText);
		AppLibrary.verifyElement(driver, successPopupText, true);
		AppLibrary.clickElement(driver, successPopupOk);
		
	}

	public void sendBulkW8RequestForAllRecipient() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, selectAllCheckbox);
		AppLibrary.clickElement(driver, selectAllCheckbox);
//		AppLibrary.clickElement(driver, removeFirstRecipient);
		AppLibrary.clickElement(driver, bulkW8request);
		AppLibrary.clickElement(driver, W8requestAllRecipients);
		AppLibrary.clickElement(driver, okButton);
		AppLibrary.waitTillElementLoaded(driver, okButton);
		AppLibrary.clickElement(driver, okButton);
		AppLibrary.clickElement(driver, okButton);
		AppLibrary.waitTillElementLoaded(driver, bulkW8RequestDropdown);
		WebElement W8options = AppLibrary.findElement(driver, bulkW8RequestDropdown);
		Select sel= new Select(W8options);
		sel.selectByIndex(1);
		AppLibrary.clickElement(driver, sendW8Request);
		AppLibrary.clickElement(driver, proceedButton);
		AppLibrary.waitTillElementLoaded(driver, successPopupText);
		AppLibrary.verifyElement(driver, successPopupText, true);
		AppLibrary.clickElement(driver, successPopupOk);
	}

	public void sendBulkW9RequestForSelectedRecipient() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, selectAllCheckbox);
		AppLibrary.clickElement(driver, selectAllCheckbox);
		AppLibrary.clickElement(driver, removeFirstRecipient);
		AppLibrary.clickElement(driver,bulkW9request );
		AppLibrary.clickElement(driver, W9requestSelectedRecipients);
//		AppLibrary.verifyElement(driver, emailSentText, true);
		AppLibrary.clickElement(driver, okButton);
		AppLibrary.waitTillElementLoaded(driver, okButton);
		AppLibrary.clickElement(driver, okButton);
		AppLibrary.waitTillElementClickable(driver, okButton);
		AppLibrary.clickByJavascript(driver, okButton);
		AppLibrary.clickElement(driver, proceedButton);
		AppLibrary.waitTillElementLoaded(driver, successPopupText);
		AppLibrary.verifyElement(driver, successPopupText, true);
		AppLibrary.clickElement(driver, successPopupOk);
//		AppLibrary.clickElement(driver, W9requestSelectedRecipients);
//		AppLibrary.clickElement(driver, secondConfiramtionProceedButton);
//		AppLibrary.clickElement(driver, successConfirmationOkButton);
	}

	public void sendBulkW9RequestForAllRecipient() throws Exception {
		
		AppLibrary.clickElement(driver, selectAllCheckbox);
		AppLibrary.clickElement(driver, bulkW9request);
		AppLibrary.clickElement(driver, W9requestAllRecipients);
		AppLibrary.waitTillElementLoaded(driver, okButton);
		AppLibrary.clickElement(driver, okButton);
		AppLibrary.clickElement(driver, okButton);
		AppLibrary.clickElement(driver, okButton);
		AppLibrary.clickElement(driver, secondConfiramtionProceedButton);
		AppLibrary.waitTillElementLoaded(driver, successConfirmationPopup);
		AppLibrary.verifyElement(driver, successConfirmationPopup, true);
		AppLibrary.clickElement(driver, successConfirmationOkButton);
	}

	public void requestTinMatchWithPrepayForSingleRecipient() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, requestTinMatchButton);
		AppLibrary.clickByJavascript(driver, requestTinMatchButton);
		AppLibrary.clickElement(driver, clickOnOkRequestTinMatchButton);
		AppLibrary.waitTillElementClickable(driver, doYouWantToUsePrepayAmountOkButton);
		AppLibrary.clickElement(driver, doYouWantToUsePrepayAmountOkButton);
		AppLibrary.waitTillElementLoaded(driver, tinMatchSuccessfullyOkButton);
		AppLibrary.clickElement(driver, tinMatchSuccessfullyOkButton);
		
	}
	
	public void multipleRequestTinMatchWithPrepay() throws Exception{
		AppLibrary.waitTillElementLoaded(driver, selectAllCheckbox);
        AppLibrary.clickElement(driver, selectAllCheckbox);
        AppLibrary.clickElement(driver, bulkRequestTinMatchButton);
        AppLibrary.clickElement(driver, tinMatchAllRecipientConfirmationButton);
        AppLibrary.waitTillElementLoaded(driver, submittedForTenNewRecipientProceedButton);
        AppLibrary.clickElement(driver, submittedForTenNewRecipientProceedButton);
        AppLibrary.clickElement(driver, proceedConfirmationButton);
        AppLibrary.clickElement(driver, usePrepayAmountForThisProceedButton);
        AppLibrary.waitTillElementClickable(driver, tinMatchSuccessfullyOkButton);
        AppLibrary.clickByJavascript(driver, tinMatchSuccessfullyOkButton);
        
    }

	public void deleteAllRecipient() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, selectAllCheckbox);
		AppLibrary.clickElement(driver, selectAllCheckbox);
		AppLibrary.clickElement(driver, deleteRecipientButton);
		AppLibrary.clickElement(driver, deleteRecipientConfirmationOkButton);
		AppLibrary.clickElement(driver, successfullyDeletedOkButton);
	}
	
	public AddRecipientPage addRecipient() throws Exception{
		AppLibrary.clickElement(driver, addRecipientButton);
		return new AddRecipientPage(appLibrary);
	}

}
