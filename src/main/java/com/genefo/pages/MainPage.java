package com.genefo.pages;

/**
 * Created by alex on 28/05/2015.
 */

import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class MainPage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    // Upper Menu buttons
    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Post')]")
    WebElement postButton;
    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Medicine')]")
    WebElement medicineButton;
    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Progress')]")
    WebElement progressButton;
    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Symptoms')]")
    WebElement symptomsButton;
    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'What Works')]")
    WebElement whatWorksButton;
    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'MD Rating')]")
    WebElement mdRatingButton;
    @FindBy(xpath = "//ul[@class='post_option']//span[contains(text(),'Question')]")
    WebElement questionButton;

    //Buttons
    @FindBy(xpath = "//div[@class='container']//i[@class='fa fa-cog fa-2x']")
    WebElement cogwheelButton;
    @FindBy(xpath = "//li[@class='ng-scope']/*[contains(text(),'My Profiles')]")
    WebElement myProfilesButton;
    @FindBy(xpath = "//li[@class='ng-scope']/*[contains(text(),'My Account')]")
    WebElement myAccountButton;
    @FindBy(xpath = "//li[@class='ng-scope']/*[contains(text(),'Logout')]")
    WebElement logOutButton;
    @FindBy(xpath = "//ul[@class='nav navbar-nav']")
    WebElement myHomeButton;
    @FindBy(xpath = "//ul[@class='people_list people-like-me-list']//li[1]//span[@class='profileName ng-binding']")
    WebElement connectPeopleThisCondition1Button;
    @FindBy(xpath = "//div[@class='panel story-panel ng-scope panel-default'][1]//div[@class='post-owner-timestamp-wrapper']//span[@class='profileName post-owner ng-binding']")
    WebElement firstPostNameLink;
    @FindBy(xpath = "//div[@class='panel story-panel ng-scope panel-default']//div[@class='post-owner-timestamp-wrapper']//span[@class='profileName post-owner ng-binding']")
    WebElement allPostNameLinks;
    @FindBy(xpath = "//div[@class='profile_selector_active profile_selector_active_hcp']//div[@class='profile_selector_name ng-binding']")
    WebElement myNameDoctor;
    @FindBy(xpath = "//div[@class='profile_selector_active profile_selector_active_regular']//div[@class='profile_selector_name ng-binding']")
    WebElement myNameUser;
    // Upper Tab of sent posts
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]")
    WebElement UpperSentPostTab;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//div[@class='post-note ng-binding']")
    WebElement SentPostText;
    @FindBy(xpath = "//div[@class='panel panel-primary']//div[@class='panel-body']//li[1]//span[@class='profileName ng-binding']")
    WebElement firstFollowed;
    @FindBy(xpath = "//div[@class='col-md-10']/input")
    WebElement viewConditionFieldForDoctor;
    @FindBy(xpath = "//div[@class='top-row']//a[@class='ng-scope ng-binding']")
    WebElement dropDownConditionDoctor;
    @FindBy(xpath = "//div[@class='top-row']//button[@class='btn btn-default']")
    WebElement viewButton;
    @FindBy(xpath = "//*[contains(text(),'REQUIRED FIELDS')]")
    WebElement requiredFieldsMessage;

    //button link to Graphs Page
    @FindBy(xpath = "//*[text()='See More Graphs']")
    WebElement SeeMoreGraphsButton;


    //New tutorial elements
    @FindBy(xpath = "//*[@id='skip-tutorial-checkbox']")
    WebElement notShowTutorialAgainCheckBox;
    @FindBy(xpath = "//*[@ng-click='skipTutorial()']")
    WebElement skipTutorialButton;
    @FindBy(xpath = "//*[@class='close']")
    WebElement closeBetaMessage;

    @FindBy(xpath = "//ul[@class='people_list']/li")
    WebElement followNames;
    private HashSet<String> followers = new HashSet<>();

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

        PropertyConfigurator.configure("log4j.properties");
    }

    public MainPage openMainPage() {
        return this;
    }

    public MainPage dealWithTutorial() {
        if (exists(notShowTutorialAgainCheckBox)) {
            Log.info("Closing tutorial");
            clickElement(notShowTutorialAgainCheckBox);
            clickElement(skipTutorialButton);
            waitUntilMainPageIsLoaded();
            clickElement(closeBetaMessage);
        }
        return this;
    }

    // Waits until title of our 'What works' Panel appears on the screen
    public MainPage waitUntilMainPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(whatWorksButton);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public MainPage openPostPanel() {
        clickElement(postButton);
        return this;
    }

    public MainPage openMedicinePanel() {
        clickElement(medicineButton);
        return this;
    }

    public MainPage openMilestonePanel() {
        clickElement(progressButton);
        return this;
    }

    public MainPage openSymptomsPanel() {
        clickElement(symptomsButton);
        return this;
    }

    public MainPage openWhatWorksButtonPanel() {
        clickElement(whatWorksButton);
        return this;
    }

    public MainPage openMDRatingButtonPanel() {
        clickElement(mdRatingButton);
        return this;
    }

    public MainPage clikToSeeMoreGraphsButton() {
        clickElement(SeeMoreGraphsButton);
        return this;
    }

    public MainPage openQuestionButtonPanel() {
        clickElement(questionButton);
        return this;
    }

    public boolean isOnMainPage() {
        Log.info("Wait for load Main page");
        waitUntilMainPageIsLoaded();
        return exists(whatWorksButton);
    }

    public MainPage selectMyProfile() {
        clickElement(cogwheelButton);
        clickElement(myProfilesButton);
        return this;
    }

    public MainPage selectMyAccount() {
        clickElement(cogwheelButton);
        clickElement(myAccountButton);
        return this;
    }

    public boolean isLoggedIn() {

        return exists(cogwheelButton);
    }

    public MainPage logOut() {
        Log.info("Loggin Out");
        clickElement(cogwheelButton);
        clickElement(logOutButton);
        return this;
    }

    //For Following tests
    public void openConnectPeopleThisConditionProfile() {
        Log.info("Open connect people with this condition 1 profile");
        clickElement(connectPeopleThisCondition1Button);
    }

    public boolean isFollowingNamePresents(String name) {
        Log.info("Assert that new following name presents/not presents");
        try {
            String[] arrName = name.split(" ");
            webDriver.findElement(By.xpath("//div[@class='panel panel-primary']/../div[7]//li[last()]//span[@class='profileName ng-binding'][contains(text()," + arrName[0] + ")]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public MainPage openFollow() {
        Log.info("Open first following profile");
        clickElement(firstFollowed);
        return this;
    }

    public String getFollowName() {
        Log.info("Profile name memorization");
        return firstFollowed.getText();
    }

    public MainPage openPostNameLink(WebElement current) {
        Log.info("Click post link to profile");
        clickElement(current);
        return this;
    }

    public void chooseConditionForDoctor(String condition) {
        Log.info("Choosing condition for doctor");
        setElementText(viewConditionFieldForDoctor, condition);
    }

    public MainPage chooseConditionFromDropDown() {
        Log.info("Choosing condition for doctor from dropdown");
        clickElement(dropDownConditionDoctor);
        return this;
    }

    public MainPage clickViewButton() {
        Log.info("Click on view button");
        clickElement(viewButton);
        return this;
    }

    public MainPage clickMyHomeButton() {
        clickElement(myHomeButton);
        return this;
    }

// Methods for verifying items on sent upper post

    public boolean isMyHomeExists() {
        return (exists(myHomeButton));
    }

    public Boolean verifyTextFromSentPost(String text) {
        return verifyTextBoolean(SentPostText, text);
    }

    public boolean getRequiredFieldsMessage() {
        return exists(requiredFieldsMessage);
    }

    //method for waiting REQUIRED FIELDS
    public MainPage waitForErrorMessage() throws IOException, InterruptedException {
        waitUntilElementIsLoaded(requiredFieldsMessage);
        return this;
    }

    public void fillSet() {
        List<WebElement> followList;
        try {
            followList = webDriver.findElements(By.xpath("//ul[@class='people_list']/li"));
            if (followList != null)
                for (WebElement current : followList) {
                    String name = current.getText();
                    name = name.replace('\n', ' ');
                    Log.info(name + " is adding to follow name set");
                    followers.add(name);
                }
        } catch (NoSuchElementException e) {
            Log.info("No followers");
        }
    }

    public void addMyDoctorNameToFillSet() {
        String strMyName = myNameDoctor.getText();
        Log.info(strMyName + " is adding to follow name set");
        followers.add(strMyName);
    }

    public void addMyUserNameToFillSet() {
        String strMyName = myNameUser.getText();
        Log.info(strMyName + " is adding to follow name set");
        followers.add(strMyName);
    }

    public void mouseScrolling() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
    robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
}
    //Method returns true if it finds a new name to follow from posts and goes to his profile page and returns false
    //if there are no new names to add to follow
    public boolean addNewFollowerFromPost() {
        mouseScrolling();
        List<WebElement> postNamesList = webDriver.findElements(By.xpath("//div[@class='panel story-panel ng-scope panel-default']//div[@class='post-owner-timestamp-wrapper']//span[@class='profileName post-owner ng-binding']"));
        int count = 0;
        for (WebElement current : postNamesList) {
            String name = current.getText();
            if (!isFollowNameExists(name)) {
                Log.info("New name for follow: " + name);
                Log.info("Click post number " + count); //post number to click
                postNamesList.get(count).click();
                return true;
            }
            count++;
        }
        Log.info("No names to add to follow");
        Log.info(count + " posts was checked");//When we open the main page only 10 posts are loaded
        return false;
    }
    //Method returns true if a name from posts is contained in the list People You Are Following
    public boolean isFollowNameExists(String name){
        return followers.contains(name);
    }

}