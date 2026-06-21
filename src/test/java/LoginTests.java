import com.Ecommercee.Pages.LoginPage;
import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.dataReader.JsonReader;
import com.Ecommercee.utiles.dataReader.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests {
    protected GUIDriver driver;
    protected JsonReader testData=new JsonReader("login-data");

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
    @Tag("Login")
    @Tag("Positive")
    @Story( "Verify that a registered user can log in with valid email and password," +
            " see the logged-in username, " +
            "and log out successfully.")
    @Severity(SeverityLevel.BLOCKER)
    @Description("User logs in successfully using valid credentials and logs out.")
    public void validLogin(){
        new LoginPage(driver)
                .navigate()
                .loginForm(testData.getJsonData("validEmail"),
                        testData.getJsonData("validPassword"))
                .clickLoginButton()
                .vrefiyLoggedSuccess(testData.getJsonData("messages"))
                .clickLogoutButton();
    }

    @Test
    @Tag("Login")
    @Tag("Negative")
    @Story("User attempts to log in using an invalid email address.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an error message is displayed when a user attempts to log in with an invalid email address.")
    public void invalidEmailLogin(){
        new LoginPage(driver)
                .navigate()
                .loginForm(testData.getJsonData("invalidEmail"),
                        testData.getJsonData("validPassword"))
                .clickLoginButton()
                .verifyErrorMsg(testData.getJsonData("invalidEmailMsg"));
    }

    @Test
    @Tag("Login")
    @Tag("Negative")
    @Story("User attempts to login without entering email and password.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user cannot log in when the email and password fields are left empty and remains on the login page.")
    public void loginWithEmptyFields(){
        new LoginPage(driver)
                .navigate()
                .clickLoginButton();
        String currentUrl=driver.browser().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"),
                "User should remain on login/signup page when name is empty");
    }
}
