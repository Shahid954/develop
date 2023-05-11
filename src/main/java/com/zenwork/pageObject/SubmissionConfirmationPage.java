package com.zenwork.pageObject;

import org.openqa.selenium.WebDriver;

import com.zenwork.library.AppLibrary;

public class SubmissionConfirmationPage {
	AppLibrary appLibrary;
	private WebDriver driver;
	public String SubmissionToIrsMsg = "xpath:-://p[contains(text(),'Your return has been submitted to the IRS')]";
	public String filingHistoryLink = "xpath:-://a[text()='filing history here.']";
	
	
	public SubmissionConfirmationPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}
	
	public void verifyConfirmationPageUi() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, SubmissionToIrsMsg);
		AppLibrary.verifyElement(driver, SubmissionToIrsMsg, true);
		AppLibrary.verifyElement(driver, filingHistoryLink, true);
	}
	
	public FilingHistory navigateToFilingHistory() throws Exception {
		AppLibrary.clickElement(driver, filingHistoryLink);
		return new FilingHistory(appLibrary);
	}
	
}
