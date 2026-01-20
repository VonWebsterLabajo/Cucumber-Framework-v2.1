package com.cheq.demo_webshop.pages;

import com.cheq.demo_webshop.utils.ElementActionUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookPage {

    private WebDriver driver;
    private ElementActionUtils elementActionUtils;

    public BookPage(WebDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    private By FICTION_BOOK_LBL = By.xpath("//h2[@class='product-title']/a[normalize-space(text())='Fiction']");
    private By FICTION_BOOK_PRICE_LBL = By.xpath("//h2[@class='product-title']/a[text()='Fiction']/ancestor::div[@class='details']//span[contains(@class, 'actual-price')]");
    private By ADD_TO_CART_BTN = By.xpath("//input[contains(@id, 'add-to-cart-button')]");
    private By SHOPPING_CART_LNK = By.cssSelector("a[class='ico-cart'] span[class='cart-label']");


    public void isFictionBookVisible() {
        elementActionUtils.verifyDisplayed(FICTION_BOOK_LBL);
    }

    public void isFictionBookPriceVisible() {
        elementActionUtils.verifyDisplayed(FICTION_BOOK_PRICE_LBL);
    }

    public void clickFictionBook() {
        elementActionUtils.clickElement(FICTION_BOOK_LBL);
    }

    public void clickdAddToCart() {
        elementActionUtils.clickElement(ADD_TO_CART_BTN);
    }

    public void clickShoppingCartLink() {
        elementActionUtils.clickElement(SHOPPING_CART_LNK);
    }
}
 