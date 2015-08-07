package com.genefo.pages;


import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Zizi, Christina and Mariya on 5/28/2015.
 */
public class MilestoneOnMainPage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    HashMap<String, WebElement> buttonsAndItemsMap = new HashMap<String, WebElement>();
    //Labels of categories
    @FindBy(xpath = "//*[@class='ng-scope active'][@popover='Report your progress']")
    //WebElement developmentalYourProgressTitle;
            WebElement developmentalMilestoneTitle;

    /*@FindBy(xpath = "//div [@class='col-sm-8']/label")
    WebElement developmentalMilestoneTitle;
    @FindBy(xpath = "//div [@class='col-sm-4']/label")
    WebElement treatmentMilestoneTitle;*/
    @FindBy(xpath = "//*[@class='ng-scope active'][@popover='Report your progress']/span")
    // WebElement treatmentReportYourProgressTitle;
            WebElement treatmentMilestoneTitle;
    //Category Developmental Progress buttons
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Language')]")
    WebElement languageButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Movement')]")
    WebElement movementButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Daily living')]")
    WebElement dailyLivingButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Procedure')]")
    WebElement procedureButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Complication')]")
    WebElement complicationButton;
    @FindBy(xpath = "//div [@class='btn-group']/button[contains(text(),'Other')]")
    WebElement otherButton;

    // DropdownList elements of Language
    //Dropdown list Button
    @FindBy(xpath = "//*[@class ='chosen-single']/div/b")
    WebElement selectDropDownListButton;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemSmiles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemBabbles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemFirstWord;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemTwoThreeWords;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemSpeaksInFullSentences;
    // DropdownList elements of Movement
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemHoldsHead;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemReachesForObjects;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemRollsOver;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='7']")
    WebElement itemSitsWithoutSupport;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemCrawls;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemPullsToStand;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='8']")
    WebElement itemWalk;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='6']")
    WebElement itemRuns;
    // DropdownList elements of Daily living
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemDressesAlone;//+
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemEatsWithSpoon;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemHoldsBottles;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemToiletTrained;//+
    //DropdownList elements of Procedure
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemAchillesTendonLengthening;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemBrainMRI;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemGastrostomy;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemMedicalTrial;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemRemission;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='6']")
    WebElement itemSpinalFusionSurgeryForScoliosis;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='7']")
    WebElement itemStrabismusSurgery;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='8']")
    WebElement itemSurgery;
    //DropdownList elements of Complication
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemBehavioralProblems;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement itemConstipation;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement itemDevelopmentalDelay;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement itemFailureToThrive;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement itemFeedingDifficulties;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement itemGastroespophagealReflux;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='6']")
    WebElement itemHearingLoss;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='7']")
    WebElement itemIntellectualDisability;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='8']")
    WebElement itemObesity;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='9']")
    WebElement itemSeizures;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='10']")
    WebElement itemSleepDisorders;
    //Years-Month button
    @FindBy(xpath = ".//*[@id='milestone_years']")
    WebElement yearsButton;
    @FindBy(xpath = ".//*[@id='milestone_months']")
    WebElement monthButton;
    //field input text for language
    @FindBy(xpath = "//*[@class='chosen-search']/input")
    WebElement selectLanguageField;
    //Field for input PostText
    @FindBy(xpath = "//*[@class='form-group']/textarea")
    WebElement inputTextPostField;
    @FindBy(xpath = "//*[@class='col-sm-12']/input")
    WebElement textField;
    //button submit
    @FindBy(xpath = "//*[@id='submit']")
    WebElement submitButton;
    //alerts
    @FindBy(xpath = "//*[text()='REQUIRED FIELDS']")
    WebElement alertRequiredFields;
    //*[contains(text(), "Numbers")]
    @FindBy(xpath = ".//*[@id='milestone_years']/../span[contains(text(),'Must be a number between 0 and 200')]")
    // @FindBy(xpath = ".//*[@id='milestone_years']/../span")
            WebElement yearsNumber;
    // @FindBy(xpath = "//*[@id='milestone_years']/../span[contains(text(),'Numbers only')]")
    @FindBy(xpath = ".//*[@id='milestone_months']/../span[contains(text(),'Must be number between 0 and 48')]")
    WebElement monthsNumber;
    //elements in created post
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[1]/td[2]")
    WebElement ageOnNewCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[2]/td[2]")
    WebElement milestoneTypeOnNewCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[3]/td[2]")
    WebElement milestoneOnNewCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='post-note']")
    WebElement textNewCreatedPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='post-note']")
    WebElement textInNewCreatedPost;


    //constructor
    public MilestoneOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Waits until title of our 'Milestone' Panel appears on the screen
    public void waitUntilMilestonePanelIsLoaded() {
        try {
            waitUntilElementIsLoaded(developmentalMilestoneTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Checks that title of our 'Milestone' Panel have appeared on the screen so we can work with it.
    public boolean isOnMilestonePanel() {
        Log.info("This is Milestone Panel");
        return exists(developmentalMilestoneTitle);
    }


    public MilestoneOnMainPage clickOnSelectItemOption() {              //button opening drop-down list
        Log.info(" Select item option");
        clickElement(selectDropDownListButton);
        return this;
    }

    //for other
    public MilestoneOnMainPage clickOnSelectOtherItemOption() {
        Log.info("Select other item option");
        clickElement(otherButton);
        return this;
    }

    //for year
    public MilestoneOnMainPage clickOnYearsOption(String year) {
        Log.info("Years option:" + year);
        setElementText(yearsButton, year);
        return this;
    }

    //for month
    public MilestoneOnMainPage clickOnMonthOption(String months) {
        Log.info("Month Option:" + months);
        setElementText(monthButton, months);
        return this;
    }

    public MilestoneOnMainPage clickOnLanguageItemOption(String text) {
        Log.info("Language Item Option:" + text);
        setElementText(selectLanguageField, text);
        return this;
    }

    //fill text post
    public MilestoneOnMainPage fillTextField(String post) {
        Log.info("Fill Text Field:" + post);
        setElementText(inputTextPostField, post);
        return this;
    }

    // fill text of category "Other"
    public MilestoneOnMainPage fillOtherField(String text) {
        Log.info("Fill Other Field:" + text);
        setElementText(textField, text);
        return this;
    }

    // Submit button
    public MilestoneOnMainPage sendPost() {
        Log.info("Send post");
        clickElement(submitButton);
        return this;
    }

    //check alert presence
    public boolean alertMessageNotValidYear() {
        return exists(yearsNumber);
    }

    public boolean alertMessageNotValidMonth() {
        return exists(monthsNumber);
    }

    public boolean alertMessageRequiredFields() {
        return exists(alertRequiredFields);
    }

    //checking data in created post
    private WebElement getWebElementByName(String name) {
        return buttonsAndItemsMap.get(name);
    }

    public MilestoneOnMainPage clickOnElement(String name) {
        Log.info("Element Name:" + name);
        WebElement element = getWebElementByName(name);
        element.click();
        return this;
    }

    public void fillAllElementsAndItemsToMap() {
        // buttons with names from WebPage
        buttonsAndItemsMap.put(languageButton.getText(), languageButton);
        buttonsAndItemsMap.put(movementButton.getText(), movementButton);
        buttonsAndItemsMap.put(dailyLivingButton.getText(), dailyLivingButton);
        buttonsAndItemsMap.put(procedureButton.getText(), procedureButton);
        buttonsAndItemsMap.put(complicationButton.getText(), complicationButton);
        buttonsAndItemsMap.put(otherButton.getText(), otherButton);
        //items with names from category "Language"
        buttonsAndItemsMap.put("Smiles", itemSmiles);
        buttonsAndItemsMap.put("Babbles", itemBabbles);
        buttonsAndItemsMap.put("First word", itemFirstWord);
        buttonsAndItemsMap.put("2-3 words", itemTwoThreeWords);
        buttonsAndItemsMap.put("Speaks in full sentences", itemSpeaksInFullSentences);
        // items with names from category "Movement"
        buttonsAndItemsMap.put("Holds head", itemHoldsHead);
        buttonsAndItemsMap.put("Reaches for objects", itemReachesForObjects);
        buttonsAndItemsMap.put("Rolls over", itemRollsOver);
        buttonsAndItemsMap.put("Sits without support", itemSitsWithoutSupport);
        buttonsAndItemsMap.put("Crawls", itemCrawls);
        buttonsAndItemsMap.put("Pulls to stand", itemPullsToStand);
        buttonsAndItemsMap.put("Walk", itemWalk);
        buttonsAndItemsMap.put("Runs", itemRuns);
        // items with names from category "Daily living"
        buttonsAndItemsMap.put("Dresses alone", itemDressesAlone);
        buttonsAndItemsMap.put("Eats with spoon", itemEatsWithSpoon);
        buttonsAndItemsMap.put("Holds bottle", itemHoldsBottles);
        buttonsAndItemsMap.put("Toilet trained", itemToiletTrained);

        // items with names from category "Procedure"
        buttonsAndItemsMap.put("Achilles tendon lengthening", itemAchillesTendonLengthening);
        buttonsAndItemsMap.put("Brain MRI", itemBrainMRI);
        buttonsAndItemsMap.put("Gastrostomy", itemGastrostomy);
        buttonsAndItemsMap.put("Medical trial", itemMedicalTrial);
        buttonsAndItemsMap.put("Remission", itemRemission);
        buttonsAndItemsMap.put("Spinal fusion surgery for scoliosis", itemSpinalFusionSurgeryForScoliosis);
        buttonsAndItemsMap.put("Strabismus surgery", itemStrabismusSurgery);
        buttonsAndItemsMap.put("Surgery", itemSurgery);
        //items with names from category " Complication"
        buttonsAndItemsMap.put("Behavioral problems", itemBehavioralProblems);
        buttonsAndItemsMap.put("Constipation", itemConstipation);
        buttonsAndItemsMap.put("Developmental delay", itemDevelopmentalDelay);
        buttonsAndItemsMap.put("Failure to thrive", itemFailureToThrive);
        buttonsAndItemsMap.put("Feeding difficulties", itemFeedingDifficulties);
        buttonsAndItemsMap.put("Gastroespophageal reflux", itemGastroespophagealReflux);
        buttonsAndItemsMap.put("Hearing loss", itemHearingLoss);
        buttonsAndItemsMap.put("Intellectual disability", itemIntellectualDisability);
        buttonsAndItemsMap.put("Obesity", itemObesity);
        buttonsAndItemsMap.put("Seizures", itemSeizures);
        buttonsAndItemsMap.put("Sleep disorders", itemSleepDisorders);

    }

    public boolean isMilestoneTrue(String name) {
        return this.verifyTextBoolean(milestoneOnNewCreatedPost, name);
    }

    public boolean isTypeTrue(String name) {
        return this.verifyTextBoolean(milestoneTypeOnNewCreatedPost, name);
    }

    public boolean isAgeIsCorrect(String age) {
        return verifyTextBoolean(ageOnNewCreatedPost, age);
    }

    public boolean isTextCorrect(String name) {
        return verifyTextBoolean(textNewCreatedPost, name);
    }


    public void waitForPostLoaded() {
        try {
            this.waitUntilElementIsLoaded(milestoneOnNewCreatedPost);
        } catch (IOException e) {
            System.out.println("no post loaded error:" + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("no post loaded error:" + e.getMessage());
        }
    }


    public boolean isOtherTextCorrect(String name) {
        return verifyTextBoolean(milestoneOnNewCreatedPost, name);
    }
}