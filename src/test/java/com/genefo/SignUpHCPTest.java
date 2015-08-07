package com.genefo;

import com.genefo.pages.ProfileDoctorPage;
import com.genefo.pages.SignUpHCPPage;
import com.genefo.util.PropertyLoader;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Oleg on 31.05.2015.
 */
public class SignUpHCPTest {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    //private String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
    private static String email1 = "one" + randomAlphabetic(5) + "usgenefo.com";
    private static String email2 = "on.e" + randomAlphabetic(5) + "@us.genefo.com";
    private static String email3 = "o_ne" + randomAlphabetic(5) + "@usgenefo.com";
    private static String email4 = "one" + randomAlphabetic(5) + "@us-genefo.com";
    public WebDriverWait wait;
    public SignUpHCPPage signUpHCPPage;
    public ProfileDoctorPage profileDoctorPage;
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
    }

    @BeforeMethod
    public void beforemethodsetup() {
        try {
            signUpHCPPage = PageFactory.initElements(driver, SignUpHCPPage.class);
            profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
            Log.info("Opening SignUp HCP page");
            driver.get(baseUrl + "/signup_hcp");
            Log.info("Wait for load SignUp HCP page");
            signUpHCPPage.waitUntilSignUpHCPPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void RegTestSuccess() {
        Log.info("Checking that all correct data added successfully");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillEmailField(email)
                    .fillFirstNameField("gggg")
                    .fillLastNameField("gggg")
                    .fillPasswordField("111111")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("all correct data added successful");
    }

    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutLastName() {
        Log.info("Checking that registration is not possible without last name ");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillEmailField(email)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("")
                    .fillPasswordField("111111")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Last Name is valid", signUpHCPPage.alertMessageNotValidLastName());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("registration is not possible without last name");
    }

    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutFirstName() {
        Log.info("Checking that registration is not possible without first name");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillEmailField(email)
                    .fillFirstNameField("")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            sleep();
            assertTrue("The First Name is valid", signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("registration is not possible without first name");
    }

    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutPassword() {
        Log.info("Checking that registration is not possible without Password");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Password is valid", signUpHCPPage.alertMessageNotValidPassword());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("registration is not possible without Password");
    }

    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutEmail() {
        Log.info("Checking that registration is not possible without Email");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Email is valid", signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("registration is not possible without Email");
    }

    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutCheckBox18() {
        Log.info("Checking that registration is not possible without age verification");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The age verification has passed", signUpHCPPage.alertMessageNonChecked18());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("registration is not possible without age verification");
    }

    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutCheckBoxTerms() {
        Log.info("Checking that registration is not possible without terms verification");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnSignUp();
            assertTrue("The terms verification has passed", signUpHCPPage.alertMessageNonCheckedTerms());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("registration is not possible without terms verification");
    }

    //EmailField
    //1
    @Test(groups = {"smoke", "negative"})
    public void RegTestWithoutAtInEmailField() {
        Log.info("Checking that registration is not possible without @ in email");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email1)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Email is valid", signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("registration is not possible without @ in email");
    }

    //2
    @Test(groups = {"negative"})
    public void RegTestWithSpecialCharactersInEmailField() {
        Log.info("Checking that special characters are not valid in email");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("!)*@#$%^&*.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Email is valid", signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("special characters are not valid in email");
    }

    //3
    @Test(groups = {"negative"})
    public void RegTestWithoutLocalPartInEmailField() {
        Log.info("Checking that email is not valid without local part");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Email is valid", signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("email is not valid without local part");
    }

    //4
    @Test(groups = {"negative"})
    public void RegTestWithoutDomainPartInEmailField() {
        Log.info("Checking that email is not valid without domain part");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Email is valid", signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("email is not valid without domain part");
    }

    //5

    @Test(groups = {"negative"})
    public void RegTestWithConsecutiveDotsInEmailField() {
        Log.info("Checking that email is not valid with consecutive dots");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo..com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Email is valid", signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("email is not valid with consecutive dots");
    }

    //6
    @Test(groups = {"negative"})
    public void RegTestWithDotInTheBeginningLocalPartEmailField() {
        Log.info("Checking that email is not valid with dot in the local beginning");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(".us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Email is valid", signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("email is not valid with dot in the local beginning");
    }

    //7
    @Test(groups = {"negative"})
    public void RegTestWithDotInTheBeginningDomainPartEmailField() {
        Log.info("Checking that email is not valid with dot in the domain beginning");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@.genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Email is valid", signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("email is not valid with dot in the domain beginning");
    }

    //8
    @Test(groups = {"negative"})
    public void RegTestWithEmailContains256Symbols() {
        Log.info("Checking that email is not valid with 256 symbols");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                            "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk@usgenefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Email is valid", signUpHCPPage.alertMessageNotValidEmail());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("email is not valid with 256 symbols");
    }

    //PasswordField
    //1
    @Test(groups = {"negative"})
    public void RegTestWithPasswordContains5Symbols() {
        Log.info("Checking that password is not valid with 5 symbols");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("11111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Password is valid", signUpHCPPage.alertMessageNotValidPassword());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("password is not valid with 5 symbols");
    }

    //2
    @Test(groups = {"negative"})
    public void RegTestWithPasswordContains13Symbols() {
        Log.info("Checking that password is not valid with 13 symbols");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("1111111111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Password is valid", signUpHCPPage.alertMessageNotValidPassword());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("password is not valid with 13 symbols");
    }

    //FirstName
    //1
    @Test(groups = {"negative"})
    public void RegTestWithFirstNameContainsSpecialCharacters() {
        Log.info("Checking that first name is not valid with special characters");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("@#$%^&*(")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The First Name is valid", signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("first name is not valid with special characters");
    }

    //2
    @Test(groups = {"negative"})
    public void RegTestWithFirstNameContainsDigits() {
        Log.info("Checking that first name is not valid with digits");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("55Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The First Name is valid", signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("first name is not valid with digits");
    }

    //3
    @Test(groups = {"negative"})
    public void RegTestWithFirstNameContainsUnderscore() {
        Log.info("Checking that first name is not valid with underscore");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter_Pit")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The First Name is valid", signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("first name is not valid with underscore");
    }

    //4
    @Test(groups = {"negative"})
    public void RegTestWithFirstNameContains26Symbols() {
        Log.info("Checking that first name is not valid with 26 symbols");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("PiterPiterPiterPiterPiterr")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The First Name is valid", signUpHCPPage.alertMessageNotValidFirstName());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("first name is not valid with 26 symbols");
    }

    //LastName
    //1
    @Test(groups = {"negative"})
    public void RegTestWithLastNameContainsSpecialCharacters() {
        Log.info("Checking that last name is not valid with special characters");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("@#$%^&*(")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Last Name is valid", signUpHCPPage.alertMessageNotValidLastName());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("last name is not valid with special characters");
    }

    //2
    @Test(groups = {"negative"})
    public void RegTestWithLastNameContainsDigits() {
        Log.info("Checking that last name is not valid with digite");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("55Pen")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Last Name is valid", signUpHCPPage.alertMessageNotValidLastName());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("last name is not valid with digits");
    }

    //3
    @Test(groups = {"negative"})
    public void RegTestWithLastNameContainsUnderscore() {
        Log.info("Checking that last name is not valid with underscore");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen_Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Last Name is valid", signUpHCPPage.alertMessageNotValidLastName());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("last name is not valid with underscore");
    }

    //4
    @Test(groups = {"negative"})
    public void RegTestWithLastNameContains26Symbols() {
        Log.info("Checking that last name is not valid with 26 symbols");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("PenPenPenPenPenPenPenPennn")
                    .fillPasswordField("111111")
                    .fillEmailField("us000998@genefo.com")
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            assertTrue("The Last Name is valid", signUpHCPPage.alertMessageNotValidLastName());
            assertTrue("The current page is changed", signUpHCPPage.isOnSignUpHCPPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("last name is not valid with 26 symbols");
    }


    //Positive Tests emails variations

    //1
    @Test(groups = {"positive"})
    public void RegTestEmailLocalPartBeginsNumber() {
        Log.info("Checking that email is valid with local part begins number");
        String email = "55" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("email is valid with local part begins number");
    }

    //2
    @Test(groups = {"positive"})
    public void RegTestEmailDomainNameBeginsNumber() {
        Log.info("Checking that email is valid with domain part begins number");
        String email = "one" + randomAlphabetic(5) + "@55usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("email is valid with domain part begins number");
    }

    //3

    @Test(groups = {"positive"})
    public void RegTestEmailWithDotsLocalAndDomain() {
        Log.info("Checking that email is valid with dots in local and domain parts");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email2)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("email is valid with dots in local and domain parts");
    }

    //4

    @Test(groups = {"positive"})
    public void RegTestEmailWithDashInLocalPart() {
        Log.info("Checking that email is valid with dash in local part");
        String email = "on-e" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("email is valid with dash in local part");
    }


    //5
    @Test(groups = {"positive"})
    public void RegTestEmailWithDashInDomainPart() {
        Log.info("Checking that email is valid with dash in domain part");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email4)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("email is valid with dash in domain part");
    }

    //6

    @Test(groups = {"positive"})
    public void RegTestEmailWithUnderscoreInLocalPart() {
        Log.info("Checking that email is valid with underscore in local part");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email3)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("email is valid with underscore in local part");
    }

    //Positive test for password

    //1
    @Test(groups = {"positive"})
    public void RegTestPassword6Symbols() {
        Log.info("Checking that password is valid with 6 symbols");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("abs123")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("password is valid with 6 symbols");
    }

    //2
    @Test(groups = {"positive"})
    public void RegTestPassword8Symbols() {
        Log.info("Checking that password is valid with 8 symbols");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absd1234")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("password is valid with 8 symbols");
    }

    //3
    @Test(groups = {"positive"})
    public void RegTestPassword12Symbols() {
        Log.info("Checking that password is valid with 12 symbols");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absdef123456")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("password is valid with 12 symbols");
    }

    //Positive Tests for FirstNameField

    //1
    @Test(groups = {"positive"})
    public void RegTestFirstName25Symbols() {
        Log.info("Checking that first name is valid with 25 symbols");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("PiterPiterPiter")
                    .fillLastNameField("Pen")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("first name is valid with 25 symbols");
    }


    @Test(groups = {"positive"})
    public void RegTestFirstName1Symbol() {
        Log.info("Checking that first name is valid with 1 symbols");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("P")
                    .fillLastNameField("Pen")
                    .fillPasswordField("absdef123456")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("first name is valid with 1 symbols");
    }


    //Positive Tests for LastNameField

    //1
    @Test(groups = {"positive"})
    public void RegTestLastName25Symbols() {
        Log.info("Checking that last name is valid with 25 symbols");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Piter")
                    .fillLastNameField("PiterPiterPiterPiterPiter")
                    .fillPasswordField("111111")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("last name is valid with 25 symbols");
    }

    //2
    @Test(groups = {"positive"})
    public void RegTestLastName1Symbol() {
        Log.info("Checking that last name is valid with 1 symbols");
        String email = "one" + randomAlphabetic(5) + "@usgenefo.com";
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .fillFirstNameField("Pitel")
                    .fillLastNameField("P")
                    .fillPasswordField("absdef123456")
                    .fillEmailField(email)
                    .clickOnCheckBox18()
                    .clickOnCheckBoxAgree()
                    .clickOnSignUp();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            profileDoctorPage.logOut();
            sleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("last name is valid with 1 symbols");
    }

    @Test(groups = {"positive"})
    public void TermOfServiceClick() {
        Log.info("Checking that Term Of Service Page open by clicking on the link");
        try {
            signUpHCPPage
                    .openHCPRegPage(driver, baseUrl)
                    .clickOnTermOfService()
                    .openTOSPage(driver, baseUrl);
            assertTrue("Term Of Service Page doesn't open", signUpHCPPage.isOnTOSage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Term Of Service Page open by clicking on the link");
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

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
