package com.cheq.demo_webshop.stepdefinitions;

import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.pages.LandingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;


public class UiSteps {

	WebDriver driver;

    private LandingPage landingPage;
    
    public UiSteps() {
        this.driver = DriverManager.getDriver();
        this.landingPage = new LandingPage(driver);
    }
    
    @Given("User is in the calculator page") 
    public void userIsInTheCalculatorPage() {
    	landingPage.isHeaderTextVisible();
        //test
    }
    
    @Then("User should see the calculator elements") 
    public void userShouldSeeTheCalculatorElements() {
    	landingPage.isCalculatorElementsVisible();
    }
    
    @When("User inputs number values")
    public void userInputsNumberValues(DataTable dataTable) {
        List<Map<String, String>> numbers = dataTable.asMaps(String.class, String.class);

        String numberOne = numbers.get(0).get("numberOne").trim();
        String numberTwo = numbers.get(0).get("numberTwo").trim();
        
    	landingPage.enterNumberOneValue(numberOne);
    	landingPage.enterNumberTwoValue(numberTwo);
    }
    
	@And("User selects operation")
	public void userSelectsOperation(DataTable dataTable) {
        List<Map<String, String>> numbers = dataTable.asMaps(String.class, String.class);

        String operation = numbers.get(0).get("operation").trim();
        
		landingPage.selectOperation(operation);
	}
	
	@And("User clicks compute")
	public void userClicksCompute() {
		landingPage.clickCompute();
	}
	
	@Then("Verify if result matched")
	public void verifyIfResultMatched(DataTable dataTable) {
        List<Map<String, String>> numbers = dataTable.asMaps(String.class, String.class);

        String expectedResult = numbers.get(0).get("expectedResult").trim();
        
		landingPage.isResultMatched(expectedResult);
	}
	
	@Then("User should see the calculator dropdown")
	public void userShouldSeeTheCalculatorDropdown() {
		landingPage.isOperationVisible();
	}
}