package com.genefo;

import com.genefo.pages.LoginPage;
import com.genefo.pages.MainPage;
import com.genefo.pages.WhatWorksOnMainPage;
import com.genefo.util.PropertyLoader;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by alex on 5/29/2015.
 */
public class WhatWorksOnMainTest {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public WebDriver driver;
    public WebDriverWait wait;
    public String baseUrl;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public WhatWorksOnMainPage whatWorksOnMainPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        baseUrl = PropertyLoader.loadProperty("site.url");
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "ru");
        String Xport = System.getProperty("lmportal.xvfb.id", ":0");
        final File firefoxPath = new File(System.getProperty(
                "lmportal.deploy.firefox.path", "/usr/bin/firefox"));
        FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
        firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);

        // Start Firefox driver
        driver = new FirefoxDriver(firefoxBinary, null);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        whatWorksOnMainPage = PageFactory.initElements(driver, WhatWorksOnMainPage.class);

        try {
            loginPage.openLoginPage(driver, baseUrl);
            loginPage.login("telrantests@yahoo.com", "12345.com");
            mainPage.waitUntilMainPageIsLoaded();
            // .openWhatWorksButtonPanel();
            //whatWorksOnMainPage.waitUntilWhatWorksPanelIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }   // We fill data structures, that we defined in whatWorksOnMainPage class.
        whatWorksOnMainPage.defineOptionsLocators();
    }


    @BeforeMethod
    public void beforemethodsetup() {

        mainPage.openMainPage(driver, baseUrl);
        mainPage.waitUntilMainPageIsLoaded()
                .openWhatWorksButtonPanel();
        whatWorksOnMainPage.waitUntilWhatWorksPanelIsLoaded();
    }

    @DataProvider
    private Object[][] myProvider() {
        return new Object[][]{
                {"Therapy", Items.LAST_ITEM_FROM_LIST},// just means that we want to choose the last item from dropdown list
                {"Equipment", Items.LAST_ITEM_FROM_LIST},
                {"Nutrition", Items.LAST_ITEM_FROM_LIST},
                {"Exercises", Items.LAST_ITEM_FROM_LIST},
                {"Alternative", Items.LAST_ITEM_FROM_LIST},
                {"Other", Items.LAST_ITEM_FROM_LIST},

                {"Therapy", Items.FIRST_ITEM_FROM_LIST},// just means that we want to choose the first item from dropdown list
                {"Equipment", Items.FIRST_ITEM_FROM_LIST},
                {"Nutrition", Items.FIRST_ITEM_FROM_LIST},
                {"Exercises", Items.FIRST_ITEM_FROM_LIST},
                {"Alternative", Items.FIRST_ITEM_FROM_LIST},
                {"Other", Items.FIRST_ITEM_FROM_LIST},

                {"Therapy", Items.FOURTH_ITEM_FROM_LIST}, // just means that we want to choose the fourth item from dropdown list
                {"Equipment", Items.FOURTH_ITEM_FROM_LIST},
                {"Nutrition", Items.FOURTH_ITEM_FROM_LIST},
                {"Exercises", Items.FOURTH_ITEM_FROM_LIST},
                {"Alternative", Items.FOURTH_ITEM_FROM_LIST},
                {"Other", Items.FOURTH_ITEM_FROM_LIST},
        };
    }

    // provider for negative tests where we do not need to choose item from dropdown list
    @DataProvider
    private Object[][] myNegativeProvider() {
        return new Object[][]{
                {"Therapy"},
                {"Equipment"},
                {"Nutrition"},
                {"Exercises"},
                {"Alternative"},
                {"Other"},
        };
    }


    // Negative test - Do not click on Category button.
    // Check that you are not able to send a post.
    @Test(groups = {"smoke", "negative"})
    public void EmptyCategoryTest() {
        Date date = new Date();
        String text = "My Empty Category Post at " + date.toString();
        Log.info("---------------------------------------------------------------");
        Log.info("Negative test: Not Selecting Category");
        try {
            whatWorksOnMainPage.clickOnItemList();
            // here we check that we can not choose from the list if our Category is empty
            assertFalse("Item List Is Chosable despite of 'Category' absence", whatWorksOnMainPage.verifyItemListIsChosen());
            Log.info("Hurra! Item from the list wasn't choosen, because Category is empty");
            whatWorksOnMainPage
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();
            sleep(2000); // wait  to see sent post.
            assertFalse("Post was sent despite of 'Category' absence", whatWorksOnMainPage.verifyTextFromSentPost(text));
            Log.info("Hurra! Post wasn't sent");
            Reporter.log("Checking that we can not send post - Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Negative tests - Click on Category button but do not click on item list.
    // Check that you are not able to send a post.
    // Category name is given by data provider.
    @Test(groups = {"smoke", "negative"}, dataProvider = "myNegativeProvider")
    public void EmptyListItemTest(String category) {
        Log.info("-------------------------------------------------------------------------");
        Log.info("Negative test: Selecting Category " + category + " but Not Selecting Item");
        Date date = new Date();
        String text = "My Empty Item Post at " + date.toString();
        try {
            whatWorksOnMainPage
                    .clickOnOption(category);// Here we cover the test that category button should be highlighted after clicking
            assertTrue("Category button is not highlighted after we click on it", whatWorksOnMainPage.isOptionHighLighted(category));
            whatWorksOnMainPage.clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();
            sleep(2000); // wait  to see sent post.
            assertFalse("Post was sent despite of Item absence", whatWorksOnMainPage.verifyTextFromSentPost(text));
            Reporter.log("Checking that we can not send post - Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Click on Category button then choose item from the item list.
    //  Check that you are able to send a post.
    // Category name and item number are given by data provider.
    @Test(groups = {"smoke", "positive"}, dataProvider = "myProvider")
    public void SendPostTest(String category, Items itemNumber) {
        Date date = new Date();
        String text = "My Post at " + date.toString();
        String otherItem = "My Other Item at " + date.toString();
        Log.info("-------------------------------------------------------------------------------------------------");
        Log.info("Positive test: Selecting Category " + category + " item number: " + itemNumber + " text: " + text);
        try {
            if (category.equals("Other")) // 'Other' option is different. It has no predetermined list
                whatWorksOnMainPage
                        .clickOnOption(category)
                        .fillItemForOtherOption(otherItem)
                        .clickOnAllStarsTogether()
                        .rateItThree()                //Click on the third star
                        .fillTextField(text)
                        .sendPost();
            else if (itemNumber == Items.LAST_ITEM_FROM_LIST) //We want to choose the last item from the dropdown list
            {
                whatWorksOnMainPage
                        .clickOnOption(category)
                        .clickOnItemList()
                        .waitUntilLastItemFromItemListIsLoaded()
                        .chooseLastItemFromItemList()
                        .clickOnAllStarsTogether()
                        .rateItThree()                //Click on the third star
                        .fillTextField(text)
                        .sendPost();
            } else            //We want to choose  item  with 'itemNumber' from the dropdown list
                whatWorksOnMainPage
                        .clickOnOption(category)
                        .clickOnItemList()
                        .waitUntilItemFromItemListIsLoaded((itemNumber.value))
                        .chooseItemFromItemList(itemNumber.value)
                        .clickOnAllStarsTogether()
                        .rateItThree()                //Click on the third star
                        .fillTextField(text)
                        .sendPost();

            sleep(2000); // wait  to see sent post.

            assertTrue("It is not the same post that we sent", whatWorksOnMainPage.verifyTextFromSentPost(text));
            assertTrue("It is not the same category that we chose", whatWorksOnMainPage.verifyCategoryExistsInSentPost(category));
            assertTrue("It is not the same star that we chose", whatWorksOnMainPage.verifyThirdStarCheckedInSentPost());
            assertTrue("It is not the same star that we chose", whatWorksOnMainPage.verifyFourthStarNonCheckedInSentPost());
            if (category.equals("Other"))
                assertTrue("It is not the same item that we sent", whatWorksOnMainPage.verifyOtherItemCorrectInSentPost(otherItem));
            else
                assertTrue("It is not the same item that we chose from the list", whatWorksOnMainPage.verifyListItemCorrectInSentPost());
            Reporter.log("Publishing of post was Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // We define variables of enum type to give meaning to numbers 0, 1 and 3
    // And then we use this variables instead of numbers
    public enum Items {
        LAST_ITEM_FROM_LIST(0), FIRST_ITEM_FROM_LIST(1), FOURTH_ITEM_FROM_LIST(4);
        private int value;

        Items(int value) {
            this.value = value;
        }
    }

}
