package com.zenwork.pageObject;

import org.openqa.selenium.WebDriver;

import com.zenwork.library.AppLibrary;

public class ScheduleForms {
	private AppLibrary appLibrary;
	private WebDriver driver;
	public String scheduleDate  = "xpath:-:(//span[@class='k-icon k-i-calendar'])[1]";
	public String scheduleDateOkButton = "xpath:-://button[text()='OK']";
	public String scheduleDateCancelButton = "xpath:-:(//button[text()='Cancel' and @type='button' ])[1]";
//	public String history = "xpath:-://p[text()='History']";
//	public String notices = "xpath:-://a[text()='Notices']";
//	public String reports = "xpath:-://p[text()='Reports']";
//	public String api = "xpath:-://p[text()='API']";
//	public String support = "xpath:-://p[text()='Support']";
//	public String logout = "xpath:-://a[text()='Logout']";
	
	
	public ScheduleForms(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void verifyScheduleFormsUi() throws Exception {
		AppLibrary.verifyElement(driver, scheduleDate, true);
		AppLibrary.verifyElement(driver, scheduleDateOkButton, true);
		AppLibrary.verifyElement(driver, scheduleDateCancelButton, true);
	}

	public Forms selectDatefromCalendar() throws Exception {
	
	return new Forms(appLibrary);	
	}


}
