package com.cheq.demo_webshop.pages;
import com.cheq.demo_webshop.factory.DropdownFactory;
import com.cheq.demo_webshop.utils.ElementActionUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    private WebDriver driver;
    private ElementActionUtils elementActionUtils;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    private By REMOVE_CHK = By.xpath("//input[@name='removefromcart']");
    private By UPDATE_CART_BTN = By.xpath("//input[contains(@value, 'Update')]");


    public void isRemoveCheckboxVisible() {
    	elementActionUtils.verifyDisplayed(REMOVE_CHK);
    }

    public void clickRemoveCheckbox() {
    	elementActionUtils.clickElement(UPDATE_CART_BTN);
    }

    public void verifySubCategory(String bookTitle) {
        By bookLocator = By.xpath("//h2[@class='product-title']/child::a[text()='" + bookTitle + "']");
        elementActionUtils.verifyDisplayed(bookLocator);
    }

    public void isOrderSummaryVisible(String bookTitle) {
        By bookLocator = By.xpath("//a[@class='product-name' and text()='" + bookTitle + "']");
        elementActionUtils.verifyDisplayed(bookLocator);
    }
    
    public void clickUpdateCartButton() {
    	elementActionUtils.clickElement(UPDATE_CART_BTN);
    }
    
 // In 'MultipleProductsPage.java'
    public void selectCountry(String strategyType, String locatorValue, String selectedValue) {
        WebElement dropdown = driver.findElement(By.id(locatorValue));
        DropdownFactory.getStrategy(strategyType).select(dropdown, selectedValue);
    }
}
 