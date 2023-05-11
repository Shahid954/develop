package com.zenwork.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zenwork.library.AppLibrary;

public class AddRecipientPage {

	private WebDriver driver;
	private AppLibrary appLibrary;
	// public String addPayer = "id:-:btnAddEditPayer";
	public String individualRadioButton = "id:-:rblPayeeTypeIndividual";
	public String disregardedEntity = "id:-:DisregardedEntity";
	public String recipientPhoneNo = "id:-:PayeePhNo";

	public String ssn = "id:-:PayeeFedaralID";
	public String firstName = "id:-:PayeeFirstName";
	public String lastName = "id:-:PayeeLastName";
	public String addNewPayer = "xpath:-://span[text()='ADD NEW PAYER']";
	public String businessRadioButton = "id:-:rblPayeeType";
	public String ein = "id:-:PayeeFedaralID";
	public String businessName = "id:-:PayeeLastName";
	// public String disregardedEntity = "xpath:-://input[@id='DisregardedEntity']";
	public String address = "id:-:PayeeAddress";
	public String city = "id:-:PayeeCity";
	public String state = "id:-:PayeeState";
	public String zipCode = "id:-:PayeeZIP";
	public String country = "id:-:PayeeCountry";
//	public String phoneNo = "id:-:PayerPhNo";
	public String email = "id:-:PayeeEmail";
	public String addButton = "xpath:-://div[div[@id='AddPayeeDialog']]//div//button[text()='Add']";
	public String alertMessage = "xpath:-://p[text()='Recipient  Info Added Successfully']";
	public String okButton = "id:-:alertify-ok";

	public AddRecipientPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void verifyAddRecipientPageUi() throws Exception {
		AppLibrary.verifyElement(driver, addNewPayer, true);
		AppLibrary.verifyElement(driver, businessRadioButton, true);
		AppLibrary.verifyElement(driver, ein, true);
//		AppLibrary.verifyElement(driver, firstName, true);
		AppLibrary.verifyElement(driver, businessName, true);

		AppLibrary.verifyElement(driver, address, true);
		AppLibrary.verifyElement(driver, city, true);
		AppLibrary.verifyElement(driver, state, true);
		AppLibrary.verifyElement(driver, zipCode, true);
		AppLibrary.verifyElement(driver, country, true);

	}

	public void fillAddRecipientForm(String einNo, String businessNameData, String addressData, String cityData,
			String stateData, String zipCodeData, String countryData, String emailData) throws Exception {

		AppLibrary.clickElement(driver, businessRadioButton);
		AppLibrary.sendKeys(driver, ein, einNo);
		// AppLibrary.enterText(driver, firstName, firstNameData);
		AppLibrary.enterText(driver, businessName, businessNameData);
//		AppLibrary.enterText(driver, disregardedEntity, disregardedEntityData);
		AppLibrary.enterText(driver, address, addressData);
		AppLibrary.enterText(driver, city, cityData);
		WebElement stateOption = AppLibrary.findElement(driver, state);
		AppLibrary.selectByPartOfVisibleText(stateOption, stateData);
//		AppLibrary.enterText(driver, state, stateData);
		AppLibrary.sendKeys(driver, zipCode, zipCodeData);
		WebElement countryOption = AppLibrary.findElement(driver, country);
		AppLibrary.selectByPartOfVisibleText(countryOption, countryData);

//		AppLibrary.enterText(driver, country, countryData);
//		AppLibrary.enterText(driver, phoneNo, phoneData);
		AppLibrary.enterText(driver, email, emailData);
	}

	public void addRecipientDetails() throws Exception {
		AppLibrary.clickElement(driver, addButton);

	}


	public NECFormSingleFiling VerifyRecipientConfirmation() throws Exception {
		AppLibrary.waitTillElementLoaded(driver, alertMessage);
		AppLibrary.verifyElement(driver, alertMessage, true);
		AppLibrary.clickElement(driver, okButton);
		return new NECFormSingleFiling(appLibrary);
	}

	public void fillIndividualAddRecipientForm(String ssnNo, String firstNameData, String lastNameData,
			String disregardedEntityData, String addressData, String cityData, String stateData, String zipCodeData,
			String countryData, String phoneData, String emailData) throws Exception {

		AppLibrary.clickElement(driver, individualRadioButton);
		AppLibrary.sendKeys(driver, ssn, ssnNo);
		AppLibrary.enterText(driver, firstName, firstNameData);
		AppLibrary.enterText(driver, lastName, lastNameData);
//		AppLibrary.enterText(driver, disregardedEntity, disregardedEntityData);
		AppLibrary.enterText(driver, address, addressData);
		AppLibrary.enterText(driver, city, cityData);
		WebElement stateOption = AppLibrary.findElement(driver, state);
		AppLibrary.selectByPartOfVisibleText(stateOption, stateData);
		AppLibrary.sendKeys(driver, zipCode, zipCodeData);
		WebElement countryOption = AppLibrary.findElement(driver, country);
		AppLibrary.selectByPartOfVisibleText(countryOption, countryData);
		AppLibrary.enterText(driver, recipientPhoneNo, phoneData);
		AppLibrary.enterText(driver, email, emailData);
	}
}
