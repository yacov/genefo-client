package com.genefo.pages;


import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Anton, Regina, Tanya on 13-May-15.
 */
public class ProfilePage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    @FindBy(xpath = "//ul[@class='nav navbar-nav']/li[1]/a[1]")
    WebElement discoverHomePage;
    //Titles
    @FindBy(xpath = "//div[@class='panel-heading']//*[contains(text(),'Create New Profile')]")
    WebElement profileTitle;
    //buttons
    @FindBy(id = "submit")
    WebElement saveProfileButton;
    @FindBy(xpath = "//*[@id='submit']/../*[1]/*[contains(text(),'Cancel')]")
    WebElement cancelButton;

    //fields
    @FindBy(name = "firstName")
    WebElement profileFirstNameField;
    @FindBy(name = "lastName")
    WebElement profileLastNameField;
    @FindBy(name = "condition")
    WebElement profileConditionField;
    @FindBy(name = "genderID")
    WebElement profileGender;

    //dropdown
    @FindBy(xpath = "//*[@name='relationID']/*[@value='2']/..")
    WebElement profilePatientDropdown;

    @FindBy()
    WebElement profileGenderTooltip;
    @FindBy()
    WebElement profileRaceToltip;
    @FindBy(name = "birthmonth")
    WebElement profileBirthdayToltipMonth;
    @FindBy(name = "birthday")
    WebElement profileBirthdayToltipDay;
    @FindBy(name = "birthyear")
    WebElement profileBirthdayToltipYear;
    @FindBy(xpath = "//*[contains(@id,'typeahead-0LH-9401') and contains(@ng-show, 'isOpen()')]/*[1]")
    WebElement profileLocationToltip;
    @FindBy(xpath = "//*[contains(@id,'typeahead') and contains(@ng-show, 'isOpen()')]/*[1]")
    WebElement conditionTooltip;
    @FindBy(name = "diagnosisYear")
    WebElement profileDiagnoseTooltipYear;

    private String label; // Keeps last label from dropdown list.


    public ProfilePage(WebDriver driver) {
        super(driver);
        PropertyConfigurator.configure("log4j.properties");
        PageFactory.initElements(driver, this);
    }

    public void waitUntilProfilePageIsLoaded() {
        try {
            waitUntilElementIsLoaded(profilePatientDropdown);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnProfilePage() {
        Log.info("Wait for load Profile page");
        waitUntilProfilePageIsLoaded();
        return exists(profilePatientDropdown);
    }

    public boolean isOnProfilePageNegative() {
        Log.info("Verify that we are on Profile page");
        return exists(cancelButton);
    }

    public ProfilePage selectGender(String value) {
        Log.info("Select Gender");
        label = selectValueInDropdown(profileGender, value);
        return this;
    }

    public boolean isGenderSelected(String chosenOption) {
        return verifyTextBooleanInDropDown(label, chosenOption);
    }

    public ProfilePage selectProfilePatient(String value2) {
        Log.info("Select Profile patient type");
        selectValueInDropdown(profilePatientDropdown, value2);
        return this;
    }

    public boolean isPatientSelected(String value2) {
        return verifyTextBoolean(profilePatientDropdown, value2);
    }

    public boolean isConditionSelected(String value) {
        return verifyTextBoolean(profileConditionField, value);
    }

    public ProfilePage selectMonth(String value) {
        Log.info("Select Month");
        selectValueInDropdown(profileBirthdayToltipMonth, value);
        return this;
    }

    public boolean isMonthSelected(String value) {

        return verifyTextBoolean(profileBirthdayToltipMonth, value);
    }

    public ProfilePage selectDay(String value) {
        selectValueInDropdown(profileBirthdayToltipDay, value);
        return this;
    }

    public boolean isDaySelected(String value) {
        return verifyTextBoolean(profileBirthdayToltipDay, value);
    }

    public ProfilePage selectYear(String value) {
        Log.info("Select year");
        selectValueInDropdown(profileBirthdayToltipYear, value);
        return this;
    }

    public boolean isYearSelected(String value) {
        return verifyTextBoolean(profileBirthdayToltipYear, value);
    }

    public ProfilePage selectDiagnoseYear(String value) {
        Log.info("Select diagnose year");
        selectValueInDropdown(profileDiagnoseTooltipYear, value);
        return this;
    }

    public boolean isDiagnoseYearSelected(String value) {
        return verifyTextBoolean(profileDiagnoseTooltipYear, value);
    }

    public ProfilePage fillProfileFirstNameField(String firstName) {
        Log.info("Fill First Name");
        setElementText(profileFirstNameField, firstName);
        return this;
    }

    public ProfilePage fillProfileLastNameField(String lastName) {
        Log.info("Fill Last Name");
        setElementText(profileLastNameField, lastName);
        return this;
    }

    public ProfilePage fillProfileConditionField(String condition) {
        Log.info("Fill condition");
        profileConditionField.clear();
        setElementText(profileConditionField, condition);
        clickElement(profileConditionField);
        return this;
    }

    public void autoFillCondition() {
        Log.info("Auto fill condition");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickElement(conditionTooltip);
    }

    public ProfilePage waitUntilRegProfPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(profileConditionField);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void clickToSubmit() {
        Log.info("Submit");
        clickElement(saveProfileButton);
        ProfilePage profilePage;
        profilePage = PageFactory.initElements(webDriver, ProfilePage.class);
    }

    public String getProfileName() {
        return profileFirstNameField.getText() + " " + profileLastNameField.getText();
    }

    public void clickOnDiscoverHome() {
        clickElement(discoverHomePage);
    }

}











