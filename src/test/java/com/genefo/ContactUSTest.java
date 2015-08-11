package com.genefo;

import com.genefo.pages.ContactUSPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Iakov Volf on 6/2/2015.
 */
public class ContactUSTest extends TestBase {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    public WebDriverWait wait;
    ContactUSPage contactUSPage;
    private boolean acceptNextAlert = true;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        contactUSPage = PageFactory.initElements(driver, ContactUSPage.class);

        try {
            contactUSPage.openContactPage(driver, baseUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"}, description = "Sending message")
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


}