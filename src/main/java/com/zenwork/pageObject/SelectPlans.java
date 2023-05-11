package com.zenwork.pageObject;

import org.openqa.selenium.WebDriver;

import com.zenwork.library.AppLibrary;

public class SelectPlans {

	private AppLibrary appLibrary;
	private WebDriver driver;
	public String selectEssential = "id:-:btnEssential1";
	public String selectEfilePlus = "id:-:btnEfilePlus1";
	public String selectEnterprise = "id:-:btnEnterprise1";

//	public String importOption = "xpath:-://p[text()=' Import']";
//	public String history = "xpath:-://p[text()='History']";
//	public String notices = "xpath:-://a[text()='Notices']";
//	public String reports = "xpath:-://p[text()='Reports']";
//	public String api = "xpath:-://p[text()='API']";
//	public String support = "xpath:-://p[text()='Support']";
//	public String logout = "xpath:-://a[text()='Logout']";

	public SelectPlans(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void verifyScheduleFormsUi() throws Exception {
		AppLibrary.verifyElement(driver, selectEssential, true);
		AppLibrary.verifyElement(driver, selectEfilePlus, true);
		AppLibrary.verifyElement(driver, selectEnterprise, true);
	}

	public DashboardPage selectEssentialPlan() throws Exception {
		AppLibrary.clickElement(driver, selectEssential);
		return new DashboardPage(appLibrary);
	}

	public DashboardPage selectEfilePlusPlan() throws Exception {
		AppLibrary.clickElement(driver, selectEfilePlus);
		return new DashboardPage(appLibrary);
	}

	public DashboardPage selectEnterprisePlan() throws Exception {
		AppLibrary.clickElement(driver, selectEnterprise);
		return new DashboardPage(appLibrary);
	}
}
