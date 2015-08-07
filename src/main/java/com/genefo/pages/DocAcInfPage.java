package com.genefo.pages;

import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Oleg on 31.05.2015.
 */
public class DocAcInfPage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    @FindBy(xpath = "//*[contains(text(),'Healthcare Professional Account Information')]")
    WebElement docAcInfTitle;

    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "newpassword")
    WebElement passwordField;

    @FindBy(xpath = "//*[@class='account-section ng-scope']/descendant::button[@class='btn btn-primary'and contains(.,'Cancel')]")
    WebElement cancelButton;

    @FindBy(xpath = "//*[@id='submit' and @data-toggle='modal']")
    WebElement saveButton;

//    @FindBy(xpath = "//*[@id='submit' and @data-target='#loginModal' and @disabled='disabled']")
//    WebElement saveDisablelButton;

    @FindBy(xpath = "//*[@class='errormsg']/*[@class='fa fa-times']")
    WebElement emailErrAlert;

    @FindBy(xpath = "//*[@class='errormsg hidden-xs']/*[@class='fa fa-times']")
    WebElement passwordErrAlert;

    @FindBy(xpath = "//*[@class = 'ng-binding' and contains (text(),'Account Login Information Updated')]")
    WebElement accountSuccess;

    @FindBy(xpath = "//*[@id='myModalLabel']")
    WebElement enterYourCurrentPassTitle;

    @FindBy(xpath = "//*[@id='loginModal']//input")
    WebElement curPasswordField;

    @FindBy(xpath = "//*[@id='loginModal']//button[2]")
    WebElement curSaveButton;

    public DocAcInfPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public DocAcInfPage waitUntilDocAcInfPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(docAcInfTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public DocAcInfPage waitUntilEnterYourCurrentPassIsLoaded() {
        try {
            waitUntilElementIsLoaded(enterYourCurrentPassTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isOnDocAcInfPage() {
        waitUntilDocAcInfPageIsLoaded();
        return exists(docAcInfTitle);
    }

    public DocAcInfPage fillEmailField(String email) {
        setElementText(emailField, email);
        Log.info("entering email: " + email + " ");
        return this;
    }

    public DocAcInfPage fillPasswordField(String password) {
        setElementText(passwordField, password);
        Log.info("entering password: " + password + " ");
        new Actions(webDriver).moveToElement(saveButton).perform();
        return this;
    }

    public DocAcInfPage fillCurrentPasswordField(String password) {
        setElementText(curPasswordField, password);
        Log.info("entering current password: " + password + " ");
        return this;
    }

    public DocAcInfPage clickOnCancel() {
        clickElement(cancelButton);
        return this;
    }

    public DocAcInfPage clickOnSaveButton() {
        clickElement(saveButton);
        return this;
    }

    public DocAcInfPage clickOnTitle() {
        clickElement(docAcInfTitle);
        return this;
    }

    public DocAcInfPage clickOnCurSaveButton() {
        clickElement(curSaveButton);
        return this;
    }

    public boolean alertMessageInvalidEmail() {
        return exists(emailErrAlert);
    }

    public boolean alertMessageInvalidPassword() {

        return exists(passwordErrAlert);
    }

    public boolean alertMessageAccountSuccess() {
        waitUntilIsLoadedCustomTime(accountSuccess, 5);
        return exists(accountSuccess);
    }

}
