package com.zenwork.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zenwork.library.AppLibrary;

public class PaymentPage {
	private WebDriver driver;
	private AppLibrary appLibrary;
	public String enterCardInformationText = "xpath:-://p[normalize-space()='Enter your card information']";
	public String cardHolderName = "id:-:CardHolderName";
	public String creditCardNumber = "id:-:CreditCardNumber";
	public String month = "id:-:ExpiryMonth";
	public String year = "id:-:ExpiryYear";
	public String cvv = "id:-:CvvNumber";
	public String saveCardForFuturePayments = "id:-:isSaveCardP";
	public String address = "id:-:salesAddress";
	public String city = "id:-:salesCity";
	public String zip = "id:-:salesZIP";
	public String state = "id:-:salesddlAddressStateUS";
	public String email = "id:-:Emailid";
	public String payAndSubmitButton = "id:-:btnPay";
	
	

	public PaymentPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void verifyPaymentPageUi() throws Exception {
		AppLibrary.verifyElement(driver, enterCardInformationText, true);
		AppLibrary.verifyElement(driver, cardHolderName, true);
		AppLibrary.verifyElement(driver, creditCardNumber, true);
		AppLibrary.verifyElement(driver, month, true);
		AppLibrary.verifyElement(driver, year, true);
		AppLibrary.verifyElement(driver, cvv, true);
		AppLibrary.verifyElement(driver, saveCardForFuturePayments, true);
		AppLibrary.verifyElement(driver, address, true);
		AppLibrary.verifyElement(driver, city, true);
		AppLibrary.verifyElement(driver, zip, true);
		AppLibrary.verifyElement(driver, state, true);
		AppLibrary.verifyElement(driver, email, true);
		AppLibrary.verifyElement(driver, payAndSubmitButton, true);
	}

	public void fillCardDetails(String cardHolderNameData, String creditCardNoData, String monthData, String YearData,
			String cvvData)
			throws Exception {
		AppLibrary.enterText(driver, cardHolderName, cardHolderNameData);
		AppLibrary.enterText(driver, creditCardNumber, creditCardNoData);
		AppLibrary.enterText(driver, creditCardNumber, creditCardNoData);
		WebElement monthOption = AppLibrary.findElement(driver, month);
		AppLibrary.selectByPartOfVisibleText(monthOption, monthData);
		WebElement yearOption = AppLibrary.findElement(driver, year);
		AppLibrary.selectByPartOfVisibleText(yearOption, YearData);
		AppLibrary.enterText(driver, cvv, cvvData);
		
//		AppLibrary.enterText(driver, email, emailData);
		

	}
	
	public void fillAddressDetails( String addressData, String cityData, String zipCodeData, String stateData) throws Exception {
		AppLibrary.waitTillElementLoaded(driver, address);
		AppLibrary.enterText(driver, address, addressData);
		AppLibrary.enterText(driver, city, cityData);
		AppLibrary.enterText(driver, zip, zipCodeData);
		WebElement stateOption = AppLibrary.findElement(driver, state);
		AppLibrary.selectByPartOfVisibleText(stateOption, stateData);
	}

	public SubmissionConfirmationPage submitPayment() throws Exception {
		AppLibrary.waitTillElementClickable(driver, payAndSubmitButton);
		AppLibrary.clickElement(driver, payAndSubmitButton);
		return new SubmissionConfirmationPage(appLibrary);
	}

}
