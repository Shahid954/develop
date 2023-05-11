package com.zenwork.pageObject;

import org.openqa.selenium.WebDriver;

import com.zenwork.library.AppLibrary;

public class Forms {
	private AppLibrary appLibrary;
	private WebDriver driver;
	public String newForm = "xpath:-://a[text()='New Form']";
	public String manageForm = "xpath:-://a[text()='Manage Forms']";

	

	public Forms(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void verifyFormsOptionsUi() throws Exception {
		AppLibrary.verifyElement(driver, newForm, true);
	}

	public NewForm selectNewForm() throws Exception {
		AppLibrary.clickElement(driver, newForm);
		return new NewForm(appLibrary);
	}
	
	public ManageFormPage selectManageForm() throws Exception {
        AppLibrary.clickElement(driver, manageForm);
        return new ManageFormPage(appLibrary);
    }
	
}
