package com.genefo.pages;

import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Л,Oleg on 5/19/2015.
 */
public class LoginPage extends Page {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    @FindBy(xpath = "//*[contains(text(),'Login to')]")
    WebElement loginTitle;
    @FindBy(name = "email")
    WebElement emailField;
    @FindBy(name = "password")
    WebElement passwordField;
    @FindBy(id = "submit")
    WebElement loginButton;
    @FindBy(xpath = "//*[contains(text(),'Sign Up')]")
    WebElement signUpButton;
    @FindBy(xpath = "//*[contains(text(),'Forgot')]")
    WebElement forgotLink;
    @FindBy(xpath = "//*[contains(text(),'Invalid Password')]")
    WebElement invalidPasswordAlert;
    @FindBy(xpath = "//*[contains(text(),'Invalid Email')]")
    WebElement invalidEmailAlert;

    public LoginPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    public LoginPage opennLoginPage(WebDriver driver) {
        return this;
    }

    public LoginPage openLoginPage(WebDriver driver, String baseUrl) {
        driver.get(baseUrl + "/login");
        Log.info("Opening login page");
        return this;
    }

    public LoginPage waitUntilLoginPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(loginTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }return this;
    }



    public LoginPage waitUntilAllertEmailIsLogIsLoaded() {
        try {
            waitUntilElementIsLoaded(invalidEmailAlert);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }return this;
    }

    public LoginPage waitUntilAllertPasswordIsLogIsLoaded() {
        try {
            waitUntilElementIsLoaded(invalidPasswordAlert);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }return this;
    }

    public boolean isOnLoginPage() {
        waitUntilLoginPageIsLoaded();
        return exists(loginTitle);
    }

    public LoginPage fillEmailField(String email) {
        setElementText(emailField, email);
        Log.info("entering email: " + email + " ");
        return this;
    }

    public LoginPage fillPasswordField(String password) {
        setElementText(passwordField, password);
        Log.info("entering password: " + password + " ");
        return this;
    }

    public LoginPage clickOnLogin() {
        clickElement(loginButton);
        return this;
    }

    public LoginPage clickOnSignUpButton() {
        clickElement(signUpButton);
        return this;
    }

    public LoginPage login(String email, String password) {
        waitUntilLoginPageIsLoaded();
        fillEmailField(email);
        fillPasswordField(password);
        clickOnLogin();
        return this;
    }

    public LoginPage clickOnForgotPasswordLink(){
        clickElement(forgotLink);
        return this;
    }

    public boolean alertMessageInvalidEmail() {
        return exists(invalidEmailAlert);
    }

    public boolean alertMessageInvalidPassword() {
        return exists(invalidPasswordAlert);
    }


}
