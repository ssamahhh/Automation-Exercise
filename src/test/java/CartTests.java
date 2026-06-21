import com.Ecommercee.Pages.CartPage;
import com.Ecommercee.Pages.CheckoutPage;
import com.Ecommercee.Pages.ProductsPage;
import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.dataReader.JsonReader;
import com.Ecommercee.utiles.dataReader.PropertyReader;
import io.qameta.allure.*;
import org.testng.annotations.*;

public class CartTests {
    protected GUIDriver driver;
    protected JsonReader testData=new JsonReader("Cart-data");
    protected JsonReader PaymentData=new JsonReader("Payment-data");

    @BeforeClass
    public void setup() {
        PropertyReader.loadProperties();
        driver = new GUIDriver();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }

    @Test()
    @Severity(SeverityLevel.NORMAL)
    @Feature("Empty Cart")
    @Story("User navigates from empty cart to products page and adds a product")
    @Description("Verify user can navigate from empty cart to products page " +
            "using the 'here' link and add a product successfully")
    public void tc(){
        new CartPage(driver)
                .ViewCart()
                .clickHereLink()
                .verifyProductsListIsVisible()
                .openFirstProduct()
                .clickAddToCart()
                .clickViewCart()
                .verifyCartTableIsVisible();
    }

    @Test()
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Remove Products")
    @Story("User removes all products from cart")
    @Description("Verify user can remove all products from cart and the empty cart message is displayed")
    public void tc2(){
        new ProductsPage(driver)
                .navigate()
                .openFirstProduct()
                .clickAddToCart()
                .clickViewCart()
                .verifyCartTableIsVisible()
                .verifyProductsExistInCart()
                .deleteAllProducts()
                .verifyCartIsEmptyMessageVisible("message");
    }

    @Test()
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Checkout Flow")
    @Story("User proceeds from cart to checkout after login")
    @Description("Verify user can login from checkout flow and proceed to review order details")
    public void tc3(){
        new ProductsPage(driver)
                .navigate()
                .openFirstProduct()
                .clickAddToCart()
                .clickViewCart()
                .verifyCartTableIsVisible()
                .verifyProductsExistInCart()
                .clickProceedToCheckout()
                .clickRegisterLoginLink()
                .loginForm(testData.getJsonData("validEmail"),
                        testData.getJsonData("validPassword"))
                .clickLoginButton()
                .ClickonCartButton()
                .clickProceedToCheckout()
                .verifyAddressDetailsSectionIsVisible()
                .verifyReviewOrderSectionIsVisible();
    }

    @Test(dependsOnMethods = "tc3")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Payment Process")
    @Story("User completes order payment successfully")
    @Description("Verify user can place order and complete payment successfully")
    public void tc4(){
       new CheckoutPage(driver)
               .enterOrderComment("comment")
               .clickPlaceOrder()
               .fillPaymentDetails(PaymentData.getJsonData("nameOnCard"),
                       PaymentData.getJsonData("cardNumber"),
                       PaymentData.getJsonData("cvc"),
                       PaymentData.getJsonData("expiryMonth"),
                       PaymentData.getJsonData("expiryYear"))
               .clickPayAndConfirmOrder()
               .verifyOrderPlacedMessageIsVisible();
    }

}
