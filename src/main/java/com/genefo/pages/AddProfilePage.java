package com.genefo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;


public class AddProfilePage extends Page {


    @FindBy(xpath = "//div[@class='errormsg']")
    WebElement errorMessage;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController'] //input[@type='file']")
    WebElement fileUploadMenu;
    @FindBy(xpath = "//div[@ng-controller='ProfileNewController'] //div[@class='profilePicUploadDragBox ng-isolate-scope ng-valid ng-dirty']")
    WebElement fileUploadMenuBox;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController'] //input[@name='condition']")
    WebElement Condition;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//input[@value='Select Some Options']")
    WebElement Patient_Race;

    @FindBy(xpath = "//*[@name='lastName']")
    WebElement Last_name;

    @FindBy(xpath = "//*[@name='firstName']")
    WebElement First_name;

    @FindBy(xpath = "//div[@class='panel-heading']//*[contains(text(),'Create New Profile')]")
    WebElement Create_New_Profile;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//input[@type='file']")
    WebElement Add_Picture;

    @FindBy(xpath = "//h2[contains(text(),'My Profiles')]")
    WebElement My_Profiles;

    @FindBy(xpath = "//a[@href='/profile/new']")
    WebElement ADD_ANOTHER_PROFILE;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//select[@name='relationID']")
    WebElement How_do_you_know;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//select[@name='diagnosisMonth'] ")
    WebElement Patient_Diagnosis_Month;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//select[@name='diagnosisYear'] ")
    WebElement Patient_Diagnosis_Year;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//select[@name='genderID']")
    WebElement Patient_Diagnosis_Gender;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//select[@name='birthmonth']")
    WebElement Patient_Birthday_Month;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//select[@name='birthday']")
    WebElement Patient_Birthday_Day;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//select[@name='birthyear']")
    WebElement Patient_Birthday_Year;

    @FindBy(xpath = "//ul[@class='dropdown-menu ng-isolate-scope']/li[1]")
    WebElement Patient_Location_1;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//input[@name='location']")
    WebElement Patient_Location;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//button[@id='submit'][not(contains(@disabled,'disabled'))]")
    WebElement ButtonSaveActive;

    @FindBy(xpath = "//div[@ng-controller='ProfileNewController']//button[@id='submit'][contains(@disabled,'disabled')]")
    WebElement ButtonSaveNotActive;

    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    WebElement ButtonCancel;

    @FindBy(xpath = "//textarea")
    WebElement Comment;


    public AddProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public AddProfilePage click_ButtonSave() {
        ButtonSaveActive.click();
        return this;
    }

