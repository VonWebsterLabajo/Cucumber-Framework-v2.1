package com.cheq.demo_webshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cheq.demo_webshop.utils.ElementActionUtils;

public class LoginPage {
    private WebDriver driver;
    private ElementActionUtils elementActionUtils;

    private By EMAIL_TXT = By.id("Email123");
    private By PASSWORD_TXT = By.id("Passwordiswrong");
    private By LOGIN_BTN = By.xpath("//input[@value='Log in']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    public void enterEmail(String userEmail) {
        elementActionUtils.inputElement(EMAIL_TXT, userEmail);
    }

    public void enterPassword(String userPassword) {
        elementActionUtils.inputElement(PASSWORD_TXT, userPassword);
    }

    public void clickLoginBtn() {
        elementActionUtils.clickElement(LOGIN_BTN);
    }

    public void clickBooksCategory(String categoryName) {
        By category = By.xpath("//ul[@class='top-menu']//a[normalize-space()='" + categoryName + "']");
        elementActionUtils.clickElement(category);
    }
}


