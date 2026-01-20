# Cucumber Java Automation Framework


## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Folder Structure](#folder-structure)
- [Running Tests](#running-tests)
- [Reporting and Logs](#reporting-and-logs)
 
## Introduction
Cucumber is a widely-used open-source testing framework that supports Behavior-Driven Development (BDD), enabling teams to write test cases in plain English using the Gherkin syntax. This approach fosters collaboration among developers, testers, and business stakeholders by making test scenarios understandable to all parties involved.


 
## Key Components of Cucumber in Java
- **Feature Files: Contain scenarios written in Gherkin syntax to describe application behavior.​**
- **Step Definitions: Java methods that implement the steps outlined in the feature files.​s**
- **Test Runner: A class that uses frameworks like JUnit or TestNG to execute the tests.​**
- **Gherkin Language: A domain-specific language that uses a set of keywords to define test cases in a human-readable format.**


 
## Installation
 **Prerequisites:**
 1. **Install Java SE 8 or higher**  
    - Download and install Java SE 22.0.2.
 2. **Verify installations**  
    - Run the following commands in the terminal to confirm that Java is successfully installed:
     ```
    java -version
     ```
 3. **Instal Apache Maven** 
    - Apache Maven 3.9.9
 4. **Verify installations**  
    - Run the following commands in the terminal to confirm that Apache Maven is successfully installed:
    ```
    mvn -v
    ```
 5. **Install Eclipse IDE** 
     - Download and install Eclipse.
 6. **Verify installations**  
    - Search and Open Eclipse
 7. **Export Automation Framework**
    1. Download and extract the zip file from this link: https://apsiph-my.sharepoint.com/:f:/r/personal/ramos_natasha_amicassa_com_ph/Documents/Sync%20Folder/desktop/Desktop/Cheq/Seller%27s%20Portal/06%20-%20Automation%20Framework/Sellers%20Portal%20Selenium-Cucumber%20Web/Default%20Framework?csf=1&web=1&e=FbsSDX
    2. Eclipse is opened then click file on the menu -> click Open Projects from file system.
    3. Click the Directory button and select the path of the framework then Open
    4. Click Finish button
 8. **Install Cucumber and TestNG Plugin at Eclipse**
    - Go to Marketplace on eclipse and search for Cucumber and TestNG plugin then install.


**Configuration:**
```
· Config.properties: Use .properties files to manage different environment URL, Bwroser and Test Data Path.
```


## Folder Structure
  ```
Main Folder Directory
└── src/test/java
    ├── com.cheq.demo_webshop.factory              # Contains driver factory for browser initialization and element factories
    │                                              # such as DropdownFactory, RadioButtonFactory, and TextLabelFactory
    │                                              # to manage creation of element strategies
    ├── com.cheq.demo_webshop.hooks                # Contains setup and teardown hooks (e.g., @BeforeAll, @AfterEach)
    ├── com.cheq.demo_webshop.listeners            # Includes TestNG/Cucumber listeners for logging, reporting, and event handling
    ├── com.cheq.demo_webshop.manager              # Manages common resources like driver manager or configuration manager
    ├── com.cheq.demo_webshop.pages                # Page Object Models (POM) representing application pages
    │   └── components                             # Reusable UI components (e.g., header, footer, navigation menu)
    ├── com.cheq.demo_webshop.stepdefinitions      # Cucumber step definitions linking Gherkin steps to code logic
    ├── com.cheq.demo_webshop.Strategy             # Strategy design pattern implementation for UI elements
    │   ├── DropdownFactory                        # Strategies for handling dropdown elements
    │   ├── radio_button                           # Strategies for interacting with radio buttons
    │   └── text_label                             # Strategies for verifying or reading text labels
    ├── com.cheq.demo_webshop.testrunner           # Cucumber or TestNG test runners (entry point for executing tests)
    └── com.cheq.demo_webshop.utils                # Utility classes for common helpers (e.g., file reader, wait utils, logger)
└── src/test/resources
    ├── config                                     # Config files (e.g., environment settings, browser config)
    ├── feature                                    # Cucumber .feature files containing Gherkin scenarios
    └── testdata                                   # JSON file containing parameterized test data
                                                   # Used for data-driven testing and dynamic input retrieval
├──logs                                            # Log files generated during test execution
├──pom.xml                                         # Maven Project Object Model (POM) – manages dependencies and build configuration
├── README.md                                      # Project documentation providing an overview and setup instructions.
└── testng.xml                                     # TestNG configuration file for defining test suites and parallel execution settings.
  ```


## Running Tests on Terminal

Open the Command Prompt, navigate to the project directory, and execute the following command:

To run a specific test tags, use the following command:
```
mvn clean test -Denv=dev -Dbrowser=chrome "-Dcucumber.filter.tags=@multipleproducts" -Ddataproviderthreadcount=1 -Dheadless=true"
```

## Running Tests on IDE Eclipse
Open your project in Eclipse.

- **Navigate to:**
src/test/java/com/cheq/demo_webshop/testrunner/TestRunner.java
- Before executing, make sure to update the tags inside @CucumberOptions to match the scenarios you intend to run.
For example, update **tags="@multipleproducts"** to another tag if needed, depending on the scenarios you want to execute.
- After updating the tag, right-click on the TestRunner class → Select Run **'TestRunner'**.
- This will automatically run all scenarios that match the specified tag and feature file(s) defined in the runner configuration.


## Reporting

Open the terminal to generate an allure report, use the following command:
```
allure generate target/allure-results --single-file --output target/allure-report
```

- **Navigate to Project folder:** /target/allure-report/
- Right-click on the index.html → Select Open with (Any browsers). This will open the allure report on browser.