    public boolean isButtonSaveActive() {
        try {
            return ButtonSaveActive.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public AddProfilePage select_How_do_you_know(String itemNumber) {
        Select select = new Select(How_do_you_know);
        select.selectByVisibleText(itemNumber);
        return this;
    }

    public boolean isErrorMessage() {
        try {
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public boolean isMandatoryFieldsPresent() {
        LinkedList<String> allReqField = new LinkedList<String>();
        String xpath = null;
        allReqField.add("How do you know");
        allReqField.add("First Name");
        allReqField.add("Last Name");
        allReqField.add("Condition");
        allReqField.add("Gender");
        allReqField.add("Birthday ");

        try {
            for (String s : allReqField) {
                xpath = "//div[@ng-controller='ProfileNewController']//*[contains(text(),'" + s + "')]//span[@class='req_star']";
                webDriver.findElement(By.xpath(xpath));
            }
            xpath = "//div[@ng-controller='ProfileNewController']//*[contains(text(),'Diagnosis')]/..//span[@class='req_star']";
            webDriver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            System.out.println("--------------------------------------------");
            System.out.println("Error Required element was not found!");
            System.out.println("xpath of the element:" + xpath);
            System.out.println("--------------------------------------------");
            return false;
        }
        return true;
    }

    public AddProfilePage select_Patient_Diagnosis_Month(String itemNumber) {
        Select select = new Select(Patient_Diagnosis_Month);
        select.selectByVisibleText(itemNumber);
        return this;
    }

    public AddProfilePage select_Patient_Diagnosis_Year(String itemNumber) {
        Select select = new Select(Patient_Diagnosis_Year);
        select.selectByVisibleText(itemNumber);
        return this;
    }

    public AddProfilePage select_Patient_Diagnosis_Gender(String itemNumber) {
        Select select = new Select(Patient_Diagnosis_Gender);
        select.selectByVisibleText(itemNumber);
        return this;
    }

    public AddProfilePage select_Patient_Race(String itemName) {
        Patient_Race.click();
        WebElement element = webDriver.findElement(By.xpath("//ul[@class='chosen-results']/li[contains(text(),'" + itemName + "')]"));

        element.click();
        return this;
    }

    public AddProfilePage select_Patient_Birthday_Day(String itemNumber) {
        Select select = new Select(Patient_Birthday_Day);
        select.selectByVisibleText(itemNumber);
        return this;
    }

    public AddProfilePage select_Patient_Birthday_Month(String itemNumber) {
        Select select = new Select(Patient_Birthday_Month);
        select.selectByVisibleText(itemNumber);
        return this;
    }

    public AddProfilePage select_Patient_Birthday_Year(String itemNumber) {
        Select select = new Select(Patient_Birthday_Year);
        select.selectByVisibleText(itemNumber);
        return this;
    }

    public AddProfilePage input_Patient_Location(String location) {
        setElementText(Patient_Location, location);
//        BUG nothing to choose
        String xpath = "//*[contains(@id, 'typeahead')]/*[1]";
        WebElement element = webDriver.findElement(By.xpath(xpath));
        element.click();
        return this;
    }

    public AddProfilePage input_Comment(String text) {
        setElementText(Comment, text);
        return this;
    }

    public AddProfilePage input_First_Name(String input) {

        setElementText(First_name, input);
        return this;
    }

    public AddProfilePage input_Condition(String input) {
        String xpath;
// logic here to find element to match input string that not sensative to case of first letter
        Condition.sendKeys(Keys.chord(Keys.CONTROL + "a"), Keys.DELETE);
        sleep(1);
        setElementText(Condition, input);
        sleep(1);
        xpath = "//*[@class='dropdown-menu ng-isolate-scope']//*[contains(text(),'" + input.substring(1, input.length()) + "')]";
        try {
            webDriver.findElement(By.xpath(xpath)).click();
        } catch (NoSuchElementException e) {
            System.out.println("----------------------------------------");
            System.out.println("AddProfilePage.input_Condition(): nothing to choose in Field: 'Condition' xpath=" + xpath);
            System.out.println("----------------------------------------");
        }
        return this;
    }

    public AddProfilePage input_Last_Name(String input) {

        setElementText(Last_name, input);
        return this;
    }

    public String getLastName() {
        return Last_name.getAttribute("value");
    }

    public String getFirstName() {
        return First_name.getAttribute("value");
    }

    public WebElement get_My_Profiles() {
        return My_Profiles;
    }

    public AddProfilePage ADD_ANOTHER_PROFILE_click() {
        ADD_ANOTHER_PROFILE.click();
        return this;
    }

    public boolean ADD_ANOTHER_PROFILE_isDisplayed() {
        return ADD_ANOTHER_PROFILE.isDisplayed();
    }


    public WebElement get_Create_New_Profile() {
        return Create_New_Profile;
    }


    public AddProfilePage sleep(int n) {
        try {
            TimeUnit.SECONDS.sleep(n);
        } catch (InterruptedException e) {
            System.out.println("Function on Page.class 'sleep'");
            e.printStackTrace();
        }
        return this;
    }

    public boolean uploadFile(String path) {
        Locatable hoverMe = (Locatable) fileUploadMenuBox;
        Mouse mouse = ((HasInputDevices) webDriver).getMouse();
        try {
            mouse.mouseMove(hoverMe.getCoordinates());
            fileUploadMenu.sendKeys(path);
        } catch (ElementNotVisibleException e) {
            System.out.println("error: Not visible picture BOX element:" + e.getMessage());
            return false;
        }
        return true;
    }
}
