package com.genefo;

import com.genefo.pages.DataProviders;
import com.genefo.pages.LoginPage;
import com.genefo.pages.MainPage;
import com.genefo.pages.MilestoneOnMainPage;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Zizi, Christina and Mariya on 5/30/2015.
 */
public class MilestoneOnMainPageTest extends TestBase {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public MilestoneOnMainPage milestoneOnMainPage;
    public String age;
    public String month;
    public String year;
    public String post;
    public String textOtherField;
    public String type;
    public String milestone;


    @BeforeClass(alwaysRun = true)
    public void setup() {
        PropertyConfigurator.configure("log4j.properties");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        milestoneOnMainPage = PageFactory.initElements(driver, MilestoneOnMainPage.class);
        try {
            //Log.info("Opening Registration page");
            loginPage.openLoginPage(driver, baseUrl);
            loginPage.login("mili9@mail.ru", "999999");
            mainPage.waitUntilMainPageIsLoaded()

                    .openMilestonePanel();
            milestoneOnMainPage.waitUntilMilestonePanelIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
        milestoneOnMainPage.fillAllElementsAndItemsToMap();

    }


   /* @BeforeClass
    public void setup() {

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        milestoneOnMainPage= PageFactory.initElements(driver, MilestoneOnMainPage.class);
        try {
            loginPage.login("mili99@mail.ru", "999999");
            mainPage.waitUntilMainPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    @BeforeMethod(alwaysRun = true)
    public void beforeMethodSetUp() {
        mainPage.openMainPage(driver, baseUrl);
        mainPage.waitUntilMainPageIsLoaded()
                .openMilestonePanel();
        milestoneOnMainPage.waitUntilMilestonePanelIsLoaded();
    }


    @Test(groups = {"smoke", "positive"}, enabled = true, dataProviderClass = DataProviders.class, dataProvider = "loadTypesFromFile")
    public void SendMilestonePostDataDrivenTest(String _type, String _milestone, String _year, String _month) {
        type = _type;
        milestone = _milestone;
        year = _year;
        month = _month;
        age = year + " years " + month + " months";
        post = randomAlphabetic(100);
        Reporter.log("Testing Type: " + type + " , milestone: " + milestone + " , year: " + year + " , month: " + month);
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectItemOption()
                    .clickOnElement(milestone)
                    .clickOnYearsOption(year)
                    .clickOnMonthOption(month)
                    .fillTextField(post)
                    .sendPost()
                    .waitForPostLoaded();
            sleep(3000);
            assertTrue("Alert:'Milestone type is not correct'", milestoneOnMainPage.isTypeTrue(type));
            assertTrue("Alert:'Milestone is not correct'", milestoneOnMainPage.isMilestoneTrue(milestone));
            assertTrue("Alert:'The age is not correct'", milestoneOnMainPage.isAgeIsCorrect(age));
            assertTrue("Alert:'The text is not correct'", milestoneOnMainPage.isTextCorrect(post));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test(groups = {"smoke", "positive"}, enabled = true)
    public void SendOtherPostTest() {
        type = "Other";
        year = "10";
        month = "5";
        age = year + " years " + month + " months";
        post = randomAlphabetic(500);
        textOtherField = "ABCD";
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectOtherItemOption()
                    .fillOtherField(textOtherField)
                    .clickOnYearsOption(year)
                    .clickOnMonthOption(month)
                    .fillTextField(post)
                    .sendPost()
                    .waitForPostLoaded();
            sleep(3000);
            assertTrue("Alert:'Milestone type is not correct'", milestoneOnMainPage.isTypeTrue(type));
            assertTrue("Other field is not fill", milestoneOnMainPage.isOtherTextCorrect(textOtherField));
            assertTrue("Alert:'The age is not correct'", milestoneOnMainPage.isAgeIsCorrect(age));
            assertTrue("Alert:'The text is not correct'", milestoneOnMainPage.isTextCorrect(post));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Send Post Milestone Negative Tests

    /* 1)Years:empty
    2)Months:
    3)Milestone:empty
    4)Message: Length>500*/
    @Test(groups = {"smoke", "negative"}, enabled = true)
    public void MilestoneNegativeTest1() {
        post = randomAlphabetic(200);
        try {
            milestoneOnMainPage
                    .clickOnYearsOption(" ")
                    .clickOnMonthOption("A");
            assertTrue("Alert 'Numbers only' for month did not appeared", milestoneOnMainPage.alertMessageNotValidYear());
            assertTrue("Alert 'Numbers only' for month did not appeared", milestoneOnMainPage.alertMessageNotValidMonth());
            milestoneOnMainPage.fillTextField(post)
                    .sendPost();
            assertTrue("Alert 'Required field' did not appeared", milestoneOnMainPage.alertMessageRequiredFields());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*1)Years:abc
     2)Months:&^$
     3)Milestone:Language:abc
     4)Message:Length:250*/
    @Test(groups = {"smoke", "negative"}, enabled = true)
    public void MilestoneNegativeTest2() {
        type = "Language";
        post = randomAlphabetic(250);
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectItemOption()
                    .clickOnLanguageItemOption("abc")
                    .clickOnYearsOption("abc")
                    .clickOnMonthOption("&^$");
            assertTrue("Alert 'Numbers only' for months appeared", milestoneOnMainPage.alertMessageNotValidMonth());
            assertTrue("Alert 'Numbers only' for year appeared", milestoneOnMainPage.alertMessageNotValidYear());
            milestoneOnMainPage.fillTextField(post)
                    .sendPost();
            assertTrue("Alert 'Required field' did not appeared", milestoneOnMainPage.alertMessageRequiredFields());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* 1)Years:Два
    2)Months:-12
    3)Milestone:Movement:Rolls over
    4)Message:Length:length>500*/
    @Test(groups = {"smoke", "negative"}, enabled = true)
    public void MilestoneNegativeTest3() {

        try {
            type = "Movement";
            milestone = "Rolls over";
            post = randomAlphabetic(500);
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectItemOption()
                    .clickOnElement(milestone)
                    .clickOnYearsOption("Два");
            assertTrue("Alert 'Numbers only' for month did not appeared", milestoneOnMainPage.alertMessageNotValidYear());
            milestoneOnMainPage.clickOnMonthOption("-12");
            assertTrue("Alert 'Numbers only' for month did not appeared", milestoneOnMainPage.alertMessageNotValidMonth());
            milestoneOnMainPage.fillTextField(post)
                    .sendPost();
            assertTrue("Alert 'Required field' did not appeared", milestoneOnMainPage.alertMessageRequiredFields());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*1)Years:שלושל
    2)Months:-One
    3)Milestone:Eating:Eats with spoon
    4)Message:Length:length=1.*/
    @Test(groups = {"smoke", "negative"}, enabled = true)
    public void MilestoneNegativeTest4() {
        type = "Daily living";
        milestone = "Eats with spoon";
        post = randomAlphabetic(1);
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectItemOption()
                    .clickOnElement(milestone)
                    .clickOnYearsOption("שלושל");
            assertTrue("Alert 'Numbers only' for month did not appeared", milestoneOnMainPage.alertMessageNotValidMonth());
            milestoneOnMainPage.clickOnMonthOption("-One");

            assertTrue("Alert 'Numbers only' for month did not appeared", milestoneOnMainPage.alertMessageNotValidMonth());
            assertTrue("Alert 'Numbers only' for year did not appeared", milestoneOnMainPage.alertMessageNotValidYear());
            milestoneOnMainPage.fillTextField(post)
                    .sendPost();
            assertTrue("Alert 'Required field' did not appeared", milestoneOnMainPage.alertMessageRequiredFields());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*1)Years:583687348237560327234686
    2)Months:36
    3)Milestone:empty
    4)Message:Length:length>2252*/
    @Test(groups = {"smoke", "negative"}, enabled = true)
    public void MilestoneNegativeTest5() {
        post = randomAlphabetic(500);
        try {
            milestoneOnMainPage
                    .clickOnYearsOption("583687348237560327234686")
                    .clickOnMonthOption("36")
                    .fillTextField("post")
                    .sendPost();
            assertTrue("Alert 'Required field' did not appeared", milestoneOnMainPage.alertMessageRequiredFields());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*1)Years:עשרים ואחד
    2)Months:00
    3)Milestone:empty
    4)Message::empty*/
    @Test(groups = {"smoke", "negative"}, enabled = true)
    public void MilestoneNegativeTest6() {
        try {
            milestoneOnMainPage
                    .clickOnYearsOption("עשרים ואחד");
            assertTrue("Alert 'Numbers only' for month did not appeared", milestoneOnMainPage.alertMessageNotValidYear());
            milestoneOnMainPage.clickOnMonthOption("00")
                    .sendPost();
            assertTrue("Alert 'Required field' did not appeared", milestoneOnMainPage.alertMessageRequiredFields());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*1)Years:-int
    2)Months:16
    3)Milestone:Toileting:dresses alone
    4)Message:Length>2252*/
    @Test(groups = {"smoke", "negative"}, enabled = true)
    public void MilestoneNegativeTest7() {
        type = "Toileting";
        milestone = "Dresses alone";
        post = randomAlphabetic(500);
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectItemOption()
                    .clickOnElement(milestone)
                    .clickOnYearsOption("-int");
            assertTrue("Alert 'Numbers only' for month did not appeared", milestoneOnMainPage.alertMessageNotValidYear());
            milestoneOnMainPage.clickOnMonthOption("16")
                    .fillTextField("")
                    .sendPost();
            assertTrue("Alert 'Required field' did not appeared", milestoneOnMainPage.alertMessageRequiredFields());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}















