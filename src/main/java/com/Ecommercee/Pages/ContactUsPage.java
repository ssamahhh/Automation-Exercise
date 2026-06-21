package com.Ecommercee.Pages;

import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ContactUsPage {
    private final GUIDriver driver;
    public ContactUsPage(GUIDriver driver){
         this.driver=driver;
    }

    //locators
    private final By getInTouchTitle = By.xpath("//h2[text()='Get In Touch']");
    private final By nameInput = By.name("name");
    private final By emailInput = By.name("email");
    private final By subjectInput = By.name("subject");
    private final By messageTextArea = By.id("message");
    private final By uploadFileInput = By.name("upload_file");
    private final By submitBtn = By.name("submit");
    private final By successMessage = By.xpath("//div[contains(@class,'status')]");
    private final By HomeButton=By.cssSelector("a[class=\"btn btn-success\"]");

    //actions
    public ContactUsPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+"/contact_us");
        return this;
    }

    public ContactUsPage fillForm(String name,String email,String subject,String message){
        driver.element().type(nameInput,name);
        driver.element().type(emailInput,email);
        driver.element().type(subjectInput,subject);
        driver.element().type(messageTextArea,message);
        return this;
    }

    public ContactUsPage chooseFile(String Path){
        driver.element().uploadFile(uploadFileInput,Path);
        return this;
    }

    public ContactUsPage Submit(){
        driver.element().click(submitBtn);
        return this;
    }

    public ContactUsPage SubmitAlrt(){
        driver.alert().acceptAlert();
        return this;
    }

    public HomePage Home()
    {
        driver.element().click(HomeButton);
        return new HomePage(driver);
    }

    //validation
    public ContactUsPage isGetInTouchVisible() {
         driver.validate().isElementVisable(getInTouchTitle);
         return this;
    }

    public void isSuccessMessageVisible() {
        driver.validate().isElementVisable(successMessage);
    }

    public String getSuccessMessageText() {
        return driver.element().getText(successMessage);
    }

    @Step("Get validation message for field")
    public String getValidationMessage(By locator) {
         return driver.element()
                .findElement(locator)
                .getAttribute("validationMessage");

    }
}
