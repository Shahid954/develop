package com.zenwork.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zenwork.library.AppLibrary;

public class SideNavigation {

	private AppLibrary appLibrary;
	private WebDriver driver;
	public String forms = "xpath:-://p[text()='Forms']";
	public String people = "xpath:-://p[text()='People']";
//	public String importOption = "xpath:-://p[text()=' Import']";
//	public String history = "xpath:-://p[text()='History']";
//	public String notices = "xpath:-://a[text()='Notices']";
//	public String reports = "xpath:-://p[text()='Reports']";
//	public String api = "xpath:-://p[text()='API']";
//	public String support = "xpath:-://p[text()='Support']";
//	public String logout = "xpath:-://a[text()='Logout']";

	public SideNavigation(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void verifySideNavigationUi() throws Exception {
		AppLibrary.verifyElement(driver, forms, true);
//		AppLibrary.verifyElement(driver, people, true);
//		AppLibrary.verifyElement(driver, importOption, true);
	}

	public Forms selectForms() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, forms);
		AppLibrary.clickElement(driver, forms);
		return new Forms(appLibrary);
	}

	public People selectPeople() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, people);
		AppLibrary.clickElement(driver, people);
		return new People(appLibrary);
	}

}
