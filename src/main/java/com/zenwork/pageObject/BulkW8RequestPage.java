package com.zenwork.pageObject;

import org.openqa.selenium.WebDriver;

import com.zenwork.library.AppLibrary;

public class BulkW8RequestPage {
	private WebDriver driver;
	private AppLibrary appLibrary;
	public String w8Text = "xpath:-://p[text()='Please select a W8 Form and click on Send Request:']";
	public String bulkW8Title = "xpath:-://span[@id='BulkW8_wnd_title']";
	public String sendRequest = "xpath:-://input[@id='btnSendW8']";
	public String cancelButton = "xpath:-://input[@value='Cancel']";
	public String bulkW8Dropdown = "xpath:-://select[@id='ddlBulkW8List']";
	
	
	public  BulkW8RequestPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}
	
	public void verifyBulkW8RequestPageUi() throws Exception {
		
		AppLibrary.verifyElement(driver, w8Text, true);
		AppLibrary.verifyElement(driver, bulkW8Title, true);
		AppLibrary.verifyElement(driver, sendRequest, true);
		AppLibrary.verifyElement(driver, cancelButton, true);
		AppLibrary.verifyElement(driver, bulkW8Dropdown, true);
	}
	
	
}
