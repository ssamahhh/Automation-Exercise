package com.Ecommercee.Pages;

import com.Ecommercee.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutPage {
    private final GUIDriver driver;
    public CheckoutPage(GUIDriver driver){
        this.driver=driver;
    }

    // Locators
    private final By addressDetailsTitle = By.xpath("//h2[contains(text(),'Address Details')]");

    private final By reviewOrderTitle = By.xpath("//h2[contains(text(),'Review Your Order')]");
    private final By registerLoginLink  = By.xpath("//u[text()='Register / Login']");
    private final By commentTextArea = By.name("message");

    private final By placeOrderButton = By.xpath("//a[contains(text(),'Place Order')]");

    // Actions
    @Step("Click Register/Login Link")
    public LoginPage clickRegisterLoginLink() {
        new P_AdPage(driver).handleAdIfPresent();
        driver.element().click(registerLoginLink);
        return new LoginPage(driver);
    }

    @Step("Verify Address Details section is visible")
    public CheckoutPage verifyAddressDetailsSectionIsVisible() {
        driver.validate().isElementVisable(addressDetailsTitle);
        return this;
    }

    @Step("Verify Review Your Order section is visible")
    public CheckoutPage verifyReviewOrderSectionIsVisible() {
        driver.validate().isElementVisable(reviewOrderTitle);
        return this;
    }

    @Step("Enter order comment: {comment}")
    public CheckoutPage enterOrderComment(String comment) {
        driver.element().type(commentTextArea, comment);
        return this;
    }

    @Step("Click Place Order")
    public PaymentPage clickPlaceOrder() {
        new P_AdPage(driver).handleAdIfPresent();
        driver.element().click(placeOrderButton);
        return new PaymentPage(driver);
    }
}
