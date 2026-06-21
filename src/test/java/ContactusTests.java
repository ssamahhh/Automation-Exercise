import com.Ecommercee.Pages.ContactUsPage;
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

import java.io.File;

public class ContactusTests {
    protected GUIDriver driver;
    protected JsonReader testData=new JsonReader("contactus-data");

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
    @Severity(SeverityLevel.NORMAL)
    @Feature("Contact Us")
    @Story("User submits contact form successfully")
    @Description("Verify that user can submit the contact us form with valid data and receive a success confirmation message")
    public void tc(){
        new ContactUsPage(driver)
                .navigate()
                .isGetInTouchVisible()
                .fillForm(testData.getJsonData("name"),
                        testData.getJsonData("email"),
                        testData.getJsonData("subject"),
                        testData.getJsonData("message"))
                .chooseFile(testData.getJsonData("filePath"))
                .Submit()
                .SubmitAlrt()
                .isSuccessMessageVisible();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature("Contact Us")
    @Story("Email field validation")
    @Description("Verify that user cannot submit the contact form when email is empty and validation message is displayed")
    public void tc2(){
        new ContactUsPage(driver)
                .navigate()
                .isGetInTouchVisible()
                .fillForm(testData.getJsonData("name"),
                        testData.getJsonData(""),
                        testData.getJsonData("subject"),
                        testData.getJsonData("message"))
                .chooseFile(testData.getJsonData("filePath"))
                .Submit();
        String msg = new ContactUsPage(driver)
                .getValidationMessage(By.name("email"));

        Assert.assertTrue(msg.contains("fill out"));
    }
}
