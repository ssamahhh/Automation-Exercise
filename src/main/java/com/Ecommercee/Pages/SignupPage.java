package com.Ecommercee.Pages;

import com.Ecommercee.drivers.GUIDriver;
import com.Ecommercee.utiles.dataReader.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage {

    private final GUIDriver driver;

    public SignupPage(GUIDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By signUpLoginButton = By.cssSelector("a[href='/login']");
    private final By nameLocator=By.cssSelector("[data-qa='signup-name']");
    private final By emailLocator=By.cssSelector("[data-qa='signup-email']");
    private final By passwordLocator=By.cssSelector("[data-qa='password']");
    private final By dayOfBirthLocator=By.cssSelector("[data-qa='days']");
    private final By monthOfBirthLocator=By.cssSelector("[data-qa='months']");
    private final By yearOfBirthLocator=By.cssSelector("[data-qa='years']");
    private final By newsletterLocator=By.id("newsletter");
    private final By offersLocator=By.id("optin");
    private final By firstNameLocator=By.cssSelector("[data-qa='first_name']");
    private final By lastNameLocator=By.cssSelector("[data-qa='last_name']");
    private final By companyLocator=By.cssSelector("[data-qa='company']");
    private final By addressOneLocator=By.cssSelector("[data-qa='address']");
    private final By addressTwoLocator=By.cssSelector("[data-qa='address2']");
    private final By countryLocator=By.cssSelector("[data-qa='country']");
    private final By stateLocator=By.cssSelector("[data-qa='state']");
    private final By cityLocator=By.cssSelector("[data-qa='city']");
    private final By zipcodeLocator=By.cssSelector("[data-qa='zipcode']");
    private final By mobileNumberLocator=By.cssSelector("[data-qa='mobile_number']");
    private final By createAccountButton=By.cssSelector("[data-qa='create-account']");
    private final By accountCreated = By.cssSelector("[data-qa=\"account-created\"]");
    private final By continueButton = By.cssSelector("[data-qa=\"continue-button\"]");
    private final By genderMrRadioButton = By.id("id_gender1");
    private final By genderMrsRadioButton = By.id("id_gender2");
    private final By signUpButton=By.cssSelector("[data-qa='signup-button']");
    private final By SignupErrorMsg = By.xpath("//p[contains(text(),'Email Address already exist!')]");
    private final By NameaccountInfo=By.cssSelector("[data-qa='name']");
    private final By EmailaccountInfo=By.cssSelector("[data-qa='email']");
    private final By AccountInformation = By.xpath("//h2[@class='title text-center']");

    public SignupPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+"/login");
        return this;
    }

    public SignupPage ClickonSignupLoginButton(){
        driver.element().click(signUpLoginButton);
        return this;
    }

    public SignupPage SignupForm(String name,String email){
        driver.element().type(nameLocator,name);
        driver.element().type(emailLocator,email);
        return this;
    }

    public SignupPage ClickSignupButton(){
        driver.element().click(signUpButton);
        return this;
    }

    public SignupPage AccountInfo()
    {
        driver.validate().isElementVisable(AccountInformation);
        return this;
    }

    public SignupPage FillDetails(String title, String password,
                                  String dayOfBirth, String monthOfBirth, String yearOfBirth,
                                  String firstName, String lastName, String company,
                                  String addressOne, String addressTwo, String country, String state,
                                  String city, String zipcode, String mobileNumber){
        driver.element().click(genderMrRadioButton);
        driver.element().type(passwordLocator,password);
        driver.element().selectFromDropdown(dayOfBirthLocator,dayOfBirth);
        driver.element().selectFromDropdown(monthOfBirthLocator,monthOfBirth);
        driver.element().selectFromDropdown(yearOfBirthLocator,yearOfBirth);
        driver.element().click(newsletterLocator);
        driver.element().click(offersLocator);
        driver.element().type(firstNameLocator,firstName);
        driver.element().type(lastNameLocator,lastName);
        driver.element().type(companyLocator,company);
        driver.element().type(addressOneLocator,addressOne);
        driver.element().type(addressTwoLocator,addressTwo);
        driver.element().selectFromDropdown(countryLocator,country);
        driver.element().type(stateLocator,state);
        driver.element().type(cityLocator,city);
        driver.element().type(zipcodeLocator,zipcode);
        driver.element().type(mobileNumberLocator,mobileNumber);
        return this;
    }

    public SignupPage ClickCreateAccountButton(){
        driver.element().click(createAccountButton);
        return this;
    }

    public SignupPage verifyAccountCreated(String expectedText) {
        String actual=driver.element().findElement(accountCreated).getText();
        driver.validate().Equals(actual,expectedText,"");
        return this;
    }

    public SignupPage ClickContinueButton(){
        driver.element().click(continueButton);
        return this;
    }

    public SignupPage verifySignupErrorMsg(String expectedText){
        String actual=driver.element().findElement(SignupErrorMsg).getText();
        driver.validate().Equals(actual,expectedText,"");
        return this;
    }

    public String getNameValue(){
       return driver.element().findElement(NameaccountInfo).getText();
    }

    public String getEmailValue(){
        return driver.element().findElement(EmailaccountInfo).getText();
    }

}
