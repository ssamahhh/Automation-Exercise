# Automation Exercise Test Automation Framework

## ITI Software Testing Track – Graduation Project (2026)

This project is a comprehensive UI Test Automation Framework developed for the Automation Exercise website.

The framework is built using Selenium WebDriver, Java, TestNG, and Maven, following the Page Object Model (POM) design pattern to create maintainable and reusable test automation scripts.

**Application Under Test:** https://automationexercise.com

---
## Project Deliverables

| Resource | Link |
|----------|------|
| 📊 Allure Report | [View Allure Report](https://drive.google.com/file/d/1wYjvSMaXH8DaEnQpP17_ov4kq6pwVW4B/view?usp=sharing) |
| 🎥 Test Execution Video | [Watch Execution Video][(https://your-video-link)](https://drive.google.com/file/d/1qZ8V1UYOFZDLDfCTJSKU_tjnHSe1jaaB/view?usp=sharing) |

---

## Test Coverage

The project contains **20 automated test cases** covering:

* User Registration
* Login & Logout
* Product Search
* Product Details
* Add to Cart
* Remove from Cart
* Checkout Process
* Place Order
* Payment Validation
* Contact Us Form
* Subscription Functionality
* Navigation Validation
* Scroll Up & Scroll Down

### Test Types

* Positive Test Cases
* Negative Test Cases
* End-to-End (E2E) Test Cases

---

## Technologies Used

* Java 24.0.1
* Selenium WebDriver
* TestNG
* Maven
* Allure Reports
* Log4j2
* Jackson (JSON)
* WebDriverManager

---

## Framework Features

* Page Object Model (POM)
* Data-Driven Testing using JSON
* Cross-Browser Support
* Factory Design Pattern
* Fluent Design Pattern
* Allure Reporting
* TestNG Listeners
* Screenshot Capture on Failure
* Reusable Utilities
* Configuration Management

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

---

## Allure Report

The framework generates detailed Allure Reports including:

* Test Execution Summary
* Passed / Failed Tests
* Screenshots on Failure
* Test Steps
* Execution Timeline
* Environment Information

To open the report:

```bash
allure serve allure-results
```

---

## How to Run

### Clone the Repository

```bash
git clone <repository-url>
```

### Navigate to the Project

```bash
cd AutomationExercise
```

### Install Dependencies

```bash
mvn clean install
```

### Run All Tests

```bash
mvn test
```

### Open Allure Report

```bash
allure serve allure-results
```
---

## What I Learned

Through this project, I gained experience in:

* Selenium WebDriver Automation
* TestNG Framework
* Maven Project Management
* Page Object Model (POM)
* Data-Driven Testing
* Allure Reporting
* Cross-Browser Testing
* Git & GitHub
* Automation Framework Design

---

## Author

**Samah Sameh**

