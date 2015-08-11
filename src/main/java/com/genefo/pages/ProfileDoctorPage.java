package com.genefo.pages;

import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;

/**
 * Created by Oleg on 29.05.2015.
 */
public class ProfileDoctorPage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    //lables
    @FindBy(xpath = "//*[@class='ng-binding' and contains(.,'HCP Account')]")
    @CacheLookup
    WebElement HCPAccountLable;

    //buttons
    @FindBy(xpath = "//*[contains(@ng-click,'account_hcp_account')]")
    @CacheLookup
    WebElement EditAccountInformationButton;

    @FindBy(xpath = "//*[contains(@ng-click,'account_hcp_basic')]")
    @CacheLookup
    WebElement EditBasicInformationButton;

    @FindBy(xpath = "//*[@class='btn btn-success ng-binding']")
    @CacheLookup
    WebElement EditHealthcareProfessionalInformationButton;

    @FindBy(xpath = "//div[@class='profile-summary-section ng-scope']/*[5]")
    @CacheLookup
    WebElement DiscoverYourHomePageButton;

    @FindBy(xpath = "//div[@class='container']//i[@class='fa fa-cog fa-2x']")
    @CacheLookup
    WebElement cogwheelButton;

    @FindBy(xpath = "//li[@class='ng-scope']/*[contains(text(),'Logout')]")
    @CacheLookup
    WebElement logOutButton;


    public ProfileDoctorPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 15), this);
        PropertyConfigurator.configure("log4j.properties");
    }

    public ProfileDoctorPage openProfileDoctorPage(WebDriver driver, String baseUrl) {
        driver.get(baseUrl + "/account_hcp");
        Log.info("Opening profile Doctor page");
        return this;
    }

    public ProfileDoctorPage waitUntilProfileDoctorPageIsLoaded() {
        Log.info("Wait for load Profile Doctor page");
        try {
            waitUntilElementIsLoaded(HCPAccountLable);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ProfileDoctorPage clickOnEditAccInf() {
        clickElement(EditAccountInformationButton);
        return this;
    }

    public ProfileDoctorPage clickOnEditBasInf() {
        clickElement(EditBasicInformationButton);
        return this;
    }

    public ProfileDoctorPage clickOnHealInf() {
        clickElement(EditHealthcareProfessionalInformationButton);
        return this;
    }

    public ProfileDoctorPage clickOnDisYourHP() {
        Log.info("Main page is open");
        clickElement(DiscoverYourHomePageButton);
        return this;
    }

    public ProfileDoctorPage logOut() {
        clickElement(cogwheelButton);
        clickElement(logOutButton);
        return this;
    }

    public boolean isOnProfileDoctorPage() {
        waitUntilProfileDoctorPageIsLoaded();
        return exists(HCPAccountLable);
    }

}
