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

public class Hooks {
    private AllureUtil allureUtil;
    private static final Logger logger = LoggerUtil.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) throws IOException {
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

        // Initialize Allure utility for reporting
        allureUtil = new AllureUtil(driver);

        // Write environment details to Allure report
        allureUtil.writeAllureEnvironment(
            ImmutableMap.<String, String>builder()
                .put("OS", System.getProperty("os.name"))
                .put("Browser", browser)
                .put("Environment", env)
                .build()
        );
        logger.info("Starting scenario: " + scenario.getName());
    }

    /**
     * Quits the WebDriver after each scenario to clean up resources.
     *
     * @param scenario the current Cucumber scenario
     */
    @After(order = 0)
    public void tearDown() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
            DriverManager.unload();
        }
    }

    /**
     * Captures and attaches a screenshot to the Allure report if the scenario fails.
     *
     * @param scenario the current Cucumber scenario
     */
    @After(order = 1)
    public void captureFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            allureUtil.captureAndAttachScreenshot();
        }
    }

    /**
     * Captures and attaches a screenshot to the Allure report after each step.
     *
     * @param scenario the current Cucumber scenario
     */
    @AfterStep 
    public void afterEachStep(Scenario scenario) {
        allureUtil.captureAndAttachScreenshot();
    }

}
 