package com.genefo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Christina on 6/22/15.
 */
public class FiltersOfPatientOnMainPage extends Page {

    //fields of Patient MainPage
    @FindBy(xpath = "//div[@class='condition-column-title condition-column-title-regular col-md-9 ng-binding']")
    WebElement conditionFieldOfPatientOnMainPage;
    @FindBy(xpath = "//div[@class='profile_selector_name ng-binding']")
    WebElement nameFieldOfPatientOnMainPage;
    @FindBy(xpath = "//div[@class='panel-body']/span[1]")
    WebElement displayingMyPosts;
    //Change Filter button
    @FindBy(xpath = "//span[@class='btn-filter btn ng-binding btn-default']")
    WebElement changeFilterButton;
    //Apply Filter button
    @FindBy(xpath = "//span[@class='btn-filter btn ng-binding btn-success']")
    WebElement applyFilterButton;
    //radioButton for change of filter
    @FindBy(xpath = ".//*[@id='post_type_profile']")
    WebElement myPostsOnlyRadioButton;
    @FindBy(xpath = ".//*[@id='post_type_network']")
    WebElement peopleIAmFollowingAndMyPostsOnlyRadioButton;
    @FindBy(xpath = ".//*[@id='post_type_condition']")
    WebElement myConditionAndPeopleIAmFollowingAndMyPostsOnlyRadioButton;


    //nameOfPatientForPost
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']//*[@class='profileName post-owner ng-binding'][contains(text(),'Pat One')]")
    WebElement nameOfPatOnePost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']//*[@class='profileName post-owner ng-binding'][contains(text(),'Pat Two')]")
    WebElement nameOfPatTwoPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']//*[@class='profileName post-owner ng-binding'][contains(text(),'Pat Three')]")
    WebElement nameOfPatThreePost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']//*[@class='profileName post-owner ng-binding'][contains(text(),'Pat Four')]")
    WebElement nameOfPatFourPost;


    //constructor
    public FiltersOfPatientOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }


    public FiltersOfPatientOnMainPage clickOnChangeFilterButton() {
        clickElement(changeFilterButton);
        return this;
    }

    public FiltersOfPatientOnMainPage clickOnApplyFilterButton() {
        clickElement(applyFilterButton);
        return this;
    }

    public FiltersOfPatientOnMainPage clickOnMyPostsOnlyRadioButton() {
        clickElement(myPostsOnlyRadioButton);
        return this;
    }

    public FiltersOfPatientOnMainPage clickOnPeopleIAmFollowingAndMyPostsOnlyRadioButton() {
        clickElement(peopleIAmFollowingAndMyPostsOnlyRadioButton);
        return this;
    }

    public FiltersOfPatientOnMainPage clickOnMyConditionAndPeopleIAmFollowingAndMyPostsOnlyRadioButton() {
        clickElement(myConditionAndPeopleIAmFollowingAndMyPostsOnlyRadioButton);
        return this;
    }


    public boolean isNameOfPatOne(String name) {
        return this.verifyTextBoolean(nameOfPatOnePost, name);
    }

    public boolean isNameOfPatTwo(String name) {
        return this.verifyTextBoolean(nameOfPatTwoPost, name);
    }

    public boolean isNameOfPatThree(String name) {
        return this.verifyTextBoolean(nameOfPatThreePost, name);
    }

    public boolean isNameOfPatFour(String name) {
        return this.verifyTextBoolean(nameOfPatFourPost, name);
    }


    //method for waiting Displaying My Posts
    public void waitForDisplayingMyPosts() throws IOException, InterruptedException {
        waitUntilElementIsLoaded(displayingMyPosts);
    }

}
