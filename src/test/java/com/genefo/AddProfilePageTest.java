package com.genefo;

import com.genefo.pages.*;
import com.genefo.util.PropertyLoader;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class AddProfilePageTest {

    private static String MY_EMAIL = "mili29@mail.ru";
    private static String MY_Password = "123qwee";
    private static String PATH_TO_Miki = Paths.get("").toAbsolutePath().toString() + "\\miki.gif";
    public MainPage mainPage;
    public LoginPage loginPage;
    public AddProfilePage thisPage;
    public EditAccountPage editAccountPage;
    public MyProfilesPage myProfilesPage;
    public WebDriver driver;
    public String baseUrl;

    public AddProfilePageTest() {
        super();
    }

    @BeforeClass(alwaysRun = true)
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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        thisPage = PageFactory.initElements(driver, AddProfilePage.class);
        editAccountPage = PageFactory.initElements(driver, EditAccountPage.class);
        myProfilesPage = PageFactory.initElements(driver, MyProfilesPage.class);
        driver.get(baseUrl + "/login");
        loginPage.openLoginPage(driver, baseUrl)

                .waitUntilLoginPageIsLoaded()
                .login(MY_EMAIL, MY_Password);
    }

    @BeforeMethod
    public void loadThisPage() {
        editAccountPage.openEditAccountPage(driver, baseUrl);
    }

    //Verify that Add profile page exists
    @Test(groups = {"positive", "smoke", "special_for_debug"}, enabled = true)
    public void IsAddProfilePageExists() {
        Assert.assertTrue(thisPage.ADD_ANOTHER_PROFILE_isDisplayed(), "page Add Another Profile exists");
        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        Assert.assertTrue(thisPage.get_Create_New_Profile().isDisplayed(), "page Create New Profile exists");
    }

    //    ProU 2	Verify that the required/mandatory fields are marked with "* " .
    @Test(groups = {"positive", "smoke"}, enabled = false)
    public void isMandatoryFieldsPresent() {

        thisPage.ADD_ANOTHER_PROFILE_click();
        Assert.assertTrue(thisPage.isMandatoryFieldsPresent(), "All Mandatory fields marked with \"*\"");
    }

    @DataProvider
    public Object[][] myDataProvider() {
        return new Object[][]{
                {"It's me", "firstName of me Vasia", "lastName Pupkin", "Male", "February", "13", "1985", "African", "comments1 "},
//                {"Family member","firstName family","lastName Pupkin","Male","February","13","1985","African","comments2"},
//                {"Friend","firstName of friend Vasia","lastName Pupkin","Male","February","13","1985","African","comments3 "}
        };
    }

    //    Click a button SELECT ONE and choose It's Me.Fill all fields and click batton Save
    @Test(groups = {"positive", "smoke"}, enabled = true, dataProvider = "myDataProvider")
    public void selectOne(String how_do_you_know, String firstName, String lastName, String gender, String month, String day, String year, String race, String comments) {
        String condition = gender;
        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        thisPage
                .select_How_do_you_know(how_do_you_know)
                .input_First_Name(firstName)
                .input_Last_Name(lastName)
                .input_Condition(condition)
                .select_Patient_Diagnosis_Month(month)
                .select_Patient_Diagnosis_Year(year)
                .select_Patient_Diagnosis_Gender(gender)
                .select_Patient_Race(race)
                .select_Patient_Birthday_Month(month)
                .select_Patient_Birthday_Day(day)
                .select_Patient_Birthday_Year(year)
                .input_Patient_Location("Moscow, Russia")
                .input_Comment(comments);


        Reporter.log("pass all input", 1);
        assertFalse(thisPage.isErrorMessage(), "Error message not displayed (assertFalse)");
        assertTrue(thisPage.isButtonSaveActive(), "Save button is active");
        thisPage.click_ButtonSave();
        myProfilesPage
                .waitUntilMyProfilesPageIsLoaded();
        assertTrue(myProfilesPage.isOnMyProfilesPage(), "My profile Page isn't loaded");
    }

    @DataProvider
    private Object[][] chooseCondition() {
        return new Object[][]{
//                input not sensative to case.
                {"Male"},
                {"Female"},
//                {"gg"}//wrong input
        };
    }

    //In the field "CONDITION", enter a first letters
    @Test(groups = "positive", enabled = true, dataProvider = "chooseCondition")
    public void chooseCondition(String condition) {
        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        thisPage.input_Condition(condition);
        thisPage.input_Comment("just to relocate mouse");
        assertFalse(thisPage.isErrorMessage(), "Error message not displayed (assertFalse)");

    }

    @DataProvider
    private Object[][] chooseGender() {
        return new Object[][]{
                {"Male"},
                {"Female"},
                {"Other"}
        };
    }

    //Click a button Select Gende
    @Test(groups = "positive", enabled = true, dataProvider = "chooseCondition")
    public void chooseGender(String gender) {
        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        thisPage.select_Patient_Diagnosis_Gender(gender);
        thisPage.input_Comment("just to relocate mouse");
        assertFalse(thisPage.isErrorMessage(), "Error message not displayed (assertFalse)");
    }


    //Verify that user able to create at  profile when filled correct data in required fields
    @Test(groups = "positive", enabled = true)
    public void isUserNamepresent() {
        String firstName, lastName;

        editAccountPage.openEditAccountPage(driver, baseUrl);
        editAccountPage.waitUntilEditElementIsLoaded();
        firstName = editAccountPage.getFirstNameElement().getAttribute("value");
        lastName = editAccountPage.getLastNameElement().getAttribute("value");
        loadThisPage();

        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        thisPage
                .select_How_do_you_know("It's me");
        assertFalse(firstName.equals(""), "assert False First name not correct read! value=/" + firstName + "/");
        assertFalse(lastName.equals(""), "assert False Last name not correct read! value=/" + lastName + "/");

        assertEquals(thisPage.getFirstName(), firstName, "first name compare");
        assertEquals(thisPage.getLastName(), lastName, "last name compare");
    }

    @DataProvider
    public Object[][] negativeProvider() {
        return new Object[][]{
                {"Select One", "My First Name", "My Last Name", "Angel", "Male", "February", "1985", "African", "March", "2", "2005", "how_do_you_know "},
                {"It's me", "", "My Last Name", "Angel", "Male", "February", "1985", "African", "March", "2", "2005", "My First Name"},
                {"It's me", "My First Name", "", "Angel", "Male", "February", "1985", "African", "March", "2", "2005", "My Last Name "},
                {"It's me", "My First Name", "My Last Name", "A", "Male", "February", "1985", "African", "March", "2", "2005", "condition "},
                {"It's me", "My First Name", "My Last Name", "Angel", "Select Gender", "February", "1985", "African", "March", "2", "2005", "Gender "},
                {"It's me", "My First Name", "My Last Name", "Angel", "Male", "Month", "1985", "African", "March", "2", "2005", "Diagnosis Month "},
                {"It's me", "My First Name", "My Last Name", "Angel", "Male", "February", "Year", "African", "March", "2", "2005", "Diagnosis Year "},
                {"It's me", "My First Name", "My Last Name", "Angel", "Male", "February", "1985", "African", "Month", "2", "2005", "Birthday Month "},
                {"It's me", "My First Name", "My Last Name", "Angel", "Male", "February", "1985", "African", "March", "Day", "2005", "Birthday day "},
                {"It's me", "My First Name", "My Last Name", "Angel", "Male", "February", "1985", "African", "March", "2", "Year", "Birthday year "}

        };
    }

    //    Click a button SELECT ONE and choose It's Me.Fill all fields and click batton Save
    @Test(groups = {"negative", "smoke"}, enabled = true, dataProvider = "negativeProvider")
    public void notFilledMandatoryFields(String how_do_you_know,
                                         String firstName, String lastName,
                                         String condition,
                                         String gender,
                                         String month, String year,
                                         String race,
                                         String birsthdayMonth, String birsthdayDay, String birsthdayYear,
                                         String assertComment) {


        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        thisPage
                .select_How_do_you_know(how_do_you_know)
                .input_First_Name(firstName)
                .input_Last_Name(lastName)
                .input_Condition("Angel")
                .input_Condition(condition)
                .select_Patient_Diagnosis_Month("February")
                .select_Patient_Diagnosis_Month(month)
                .select_Patient_Diagnosis_Year("1995")
                .select_Patient_Diagnosis_Year(year)
                .select_Patient_Diagnosis_Gender("Male")
                .select_Patient_Diagnosis_Gender(gender)
                .select_Patient_Race(race)
                .select_Patient_Birthday_Month("February")
                .select_Patient_Birthday_Month(birsthdayMonth)
                .select_Patient_Birthday_Day("13")
                .select_Patient_Birthday_Day(birsthdayDay)
                .select_Patient_Birthday_Year("2010")
                .select_Patient_Birthday_Year(birsthdayYear)
// BUG                .input_Patient_Location("Russia")
                .input_Comment("My comments");

        if (!how_do_you_know.equals("Select One"))
            assertTrue(thisPage.isErrorMessage(), "Error message displayed. Not mandatory field: " + assertComment);
        assertFalse(thisPage.isButtonSaveActive(), "Save button is not active (FalseAssert). Not mandatory field: " + assertComment);
    }


    @Test(groups = {"positive", "smoke"})
    public void UploadPicture() {
        assertTrue((new File(PATH_TO_Miki)).exists(), "if file exists or not");
        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        assertTrue(thisPage.uploadFile(PATH_TO_Miki), "File upload correct");
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
