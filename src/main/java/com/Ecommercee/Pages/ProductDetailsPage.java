package com.Ecommercee.Pages;

import com.Ecommercee.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {

    private final GUIDriver driver;

    public ProductDetailsPage(GUIDriver driver) {
        this.driver = driver;
    }

    //locator
    private final By productName = By.xpath("//div[@class='product-information']/h2");
    private final By productCategory = By.xpath("//div[@class='product-information']/p[1]");
    private final By productPrice = By.xpath("//div[@class='product-information']/span/span");
    private final By productAvailability = By.xpath("//div[@class='product-information']/p[2]");
    private final By productCondition = By.xpath("//div[@class='product-information']/p[3]");
    private final By productBrand = By.xpath("//div[@class='product-information']/p[4]");

    private final By quantityInput = By.id("quantity");
    private final By addToCartButton = By.cssSelector("button.cart");

    private final By viewCartButton = By.xpath("//u[text()='View Cart']");
    private final By continueShoppingButton = By.cssSelector(".btn.btn-success.close-modal");

    private final By writeReviewTab = By.cssSelector("a[href='#reviews']");
    private final By NameforReview=By.id("name");
    private final By EmailforReview=By.id("email");
    private final By ReviewTextArea=By.id("review");
    private final By SubmitReviewButton=By.id("button-review");
    private final By successMessage = By.xpath("//div[@id='review-section']//span");

    //actions
    @Step("Verify product name is visible")
    public void verifyProductNameVisible() {
        driver.validate().isElementVisable(productName);
    }


    @Step("Verify product details section is visible")
    public ProductDetailsPage verifyProductDetailsVisible() {
        driver.validate().isElementVisable(productPrice);
        driver.validate().isElementVisable(productCategory);
        driver.validate().isElementVisable(productAvailability);
        driver.validate().isElementVisable(productCondition);
        driver.validate().isElementVisable(productBrand);
        return this;
    }


    @Step("Change product quantity to: {quantity}")
    public ProductDetailsPage setQuantity(String quantity) {
        driver.element().type(quantityInput,quantity);
        return this;
    }

    @Step("Click Add To Cart button")
    public ProductDetailsPage clickAddToCart() {
        driver.element().click(addToCartButton);
        return this;
    }

    @Step("Click Continue Shopping")
    public ProductDetailsPage clickContinueShopping() {
        driver.element().click(continueShoppingButton);
        return this;
    }

    @Step("Click View Cart")
    public CartPage clickViewCart() {
        driver.element().click(viewCartButton);
        return new CartPage(driver);
    }

    @Step("verify Review tab is visible")
    public ProductDetailsPage verifyReviewTabVisible() {
        driver.element().scrollToElement(writeReviewTab);
        driver.validate().isElementVisable(writeReviewTab);
        return this;
    }

    @Step("Fill review form with name: {name}, email: {email} and review: {review}")
    public ProductDetailsPage fillReviewForm(String name, String email, String review) {
        driver.element().type(NameforReview,name);
        driver.element().type(EmailforReview,email);
        driver.element().type(ReviewTextArea,review);
        return this;
    }

    public ProductDetailsPage submitReview() {
        driver.element().click(SubmitReviewButton);
        return this;
    }

    public void isReviewSuccessVisible()
    {
        driver.validate().isElementVisable(successMessage);
    }
}

