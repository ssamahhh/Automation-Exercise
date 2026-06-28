package com.Ecommercee.Pages;

import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    private final GUIDriver driver;
    public ProductsPage(GUIDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By allProductsTitle = By.cssSelector(".title.text-center");
    private final By productsList = By.className("features_items");

    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By searchedProductsTitle = By.cssSelector(".title.text-center");

    private final By firstProductView = By.xpath("(//a[contains(text(),'View Product')])[1]");
    private final By secondProductView=By.xpath("(//a[contains(text(),'View Product')])[2]");

    // Brands
    private final By brandsSection = By.className("brands_products");

    // Add to cart popup
    private final By continueShoppingButton = By.cssSelector(".btn.btn-success.close-modal");
    private final By viewCartButton = By.xpath("//u[text()='View Cart']");


    //actions
    public ProductsPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+"/products");
        return this;
    }

    @Step("Verify All Products Page is visible")
    public ProductsPage verifyAllProductsPageIsVisible() {
          driver.validate().isElementVisable(allProductsTitle);
          return this;
    }

    @Step("Verify Products list is visible")
    public ProductsPage verifyProductsListIsVisible() {
         driver.validate().isElementVisable(productsList);
         return this;

    }

    @Step("Search for product: {productName}")
    public ProductsPage searchProduct(String productName) {
        driver.element().type(searchInput,productName);
        new P_AdPage(driver).handleAdIfPresent();
        driver.element().click(searchButton);
        return this;
    }

    @Step("Verify searched products are visible")
    public ProductsPage verifySearchedProductsVisible() {
        driver.validate().isElementVisable(searchedProductsTitle);
        return this;
    }


    @Step("Open first product details")
    public ProductDetailsPage openFirstProduct() {
        new P_AdPage(driver).handleAdIfPresent();
        driver.element().click(firstProductView);
        return new ProductDetailsPage(driver);
    }

    @Step("Add first product to cart")
    public ProductsPage addFirstProductToCart() {
        new P_AdPage(driver).handleAdIfPresent();
        driver.element().click(firstProductView);
        return this;
    }

    @Step("Click Continue Shopping")
    public ProductsPage clickContinueShopping() {
        new P_AdPage(driver).handleAdIfPresent();
        driver.element().click(continueShoppingButton);
        return this;
    }

    @Step("Click View Cart")
    public CartPage clickViewCart() {
        new P_AdPage(driver).handleAdIfPresent();
        driver.element().click(viewCartButton);
        return new CartPage(driver);
    }

    @Step("Verify Brands section is visible")
    public void verifyBrandsSectionVisible() {
        driver.validate().isElementVisable(brandsSection);
    }
}
