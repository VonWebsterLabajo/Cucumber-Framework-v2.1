package com.cheq.demo_webshop.pages;
import com.cheq.demo_webshop.factory.RadioButtonFactory;
import com.cheq.demo_webshop.utils.ElementActionUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegisterPage {

    private WebDriver driver;
    private ElementActionUtils elementActionUtils;

    private By FIRST_NAME_TXT = By.xpath("//input[@id='FirstName']");
    private By LAST_NAME_TXT = By.xpath("//input[@id='LastName']");
    private By EMAIL_TXT = By.xpath("//input[@id='Email']");
    private By PASSWORD_TXT = By.xpath("//input[@id='Password']");
    private By CONFIRM_PASSWORD_TXT = By.xpath("//input[@id='ConfirmPassword']");
    private By REGISTER_BTN = By.xpath("//input[@id='register-button']");
    private By REGISTRATION_SUCCESS_LBL = By.xpath("//div[@class='result' and contains(text(),'Your registration completed')]");

    // Constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    public void selectGender(String locatorType, String locatorValue) {
    	elementActionUtils.clickElement(RadioButtonFactory.getRadioButtonStrategy(locatorType)
    			.getLocator(locatorValue));
    }

    public void enterFirstName(String firstName) {
    	elementActionUtils.inputElement(FIRST_NAME_TXT, firstName);
    }

    public void enterLastName(String lastName) {
    	elementActionUtils.inputElement(LAST_NAME_TXT, lastName);
    }

    public void enterEmail(String email) {
    	elementActionUtils.inputElement(EMAIL_TXT, email);
    }

    public void enterPassword(String password) {
    	elementActionUtils.inputElement(PASSWORD_TXT, password);
    }
    
    public void enterConfirmPassword(String confirmPassword) {
    	elementActionUtils.inputElement(CONFIRM_PASSWORD_TXT, confirmPassword);
    }

    public void clickRegisterButton() {
    	elementActionUtils.clickElement(REGISTER_BTN);
    }

    public void isRegistrationSuccessful() {
        elementActionUtils.verifyDisplayed(REGISTRATION_SUCCESS_LBL);

    }
}
 