package com.genefo.pages;

import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * Created by Iakov Volf on 17.06.2015.
 */
public class ContactUSPage extends Page {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    //fields
    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "name")
    WebElement nameField;

    @FindBy(name = "note")
    WebElement messageField;

    @FindBy(xpath = "//*[@id='submit'][@disabled='disabled']")
    WebElement sendButtonDisabled;

    @FindBy(id = "submit")
    WebElement sendButton;

    @FindBy(xpath = "//*[text()='Message has been sent!']")
    WebElement messageSentConfirmation;

    @FindBy(xpath = "//*[text()='Not a valid email']")
    WebElement notValidEmailAlert;


    public ContactUSPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public ContactUSPage openContactPage(WebDriver driver, String baseUrl) {
        driver.get(baseUrl + "/legal/contact");
        Log.info("Opening ContactUs page");
        return this;
    }

    public ContactUSPage fillEmailField(String email) {
        Log.info("Entering Email");
        setElementText(emailField, email);
        return this;
    }

    public ContactUSPage fillMessageField(String message) {
        Log.info("Entering Message");
        setElementText(messageField, message);
        return this;
    }

    public ContactUSPage fillFirstNameField(String firstName) {
        Log.info("Entering First name");
        setElementText(nameField, firstName);
        return this;
    }


    public ContactUSPage clickOnSendButton() {
        Log.info("Clicking on 'Send' button");
        clickElement(sendButton);
        return this;
    }

    public ContactUSPage clickOnNameField() {
        clickElement(nameField);
        return this;
    }

    public ContactUSPage clickOnMessageField() {
        clickElement(messageField);
        return this;
    }

    public ContactUSPage clickOnEmailField() {
        clickElement(emailField);
        return this;

    }


    public boolean messageSentSuccesifully() {
        return exists(messageSentConfirmation);
    }

    public boolean emailALert() {
        return exists(notValidEmailAlert);
    }

    public boolean isOnSignUpHCPPage() {
        return exists(sendButton);
    }

    public boolean sendButtonDisabled() {
        return exists(sendButtonDisabled);
    }

    public boolean sendButtonEnaabled() {
        return exists(sendButton);
    }


}













