package com.Ecommercee.Pages;

import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage {
    private final GUIDriver driver;
    public CartPage(GUIDriver driver) {
        this.driver=driver;
    }

    //locator
    private final By cartTable          = By.id("cart_info_table");
    private final By cartRows           = By.cssSelector("#cart_info_table tbody tr");
    private final By proceedToCheckout  = By.cssSelector(".btn.btn-default.check_out");
    private final By emptyCartText = By.xpath("//b[text()='Cart is empty!']");
    private final By hereLink = By.xpath("//a[@href='/products']");
    private final By deleteButtons = By.cssSelector("a.cart_quantity_delete");
    //actions
    @Step("Navigate to View cart Page")
    public CartPage ViewCart(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+"/view_cart");
        return this;
    }


    @Step("Verify Cart Table is Visible")
    public CartPage verifyCartTableIsVisible() {
        driver.validate().isElementVisable(cartTable);
        return this;
    }

    @Step("Verify Products Exist In Cart")
    public CartPage verifyProductsExistInCart() {
        driver.validate().isElementVisable(cartRows);
        return this;
    }

    @Step("Click Proceed To Checkout")
    public CheckoutPage clickProceedToCheckout() {
        driver.element().click(proceedToCheckout);
        return new CheckoutPage(driver);
    }

    @Step("Verify cart is empty message is visible")
    public CartPage verifyCartIsEmptyMessageVisible(String expected) {
        String actual=driver.element().getText(emptyCartText);
        driver.validate().Equals(actual ,expected,"");
        return this;
    }

    @Step("Click here link to navigate to products page")
    public ProductsPage clickHereLink() {
        driver.element().click(hereLink);
        return new ProductsPage(driver);
    }

    @Step("Delete all products from cart")
    public CartPage deleteAllProducts() {
        driver.element().click(deleteButtons);
        return this;
    }

}
