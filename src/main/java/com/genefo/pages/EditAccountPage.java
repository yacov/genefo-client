package com.genefo.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class EditAccountPage extends Page {

    //    fields
    @FindBy(name = "email")
    WebElement emailElement;

    @FindBy(name = "newpassword")
    WebElement newPasswordElement;

    @FindBy(name = "firstName")
    WebElement firstNameElement;

    @FindBy(name = "lastName")
    WebElement lastNameElement;

    @FindBy(name = "oldpassword")
    WebElement oldpasswordElement;

    @FindBy(xpath = "//*[contains(text(),'Edit Account')]")
    WebElement editAccountTitle;

    //    buttons

    @FindBy(xpath = "//*[@id='submit' and @data-target='#loginModal'][not(contains(@disabled,'disabled'))]")
    WebElement submitButton1;

    @FindBy(xpath = "//button[@id='submit' and @class='btn btn-primary'][not(contains(@disabled,'disabled'))]")
    WebElement submitButton2;

    @FindBy(xpath = "//div[@class='modal-footer']/a[contains(text(),'Save')]")
    WebElement submitButtonOldPassword;

// alerts

    @FindBy(xpath = "//*[@class='alert alert-success alert-dismissible']/div")
    WebElement changesSuccessAlert;

    public EditAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public EditAccountPage openEditAccountPage(WebDriver driver, String baseUrl) {

        driver.get(baseUrl + "/account");

        return this;
    }


    public WebElement getNewPasswordElement() {
        return newPasswordElement;
    }

    public WebElement getFirstNameElement() {
        return firstNameElement;
    }

    public WebElement getLastNameElement() {
        return lastNameElement;
    }

    public WebElement getEmailElement() {
        return emailElement;
    }


    public EditAccountPage fillEmailField(String e_mail) {
        //Added because function "element.clean()" not worked for me!
        cleanElement(emailElement);
        setElementText(emailElement, e_mail);
        return this;
    }

    public EditAccountPage fillPasswordField(String pass) {
        //Added because function "element.clean()" not worked for me!

        pause();
        clickElement(newPasswordElement);
        pause();
        cleanElement(newPasswordElement);
        pause();
        setElementText(newPasswordElement, pass);

        return this;
    }

    public EditAccountPage fillField(WebElement element, String str) {

        clickElement(element);
        pause();
        cleanElement(element);
        pause();
        setElementText(element, str);
        pause();
        return this;
    }


    private void cleanElement(WebElement element) {

        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    public EditAccountPage waitUntilEditElementIsLoaded() {
        try {
            waitUntilElementIsLoaded(emailElement);
            waitUntilElementIsLoaded(newPasswordElement);
            waitUntilElementIsLoaded(firstNameElement);
            waitUntilElementIsLoaded(lastNameElement);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isOnEditAccountPage() {
        try {
            return editAccountTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("------------------------------");
            System.out.println("EditAccountPage.isOnEditAccountPage() no title element found");
            System.out.println("------------------------------");
            return false;
        }
    }

    public EditAccountPage fillOldPasswordField(String pass) {

        pause();
        clickElement(oldpasswordElement);
        pause();
        cleanElement(oldpasswordElement);
        pause();
        setElementText(oldpasswordElement, pass);

        return this;
    }

    public EditAccountPage clickOnSubmitButton1() {
        clickElement(submitButton1);
        return this;
    }

    public EditAccountPage clickOnSubmitButton2() {
        clickElement(submitButton2);
        return this;
    }

    public EditAccountPage clickOnSubmitButtonOldPassword() {
        clickElement(submitButtonOldPassword);
        return this;
    }

    public boolean isSuccessAlert() {

        try {
            waitUntilElementIsLoaded(changesSuccessAlert);
            return exists(changesSuccessAlert);
        } catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        }

    }

    public boolean isButton1Clickable() {
        return exists(submitButton1);
    }

    public boolean isButton2Clickable() {
        return exists(submitButton2);
    }

    private void pause() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
