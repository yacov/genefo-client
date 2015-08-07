package com.genefo.pages;


import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//import org.openqa.selenium.WebDriver;

/**
 * Created by Iakov Volf 27.05.15.
 */
public class WhatWorksOnMainPage extends Page {


    //Category Symptom buttons

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Therapy')]")
    WebElement therapyButton;
    // We need it to check if the button is highlighted after click.
    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Therapy')][contains(@class, 'active')]")
    WebElement highLightedTherapyButton;
    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Equipment')]")
    WebElement equipmentButton;
    // We need it to check if the button is highlighted after click.
    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Equipment')][contains(@class, 'active')]")
    WebElement highLightedEquipmentButton;
    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Nutrition')]")
    WebElement nutritionButton;
    // We need it to check if the button is highlighted after click.
    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Nutrition')][contains(@class, 'active')]")
    WebElement highLightedNutritionButton;
    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Exercises')]")
    WebElement exercisesButton;
    // We need it to check if the button is highlighted after click.
    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Exercises')][contains(@class, 'active')]")
    WebElement highLightedExercisesButton;
    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Alternative')]")
    WebElement alternativeButton;
    // We need it to check if the button is highlighted after click.
    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Alternative')][contains(@class, 'active')]")
    WebElement highLightedAlternativeButton;
    @FindBy(xpath = "//div[@class='btn-group']/button[6][contains(text(),'Other')]")
    WebElement otherButton;
    // We need it to check if the button is highlighted after click.
    @FindBy(xpath = "//div[@class='btn-group']/button[contains(text(),'Other')][contains(@class, 'active')]")
    WebElement highLightedOtherButton;
    // We use it only to calculate number of items in dropdown list
    @FindBy(xpath = " //*[contains(text(),'Please select a specific item')]/../..//ul[@class = 'chosen-results']")
    WebElement ItemListOptions;
    //Dropdown list
    @FindBy(xpath = "//*[@placeholder = 'Please Specify Your Item']")
    WebElement specifyItemForOtherOption;
    @FindBy(xpath = "//*[contains(text(),'Please select a specific item')]")
    WebElement selectItemList;
    @FindBy(xpath = "//*[contains(text(),'Please select a specific item')]/../div/b")
    WebElement selectItemListButton;
    // Here we distinguish list that chosen, from list that is not chosen
    @FindBy(xpath = "//div[contains(@class,'chosen-with-drop chosen-container-active')]//span[contains(text(),'Please select a specific item')]")
    WebElement ItemListButtonThatChosen;
    //elements of dropdown list
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='1']")
    WebElement firstItemInList;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='2']")
    WebElement secondItemInList;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='3']")
    WebElement thirdItemInList;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='4']")
    WebElement fourthItemInList;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='5']")
    WebElement fifthItemInList;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='6']")
    WebElement sixthItemInList;
    @FindBy(xpath = "//ul[@class='chosen-results']/li[@data-option-array-index='7']")
    WebElement seventhItemInList;
    // Rating star( marked one. Have asterisk sign in definition)
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/i[3]/span[contains(text(),'*')]")
    WebElement thirdMarkedRatingStar;
    // Rating star( non-marked one. Do not have asterisk sign in definition)
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/i[3]/span[not(contains(text(),'*'))]")
    WebElement thirdNonMarkedRatingStar;
    // Rating star - marked and non-marked together
    @FindBy(xpath = "//*[@class='ng-isolate-scope ng-valid ng-dirty']/*[3]")
    WebElement thirdRatingStar;
    // Serves as indication that we are on 'WhatWorks' Panel.
    @FindBy(xpath = "//label[@for='what_works_category_1']/../label[@for='symptoms_select'] ")
    WebElement categorySymptomTitle;
    @FindBy(xpath = "//*[@class='panel-body ng-isolate-scope ng-valid ng-dirty']//*[@ng-model='what_works_rating']//i[contains(@class, 'glyphicon' )]")
    WebElement allStarsTogether;
    // text field for posting
    @FindBy(xpath = "//textarea[@name = 'bio']")
    WebElement postField;
    @FindBy(xpath = "//button[@id='submit'][contains(text(),'Post')]")
    WebElement submitButton;
    // Upper Tab of sent posts
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]")
    WebElement UpperSentPostTab;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//span[@class='ng-binding ng-isolate-scope']")
    WebElement SentPostText;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[1]/td[2]")
    WebElement SentPostCategory;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-model=\"what_works_rating\"]/i[3]/span[contains(text(),'*')]")
    WebElement filledThirdStarInSentPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@ng-model=\"what_works_rating\"]/i[4]/span[not(contains(text(),'*'))]")
    WebElement nonFilledFourthStarInSentPost;
    @FindBy(xpath = "//*[@class='panel story-panel ng-scope panel-default']/../div[5]//*[@class='table post-table']//tr[2]/td[2]")
    WebElement ListItemInSentPost;
    private String textInListItem; // Serves to keep text of the item from the list to give it after for assertion.
    // Data structure that keeps button( type WebElement )and what is written on it (String)
    // row by row. Has two methods - put() and get() (see below)
    private HashMap<String, WebElement> optionsLocator = new HashMap<String, WebElement>();

    // Sort of array but without size limits. Keeps only variables of  WebElement type.
    // has two methods - add() and put()  (see below)
    // private ArrayList<WebElement> itemsInListById = new ArrayList<WebElement>();
    private HashMap<String, WebElement> highLightedOptionsLocator = new HashMap<String, WebElement>();


    public WhatWorksOnMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Waits until title of our 'What works' Panel appears on the screen
    public void waitUntilWhatWorksPanelIsLoaded() {
        Log.info("Waiting Until WhatWorksPanel Is Loaded");
        try {
            waitUntilElementIsLoaded(categorySymptomTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Checks that title of our 'What works' Panel have appeared on the screen so we can work with it.
    public boolean isOnWhatWorksPanel() {
        waitUntilWhatWorksPanelIsLoaded();
        return exists(categorySymptomTitle);
    }

    // Fills data structure optionsLocator (has type HashMap<String,WebElement>)
    // and data structure itemsInListById ( has type ArrayList<WebElement> )
    public void defineOptionsLocators() {

        highLightedOptionsLocator.put("Therapy", highLightedTherapyButton);
        highLightedOptionsLocator.put("Equipment", highLightedEquipmentButton);
        highLightedOptionsLocator.put("Nutrition", highLightedNutritionButton);
        highLightedOptionsLocator.put("Exercises", highLightedExercisesButton);
        highLightedOptionsLocator.put("Alternative", highLightedAlternativeButton);
        highLightedOptionsLocator.put("Other", highLightedOtherButton);

        optionsLocator.put("Therapy", therapyButton);
        optionsLocator.put("Equipment", equipmentButton);
        optionsLocator.put("Nutrition", nutritionButton);
        optionsLocator.put("Exercises", exercisesButton);
        optionsLocator.put("Alternative", alternativeButton);
        optionsLocator.put("Other", otherButton);

    }

    public WhatWorksOnMainPage clickOnOption(String option) {
        Log.info("Clicking on 'Category' option " + option);
        try {
            clickElement(optionsLocator.get(option));// Choose and click on button that has 'option' string written on it
        } catch (Exception e) {
            e.printStackTrace();  // In this way we define our own exception
            System.out.println("Wrong option! \nOption with name :" + option + " does not exist!");
        }
        return this;
    }


    public boolean isOptionHighLighted(String option) {
        Log.info("Checking that 'Category' option " + option + " is highlighted after clicking");
        boolean temp = false;
        try {
            temp = exists(highLightedOptionsLocator.get(option));
        } catch (Exception e) {
            e.printStackTrace();  // In this way we define our own exception
            System.out.println("Wrong option! \nOption with name :" + option + " does not exist!");
        }
        return temp;
    }

    public WhatWorksOnMainPage clickOnItemList() {
        Log.info("Clicking on item list to receive the list of items to choose");
        clickElement(selectItemList);
        return this;
    }


    // Waits until our last item from dropdoown list appears on the screen
    public WhatWorksOnMainPage waitUntilLastItemFromItemListIsLoaded() {
        Log.info("Waiting Until Last Item From Item List Is Loaded");
        try {
            List<WebElement> elements = ItemListOptions.findElements(By.tagName("li"));
            waitUntilElementIsLoaded(elements.get(elements.size() - 1));
        } catch (Exception e) {
            e.printStackTrace();           // In this way we define our own exception
            System.out.println("Wrong last item! \nItem does not exist!");
        }
        return this;
    }


    public WhatWorksOnMainPage chooseLastItemFromItemList() {
        Log.info("Chosing  Last Item From Item List ");
        // We fill list of elements with items from the dropdown list
        List<WebElement> elements = ItemListOptions.findElements(By.tagName("li"));
        WebElement el = elements.get(elements.size() - 1);
        textInListItem = el.getText();
        el.click();
        return this;
    }

    // Waits until our item from dropdown list appears on the screen
    public WhatWorksOnMainPage waitUntilItemFromItemListIsLoaded(int itemNumber) {
        Log.info("Waiting Until Number " + itemNumber + " Item From Item List Is Loaded");
        // We fill list of elements with items from the dropdown list
        List<WebElement> elements = ItemListOptions.findElements(By.tagName("li"));
        WebElement optionChooser = elements.get(itemNumber - 1);
        try {
            waitUntilElementIsLoaded(optionChooser);
        } catch (Exception e) {
            e.printStackTrace();           // In this way we define our own exception
            System.out.println("Wrong item number! \nItem with number :" + itemNumber + " does not exist!");
        }
        return this;
    }


    public WhatWorksOnMainPage chooseItemFromItemList(int itemNumber) {
        Log.info("Choosing Number " + itemNumber + " Item From Item List ");
        // We fill list of elements with items from the dropdown list
        List<WebElement> elements = ItemListOptions.findElements(By.tagName("li"));
        WebElement optionChooser = elements.get(itemNumber - 1);
        textInListItem = optionChooser.getText();
        clickElement(optionChooser);
        return this;
    }

    public WhatWorksOnMainPage fillTextField(String post) {
        Log.info("Filling post field with post: " + post);
        setElementText(postField, post);
        return this;
    }

    // Works only for 'Other' Category.
    public WhatWorksOnMainPage fillItemForOtherOption(String item) {
        Log.info("Filling item field for 'Other' category with item: " + item);
        setElementText(specifyItemForOtherOption, item);
        return this;
    }

    public WhatWorksOnMainPage sendPost() {
        Log.info("Sending post with all fields are filled");
        clickElement(submitButton);
        return this;
    }


    // We need to click on all stars together to set free each one of them
    public WhatWorksOnMainPage clickOnAllStarsTogether() throws InterruptedException {
        Log.info("Clicking on all stars together to set free each one of them ");
        clickElement(allStarsTogether);
        return this;
    }

    // Click on the third star
    public WhatWorksOnMainPage rateItThree() {
        Log.info("Clicking on the third star ");
        clickElement(thirdRatingStar);
        return this;
    }

    // Methods for verifying items on sent upper post for What Works Tab

    public Boolean verifyTextFromSentPost(String text) {
        return verifyTextBoolean(SentPostText, text);
    }


    public Boolean verifyCategoryExistsInSentPost(String category) {

        return verifyTextBoolean(SentPostCategory, category);
    }


    // Use it after 'chooseItemFromItemList()' method: First you choose item, put it
    // in variable textInListItem ,
    // then you verify that it is seen on Sent Post Panel(on ListItemInSentPost WebElement).
    public Boolean verifyListItemCorrectInSentPost() {
        return verifyTextBoolean(ListItemInSentPost, textInListItem);
    }

    public Boolean verifyOtherItemCorrectInSentPost(String item) {
        return verifyTextBoolean(ListItemInSentPost, item);
    }


    public Boolean verifyThirdStarCheckedInSentPost() {
        return exists(filledThirdStarInSentPost);
    }

    public Boolean verifyFourthStarNonCheckedInSentPost() {
        return exists(nonFilledFourthStarInSentPost);
    }

    public Boolean verifyItemListIsChosen() {
        return exists(ItemListButtonThatChosen);
    }


}











