package com.genefo;

import com.genefo.pages.DataProviders;
import com.genefo.pages.LoginPage;
import com.genefo.pages.MainPage;
import com.genefo.pages.MedicineOnMainPage;
import com.genefo.util.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Marina on 6/1/2015.
 */
public class MedicineOnMainPageTest {
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public MedicineOnMainPage medicineOnMainPage;
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
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        medicineOnMainPage = PageFactory.initElements(driver, MedicineOnMainPage.class);

        try {
            loginPage.openLoginPage(driver, baseUrl);
            loginPage.login("jakoff+Rere@gmail.com", "111111");
            mainPage.waitUntilMainPageIsLoaded()
                    .openMedicinePanel();
            medicineOnMainPage.isOnMedicinePanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void beforeMethodSetUp() {
        mainPage
                .openMedicinePanel();
        medicineOnMainPage.waitUntilMedicinePanelIsLoaded();
    }

    //Positive tests
    @Test(groups = {"smoke", "positive"}, enabled = true, dataProviderClass = DataProviders.class, dataProvider = "loadMedicineTypesFromFile")
    public void postSomeMedicineRandom(String name, String reason) throws InterruptedException {
        medicineOnMainPage.createMedicinePostRandom(name, reason);
    }
    //Negative tests

    @Test(groups = {"smoke", "negative"})
    public void sendMedicineWithBlankFieldTest() {


        try {
            medicineOnMainPage
                    .fillNewNameOfMedicine(" ")
                    .fillNewReasonForMedicine(" ")
                    .typeTellUsMore(" ")
                    .clickOnPostButton();
            sleep(3000);

            assertTrue(medicineOnMainPage.alertErrorMessageRequiredFields());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Rating with blank mandatory field
    @Test(groups = {"smoke", "negative"})
    public void sendMedicineRatingWithBlankMandatoryFieldTest() {


        try {
            medicineOnMainPage
                    .fillNewNameOfMedicine(" ")
                    .fillNewReasonForMedicine(" ")
                    .clickOnAllStarsTogether()
                    .rateThreeStars()             //Click on the third star
                    .typeTellUsMore(" ")
                    .clickOnPostButton();
            sleep(3000);


            assertTrue(medicineOnMainPage.alertErrorMessageRequiredFields());


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
}

