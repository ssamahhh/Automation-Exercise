import com.Ecommercee.Pages.HomePage;
import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.dataReader.JsonReader;
import com.Ecommercee.utiles.dataReader.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeTests {
    protected GUIDriver driver;
    protected JsonReader testData=new JsonReader("home-data");

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
    @Tag("Subscription")
    @Story("Subscribe from home page and scroll to top")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify user can subscribe from home page, "
            + "see the success message, "
            + "and use the scroll up button to return to the top of the page.")
    public void verifySubscriptionAndScrollUpSuccessfully(){
        new HomePage(driver)
                .navigateHomePage()
                .verifyHomePageLogoIsVisible()
                .scrolltoSubscriptionLocator()
                .verifySubscriptionLocatorIsVisible()
                .addSubscriptionEmail(testData.getJsonData("email"))
                .clickSubscriptionArrowButton()
                .verifySubscriptionSuccessMessage(testData.getJsonData("message"))
                .pressScrollUpButton()
                .verifyHomePageLogoIsVisible();
    }

    @Test
    public void tc()
    {
        new HomePage(driver)
                .navigateHomePage()
                .scrolltoSubscriptionLocator()
                .ScrolltoHomePageLogo()
                .verifyHomePageLogoIsVisible();
    }
}
