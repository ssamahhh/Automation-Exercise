# Automation Exercise Test Automation Framework

## Overview

This project is a comprehensive UI Test Automation Framework developed for the Automation Exercise website.

The framework automates end-to-end user workflows and validates critical functionalities using modern automation testing practices, including Page Object Model (POM), data-driven testing, reporting, and reusable utilities.

**Application Under Test:** https://automationexercise.com

---

## Features

### Automated Test Coverage

The framework contains **20 automated test cases** covering:

* User Registration
* User Login & Logout
* Product Search
* Product Details Verification
* Add Products to Cart
* Remove Products from Cart
* Subscription Functionality
* Contact Us Form
* Navigation Validation
* Scroll Up & Scroll Down Actions
* Checkout Process
* Place Order Workflow
* Payment Validation

### Test Types

* Positive Test Scenarios
* Negative Test Scenarios
* End-to-End (E2E) Test Scenarios

---

## Technology Stack

* Java 17
* Selenium WebDriver
* TestNG
* Maven
* Allure Reports
* WebDriver Manager
* Jackson (JSON Data Handling)
* Log4j2
* Page Object Model (POM)

---

## Project Structure

```text

Automation-Exercise
│
├── src
│   ├── main
│   │   ├── java/com/Ecommercee
│   │   │
│   │   ├── Pages
│   │   │   ├── HomePage.java
│   │   │   ├── LoginPage.java
│   │   │   ├── SignupPage.java
│   │   │   ├── ProductsPage.java
│   │   │   ├── ProductDetailsPage.java
│   │   │   ├── CartPage.java
│   │   │   ├── CheckoutPage.java
│   │   │   ├── PaymentPage.java
│   │   │   ├── ContactUsPage.java
│   │   │   ├── TestCasesPage.java
│   │   │   └── P_AdPage.java
│   │   │
│   │   ├── drivers
│   │   │   ├── AbstractDriver.java
│   │   │   ├── GUIDriver.java
│   │   │   ├── WebDriverProvider.java
│   │   │   ├── BrowserType.java
│   │   │   ├── chromeFactory.java
│   │   │   ├── FirefoxFactory.java
│   │   │   ├── EdgeFactory.java
│   │   │   └── SafariFactory.java
│   │   │
│   │   ├── listeners
│   │   │   └── TestNGListeners.java
│   │   │
│   │   └── utiles
│   │       ├── actions
│   │       ├── Validations
│   │       ├── allurereport
│   │       ├── dataReader
│   │       ├── logs
│   │       ├── media
│   │       ├── FilesUtils.java
│   │       ├── OSUtils.java
│   │       ├── TerminalUtils.java
│   │       ├── TimeManager.java
│   │       └── WaitManager.java
│   │
│   └── resources
│       ├── META-INF.services
│       ├── allure.properties
│       ├── environment.properties
│       ├── webApp.properties
│       ├── seleniumGrid.properties
│       ├── waits.properties
│       ├── vedio.properties
│       ├── db.properties
│       └── log4j2.properties
│
├── src/test
│   ├── java
│   │   ├── HomeTests.java
│   │   ├── LoginTests.java
│   │   ├── RegisterTests.java
│   │   ├── ProductTests.java
│   │   ├── CartTests.java
│   │   ├── PaymentTests.java
│   │   └── ContactusTests.java
│   │
│   └── resources
│       ├── test-data
│       │   ├── sign-up.json
│       │   ├── login-data.json
│       │   ├── home-data.json
│       │   ├── Product-data.json
│       │   ├── Cart-data.json
│       │   ├── Payment-data.json
│       │   └── contactus-data.json
│       │
│       ├── testfile.txt
│       ├── cat.jpg
│       └── config.properties
│
├── test-output
│
├── testng.xml
├── pom.xml
├── .gitignore
└── README.md
```

### Framework Architecture

* **Page Object Model (POM)** for maintainable test design.
* **Factory Design Pattern** for browser initialization.
* **Fluent Interface Design** for readable test steps.
* **Data-Driven Testing** using JSON files.
* **Cross-Browser Testing** (Chrome, Firefox, Edge, Safari).
* **Allure Reporting** with screenshots, logs, and execution details.
* **TestNG Listeners** for reporting and test lifecycle management.
* **Reusable Utilities** for waits, validations, file handling, logging, and media capture.
* **Configuration Management** through property files.

---

## Reporting

### Allure Report

The framework generates detailed Allure Reports including:

* Test execution summary
* Passed / Failed test cases
* Screenshots on failure
* Execution timeline
* Test steps
* Environment information

### View Report

```bash
allure serve allure-results
```

---

## Installation

### Clone Repository

```bash
git clone <repository-url>
```

### Navigate to Project

```bash
cd AutomationExercise
```

### Install Dependencies

```bash
mvn clean install
```

---

## Running Tests

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=TestClassName
```

### Generate Allure Results

```bash
mvn clean test
```

### Open Allure Report

```bash
allure serve allure-results
```
---

## Framework Highlights

* Scalable Architecture
* Reusable Components
* Easy Maintenance
* Detailed Reporting
* Clean Code Practices
* Industry Standard Design Patterns
* Cross-Browser Ready Structure

---

## Author

**Samah Sameh**

Software Testing Engineer
