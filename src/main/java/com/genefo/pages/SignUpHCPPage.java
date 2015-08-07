package com.genefo.pages;

import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Oleg on 29.05.2015.
 */
public class SignUpHCPPage extends Page {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    //fields
    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(name = "firstName")
    WebElement firstNameField;

    @FindBy(name = "lastName")
    WebElement lastNameField;

    //lables
    @FindBy(xpath = "//*[@class='col-sm-2 control-label' and contains(.,'Last') and contains(.,'*')]")
    WebElement lastNameLableHCP;

    @FindBy(xpath = "//*[@id='myModalLabel']")
    WebElement termOfSeviceLable;

    //buttons
    @FindBy(id = "submit")
    WebElement signUpHCPButton;

    @FindBy(xpath = "//*[@class='ng-pristine ng-valid']/a")
    WebElement termOfServiceLink;

    //checkboxs
    @FindBy(name = "isOver18")
    WebElement checkBox18;

    @FindBy(name = "TOS")
    WebElement checkBoxAgree;

    //alerts
    @FindBy(xpath = "//*[@class='col-sm-4' and contains(.,'email')]")
    WebElement nonValidEmail;

    @FindBy(xpath = "//*[@class='col-sm-4' and contains(.,'password')]")
    WebElement nonValidPassword;

    @FindBy(xpath = "//*[@class='col-sm-4' and contains(.,'first name')]")
    WebElement nonValidFirstName;

    @FindBy(xpath = "//*[@class='col-sm-4' and contains(.,'last name')]")
    WebElement nonValidLastName;

    @FindBy(xpath = "//*[@class='col-sm-4' and contains(.,'18 or older')]")
    WebElement alertToCheckBox18;

    @FindBy(xpath = "//*[@class='col-sm-4 col-xs-12' and contains(.,'Terms')]")
    WebElement alertToCheckBoxAgree;

    public SignUpHCPPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SignUpHCPPage openHCPRegPage(WebDriver driver, String baseUrl) {
        driver.get(baseUrl + "/signup_hcp");
        Log.info("Opening HCP registration page");
        waitUntilSignUpHCPPageIsLoaded();
        return this;
    }

    public SignUpHCPPage openTOSPage(WebDriver driver, String baseUrl) {
        driver.get(baseUrl + "");
        Log.info("Opening login page");
        waitUntilTOSPageIsLoaded();
        return this;
    }

    private SignUpHCPPage waitUntilTOSPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(termOfSeviceLable);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SignUpHCPPage fillEmailField(String email) {
        setElementText(emailField, email);
        Log.info("entering email: " + email + " ");
        return this;
    }

    public SignUpHCPPage fillPasswordField(String password) {
        setElementText(passwordField, password);
        Log.info("entering password: " + password + " ");
        return this;
    }

    public SignUpHCPPage fillFirstNameField(String firstName) {
        setElementText(firstNameField, firstName);
        Log.info("entering first name: " + firstName + " ");
        return this;
    }

    public SignUpHCPPage fillLastNameField(String lastName) {
        setElementText(lastNameField, lastName);
        Log.info("entering last name: " + lastName + " ");
        return this;
    }

    public SignUpHCPPage waitUntilSignUpHCPPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(lastNameLableHCP);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SignUpHCPPage clickOnSignUp() {
        clickElement(signUpHCPButton);
        return this;
    }

    public SignUpHCPPage clickOnCheckBox18() {
        clickElement(checkBox18);
        return this;
    }

    public SignUpHCPPage clickOnCheckBoxAgree() {
        clickElement(checkBoxAgree);
        return this;
    }

    public SignUpHCPPage clickOnTermOfService() {
        clickElement(termOfServiceLink);
        return this;
    }

    public boolean isOnSignUpHCPPage() {
        return exists(checkBox18);
    }

    public boolean isOnTOSage() {
        return exists(termOfSeviceLable);
    }

    //check alert presence

    public boolean alertMessageNotValidFirstName() {
        return exists(nonValidFirstName);
    }

    public boolean alertMessageNotValidLastName() {
        return exists(nonValidLastName);
    }

    public boolean alertMessageNotValidEmail() {
        return exists(nonValidEmail);
    }

    public boolean alertMessageNotValidPassword() {
        return exists(nonValidPassword);
    }

    public boolean alertMessageNonChecked18() {
        return exists(alertToCheckBox18);
    }

    public boolean alertMessageNonCheckedTerms() {
        return exists(alertToCheckBoxAgree);
    }

}













