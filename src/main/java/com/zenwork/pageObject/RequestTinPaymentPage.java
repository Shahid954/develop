package com.zenwork.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zenwork.library.AppLibrary;

public class RequestTinPaymentPage {
	private WebDriver driver;
	private AppLibrary appLibrary;
	public String savedCardCheckBox="id:-:commonisSaveCard";
	public String enterCardHolderName="id:-:CommonCardHolderName";
	public String enterCreditCardNumber="id:-:CommonCreditCardNumber";
	public String month="id:-:CommonExpiryMonth";
	public String year="id:-:CommonExpiryYear";
	public String cvv="id:-:CommonCvvNumber";
	public String billingAddress="id:-:CommonBillingaddress";
	public String city="id:-:CommonCity";
	public String state="id:-:StateCodesdll";
	public String enterZipCode="id:-:CommonZipcode";
	public String country="id:-:CommonCountry";
	public String enterEmail="id:-:CommonContactEmail";
	public String payNowButton="xpath:-://button[text()='Pay Now']";
	
	
	public RequestTinPaymentPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}
	
	
	public void verifyRequestTinPaymentPageUi() {
		AppLibrary.verifyElement(driver, enterCardHolderName, true);
		AppLibrary.verifyElement(driver, enterCreditCardNumber, true);
		AppLibrary.verifyElement(driver, month, true);
		AppLibrary.verifyElement(driver, year, true);
		AppLibrary.verifyElement(driver, cvv, true);
		AppLibrary.verifyElement(driver, billingAddress, true);
		AppLibrary.verifyElement(driver, city, true);
		AppLibrary.verifyElement(driver, state, true);
		AppLibrary.verifyElement(driver, enterZipCode, true);
		AppLibrary.verifyElement(driver, country, true);
		AppLibrary.verifyElement(driver, enterEmail, true);
		
	}
	
	
	public void saveCardDetails(String cardHoldernameData,String creditCardData,String monthData,String yearData,String cvvData) throws Exception {
		AppLibrary.clickElement(driver, savedCardCheckBox);
		AppLibrary.enterText(driver, enterCardHolderName, cardHoldernameData);
		AppLibrary.enterText(driver, enterCreditCardNumber, creditCardData);
		WebElement monthOption = AppLibrary.findElement(driver, month);
		AppLibrary.selectByPartOfVisibleText(monthOption, monthData);
		WebElement yearOption = AppLibrary.findElement(driver, year);
		AppLibrary.selectByPartOfVisibleText(yearOption, yearData);
		AppLibrary.enterText(driver, cvv, cvvData);
		
		
	}
	public void saveCardAddressDetails(String billingAddressData,String cityData,String stateData,String zipCodeData,String countryData,
			String emailData) throws Exception {
		AppLibrary.enterText(driver, billingAddress, billingAddressData);
		AppLibrary.enterText(driver, city, cityData);
		WebElement stateOption = AppLibrary.findElement(driver, state);
		AppLibrary.selectByPartOfVisibleText(stateOption, stateData);
		AppLibrary.enterText(driver, enterZipCode, zipCodeData);
		WebElement countryOption = AppLibrary.findElement(driver, country);
		AppLibrary.selectByPartOfVisibleText(countryOption, countryData);
		
		}
	public void clickOnPayAndNow() throws Exception {
		AppLibrary.clickElement(driver, payNowButton);
		
	}

}
