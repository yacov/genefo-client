package com.genefo;

import com.genefo.pages.HomePage;
import com.genefo.pages.LoginPage;
import com.genefo.pages.MainPage;
import com.genefo.pages.ResetYourPasswordPage;
import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Oleg on 30.05.2015.
 */
public class LoginTest extends TestBase {
    public static String USER = "osh_il+4@yahoo.com";
    public static String PASSWORD = "111111";
    public static String USER1 = "osh_il+1@yahoo.com";
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public HomePage  homePage;
    public LoginPage  loginPage;
    public ResetYourPasswordPage  resetYourPasswordPage;
    public MainPage mainPage;
    public String baseUrl;

    @BeforeClass
    public void setup() {
        /*baseUrl = PropertyLoader.loadProperty("site.url");
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "ru");
        String Xport = System.getProperty("lmportal.xvfb.id", ":0");
        final File firefoxPath = new File(System.getProperty(
                "lmportal.deploy.firefox.path", "/usr/bin/firefox"));
        FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
        firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);

        // Start Firefox driver
        driver = new FirefoxDriver(firefoxBinary, null);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PropertyConfigurator.configure("log4j.properties");*/
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        resetYourPasswordPage = PageFactory.initElements(driver, ResetYourPasswordPage.class);
    }

    @BeforeMethod
    public void beforeMethodSetUp() {
        try {
            driver.get(baseUrl + "/login");
            loginPage.openLoginPage()
                    .waitUntilLoginPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void LoginSuccess() {
        Log.info("Checking that all correct data added successfully");
        try {
            loginPage
                    .fillEmailField(USER)
                    .fillPasswordField(PASSWORD)
                    .clickOnLogin();
            mainPage.waitUntilMainPageIsLoaded();
            assertTrue("The Main Page doesn't open", mainPage.isOnMainPage());
            mainPage.logOut();
            homePage.waitUntilHomePageIsLoaded();
            //assertTrue("The Home Page doesn't open", homePage.isOnHomePage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Login successful");
    }

    @Test(groups = {"smoke", "positive"})
    public void LoginLogoutLogin() {
        Log.info("Checking ability login ,logout and login again with another user");
        try {
        loginPage
                .fillEmailField(USER)
                .fillPasswordField(PASSWORD)
                .clickOnLogin();
        mainPage.waitUntilMainPageIsLoaded();
        mainPage.logOut();
        homePage.waitUntilHomePageIsLoaded();
        homePage.clickOnLogin();
        loginPage
                .waitUntilLoginPageIsLoaded()
                .fillEmailField(USER1)
                .fillPasswordField(PASSWORD)
                .clickOnLogin();
        mainPage.waitUntilMainPageIsLoaded();
        assertTrue("The Main Page doesn't open", mainPage.isOnMainPage());
        mainPage.logOut();
        homePage.waitUntilHomePageIsLoaded();
        }catch (Exception e){
            e.printStackTrace();
        }
        Reporter.log("Login successful");
    }

    @Test(groups = {"smoke", "negative"})
    public void LoginWithoutAtInEmailField() {
        Log.info("Checking inability lodin without @ in email field");
        try {
            loginPage
                    .fillEmailField("osh_il+4yahoo.com")
                    .fillPasswordField(PASSWORD)
                    .waitUntilAllertEmailIsLogIsLoaded()
                    .clickOnLogin();
            assertTrue("The Email is valid", loginPage.alertMessageInvalidEmail());
            assertTrue("The current page is changed", loginPage.isOnLoginPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Not logged in successful");
    }
    @Test(groups = {"smoke", "negative"})
    public void LoginWithPasswordContains1Symbol() {
        Log.info("Checking inability lodin with password contains 1 symbol");
        try {
            loginPage
                    .fillEmailField(USER)
                    .fillPasswordField("1")
                    .waitUntilAllertPasswordIsLogIsLoaded()
                    .clickOnLogin();
            assertTrue("The Password is valid", loginPage.alertMessageInvalidPassword());
            assertTrue("The current page is changed", loginPage.isOnLoginPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Not logged in successful");
    }

    @Test(groups = {"smoke", "positive"})
    public void ForgotPassword() {
        Log.info("Checking ability recreate password");
        try {
            loginPage
                    .clickOnForgotPasswordLink();
            resetYourPasswordPage.waitUntilResetPageIsLoaded();
            assertTrue("The Reset Password Page doesn't open", resetYourPasswordPage.isOnResetPage());
            resetYourPasswordPage.fillEmailField(USER);
            resetYourPasswordPage.clickOnEmailMe();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Password recreated successful");
    }

    @Test(groups = {"smoke", "negative"})
    public void LoginWithEmptyFields() {
        Log.info("Checking inability lodin with empty fields");
        try {
            loginPage
                    .fillEmailField("")
                    .fillPasswordField("")
                    .waitUntilAllertEmailIsLogIsLoaded()
                    .clickOnLogin();
            assertTrue("The Email is valid", loginPage.alertMessageInvalidEmail());
            assertTrue("The Password is valid", loginPage.alertMessageInvalidPassword());
            assertTrue("The current page is changed", loginPage.isOnLoginPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Not logged in successful");
    }

}
