package com.genefo;

import com.genefo.pages.ContactUSPage;
import com.genefo.util.PropertyLoader;
import org.apache.log4j.Logger;
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

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Iakov Volf on 6/2/2015.
 */
public class ContactUSTest {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    public WebDriverWait wait;
    public WebDriver driver;
    public String baseUrl;
    ContactUSPage contactUSPage;
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
        //this.driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        contactUSPage = PageFactory.initElements(driver, ContactUSPage.class);

        try {
            contactUSPage.openContactPage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void SendMessageTest() {
        contactUSPage
                .fillEmailField("jakoff+33@gmail.com")
                .fillFirstNameField("Patient")
                .fillMessageField("QA test")
                .clickOnSendButton();
        assertTrue("No confirmation Message", contactUSPage.messageSentSuccesifully());
        Reporter.log("Message sent successfuly - confirmation is appeared");

    }

    @Test(groups = {"smoke", "negative"})
    public void SendMessageWithoutEmailTest() throws InterruptedException {
        contactUSPage
                .fillEmailField("jakoff+33@gmail.com")
                .clickOnNameField();
        assertTrue("Sent Button is not disabled", contactUSPage.sendButtonDisabled());
        Reporter.log("Send Button is disable, if First name is empty");
        contactUSPage.fillFirstNameField("Patient");
        assertTrue("Sent Button is disabled", contactUSPage.sendButtonEnaabled());
        Reporter.log("Send Button is enabled, after filling First name");
        contactUSPage.fillMessageField("QA test")
                .clickOnSendButton();
        assertTrue("No confirmation Message", contactUSPage.messageSentSuccesifully());
        Reporter.log("Message sent successfuly - confirmation is appeared");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}