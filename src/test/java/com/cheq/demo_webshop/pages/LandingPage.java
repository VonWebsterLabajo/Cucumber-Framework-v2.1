package com.cheq.demo_webshop.pages;

import com.cheq.demo_webshop.utils.ElementActionUtils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

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
    private final By NUMBER_ONE_INPUT = By.xpath("//input[@id='a']");
    private final By NUMBER_TWO_INPUT = By.xpath("//input[@id='b']");
    private final By OPERATIONS_SELECT = By.xpath("//select[@id='op']");
    private final By COMPUTE_BTN = By.xpath("//button[@id='compute']");
    private final By RESULT_LBL = By.xpath("//div[@id='result']");
    
    public void isHeaderTextVisible() {
        //verify
        elementActionUtils.verifyDisplayed(HEADER_TITLE_LBL);
    }
    
    public void isCalculatorElementsVisible() {
    	elementActionUtils.verifyDisplayed(NUMBER_ONE_INPUT);
    	elementActionUtils.verifyDisplayed(NUMBER_TWO_INPUT);
    	elementActionUtils.verifyDisplayed(OPERATIONS_SELECT);
    	elementActionUtils.verifyDisplayed(COMPUTE_BTN);
    	elementActionUtils.verifyDisplayed(RESULT_LBL);
    }
    
	public void enterNumberOneValue(String numberOne) {
		elementActionUtils.removeInput(NUMBER_ONE_INPUT);
		elementActionUtils.inputElement(NUMBER_ONE_INPUT, numberOne);
	}
	
	public void enterNumberTwoValue(String numberTwo) {
		elementActionUtils.removeInput(NUMBER_TWO_INPUT);
		elementActionUtils.inputElement(NUMBER_TWO_INPUT, numberTwo);
	}

	public void selectOperation(String option) {
		elementActionUtils.selectDropdown(OPERATIONS_SELECT, option);
	}

	public void clickCompute() {
		elementActionUtils.clickFluentElement(COMPUTE_BTN);
	}

	public void isResultMatched(String result) {
		elementActionUtils.getTextAndCompare(RESULT_LBL, result);
	}
	
	public void isOperationVisible() {
		elementActionUtils.verifyDisplayed(OPERATIONS_SELECT);
	}

}