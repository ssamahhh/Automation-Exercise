package com.Ecommercee.Pages;

import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final GUIDriver driver;

    public HomePage(GUIDriver driver) {
        this.driver = driver;
    }

    //locators

    private final By homePageLogolocator = By.cssSelector(".logo.pull-left");
    //header
    private final By signUpLoginButtonlocator = By.cssSelector("a[href='/login']");
    private final By productsButtonlocator = By.cssSelector("a[href='/products']");
    private final By cartButtonlocator = By.xpath("//*[normalize-space(text())='Cart']");
    private final By contactUsButtonlocator = By.cssSelector("a[href='/contact_us']");
    private final By testCasesButtonlocator = By.xpath("//a[normalize-space(text())='Test Cases']");


    private final By homePageCategorylocator = By.xpath("/html/body/section[2]/div/div/div[1]/div/h2");
    private final By ScrollUpButton = By.cssSelector("[id=\"scrollUp\"]");
    //women category
    private final By homePageCategoryWomenlocator = By.xpath("//div[@id='accordian']//a[contains(.,'Women')]");
    private final By HomePageCategoryWomenDresslocator = By.cssSelector("a[href='/category_products/1']");
    private final By HomePageCategoryWomenDressMsglocator = By.cssSelector("h2.title.text-center");

    //men category
    private final By homePageCategoryMenlocator = By.xpath("//div[@id='accordian']//a[contains(.,'Men')]");
    private final By getHomePageCategoryMenTshirtlocator = By.cssSelector("a[href='/category_products/3']");
    private final By HomePageCategoryMenTshirtMsglocator = By.cssSelector("h2.title.text-center");

    //kids Category
    private final By homePageCategoryKidslocator = By.xpath("//div[@id='accordian']//a[contains(.,'Kids')]");
    private final By getHomePageCategoryKidsDresslocator = By.cssSelector("a[href='/category_products/4']");
    private final By HomePageCategoryKidsDressMsglocator = By.cssSelector("h2.title.text-center");

    //subscribtion
    private static final By homePageSubscriptionLocator = By.xpath("//h2[text()='Subscription']");
    private static final By homePageSubscriptionEmailLocator = By.xpath("//input[@id='susbscribe_email']");
    private static final By homePageSubscriptionArrowButtonLocator = By.xpath("//button[@id='subscribe']");
    private static final By homePageSubscriptionMsglocator = By.xpath("//*[normalize-space(text())='You have been successfully subscribed!']");


    //actions
    @Step("Navigate to home page")
    public HomePage navigateHomePage() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    public ProductsPage navigatetoProductPage(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+"/products");
        return new ProductsPage(driver);
    }


    @Step("click on home page")
    public HomePage clickonHomePage() {
        driver.element().click(homePageLogolocator);
        return this;
    }

    @Step("click on signup login button")
    public LoginPage ClickonSignupLoginButton() {
        driver.element().click(signUpLoginButtonlocator);
        return new LoginPage(driver);
    }

    @Step("click on products button")
    public ProductsPage clickonProductsButton() {
        driver.element().click(productsButtonlocator);
        return new ProductsPage(driver);
    }

    @Step("click on cart button")
    public CartPage ClickonCartButton() {
        driver.element().click(cartButtonlocator);
        return new CartPage(driver);
    }

    @Step("click on contact us button")
    public ContactUsPage ClickonContactUsButton() {
        driver.element().click(contactUsButtonlocator);
        return new ContactUsPage(driver);
    }

    @Step("click on test cases button")
    public TestCasesPage ClickonTestCasesButton() {
        driver.element().click(testCasesButtonlocator);
        return new TestCasesPage(driver);
    }

    @Step("Scroll to subscription locator")
    public HomePage scrolltoSubscriptionLocator() {
        driver.element().scrollToElement(homePageSubscriptionLocator);
        return this;
    }

    @Step("verify subscription locator is visible")
    public HomePage verifySubscriptionLocatorIsVisible() {
        driver.validate().isElementVisable(homePageSubscriptionLocator);
        return this;
    }


    @Step("add subscribtion email")
    public HomePage addSubscriptionEmail(String email) {
        driver.element().findElement(homePageSubscriptionEmailLocator).sendKeys(email);
        return this;
    }

    @Step("click on subscription arrow button")
    public HomePage clickSubscriptionArrowButton() {
        driver.element().click(homePageSubscriptionArrowButtonLocator);
        return this;
    }

    @Step("verify subscription success message")
    public HomePage verifySubscriptionSuccessMessage(String expectedMessage) {
        String actual = driver.element().getText(homePageSubscriptionMsglocator);
        driver.validate().Equals(actual,expectedMessage,"Subscription success message validation");
        return this;
    }

    @Step("Verify home page logo is visible")
    public HomePage verifyHomePageLogoIsVisible() {
        driver.validate().isElementVisable(homePageLogolocator);
        return this;
    }

    @Step("Verify category section is visible")
    public HomePage verifyCategorySectionIsVisible() {
        driver.validate().isElementVisable(homePageCategorylocator);
        return this;
    }

    @Step("Click on Women category")
    public HomePage clickOnWomenCategory() {
        driver.element().click(homePageCategoryWomenlocator);
        return this;
    }

    @Step("Click on Women Dress category")
    public HomePage clickOnWomenDressCategory() {
        driver.element().click(HomePageCategoryWomenDresslocator);
        return this;
    }

    @Step("Verify Women Dress products page")
    public HomePage verifyWomenDressProductsPage(String expectedMessage) {
        String actual=driver.element().getText(HomePageCategoryWomenDressMsglocator);
        driver.validate().Equals(actual,expectedMessage,"");
        return this;
    }

    @Step("Click on Men category")
    public HomePage clickOnMenCategory() {
        driver.element().click(homePageCategoryMenlocator);
        return this;
    }

    @Step("Click on Men T-Shirt category")
    public HomePage clickOnMenTshirtCategory() {
        driver.element().click(getHomePageCategoryMenTshirtlocator);
        return this;
    }

    @Step("Verify Men T-Shirt products page")
    public HomePage verifyMenTshirtProductsPage(String expectedMessage) {
        String actual=driver.element().getText(HomePageCategoryMenTshirtMsglocator);
        driver.validate().Equals(actual,expectedMessage,"");
        return this;
    }

    @Step("Click on Kids category")
    public HomePage clickOnKidsCategory() {
        driver.element().click(homePageCategoryKidslocator);
        return this;
    }

    @Step("Click on Kids Dress category")
    public HomePage clickOnKidsDressCategory() {
        driver.element().click(getHomePageCategoryKidsDresslocator);
        return this;
    }

    @Step("Verify Kids Dress products page")
    public HomePage verifyKidsDressProductsPage(String expectedMessage) {
        String actual=driver.element().getText(HomePageCategoryKidsDressMsglocator);
        driver.validate().Equals(actual,expectedMessage,"");
        return this;
    }

    @Step("click in scroll up button")
    public HomePage pressScrollUpButton() {
        driver.element().click(ScrollUpButton);
        return this;
    }

    public HomePage ScrolltoHomePageLogo()
    {
        driver.element().scrollToElement(homePageLogolocator);
        return this;
    }

}
