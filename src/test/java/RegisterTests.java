import com.Ecommercee.Pages.HomePage;
import com.Ecommercee.Pages.SignupPage;
import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.TimeManager;
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

public class RegisterTests {
    protected GUIDriver driver;
    protected JsonReader testData=new JsonReader("sign-up");

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
    @Tag("New User")
    @Story("User navigates to the login/signup page, " +
            "enters a new name and email, signs up, " +
            "fills account details, verifies account creation, " +
            "and continues to home page.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that a user can successfully sign up with valid new data and create an account.")
    public void newUser(){
        new SignupPage(driver)
                .navigate()
                .SignupForm (testData.getJsonData("name")
                ,testData.getJsonData("email") +TimeManager.getTimeStamp()+"@gmail.com")
                .ClickSignupButton()
                .AccountInfo()
                .FillDetails(testData.getJsonData("titleMale")
                ,testData.getJsonData("password")
                ,testData.getJsonData("day")
                ,testData.getJsonData("month")
                ,testData.getJsonData("year")
                ,testData.getJsonData("firstName")
                ,testData.getJsonData("lastName")
                ,testData.getJsonData("company")
                ,testData.getJsonData("address1")
                ,testData.getJsonData("address2")
                ,testData.getJsonData("country")
                , testData.getJsonData("state")
                ,testData.getJsonData("city")
                ,testData.getJsonData("zipcode")
                ,testData.getJsonData("mobilePhone"))
                .ClickCreateAccountButton()
                .verifyAccountCreated(testData.getJsonData("messages.accountCreatedMsg"))
                .ClickContinueButton();
    }


    @Test
    @Tag("Existing User")
    @Story("User navigates to the login/signup page," +
            " enters an already registered email address," +
            " clicks Signup, and verifies that the appropriate error message is displayed.")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that the system prevents registration with an " +
            "already existing email address and displays the correct error message.")
    public void existUser(){
        new SignupPage(driver)
                .navigate()
                .SignupForm(testData.getJsonData("name")
                ,testData.getJsonData("existemail"))
                .ClickSignupButton()
                .verifySignupErrorMsg("messages.error");
    }
}
