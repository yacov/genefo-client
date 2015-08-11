package com.genefo;

import com.genefo.pages.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Oleg on 20.06.2015.
 */
public class ProfileDoctorTest extends TestBase {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    public LoginPage loginPage;
    public MainPage mainPage;
    public ProfileDoctorPage profileDoctorPage;
    public DocAcInfPage docAcInfPage;
    public DocBasInfPage docBasInfPage;
    public DocProfInfPage docProfInfPage;
    private boolean acceptNextAlert = true;

    @BeforeClass(alwaysRun = true)
    public void setup() {

        PropertyConfigurator.configure("log4j.properties");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
        docAcInfPage = PageFactory.initElements(driver, DocAcInfPage.class);
        docBasInfPage = PageFactory.initElements(driver, DocBasInfPage.class);
        docProfInfPage = PageFactory.initElements(driver, DocProfInfPage.class);

        try {
            loginPage.openLoginPage(driver, baseUrl);
            loginPage.login(LoginTest.USER, LoginTest.PASSWORD);
            mainPage.waitUntilMainPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethodSetUp() {
        Log.info("Opening Profile Doctor Page");
        driver.get(baseUrl + "/account_hcp");
        profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
    }

    @Test(groups = {"smoke", "positive"})
    public void DiscoverYourHomePage() {
        Log.info("Checking that home page discovered successfully");
        try {
            profileDoctorPage.clickOnDisYourHP();
            assertTrue("The Main Page doesn't open", mainPage.isOnMainPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("home page discovered successfully");
    }

    @Test(groups = {"smoke", "positive"})
    public void EditAccountInformation() {
        Log.info("Checking that Account Information Page open");
        try {
            profileDoctorPage.clickOnEditAccInf();
            assertTrue("The Account Information Page doesn't open", docAcInfPage.isOnDocAcInfPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Account Information Page open");

    }

    @Test(groups = {"smoke", "positive"})
    public void EditBasicInformation() {
        Log.info("Checking that Basic Information Page open");
        try {
            profileDoctorPage.clickOnEditBasInf();
            assertTrue("The Basic Information Page doesn't open", docBasInfPage.isOnDocBasInfPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Basic Information Page open");

    }

    @Test(groups = {"smoke", "positive"})
    public void EditHealthcareProfInf() {
        Log.info("Checking that Healthcare Professional Information Page open");
        try {
            profileDoctorPage.clickOnHealInf();
            assertTrue("The Healthcare Professional Information Page doesn't open", docProfInfPage.isOnDocProfInfPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Healthcare Professional Information Page open");
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

}