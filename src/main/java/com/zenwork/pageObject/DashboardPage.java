package com.zenwork.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zenwork.library.AppLibrary;

public class DashboardPage {

	private AppLibrary appLibrary;
	private WebDriver driver;
	public String dashboardPage = "xpath:-://a[text()='Dashboard']";
	public String selctPlansText = "id:-:ui-id-4";
	public String cancelPlansPopup = "xpath:-:(//button[@class='ui-dialog-titlebar-close'])[4]";

	public DashboardPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	/**
	 * This method is verifying the available links on Dashboard page
	 **/
	public void verifyDashboardPage() throws Exception {
		AppLibrary.verifyElement(driver, dashboardPage, true);
	}

	public void plansPopupCancel() throws Exception {
		WebElement ele = AppLibrary.findElement(driver, selctPlansText);
		String s = ele.getText();
		if (s.equalsIgnoreCase("Select Plan")) {
			AppLibrary.clickElement(driver, cancelPlansPopup);
		}
		
	}

}
