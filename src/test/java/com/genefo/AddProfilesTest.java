package com.genefo;

import com.genefo.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Л on 5/19/2015
 */
public class AddProfilesTest extends TestBase {

    private static String EMAIL = "ri-lopatina@yandex.ru";
    private static String PASSWORD = "111111";

    public WebDriverWait wait;

    MyProfilesPage myProfilesPage;
    ProfilePage profilePage;
    LoginPage loginPage;
    MainPage mainPage;
    SummaryPage summaryPage;

    @BeforeClass(alwaysRun = true)
    public void setup() {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        myProfilesPage = PageFactory.initElements(driver, MyProfilesPage.class);
        profilePage = PageFactory.initElements(driver, ProfilePage.class);
        summaryPage = PageFactory.initElements(driver, SummaryPage.class);
        try {
            loginPage.openLoginPage(driver, baseUrl);
            loginPage.isOnLoginPage();
            loginPage.fillEmailField(EMAIL)
                    .fillPasswordField(PASSWORD)
                    .clickOnLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadDataForProfile")
    public void AddProfileSuccess(String first_name, String last_name, String patient_profile_type, String gender, String condition, String month,
                                  String day, String year, String diagnose_year, String patient_profile_check, String condition_check, String gender_check,
                                  String birth_date_check, String diagnose_year_check, String name_check, String last_name_check) {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(first_name);
        profilePage.fillProfileLastNameField(last_name);
        profilePage.selectProfilePatient(patient_profile_type);
        profilePage.selectGender(gender);
        profilePage.fillProfileConditionField(condition);
        profilePage.autoFillCondition();
        profilePage.selectMonth(month);
        profilePage.selectDay(day);
        profilePage.selectYear(year);
        profilePage.selectDiagnoseYear(diagnose_year);
        profilePage.clickToSubmit();
        assertTrue("The Summary Page doesn't open", summaryPage.isOnSummaryPage());
        summaryPage.clickOnLastProfile();
        assertTrue(summaryPage.areProfileFieldsCorrect(patient_profile_check, condition_check, gender_check, birth_date_check, diagnose_year_check, name_check, last_name_check));
        Reporter.log("New profile created successfuly");
        summaryPage.clickOnDiscoverHome();
    }

    //  Negative tests
//    @Test(groups = {"smoke", "negative"}, dataProviderClass = DataProviders.class, dataProvider = "loadNegativeDataForProfile")    //Bug, because the condition field is filled automatically !!!
    public void AddProfileWithoutCondition(String first_name, String last_name, String patient_profile_type, String gender, String month,
                                           String day, String year, String diagnose_year) {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(first_name);
        profilePage.fillProfileLastNameField(last_name);
        profilePage.selectProfilePatient(patient_profile_type);
        profilePage.selectGender(gender);
        profilePage.selectMonth(month);
        profilePage.selectDay(day);
        profilePage.selectYear(year);
        profilePage.selectDiagnoseYear(diagnose_year);
        profilePage.clickToSubmit();
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
        Reporter.log("Negative test(profile without condition): profile is not created");
        profilePage.clickOnDiscoverHome();
    }

    @Test(groups = {"smoke", "positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadNegativeDataForProfile")
    public void AddProfileWithoutFirstName(String first_name, String last_name, String patient_profile_type, String gender, String condition, String month,
                                           String day, String year, String diagnose_year) {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileLastNameField(last_name);
        profilePage.selectProfilePatient(patient_profile_type);
        profilePage.selectGender(gender);
        profilePage.fillProfileConditionField(condition);
        profilePage.selectMonth(month);
        profilePage.selectDay(day);
        profilePage.selectYear(year);
        profilePage.selectDiagnoseYear(diagnose_year);
        profilePage.clickToSubmit();
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
        Reporter.log("Negative test(profile without first name): profile is not created");
        profilePage.clickOnDiscoverHome();
    }

    @Test(groups = {"smoke", "negative"}, dataProviderClass = DataProviders.class, dataProvider = "loadNegativeDataForProfile")
    public void AddProfileWoutLastName(String first_name, String last_name, String patient_profile_type, String gender, String condition, String month,
                                       String day, String year, String diagnose_year) {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(first_name);
        profilePage.selectProfilePatient(patient_profile_type);
        profilePage.selectGender(gender);
        profilePage.fillProfileConditionField(condition);
        profilePage.selectMonth(month);
        profilePage.selectDay(day);
        profilePage.selectYear(year);
        profilePage.selectDiagnoseYear(diagnose_year);
        profilePage.clickToSubmit();
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
        Reporter.log("Negative test(profile without last name): profile is not created");
        profilePage.clickOnDiscoverHome();
    }

    @Test(groups = {"smoke", "positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadNegativeDataForProfile")
    public void AddProfileWithoutPatientType(String first_name, String last_name, String patient_profile_type, String gender, String condition, String month,
                                             String day, String year, String diagnose_year) {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(first_name);
        profilePage.fillProfileLastNameField(last_name);
        profilePage.selectGender(gender);
        profilePage.fillProfileConditionField(condition);
        profilePage.selectMonth(month);
        profilePage.selectDay(day);
        profilePage.selectYear(year);
        profilePage.selectDiagnoseYear(diagnose_year);
        profilePage.clickToSubmit();
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
        Reporter.log("Negative test(profile without patient type): profile is not created");
        profilePage.clickOnDiscoverHome();
    }

    @Test(groups = {"smoke", "positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadNegativeDataForProfile")
    public void AddProfileWithoutGender(String first_name, String last_name, String patient_profile_type, String gender, String condition, String month,
                                        String day, String year, String diagnose_year) {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(first_name);
        profilePage.fillProfileLastNameField(last_name);
        profilePage.selectProfilePatient(patient_profile_type);
        profilePage.fillProfileConditionField(condition);
        profilePage.selectMonth(month);
        profilePage.selectDay(day);
        profilePage.selectYear(year);
        profilePage.selectDiagnoseYear(diagnose_year);
        profilePage.clickToSubmit();
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
        Reporter.log("Negative test(profile without condition): profile is not created");
        profilePage.clickOnDiscoverHome();
    }

    @Test(groups = {"smoke", "positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadNegativeDataForProfile")
    public void AddProfileWithoutMonth(String first_name, String last_name, String patient_profile_type, String gender, String condition, String month,
                                       String day, String year, String diagnose_year) {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(first_name);
        profilePage.fillProfileLastNameField(last_name);
        profilePage.selectProfilePatient(patient_profile_type);
        profilePage.selectGender(gender);
        profilePage.fillProfileConditionField(condition);
        profilePage.selectDay(day);
        profilePage.selectYear(year);
        profilePage.selectDiagnoseYear(diagnose_year);
        profilePage.clickToSubmit();
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
        Reporter.log("Negative test(profile without month): profile is not created");
        profilePage.clickOnDiscoverHome();
    }

    @Test(groups = {"smoke", "positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadNegativeDataForProfile")
    public void AddProfileWithoutDay(String first_name, String last_name, String patient_profile_type, String gender, String condition, String month,
                                     String day, String year, String diagnose_year) {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(first_name);
        profilePage.fillProfileLastNameField(last_name);
        profilePage.selectProfilePatient(patient_profile_type);
        profilePage.selectGender(gender);
        profilePage.fillProfileConditionField(condition);
        profilePage.selectMonth(month);
        profilePage.selectYear(year);
        profilePage.selectDiagnoseYear(diagnose_year);
        profilePage.clickToSubmit();
        assertTrue("Main Page is opened", profilePage.isOnProfilePageNegative());
        Reporter.log("Negative test(profile without day): profile is not created");
        profilePage.clickOnDiscoverHome();
    }

    @Test(groups = {"smoke", "positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadNegativeDataForProfile")
    public void AddProfileWithoutYear(String first_name, String last_name, String patient_profile_type, String gender, String condition, String month,
                                      String day, String year, String diagnose_year) {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(first_name);
        profilePage.fillProfileLastNameField(last_name);
        profilePage.selectProfilePatient(patient_profile_type);
        profilePage.selectGender(gender);
        profilePage.fillProfileConditionField(condition);
        profilePage.selectMonth(month);
        profilePage.selectDay(day);
        profilePage.selectDiagnoseYear(diagnose_year);
        profilePage.clickToSubmit();
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
        Reporter.log("Negative test(profile without year): profile is not created");
        profilePage.clickOnDiscoverHome();
    }

    @Test(groups = {"smoke", "positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadNegativeDataForProfile")
    public void AddProfileWithoutDiagnoseYear(String first_name, String last_name, String patient_profile_type, String gender, String condition, String month,
                                              String day, String year, String diagnose_year) {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(first_name);
        profilePage.fillProfileLastNameField(last_name);
        profilePage.selectProfilePatient(patient_profile_type);
        profilePage.selectGender(gender);
        profilePage.fillProfileConditionField(condition);
        profilePage.selectMonth(month);
        profilePage.selectDay(day);
        profilePage.selectYear(year);
        profilePage.clickToSubmit();
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
        Reporter.log("Negative test(profile without diagnose year): profile is not created");
        profilePage.clickOnDiscoverHome();
    }


}
