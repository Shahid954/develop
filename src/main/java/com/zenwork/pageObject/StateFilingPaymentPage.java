package com.zenwork.pageObject;

import org.openqa.selenium.WebDriver;

import com.zenwork.library.AppLibrary;

public class StateFilingPaymentPage {
	
	 private AppLibrary appLibrary;
	    private WebDriver driver;
	    public String paynowButton="xpath:-://button[text()='Pay Now']";
	    public String cancelButton="xpath:-:(//button[text()='Cancel' and @class='red_button_big'])[17]";
	    
	    
	    
	    
	    
	    public StateFilingPaymentPage(AppLibrary appLibrary) {
	        super();
	        this.appLibrary = appLibrary;
	        this.driver = appLibrary.getCurrentDriverInstance();
	    }
	    
	    public void payNowButton() throws Exception {
	        AppLibrary.clickElement(driver, paynowButton);
	    }
}
