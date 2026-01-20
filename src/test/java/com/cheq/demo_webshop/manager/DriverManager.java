package com.cheq.demo_webshop.manager;

import org.openqa.selenium.WebDriver;

/**
 * Manages WebDriver instances using ThreadLocal to support parallel execution.
 */
public class DriverManager {

    // ThreadLocal ensures each thread (test) gets its own WebDriver instance
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void unload() {
        driver.remove();
    }
}

