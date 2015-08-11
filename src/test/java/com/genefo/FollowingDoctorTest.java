package com.genefo;

import com.genefo.pages.LoginPage;
import com.genefo.pages.MainPage;
import com.genefo.pages.ProfileDoctorPage;
import com.genefo.pages.PublicProfilePage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Л on 6/2/2015.
 */
public class FollowingDoctorTest extends TestBase {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    LoginPage loginPage;
    MainPage mainPage;
    PublicProfilePage publicProfilePage;
    ProfileDoctorPage profileDoctorPage;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        PropertyConfigurator.configure("log4j.properties");

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        publicProfilePage = PageFactory.initElements(driver, PublicProfilePage.class);
        try {
            loginPage.openLoginPage(driver, baseUrl)
                    .waitUntilLoginPageIsLoaded()
                    .login("gjgfytf@jhghtf.com", "123456");
            profileDoctorPage.isOnProfileDoctorPage();
            profileDoctorPage.clickOnDisYourHP();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainPage.isOnMainPage();
        mainPage.chooseConditionForDoctor("Insomnia");
        mainPage.chooseConditionFromDropDown();
        mainPage.clickViewButton();
    }

    @Test(groups = {"smoke", "positive"}, description = "Follow user From Connect People Condition Field")
    public void addFollowSuccessFromConnectPeopleConditionField() {
        Reporter.log("AddFollowSuccessFromConnectPeopleConditionField test");
        Log.info("1.AddFollowSuccessFromConnectPeopleConditionField test");
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
        Reporter.log("1.New profile was added to following successfully from ConnectPeopleThisConditionProfile");
    }

    @Test(groups = {"smoke", "positive"}, description = "Unfollow user")
    public void unFollowSuccess() {
        Reporter.log("UnFollowSuccess test");
        Log.info("2.UnFollowSuccess test");
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
        Reporter.log("2.New profile was unfollowed successfully");
    }

    @Test(groups = {"smoke", "positive"}, description = "Follow user From Post")
    public void addFollowSuccessFromPosts() {
        Reporter.log("AddFollowSuccessFromPosts test");
        Log.info("3.AddFollowSuccessFromPosts test");
        mainPage.fillSet();
        mainPage.addMyDoctorNameToFillSet();
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
        Reporter.log("3.New profile was added to follow successfully from posts");
    }


}