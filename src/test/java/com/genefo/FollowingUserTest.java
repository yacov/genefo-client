package com.genefo;

import com.genefo.pages.LoginPage;
import com.genefo.pages.MainPage;
import com.genefo.pages.PublicProfilePage;
import com.genefo.util.PropertyLoader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Л on 6/2/2015.
 */
public class FollowingUserTest {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public WebDriver driver;
    public WebDriverWait wait;
    public String baseUrl;
    LoginPage loginPage;
    MainPage mainPage;
    PublicProfilePage publicProfilePage;

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
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        publicProfilePage = PageFactory.initElements(driver, PublicProfilePage.class);

        try {
            loginPage.openLoginPage(driver, baseUrl)
                    .waitUntilLoginPageIsLoaded()
                    .login("ri-lopatina@yandex.ru", "111111");
            mainPage.waitUntilMainPageIsLoaded();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void addFollowSuccessFromConnectPeopleConditionField() {
        Reporter.log("AddFollowSuccessFromConnectPeopleConditionField test");
        Log.info("AddFollowSuccessFromConnectPeopleConditionField test");
        mainPage.isOnMainPage();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainPage.openConnectPeopleThisConditionProfile();
        publicProfilePage.isOnPublicProfilePage();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String name = publicProfilePage.getPublicProfileName();
        publicProfilePage.addFollow();
        assertTrue(publicProfilePage.isUnFollowPanelOnPage());
        publicProfilePage.clickOnHome();
        mainPage.isOnMainPage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(mainPage.isFollowingNamePresents(name));
        Reporter.log("New profile was added to following successfully from ConnectPeopleThisConditionProfile");
    }

    @Test(groups = {"smoke", "positive"})
    public void unFollowSuccess() {
        Reporter.log("UnFollowSuccess test");
        Log.info("UnFollowSuccess test");
        mainPage.isOnMainPage();
        String name = mainPage.getFollowName();
        mainPage.openFollow();
        publicProfilePage.isOnPublicProfilePage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publicProfilePage.removeFollow();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(publicProfilePage.plusFollowPanel());
        publicProfilePage.clickOnHome();
        mainPage.isOnMainPage();
        assertFalse(mainPage.isFollowingNamePresents(name));
        Reporter.log("New profile was unfollowed successfully");
    }

    @Test(groups = {"smoke", "positive"})
    public void addFollowSuccessFromPosts() {
        Reporter.log("AddFollowSuccessFromPosts test");
        Log.info("AddFollowSuccessFromPosts test");
        mainPage.isOnMainPage();
        mainPage.fillSet();
        mainPage.addMyUserNameToFillSet();
        if (!mainPage.addNewFollowerFromPost())
            return;
        publicProfilePage.isOnPublicProfilePage();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String name = publicProfilePage.getPublicProfileName();
        publicProfilePage.addFollow();
        assertTrue(publicProfilePage.isUnFollowPanelOnPage());
        publicProfilePage.clickOnHome();
        mainPage.isOnMainPage();
        assertTrue(mainPage.isFollowingNamePresents(name));
        Reporter.log("New profile was added to follow successfully from posts");

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}