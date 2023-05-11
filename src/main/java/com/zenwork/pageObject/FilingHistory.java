package com.zenwork.pageObject;

import org.openqa.selenium.WebDriver;

import com.zenwork.library.AppLibrary;

public class FilingHistory {
	AppLibrary appLibrary;
	private WebDriver driver;
	public String filingHistory = "xpath:-://h3[text()='Filing History']";
	public String firstRow = "xpath:-:(//tr//td//span//a[text()='Click here'])[1]";
	public String refrenceNo = "xpath:-:(//tr//td//span[contains(text(),'Ref#')])[1]";
	
	
	public FilingHistory(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}
	
	public void verifyFilingHistoryPageUi() throws Exception {
		AppLibrary.verifyElement(driver, filingHistory, true);
		AppLibrary.verifyElement(driver, firstRow, true);
		AppLibrary.verifyElement(driver, refrenceNo, true);
	}
	
	public ManageFormPage selectLatestRecord() throws Exception {
		AppLibrary.clickElement(driver, firstRow);
		return new ManageFormPage(appLibrary);
	}
	
	public String getMessage() {
	String refNoFilingHitory = AppLibrary.GetTextForVerification(driver, refrenceNo);
	return refNoFilingHitory;
	}
			
	
}
