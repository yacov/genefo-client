package com.genefo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by tanyagaus on 6/14/15.
 */
public class FilterOnHomePage extends Page {

    /**
     * Buttons
     */
    @FindBy(xpath = "//*[contains(text(),'Change Filter')]")
    WebElement changeFilterButton;

    @FindBy(xpath = "//*[@class = 'panel-body']//*[contains(text(),'Apply Filter')]")
    WebElement applayFilterButton;


    // Radio Buttons/Check boxes

    @FindBy(xpath = "//*[@id='post_type_profile']")
    WebElement myPostOnly;

    @FindBy(xpath = "//*[@id='post_type_network']")
    WebElement peopleFollowingMyPost;

    @FindBy(xpath = "//*[@id='post_type_condition']")
    WebElement myConditionPeopleFollowingMyPosts;

    @FindBy(xpath = "//*[@id='filter_posts_general']")
    WebElement post;

    @FindBy(xpath = "//*[@id='filter_posts_medicine']")
    WebElement medicine;

    @FindBy(xpath = "//*[@id='filter_posts_milestone']")
    WebElement milestone;

    @FindBy(xpath = "//*[@id='filter_posts_symptoms']")
    WebElement symptoms;

    @FindBy(xpath = "//*[@id='filter_posts_whatworks']")
    WebElement whatworks;

    @FindBy(xpath = "//*[@id='filter_posts_medical']")
    WebElement medical;

    @FindBy(xpath = "//*[@id='filter_posts_whatsinnews']")
    WebElement whatsinnews;


    /**
     * @param driver
     */
    public FilterOnHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     *
     */
    public FilterOnHomePage clickOnChangeFilterButton() {
        clickElement(changeFilterButton);
        return this;
    }

    /**
     *
     */
    public void waitUntilFilterPanelIsLoaded() {
        try {
            waitUntilElementIsLoaded(applayFilterButton);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * @return
     */
    public boolean isOnFilterPanel() {
        waitUntilFilterPanelIsLoaded();
        return exists(applayFilterButton);
    }

    /**
     * @return
     */
    public FilterOnHomePage checkedFirstRadioButton() {
        clickElement(myPostOnly);
        return this;
    }

    /**
     * @return
     */
    public FilterOnHomePage checkedSecondRadioButton() {
        clickElement(peopleFollowingMyPost);
        return this;
    }

    /**
     * @return
     */
    public FilterOnHomePage checkedThirdRadioButton() {
        clickElement(myConditionPeopleFollowingMyPosts);
        return this;
    }

    /**
     * @return
     */
    public FilterOnHomePage checkedFirstFilterOptions() {
        clickElement(post);
        return this;
    }

    /**
     * @return
     */
    public FilterOnHomePage checkedSecondFilterOptions() {
        clickElement(medicine);
        return this;
    }

    /**
     * @return
     */
    public FilterOnHomePage checkedThirdFilterOptions() {
        clickElement(milestone);
        return this;
    }

    /**
     * @return
     */
    public FilterOnHomePage checkedFourthFilterOptions() {
        clickElement(symptoms);
        return this;
    }

    /**
     * @return
     */
    public FilterOnHomePage checkedFifthFilterOptions() {
        clickElement(whatworks);
        return this;
    }


    /**
     * @return
     */
    public FilterOnHomePage checkedSixthFilterOptions() {
        clickElement(medical);
        return this;
    }

    /**
     * @return
     */
    public FilterOnHomePage checkedSeventhFilterOptions() {
        clickElement(whatsinnews);
        return this;
    }

    /**
     * @return
     */
    public FilterOnHomePage clickApplayFilterButton() {
        clickElement(applayFilterButton);
        return this;
    }

}