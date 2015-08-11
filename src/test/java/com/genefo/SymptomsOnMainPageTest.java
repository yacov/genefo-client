package com.genefo;

import com.genefo.pages.LoginPage;
import com.genefo.pages.MainPage;
import com.genefo.pages.SymptomsOnMainPage;
import com.genefo.util.PropertyLoader;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by tanyagaus, Lev on 6/10/15.
 */
public class SymptomsOnMainPageTest extends TestBase {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    private static String MY_EMAIL = "jakoff+444@gmail.com";
    private static String MY_Password = "111111";
    private static String PATH_TO_Miki = Paths.get("").toAbsolutePath().toString() + "\\miki.gif";

    public WebDriverWait wait;
    public MainPage mainPage;
    public LoginPage loginPage;
    public String fillTellUs;
    SymptomsOnMainPage symptomsOnMainPage;

    @BeforeClass(alwaysRun = true)

    public void setup() {
        baseUrl = PropertyLoader.loadProperty("site.url");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        symptomsOnMainPage = PageFactory.initElements(driver, SymptomsOnMainPage.class);

        try {
            loginPage.openLoginPage(driver, baseUrl);
            loginPage.login(MY_EMAIL, MY_Password);
            mainPage.waitUntilMainPageIsLoaded();
            Assert.assertTrue(mainPage.isOnMainPage(), "Login not ok");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethodSetUp() {
        mainPage
                .openSymptomsPanel();
        symptomsOnMainPage.waitUntilSymptomsPanelIsLoaded();
    }

    @DataProvider
    private Object[][] myProvider() {
        return new Object[][]{
                {"Growth", 1, 1},
                {"Head and Neck", 1, 1},
                {"Head and Neck", 2, 1},
                {"Head and Neck", 3, 1},
                {"Head and Neck", 4, 1},
                {"Head and Neck", 5, 1},
                {"Head and Neck", 6, 1},
                {"Head and Neck", 7, 1},
                {"Heart and Blood Vessels", 1, 1},
                {"Heart and Blood Vessels", 2, 1},
                {"Chest and Lungs", 1, 1},
                {"Chest and Lungs", 2, 1},
                {"Abdomen", 1, 1},
                {"Genitalia and Urinary Trac", 1, 1},
                {"Skeletal", 1, 1},
                {"Skin, Nail, Hair", 1, 1},
                {"Neurologic", 1, 1},
                {"Complications During Pregnancy", 1, 1},
                {"Tumors", 1, 1}

        };
    }

    @Test(groups = {"smoke", "positive"}, dataProvider = "myProvider")
    public void myTest1(String general_Area, int specific_Area, int symptom) {

        Date date = new Date();

        Assert.assertTrue(symptomsOnMainPage.select_General_Area(general_Area), "General_Area element is choosen");
        Assert.assertTrue(symptomsOnMainPage.select_Specific_Area(specific_Area), "Specific_Area element is choosen");
        Assert.assertTrue(symptomsOnMainPage.select_Symptom(symptom), "Symptom element is choosen");
        symptomsOnMainPage.postText("My Post at " + date.toString());
        // Assert.assertTrue((new File(PATH_TO_Miki)).exists(), "if file exists or not");
        // symptomsOnMainPage.uploadFile(PATH_TO_Miki);
        symptomsOnMainPage.submitPost();
        mainPage.openMainPage(driver, baseUrl);
        beforeMethodSetUp();

    }

    @Test(groups = {"smoke", "positive"})
    public void TryAllSymptoms() {
        symptomsOnMainPage.createSymptomPost();
    }


}


