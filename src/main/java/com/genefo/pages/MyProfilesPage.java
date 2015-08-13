package com.genefo.pages;

import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;

/**
 * Created by Л on 5/19/2015.
 */
public class MyProfilesPage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    //Labels
    @FindBy(xpath = "//*[@class='ng-binding' and contains(.,'HCP Account')]")
    WebElement MyProfilesLable;

    //Button
    @FindBy(xpath = "//div[@class='panel-body']//div[@class='btn-add-profile']/i")

    WebElement addPlusButton;

    @FindBy(xpath = "//ul[@class='people_list people_list_in_profiles']/*[last()]//div[@class='profileName ng-binding']")
    WebElement secondProfile;

    public MyProfilesPage(WebDriver driver) {
        super(driver);
        PropertyConfigurator.configure("log4j.properties");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public MyProfilesPage openMyProfilesPage() {
        return this;
    }

    public void waitUntilMyProfilesPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(addPlusButton);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnMyProfilesPage() {
        Log.info("Wait for load MyProfilesPage page");
        waitUntilMyProfilesPageIsLoaded();
        return exists(addPlusButton);
    }

    public void clickToPlus() {
        clickElement(addPlusButton);
    }

    public void clickSecondProfile() {
        clickElement(secondProfile);
    }


}