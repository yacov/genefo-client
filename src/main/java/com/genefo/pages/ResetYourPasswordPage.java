package com.genefo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Oleg on 30.05.2015.
 */
public class ResetYourPasswordPage extends Page{


    @FindBy(xpath = "//*[contains(text(),'Reset Your')]")
    WebElement resetTitle;

    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(xpath = "//*[contains(text(),'Email Me')]")
    WebElement emailMeButton;

    @FindBy(xpath = "//*[contains(text(),'Sign Up')]")
    WebElement signUpButton;

    @FindBy(xpath = "//*[contains(text(),'Login')]")
    WebElement loginButton;

    @FindBy(xpath = "//*[@class='errormsg']")
    WebElement notValidEmailAlert;

    @FindBy(xpath = "//*[@class='alert alert-success ng-binding ng-scope']")
    WebElement passwordResetAlert;

    public ResetYourPasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ResetYourPasswordPage fillEmailField(String email) {
        setElementText(emailField, email);
        return this;
    }

    public ResetYourPasswordPage clickOnEmailMe() {
        clickElement(emailMeButton);
        return this;
    }

    public boolean isOnResetPage() {
        waitUntilResetPageIsLoaded();
        return exists(resetTitle);
    }

    public ResetYourPasswordPage waitUntilResetPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(resetTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }return this;
    }
}
