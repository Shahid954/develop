
package com.zenwork.pageObject;

import org.openqa.selenium.WebDriver;

import com.zenwork.library.AppLibrary;

public class NewForm {
	
	private AppLibrary appLibrary;
	private WebDriver driver;
	
	
	public String year = "xpath:-://li//h1[text()='2022']";
	public String necForm = "id:-:NEC";
	public String miscForm = "id:-:MISC";
	
	public NewForm(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}
	
	public void verifyNewFormUi() throws Exception {
		AppLibrary.verifyElement(driver, year, true);
		AppLibrary.verifyElement(driver, necForm, true);
		AppLibrary.verifyElement(driver, miscForm, true);
	}
	
	
	
	public void selectYear() throws Exception {
		AppLibrary.clickElement(driver, year);
		
	}
	
	public NECFormSingleFiling selectNecForm() throws Exception {
		AppLibrary.clickElement(driver, necForm);
		return new NECFormSingleFiling(appLibrary);
	}
	
	public MiscFormSingleFiling selectMiscForm() throws Exception {
		AppLibrary.clickElement(driver, miscForm);
		return new MiscFormSingleFiling(appLibrary);
	}

}
