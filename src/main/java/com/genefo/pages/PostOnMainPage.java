package com.genefo.pages;

import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by alex on 01/06/2015.
 */
public class PostOnMainPage extends Page {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    // Serves as indication that we are on 'Post' Panel. We need double quota inside. Do not remove it, please
    @FindBy(xpath = "//a[@class='ng-scope active']//*[contains(@ng-class, \"isType('general')\")]")

    WebElement postButtonHighLighted;
    // text field for posting
    @FindBy(xpath = "//textarea[@name = 'bio']")
    WebElement postField;
    @FindBy(xpath = "//button[@id='submit'][contains(text(),'Post')]")
    WebElement submitButton;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//span[@class='ng-binding ng-isolate-scope']")
    WebElement SentPostText;

    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='post-type']//i[contains(@class,'pencil')]")
    WebElement PencilPicture;


    public PostOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Waits until title of our 'What works' Panel appears on the screen
    public void waitUntilPostPanelIsLoaded() {
        Log.info("Waiting Until Post Panel Is Loaded");
        try {
            waitUntilElementIsLoaded(postButtonHighLighted);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Checks that title of our 'Post' Panel have appeared on the screen so we can work with it.
    public boolean isOnPostPanel() {
        waitUntilPostPanelIsLoaded();
        return exists(postButtonHighLighted);
    }


    // Checks that title of our 'Post' Panel have appeared on the screen so we can work with it.
    public boolean isPencilPictureExists() {
        Log.info("Checking that pencil icon in top right corner is present ");
        try {
            waitUntilElementIsLoaded(PencilPicture);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return exists(PencilPicture);
    }

    public PostOnMainPage fillTextField(String post) {
        Log.info("Filling  post field with " + post);
        setElementText(postField, post);
        return this;
    }

    public PostOnMainPage sendPost() {
        Log.info("Sending post");
        clickElement(submitButton);
        return this;
    }

    public Boolean verifyTextFromSentPost(String text) {
        return verifyTextBoolean(SentPostText, text);
    }

    public PostOnMainPage createAndSendPost() {

        String text = "New text for testing purpose";
        Log.info("'Posting' post: " + text);
        try {

            fillTextField(text);
            sendPost();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }


}

