package com.cheq.demo_webshop.testrunner;

import io.cucumber.testng.*;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "classpath:feature/",
        glue = {
                "com.cheq.demo_webshop.stepdefinitions",
                "com.cheq.demo_webshop.hooks",
                "com.cheq.demo_webshop.listener"
        },
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.cheq.demo_webshop.listener.StepListener"
        },
        monochrome = true,
        tags = "@calc"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    private static final int MAX_RETRIES = 3;

    @Override
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        int attempt = 0;
        while (attempt <= MAX_RETRIES) {
            try {
                super.runScenario(pickleWrapper, featureWrapper);
                return; // ✅ Stop on first success
            } catch (Throwable t) {
                attempt++;

                if (attempt > MAX_RETRIES) {
                    throw t; // ❌ All retries failed
                }

                System.out.println("🔁 Retry attempt " + attempt +
                        " for scenario: " + pickleWrapper.getPickle().getName());
            }
        }
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        String threadCount = System.getProperty("dataproviderthreadcount", "4");
        System.setProperty("dataproviderthreadcount", threadCount);
        return super.scenarios();
    }
}
