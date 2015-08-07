package com.genefo;

import com.genefo.pages.DocAcInfPage;
import com.genefo.pages.LoginPage;
import com.genefo.pages.MainPage;
import com.genefo.pages.ProfileDoctorPage;
import com.genefo.util.PropertyLoader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Oleg on 31.05.2015.
 */
public class DocAcInfTest {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public String baseUrl;
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;
    public MainPage mainPage;

    public ProfileDoctorPage profileDoctorPage;
    public DocAcInfPage docAcInfPage;
    public String EmailNickname; // Keeps the part of email before sign @
    private boolean acceptNextAlert = true;
    private String EMAIL1 = "osh_il+21@yahoo.com";
    private String EMAIL2 = "osh_il+19@yahoo.com";

    @BeforeClass(groups = {"smoke"}, alwaysRun = true)
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
        PropertyConfigurator.configure("log4j.properties");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
        docAcInfPage = PageFactory.initElements(driver, DocAcInfPage.class);

        try {
            loginPage.openLoginPage(driver, baseUrl);
            loginPage.login(EMAIL1, LoginTest.PASSWORD);
            mainPage.waitUntilMainPageIsLoaded();
            mainPage.selectMyAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod(groups = {"smoke"}, alwaysRun = true)
    public void beforeMethodSetUp() {
        try {
            if (profileDoctorPage.isOnProfileDoctorPage() == false) {
                mainPage.selectMyAccount();
            }
            Log.info("Wait for load Profile HCP page");
            profileDoctorPage.openProfileDoctorPage(driver, baseUrl);
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            profileDoctorPage.clickOnEditAccInf();
            Log.info("Wait for load DocAcInf page");
            docAcInfPage.waitUntilDocAcInfPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(groups = {"smoke", "positive"})
    public void UpdateEditAccInfSuccess() {
        Log.info("Checking that all correct data added successfully");
        try {
            docAcInfPage
                    .fillPasswordField(LoginTest.PASSWORD)
                    .fillEmailField(EMAIL2)
                    .clickOnSaveButton()
                    .waitUntilEnterYourCurrentPassIsLoaded()
                    .fillCurrentPasswordField(LoginTest.PASSWORD)
                    .clickOnCurSaveButton();
            assertTrue("Confirmation message 'Account Login Information Updated' didn't appeared", docAcInfPage.alertMessageAccountSuccess());
            mainPage.selectMyAccount();
            Log.info("Wait for load Profile HCP page");
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            profileDoctorPage.clickOnEditAccInf();
            Log.info("Wait for load DocAcInf page");
            docAcInfPage.waitUntilDocAcInfPageIsLoaded();
            docAcInfPage
                    .fillPasswordField(LoginTest.PASSWORD)
                    .fillEmailField(EMAIL1)
                    .clickOnSaveButton()
                    .waitUntilEnterYourCurrentPassIsLoaded()
                    .fillCurrentPasswordField(LoginTest.PASSWORD)
                    .clickOnCurSaveButton();
            assertTrue("Confirmation message 'Account Login Information Updated' didn't appeared", docAcInfPage.alertMessageAccountSuccess());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("all correct data added successful");

    }

    @Test(groups = {"smoke", "positive"})
    public void ClickOnCancel() {
        Log.info("Checking that operation is canceled");
        try {
            docAcInfPage
                    .clickOnCancel();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("operation is canceled successful");
    }

    @Test(groups = {"smoke", "negative"})
    public void EditAccInfEmptyFiels() {
        Log.info("Checking that empty fields are not updated");
        try {
            docAcInfPage
                    .fillEmailField("")
                    .fillPasswordField("")
                    .clickOnTitle();
            assertTrue("The Email is valid", docAcInfPage.alertMessageInvalidEmail());
            assertTrue("The Password is valid", docAcInfPage.alertMessageInvalidPassword());
            assertTrue("The current page is changed", docAcInfPage.isOnDocAcInfPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("empty fields are not updated successful");
    }


    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass(groups = {"smoke"}, alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
