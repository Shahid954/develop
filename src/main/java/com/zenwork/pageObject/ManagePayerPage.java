package com.zenwork.pageObject;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zenwork.library.AppLibrary;

public class ManagePayerPage {
	AppLibrary appLibrary;
	private WebDriver driver;

	public String managePayer = "xpath:-://h3[text()='Manage Payer']";
	public String addPayerButton = "id:-:btnAddEditPayer";
	public String deletePayerButton = "id:-:btnDeletePayer";
	public String assignPayerButton = "id:-:btnAssignPayer";
	public String activeInactiveButton = "id:-:btnActInactPayer";
	public String exportToExcelButton = "id:-:btnExportToExcelAll_Payerss";
	public String selectAllPayerCheckbox = "id:-:selectall";
	public String singlePayerCheckbox = "xpath:-:(//input[@id='checkbox'])[1]";
	public String selectedPayerDeleteButton = "xpath:-:(//a[@id='deleteButton'])[1]";
	public String searchPayerTextBox = "id:-:payerName";
	public String alertifyTextMessage = "xpath:-://p[text()='Are You Sure You Want to Delete?']";
	public String alertifyOkButton = "xpath:-://button[text()='OK']";
	public String payerSuccessfullyDeletedTextMessage = "xpath:-://p[text()='Payer Successfully Deleted']";
	public String selectedPayerDeletedSuccessfully="xpath:-://p[contains(text(),'Selected Payer(s) Deleted Successfully.')]";
	public String selectFile="xpath:-://div[span[text()='Select files...']]//input[@id='upload']";
	public String bulkUploadButton = "id:-:Next";
	public String spreadSheetUploadedSuccessfullyOkButton = "xpath:-://article[p[contains(text(),'Spread Sheet Uploaded Successfully')]]//button";
	public String filterIcon = "xpath:-:(//span[@class='k-icon k-i-filter'])[2]";
	public String payerSearchTextField = "xpath:-:(//input[@class='k-textbox'])[1]";
	public String filterButton = "xpath:-:(//button[text()='Filter'])[1]";

	public ManagePayerPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void verifyManagePayerPageUi() throws Exception {
		AppLibrary.verifyElement(driver, managePayer, true);
	}

	public AddPayerForm addPayerButton() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, addPayerButton);
		AppLibrary.clickElement(driver, addPayerButton);
		return new AddPayerForm(appLibrary);
	}

	public void singlePayerDelete(String payer) throws Exception {
		AppLibrary.clickElement(driver, filterIcon);
		AppLibrary.enterText(driver, payerSearchTextField, payer);
		AppLibrary.clickElement(driver, filterButton);
		AppLibrary.clickElement(driver, selectAllPayerCheckbox);
		AppLibrary.waitTillElementLoaded(driver, selectedPayerDeleteButton);
		AppLibrary.clickElement(driver, selectedPayerDeleteButton);
	}

	public void bulkDeletePayerButton() throws Exception {
		AppLibrary.clickElement(driver, selectAllPayerCheckbox);
		AppLibrary.clickElement(driver, deletePayerButton);
	}

	public void assignPayerButton() throws Exception {
		AppLibrary.clickElement(driver, assignPayerButton);
	}

	public void activeInactiveButton() throws Exception {
		AppLibrary.clickElement(driver, activeInactiveButton);
	}

	public void exportToExcel() throws Exception {
		AppLibrary.clickElement(driver, exportToExcelButton);
	}

	public void deleteAlertifyOk() throws Exception {
		AppLibrary.verifyElement(driver, alertifyTextMessage, true);
		AppLibrary.clickElement(driver, alertifyOkButton);
		AppLibrary.waitTillElementLoaded(driver, payerSuccessfullyDeletedTextMessage);
		AppLibrary.verifyElement(driver, payerSuccessfullyDeletedTextMessage, true);
		AppLibrary.clickElement(driver, alertifyOkButton);
	}

	public void uploadFile() throws Exception {
		WebElement file = AppLibrary.findElement(driver, selectFile);
		String filePath = System.getProperty("user.dir")+File.separator+"Resources"+File.separator+"Recipient_Data_Import_Templatew8w9.xlsx";
		file.sendKeys(filePath);
		AppLibrary.clickElement(driver, bulkUploadButton);
		AppLibrary.clickElement(driver, spreadSheetUploadedSuccessfullyOkButton);

	}
	public void uploadFileForPayer() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, selectFile);
		WebElement file = AppLibrary.findElement(driver, selectFile);
		String filePath = System.getProperty("user.dir")+File.separator+"Resources"+File.separator+"Payer_Data_Import_Template (1).xlsx";
		file.sendKeys(filePath);
		AppLibrary.clickElement(driver, bulkUploadButton);
		AppLibrary.clickElement(driver, spreadSheetUploadedSuccessfullyOkButton);

	}

	public void bulkDeletePayerButton(String payerName) throws Exception {
		AppLibrary.clickElement(driver, filterIcon);
		AppLibrary.enterText(driver, payerSearchTextField, payerName);
		AppLibrary.clickElement(driver, filterButton);
		AppLibrary.clickElement(driver, selectAllPayerCheckbox);
		AppLibrary.clickElement(driver, deletePayerButton);
		AppLibrary.waitTillElementClickable(driver, alertifyOkButton);
		AppLibrary.clickByJavascript(driver, alertifyOkButton);
		AppLibrary.waitTillElementLoaded(driver, selectedPayerDeletedSuccessfully);
		AppLibrary.verifyElement(driver, selectedPayerDeletedSuccessfully, true);
		AppLibrary.clickElement(driver, alertifyOkButton);

	}
}
