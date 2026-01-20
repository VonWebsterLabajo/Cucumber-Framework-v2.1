package com.cheq.demo_webshop.pages;

import com.cheq.demo_webshop.utils.ElementActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {

    private WebDriver driver;
    private ElementActionUtils elementActionUtils;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    private final By HEADER_TITLE_LBL = By.xpath("//h1[normalize-space()='Simple Calculator App']");


    public void isHeaderTextVisible() {
        //verify
        elementActionUtils.verifyDisplayed(HEADER_TITLE_LBL);
    }

}