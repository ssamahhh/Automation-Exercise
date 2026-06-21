package com.Ecommercee.Pages;

import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PaymentPage {
    private final GUIDriver driver;
    public PaymentPage(GUIDriver driver) {
        this.driver=driver;
    }

    // Locators
    private final By nameOnCard = By.name("name_on_card");
    private final By cardNumber = By.name("card_number");
    private final By cvc = By.name("cvc");
    private final By expiryMonth = By.name("expiry_month");
    private final By expiryYear = By.name("expiry_year");
    private final By payAndConfirmOrderButton = By.id("submit");
    private final By orderPlacedMessage = By.xpath("//b[text()='Order Placed!']");

    // Actions

    public PaymentPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+"/payment");
        return this;
    }

    @Step("Enter name on card: {name}")
    public PaymentPage enterNameOnCard(String name) {
        driver.element().type(nameOnCard, name);
        return this;
    }

    @Step("Enter card number")
    public PaymentPage enterCardNumber(String number) {
        driver.element().type(cardNumber, number);
        return this;
    }

    @Step("Enter CVC")
    public PaymentPage enterCVC(String cvcValue) {
        driver.element().type(cvc, cvcValue);
        return this;
    }

    @Step("Enter expiry month")
    public PaymentPage enterExpiryMonth(String month) {
        driver.element().type(expiryMonth, month);
        return this;
    }

    @Step("Enter expiry year")
    public PaymentPage enterExpiryYear(String year) {
        driver.element().type(expiryYear, year);
        return this;
    }

    @Step("Fill payment details")
    public PaymentPage fillPaymentDetails(
            String name,
            String cardNum,
            String cvcValue,
            String month,
            String year) {

        enterNameOnCard(name);
        enterCardNumber(cardNum);
        enterCVC(cvcValue);
        enterExpiryMonth(month);
        enterExpiryYear(year);

        return this;
    }

    @Step("Click Pay and Confirm Order")
    public PaymentPage clickPayAndConfirmOrder() {
        driver.element().click(payAndConfirmOrderButton);
        return this;
    }

    @Step("Verify Order Placed message is visible")
    public PaymentPage verifyOrderPlacedMessageIsVisible() {
        driver.validate().isElementVisable(orderPlacedMessage);
        return this;
    }

    @Step("Get validation message for field")
    public String getValidationMessage(By locator) {
        return driver.element()
                .findElement(locator)
                .getAttribute("validationMessage");
    }
}
