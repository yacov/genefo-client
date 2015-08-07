package com.genefo;

import com.genefo.pages.DataProviders;
import com.genefo.pages.LoginPage;
import com.genefo.pages.MDRatingOnMainPage;
import com.genefo.pages.MainPage;
import com.genefo.util.PropertyLoader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Л on 5/30/2015.
 */
public class MDRatingTest {
    private static String EMAIL = "jakoff+444@gmail.com";
    private static String PASSWORD = "111111";
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public MDRatingOnMainPage mdRatingOnMainPage;
    public String baseUrl;

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
        PropertyConfigurator.configure("log4j.properties");
        //this.driver = new FirefoxDriver();
        // wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        mdRatingOnMainPage = PageFactory.initElements(driver, MDRatingOnMainPage.class);

        try {
            loginPage.openLoginPage(driver, baseUrl)
                    .login(EMAIL, PASSWORD);
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue(mainPage.isOnMainPage());
            mainPage.waitUntilMainPageIsLoaded()
                    .openMDRatingButtonPanel();
            mdRatingOnMainPage.isOnMDRatingPanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(groups = {"smoke", "positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRatingPostSuccess(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
        Reporter.log("SendMDRatingPostSuccess test");
        Log.info("SendMDRatingPostSuccess test");
        try {
            int number = Integer.parseInt(starNumber);
            mdRatingOnMainPage
                    .fillMedicalFacilityField(facility_name)
                    .fillPhysicianFields(physician_fname, physician_lname)
                    .clickOnAnyStar(number)
                    .fillTextField(text)
                    .sendPost()
                    .waitUntilNewPostisLoaded();
            sleep(2000);
            Assert.assertTrue(mdRatingOnMainPage.isThirdStarYellow(number), "Matched star is not yellow");
            Assert.assertTrue(mdRatingOnMainPage.isFacilityNameCorrect(facility_name), "Facility name is not correct");
            Assert.assertTrue(mdRatingOnMainPage.isPhysicianNameCorrect(physician_fname + " " + physician_lname), "Physician name is not correct");
            Assert.assertTrue(mdRatingOnMainPage.isTextCorrect(text), "Something wrong with post");

        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("MDRating post was sent successfully");
    }

    @Test(groups = {"smoke", "negative"}, dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRatingPostTestWithoutFacilityName(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
        Reporter.log("sendMDRatingPostTestWithoutFacilityName test");
        Log.info("sendMDRatingPostTestWithoutFacilityName test");
        int number = Integer.parseInt(starNumber);
        try {
            mdRatingOnMainPage
                    .fillPhysicianFields(physician_fname, physician_lname)
                    .clickOnAnyStar(number)
                    .fillTextField(text)
                    .sendPost();
            mainPage.waitForErrorMessage();
            assertTrue("No alert message", mainPage.getRequiredFieldsMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Post is not sent");
    }

    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRatingPostWithoutPhysicianFName(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
        Reporter.log("sendMDRatingPostWithoutPhysicianFName test");
        Log.info("sendMDRatingPostWithoutPhysicianFName test");
        int number = Integer.parseInt(starNumber);
        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField(facility_name)
                    .fillPhysicianFields("", physician_lname)
                    .clickOnAnyStar(number)
                    .fillTextField(text)
                    .sendPost();
            mainPage.waitForErrorMessage();
            assertTrue("No alert message", mainPage.getRequiredFieldsMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Post is not sent");
    }

    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRatingPostWithoutPhysicianLName(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
        Reporter.log("sendMDRatingPostWithoutPhysicianLName test");
        Log.info("sendMDRatingPostWithoutPhysicianLName test");
        int number = Integer.parseInt(starNumber);
        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField(facility_name)
                    .fillPhysicianFields(physician_fname, "")
                    .clickOnAnyStar(number)
                    .fillTextField(text)
                    .sendPost();
            mainPage.waitForErrorMessage();
            assertTrue("No alert message", mainPage.getRequiredFieldsMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Post is not sent");
    }

    /*@Test (groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRatingWithEmptyPost(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
        int number = Integer.parseInt(starNumber);
        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField(facility_name)
                    .fillPhysicianFields(physician_fname, physician_lname)
                    .clickOnAnyStar(number)
                    .sendPost();
            mainPage.waitForErrorMessage();
            assertTrue("No alert message", mainPage.getRequiredFieldsMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Post is not sent");
    }*/
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRatingPostWithoutRating(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
        Reporter.log("sendMDRatingPostWithoutRating test");
        Log.info("sendMDRatingPostWithoutRating test");
        int number = Integer.parseInt(starNumber);
        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField(facility_name)
                    .fillPhysicianFields(physician_fname, physician_lname)
                    .fillTextField(text)
                    .sendPost();
            mainPage.waitForErrorMessage();
            assertTrue("No alert message", mainPage.getRequiredFieldsMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Post is not sent");
    }

    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRating1LatterPost(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
        Reporter.log("sendMDRating1LatterPost test");
        Log.info("sendMDRating1LatterPost test");
        int number = Integer.parseInt(starNumber);
        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField(facility_name)
                    .fillPhysicianFields(physician_fname, physician_lname)
                    .clickOnAnyStar(number)
                    .fillTextField("p")
                    .sendPost()
                    .waitUntilNewPostisLoaded();
            sleep(2000);
            Assert.assertTrue(mdRatingOnMainPage.isThirdStarYellow(number), "Matched star is not yellow");
            Assert.assertTrue(mdRatingOnMainPage.isFacilityNameCorrect(facility_name), "Facility name is not correct");
            Assert.assertTrue(mdRatingOnMainPage.isPhysicianNameCorrect(physician_fname + " " + physician_lname), "Physician name is not correct");
            Assert.assertTrue(mdRatingOnMainPage.isTextCorrect("p"), "Something wrong with the post");

        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("1 latter post was sent successfully");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

