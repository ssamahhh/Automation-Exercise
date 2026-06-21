import com.Ecommercee.Pages.PaymentPage;
import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.dataReader.JsonReader;
import com.Ecommercee.utiles.dataReader.PropertyReader;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PaymentTests {
    protected GUIDriver driver;
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

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Payment Validation")
    @Story("User tries to submit payment with empty fields")
    @Description("Verify that user cannot submit payment when required fields are empty and validation message is displayed")
    public void payment_empty_fields() {

        new PaymentPage(driver)
                .navigate()
                .clickPayAndConfirmOrder();

        String msg = new PaymentPage(driver)
                .getValidationMessage(By.name("name_on_card"));

        Assert.assertTrue(msg.contains("fill out"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Payment Validation")
    @Story("User submits payment without CVC")
    @Description("Verify that system shows validation error when CVC field is empty")
    public void PaymentWithoutCvc(){
        new PaymentPage(driver)
                .navigate()
                .fillPaymentDetails(PaymentData.getJsonData("nameOnCard"),
                        PaymentData.getJsonData("cardNumber"),
                        PaymentData.getJsonData(""),
                        PaymentData.getJsonData("expiryMonth"),
                        PaymentData.getJsonData("expiryYear"))
                .clickPayAndConfirmOrder();

        String msg = new PaymentPage(driver)
                .getValidationMessage(By.name("cvc"));

        Assert.assertTrue(msg.contains("fill out"));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Successful Payment")
    @Story("User completes valid payment successfully")
    @Description("Verify that user can successfully place an order using valid payment details")
    public void ValidPaymentTc(){
        new PaymentPage(driver)
                .navigate()
                .fillPaymentDetails(PaymentData.getJsonData("nameOnCard"),
                        PaymentData.getJsonData("cardNumber"),
                        PaymentData.getJsonData("cvc"),
                        PaymentData.getJsonData("expiryMonth"),
                        PaymentData.getJsonData("expiryYear"))
                .clickPayAndConfirmOrder()
                .verifyOrderPlacedMessageIsVisible();

    }
}
