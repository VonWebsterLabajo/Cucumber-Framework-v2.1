package com.cheq.demo_webshop.stepdefinitions;

import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.pages.LandingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;


public class UiSteps {

	WebDriver driver;

    private LandingPage landingPage;
    
    public UiSteps() {
        this.driver = DriverManager.getDriver();
        this.landingPage = new LandingPage(driver);
    }
    
    @Given("User is in the calculator page") 
    public void checkPage() {
    	landingPage.isHeaderTextVisible();
        //test
    }
    
    @Then("User should see the calculator elements") 
    public void verify() {
    }
}