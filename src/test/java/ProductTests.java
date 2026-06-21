import com.Ecommercee.Pages.HomePage;
import com.Ecommercee.Pages.ProductsPage;
import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.dataReader.JsonReader;
import com.Ecommercee.utiles.dataReader.PropertyReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTests {
    protected GUIDriver driver;
    protected JsonReader testData=new JsonReader("Product-data");
    @BeforeMethod
    public void setup() {
        PropertyReader.loadProperties();
        driver = new GUIDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Products Page")
    @Story("View All Products")
    @Description("Verify that user can navigate to Products page and view all available products")
    public void tc1(){
        new HomePage(driver)
                .navigateHomePage()
                .clickonProductsButton()
                .verifyAllProductsPageIsVisible()
                .verifyProductsListIsVisible();
    }

    @Test()
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Add Product To Cart")
    @Story("User adds a product with specific quantity and verifies it exists in cart")
    @Description("Verify user can open product details, " +
            "update quantity, add product to cart, and view it in cart successfully")
    public void tc2(){
        new ProductsPage(driver)
                .navigate()
                .openFirstProduct()
                .verifyProductDetailsVisible()
                .setQuantity(testData.getJsonData("Quantity"))
                .clickAddToCart()
                .clickViewCart()
                .verifyCartTableIsVisible()
                .verifyProductsExistInCart();
    }

    @Test()
    @Severity(SeverityLevel.NORMAL)
    @Feature("Search Product")
    @Story("User searches for a product and opens its details page")
    @Description("Verify user can search for a product and navigate to its details page")
    public void tc3()
    {
        new ProductsPage(driver)
                .navigate()
                .searchProduct(testData.getJsonData("searchProduct"))
                .verifySearchedProductsVisible()
                .openFirstProduct()
                .verifyProductDetailsVisible();
    }


    @Test()
    @Severity(SeverityLevel.NORMAL)
    @Feature("Product Review")
    @Story("User submits a review for a product")
    @Description("Verify user can submit a review and receive a success confirmation message")
    public void tc4(){
        new ProductsPage(driver)
                .navigate()
                .openFirstProduct()
                .verifyReviewTabVisible()
                .fillReviewForm(testData.getJsonData("name"),
                        testData.getJsonData("email"),
                        testData.getJsonData("review"))
                .submitReview()
                .isReviewSuccessVisible();
    }

}
