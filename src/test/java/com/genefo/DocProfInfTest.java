package com.genefo;

import com.genefo.pages.DocProfInfPage;
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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oleg on 05.06.2015.
 */
public class DocProfInfTest {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    public LoginPage loginPage;
    public MainPage mainPage;
    public ProfileDoctorPage profileDoctorPage;
    public DocProfInfPage docProfInfPage;
    public WebDriver driver;
    public String baseUrl;
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
        PropertyConfigurator.configure("log4j.properties");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
        docProfInfPage = PageFactory.initElements(driver, DocProfInfPage.class);

        try {
            loginPage.openLoginPage(driver, baseUrl);
            loginPage.login(LoginTest.USER, LoginTest.PASSWORD);
            mainPage.waitUntilMainPageIsLoaded();
            mainPage.selectMyAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void beforeMethodSetUp() {
        try {
            Log.info("Opening Profile HCP page");
            if (profileDoctorPage.isOnProfileDoctorPage() == false) {
                docProfInfPage.clickOnDoneButton();
            }
            Log.info("Wait for load Profile HCP page");
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            profileDoctorPage.clickOnHealInf();
            Log.info("Wait for load DocProfInf page");
            docProfInfPage.waitUntilDocProfInfPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void EditProfInfSuccess() {
        Log.info("Checking that all correct data added successfully");
        try {
            docProfInfPage
                    .fillSpecialtiesField("Oncology")
                    .clickOnAddSpecialtiesButton()
                    .fillSubspecialtiesField("insomnia")
                    .clickOnAddSubspecialtiesButton()
                    .fillTitlesField("doctor")
                    .clickOnAddTitlesButton()
                    .fillAreasField("Canavan")
                    .clickOnTooltipAreas()
                    .clickOnAddAreasButton()
                    .fillWorkPlacesNameField("assuta")
                    .fillWorkPlacesLocationField("a")
                    .clickOnTooltipWP()
                    .clickOnAddWorkPlacesButton()
                    .clickOnDoneButton();
            Assert.assertTrue(profileDoctorPage.isOnProfileDoctorPage(), "Profile HCP Page doesn't open");
            Reporter.log("all correct data added successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"})
    public void AddWorkPlaceInf() {
        Log.info("Checking that work place information added");
        try {
            docProfInfPage
                    .fillWorkPlacesNameField("Ikhilov")
                    .fillWorkPlacesLocationField("J")
                    .clickOnTooltipWP()
                    .clickOnAddWorkPlacesButton()
                    .clickOnDoneButton();
            Assert.assertTrue(profileDoctorPage.isOnProfileDoctorPage(), "Profile HCP Page doesn't open");
            Reporter.log("work place information added successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void AddEmptyFields() {
        Log.info("Checking that all empty fields added");
        try {
            docProfInfPage
                    .fillSpecialtiesField("")
                    .fillSubspecialtiesField("")
                    .fillTitlesField("")
                    .fillAreasField("")
                    .fillWorkPlacesNameField("")
                    .fillWorkPlacesLocationField("")
                    .clickOnDoneButton();
            Assert.assertTrue(profileDoctorPage.isOnProfileDoctorPage(), "Profile HCP Page doesn't open");
            Reporter.log("all empty fields added successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void AddEmptySpecialties() {
        Log.info("Checking that Specialties empty field added");
        try {
            docProfInfPage
                    .fillSpecialtiesField("");
            Assert.assertTrue(docProfInfPage.isAddSpecButtonExists() == false, "The Add Specialties Button Enable");
            Reporter.log("Specialties Button disnable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void AddDelSpecialties() {
        Log.info("Checking that Specialties added and deleted");
        try {
            docProfInfPage
                    .fillSpecialtiesField("abcd")
                    .clickOnAddSpecialtiesButton()
                    .clickOnDelSpecButton()
                    .clickOnConfSpecButton();
            Assert.assertTrue(docProfInfPage.isSpecExists(), "The specialty exists");
            Reporter.log("Specialties added and deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"negative"})
    public void AddEmptyLocationWPandFillNameWPFields() {
        Log.info("Checking that Work Place Name added and Work Place Location not");
        try {
            docProfInfPage
                    .fillWorkPlacesNameField("Assuta")
                    .fillWorkPlacesLocationField("");
            Assert.assertTrue(docProfInfPage.isAddWorkPlacesDisButtonExists(), "The button add work place is clickable");
            Reporter.log("The button add work place is not clickable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"negative"})
    public void AddEmptyLocationWPandNameWPFields() {
        Log.info("Checking that Work Place Name and Work Place Location do not added ");
        try {
            docProfInfPage
                    .fillWorkPlacesNameField("")
                    .fillWorkPlacesLocationField("");
            Assert.assertTrue(docProfInfPage.isAddWorkPlacesDisButtonExists(), "The button add work place is clickable");
            Reporter.log("The button add work place is not clickable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"})
    public void DeleteLocationWPandNameWP() {
        Log.info("Checking that Work Place Name and Work Place Location added and deleted");
        try {
            sleep();
            docProfInfPage
                    .fillWorkPlacesNameField("Assuta");
            //sleep();
            docProfInfPage
                    .fillWorkPlacesLocationField("t")
                    .clickOnTooltipWP()
                    .clickOnAddWorkPlacesButton()
                    .clickOnDelWorkPlacesButton()
                    .clickOnConfWorkPlacesButton();
            Assert.assertTrue(docProfInfPage.isLocationWPExists(), "Location is not disappear");
            Reporter.log("Work Place Name and Work Place Location added and deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"})
    public void DeleteAllDataIfExist() {
        Log.info("Checking that All Data deleted");
        try {
            sleep();
            if (docProfInfPage.isSpecExists()) {
                docProfInfPage
                        .clickOnDelSpecButton()
                        .clickOnConfSpecButton();
            }
            if (docProfInfPage.isSubspecExists()) {
                docProfInfPage
                        .clickOnDelSubspecButton()
                        .clickOnConflSubspecButton();
            }
            if (docProfInfPage.isTitlesExists()) {
                docProfInfPage
                        .clickOnDelTitleButton()
                        .clickOnConfTitleButton();
            }
            if (docProfInfPage.isAreasExists()) {
                docProfInfPage
                        .clickOnDelAreasButton()
                        .clickOnConfAreasButton();
            }
            if (docProfInfPage.isLocationWPExists()) {
                docProfInfPage
                        .clickOnDelWorkPlacesButton()
                        .clickOnConfWorkPlacesButton();
            }

            docProfInfPage
                    .clickOnDoneButton();
            Assert.assertTrue(profileDoctorPage.isOnProfileDoctorPage(), "Profile HCP Page doesn't open");
            Reporter.log("All Data deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
