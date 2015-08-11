package com.genefo;

import com.genefo.pages.LoginPage;
import com.genefo.pages.MainPage;
import com.genefo.pages.PostOnMainPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by alex on 01/06/2015.
 */
public class PostOnMainTest extends TestBase {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public PostOnMainPage postOnMainPage;
    private boolean acceptNextAlert = true;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        //PropertyConfigurator.configure("log4j.properties");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        postOnMainPage = PageFactory.initElements(driver, PostOnMainPage.class);

        try {
            loginPage.openLoginPage(driver, baseUrl);
            loginPage.login("telrantests@yahoo.com", "12345.com");
            mainPage.waitUntilMainPageIsLoaded();
            //  .openPostPanel();
            // postOnMainPage.waitUntilPostPanelIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforemethodsetup() {
        //mainPage.openMainPage(driver, baseUrl);
        mainPage.waitUntilMainPageIsLoaded()
                .openPostPanel();
        postOnMainPage.waitUntilPostPanelIsLoaded();
    }


    // @Test(groups = {"negative"}, description = "Negative test: 'Post Category' empty post")
    public void SendEmptyPostTest() {
        Log.info("---------------------------------------------------------------");
        Log.info("Negative test: 'Post Category' empty post. ");
        String text = "";

        try {
            postOnMainPage
                    .fillTextField(text)
                    .sendPost();


            assertFalse("Post was sent despite of absence of text", postOnMainPage.verifyTextFromSentPost(text));
            Reporter.log("Negative Test: Publishing of empty post was not Successful as planned");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Test(groups = {"smoke", "negative"}, description = "Negative test: 'Post Category' one letter post. ")
    public void SendOneLetterPostTest() {
        Log.info("---------------------------------------------------------------");
        Log.info("Negative test: 'Post Category' one letter post. ");
        String text = "A";

        try {
            postOnMainPage
                    .fillTextField(text)
                    .sendPost();
            sleep(2000);
            assertFalse("Post was sent despite of lack of letters in post ", postOnMainPage.verifyTextFromSentPost(text));
            Reporter.log("Negative Test: Publishing of one letter post was not Successful as planned");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(groups = {"smoke", "positive"}, description = "Positive test: 'Post Category' post: ")
    public void SendPostSuccessTest() {
        Date date = new Date();
        String text = "My 'Post Category' post at " + date.toString();
        Log.info("---------------------------------------------------------------");
        Log.info("Positive test: 'Post Category' post: " + text);
        try {
            postOnMainPage
                    .fillTextField(text)
                    .sendPost();
            sleep(2000);

            assertTrue("It is not the same post that we sent", postOnMainPage.verifyTextFromSentPost(text));
            assertTrue("No pencil picture in top right corner", postOnMainPage.isPencilPictureExists());
            Reporter.log("Publishing of post was Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
