package com.genefo.pages;

import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Л on 5/20/2015.
 */
public class SummaryPage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    @FindBy(xpath = "//div[@class='progress' and contains(.,'75% Complete')]")
    WebElement progressBar;
    @FindBy(xpath = "//div[@class='profile-summary-section ng-scope']//a [@class='btn btn-success btn-discover-homepage']")
    WebElement discoverHomePage;
    @FindBy(xpath = "//div[@class='panel panel-default']//div[@class='col-xs-7 text-left text-capitalize ng-binding'][1]")
    WebElement relationshipField;
    @FindBy(xpath = "//div[@class='panel panel-default']//div[@class='col-xs-7 text-left text-capitalize ng-binding'][2]")
    WebElement nameField;
    @FindBy(xpath = "//div[@class='panel panel-default']//div[@class='col-xs-7 text-left text-capitalize ng-binding'][3]")
    WebElement conditionField;
    @FindBy(xpath = "//div[@class='panel panel-default']//div[@class='col-xs-7 text-left text-capitalize ng-binding'][4]")
    WebElement patientDiagnosisDateField;
    @FindBy(xpath = "//div[@class='panel panel-default']//div[@class='col-xs-7 text-left text-capitalize ng-binding'][5]")
    WebElement genderField;
    @FindBy(xpath = "//div[@class='panel panel-default']//div[@class='col-xs-7 text-left text-capitalize ng-binding'][6]")
    WebElement birthdayField;
    @FindBy(xpath = "//ul[@class='profile_list people_list_sidebar']/li[1]//div[@class='profileName ng-binding']")
    WebElement firstProfileButton;
    @FindBy(xpath = "//ul[@class='profile_list people_list_sidebar']/*[last()]//div[@class='profileName ng-binding']")
    WebElement lastProfileButton;

    public SummaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        PropertyConfigurator.configure("log4j.properties");
    }

    public void waitUntilProfilePageIsLoaded() {
        try {
            waitUntilElementIsLoaded(progressBar);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnSummaryPage() {
        waitUntilProfilePageIsLoaded();
        return exists(progressBar);
    }

    public void clickOnDiscoverHome() {
        clickElement(discoverHomePage);
    }

    public boolean isProfileNamePresents(String name) {
        try {
            webDriver.findElement(By.xpath("//*[contains(text(),'" + name + "')]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickOnFirstProfile() {
        clickElement(firstProfileButton);
    }

    public void clickOnLastProfile() {
        clickElement(lastProfileButton);
    }

    public boolean areProfileFieldsCorrect(String patient_profile_check, String condition_check, String gender_check,
                                           String birth_date_check, String diagnose_year_check, String name_check, String last_name_check) {
        LinkedList<String> allReqField = new LinkedList<String>();
        String xpath = null;
        allReqField.add(patient_profile_check);
        allReqField.add(condition_check);
        allReqField.add(gender_check);
        allReqField.add(birth_date_check);
        allReqField.add(diagnose_year_check);
        allReqField.add(name_check);
        allReqField.add(last_name_check);
        try {
            Thread.sleep(1000);
            for (String s : allReqField) {
                xpath = "//div [@class='col-lg-9']//*[contains(text(), '" + s + "')][@class='col-xs-7 text-left text-capitalize ng-binding']";
                webDriver.findElement(By.xpath(xpath));
                Log.info("Element " + s + " checked");
            }
        } catch (NoSuchElementException e) {
            System.out.println("--------------------------------------------");
            System.out.println("Error Required element was not found!");
            System.out.println("xpath of the element:" + xpath);
            System.out.println("--------------------------------------------");
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}