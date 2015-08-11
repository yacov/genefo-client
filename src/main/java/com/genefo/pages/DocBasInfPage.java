package com.genefo.pages;

import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oleg on 02.06.2015.
 */
public class DocBasInfPage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    //Titles
    @FindBy(xpath = "//*[contains(text(),'Healthcare Professional Basic Information')]")
    @CacheLookup
    WebElement basInfTitle;

    //buttons
    @FindBy(xpath = "//*[@id='submit']/span")
    @CacheLookup
    WebElement saveButton;
    @FindBy(xpath = "//*[@id='submit' and @disabled='disabled']")
    @CacheLookup
    WebElement saveDisableButton;
    @FindBy(xpath = "//*[@class='profile-info-section ng-scope']/descendant::button[@class='btn btn-primary'and contains(.,'Cancel')]")
    @CacheLookup
    WebElement cancelButton;
    @FindBy(xpath = "//*[@style='overflow: hidden;']")
    @CacheLookup
    WebElement addProfilePicture;

    //fields
    @FindBy(name = "firstName")
    @CacheLookup
    WebElement firstNameField;
    @FindBy(name = "lastName")
    @CacheLookup
    WebElement lastNameField;
    @FindBy(xpath = "//*[@name='location']")
    @CacheLookup
    WebElement locationField;

    //dropdown
    @FindBy(name = "birthmonth")
    @CacheLookup
    WebElement birthmonthToltipMonth;
    @FindBy(name = "birthday")
    @CacheLookup
    WebElement birthdayToltipDay;
    @FindBy(name = "birthyear")
    @CacheLookup
    WebElement birthyearToltipYear;
    @FindBy(xpath = "//*[contains(@id,'option-0')]/a")
    @CacheLookup
    WebElement locationToltip;


    //Allerts
    @FindBy(xpath = "//*[contains(@ng-show,'firstName')]/*[@class='fa fa-times']")
    WebElement fNameErrAlert;
    @FindBy(xpath = "//*[contains(@ng-show,'lastName')]/*[@class='fa fa-times']")
    WebElement lNameErrAlert;
    @FindBy(xpath = "//*[contains(@ng-show,'location')]/*[@class='fa fa-times']")
    WebElement locationErrAlert;


    public DocBasInfPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public DocBasInfPage waitUntilDocBasInfPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(basInfTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isOnDocBasInfPage() {
        waitUntilDocBasInfPageIsLoaded();
        return exists(basInfTitle);
    }

    public DocBasInfPage fillFirstNameField(String firstName) {
        setElementText(firstNameField, firstName);
        Log.info("entering firstName: " + firstName + " ");
        return this;
    }

    public DocBasInfPage fillLastNameField(String lastName) {
        setElementText(lastNameField, lastName);
        Log.info("entering lastName: " + lastName + " ");
        return this;
    }

//    public DocBasInfPage selectMonth(String value) {
//        selectValueInDropdown(birthmonthToltipMonth, value);
//        return this;
//    }
//
//    public boolean isMonthSelected(String value) {
//
//        return verifyTextBoolean(birthmonthToltipMonth, value);
//    }
//
//    public DocBasInfPage selectDay(String value) {
//        selectValueInDropdown(birthdayToltipDay, value);
//        return this;
//    }
//
//    public boolean isDaySelected(String value) {
//
//        return verifyTextBoolean(birthdayToltipDay, value);
//    }
//
//    public DocBasInfPage selectYear(String value) {
//        selectValueInDropdown(birthyearToltipYear, value);
//        return this;
//    }
//
//    public boolean isYearSelected(String value) {
//
//        return verifyTextBoolean(birthyearToltipYear, value);
//    }

    public DocBasInfPage fillLocationField(String location) {
        setElementText(locationField, location);
        Log.info("entering location: " + location + " ");
        sleep();
        return this;
    }

    public DocBasInfPage clickOnTooltip() {
        clickElement(locationToltip);
        Log.info("selection from location tooltip: " + locationToltip + " ");
        return this;
    }

    public DocBasInfPage clickOnAddPicture() {
        clickElement(addProfilePicture);
        return this;
    }

    public DocBasInfPage clickOnCancel() {
        clickElement(cancelButton);
        return this;
    }

    public DocBasInfPage clickOnSaveButton() {
        clickElement(saveButton);
        return this;
    }

//    public DocBasInfPage clickOnSaveDisableButton() {
//        clickElement(saveDisableButton);
//        return this;
//    }

    public boolean alertMessageInvalidFirstName() {

        return exists(fNameErrAlert);
    }

    public boolean alertMessageInvalidLastName() {

        return exists(lNameErrAlert);
    }

    public boolean alertMessageInvalidLocation() {

        return exists(locationErrAlert);
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
