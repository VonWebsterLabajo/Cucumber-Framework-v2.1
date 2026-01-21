package com.cheq.demo_webshop.hooks;

import com.cheq.demo_webshop.utils.DataDictionaryUtil;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.cheq.demo_webshop.factory.WebDriverFactory;
import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.utils.AllureUtil;
import com.cheq.demo_webshop.utils.ConfigReader;
import com.cheq.demo_webshop.utils.LoggerUtil;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;

/**
* Cucumber hooks for managing WebDriver lifecycle and Allure reporting.
* Handles setup, teardown, screenshot capture, and driver access per scenario.
*/
public class Hooks {
//    private AllureUtil allureUtil;
    private ThreadLocal<AllureUtil> allureUtil = new ThreadLocal<>();
    private static final Logger logger = LoggerUtil.getLogger(Hooks.class);

    /**
    * Initializes WebDriver, loads environment configuration,
    * sets up Allure reporting, and starts the test scenario.
    *
    * @param scenario the current Cucumber scenario
    * @throws IOException if configuration properties fail to load
    */    
    @Before
    public void setUp(Scenario scenario) throws IOException {
    	// Verify if the run is parallel or sequential!
		System.out.println(
				 "Thread=" + Thread.currentThread().getName() +
				 " | Scenario=" + scenario.getName()
				);
		
        // Load environment-specific properties
        String env = System.getProperty("env", "dev");
        ConfigReader.loadProperties(env);

        // Determine browser type and base URL
        String browser = System.getProperty("browser", ConfigReader.get("browser"));
        String url = ConfigReader.get("baseUrl");

        // Create and configure WebDriver for this thread
        WebDriver driver = WebDriverFactory.loadDriver(browser);
        driver.manage().window().maximize();
        driver.get(url);

        // Make WebDriver globally accessible
        DriverManager.setDriver(driver);

        // Write environment details to Allure report	 	
	     allureUtil.set(new AllureUtil(driver));
	     allureUtil.get().writeAllureEnvironment(
	         ImmutableMap.<String, String>builder()
	             .put("OS", System.getProperty("os.name"))
	             .put("Browser", browser)
	             .put("Environment", env)
	             .build()
	     );
        logger.info("Starting scenario: " + scenario.getName());
    }
    
    /**
     * Captures and attaches a screenshot to the Allure report
     * if the scenario execution fails.
     *
     * @param scenario the executed Cucumber scenario
     */
    @After(order = 0)
    public void captureFailure(Scenario scenario) {
    	WebDriver drv = DriverManager.getDriver();
    	if (scenario.isFailed() && drv != null) {
    		allureUtil.get().captureAndAttachScreenshot();
    	}
    }
    
    /**
    * Quits the WebDriver instance and cleans up thread-local resources
    * after scenario execution.
    *
    * @param scenario the executed Cucumber scenario
    */
    @After(order = 1)
    public void tearDown(Scenario scenario) {
     WebDriver drv = DriverManager.getDriver();
     if (drv != null) {
      drv.quit();
     }
     DriverManager.unload();
     allureUtil.remove();
    }

    /**
    * Captures and attaches a screenshot to the Allure report
    * after each executed step.
    *
    * @param scenario the current Cucumber scenario
    */
    @AfterStep
    public void afterEachStep(Scenario scenario) {
     WebDriver drv = DriverManager.getDriver();
     if (drv == null) {
      return;
     }
     try {
      allureUtil.get().captureAndAttachScreenshot();
     } catch (Exception e) {
      // swallow to prevent CI flakiness
     }
    }
}
 