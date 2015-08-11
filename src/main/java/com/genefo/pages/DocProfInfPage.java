package com.genefo.pages;

import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;

/**
 * Created by Oleg on 02.06.2015.
 */
public class DocProfInfPage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    //Titles
    @FindBy(xpath = "//*[contains(text(),'Healthcare Professional Information')]")
    @CacheLookup
    WebElement profInfTitle;

    @FindBy(xpath = "//*[@ng-repeat='specialty in profile.hcpSpecialties']/../tr[1]/td/h4")
    @CacheLookup
    WebElement specialties;
    @FindBy(xpath = "//*[@ng-repeat='subspecialty in profile.hcpSubspecialties']/../tr[1]/td/h4")
    @CacheLookup
    WebElement subspecialties;
    @FindBy(xpath = "//*[@ng-repeat='hcptitle in profile.hcpTitles']/../tr[1]/td/h4")
    @CacheLookup
    WebElement titles;
    @FindBy(xpath = "//*[@ng-repeat='areaofinterest in profile.hcpAreasOfInterests']/../tr[1]/td/h4")
    @CacheLookup
    WebElement areas;
    @FindBy(xpath = "//*[@ng-repeat='workplace in profile.hcpWorkPlaces']/../tr[1]/td[1]/h4")
    @CacheLookup
    WebElement workName;
    @FindBy(xpath = "//*[@ng-repeat='workplace in profile.hcpWorkPlaces']/../tr[1]/td[2]/h4")
    @CacheLookup
    WebElement workLocation;

    //buttons
    @FindBy(xpath = "//*[@ng-click='addHCPspecialty()' and not(@disabled='disabled')]")
    @CacheLookup
    WebElement addSpecButton;
    @FindBy(xpath = "//*[@ng-click='addHCPspecialty()' and @disabled='disabled']")
    @CacheLookup
    WebElement addSpecDisButton;
    @FindBy(xpath = "//*[@ng-click='checkDelete(specialty)']")
    @CacheLookup
    WebElement delSpecButton;
    @FindBy(xpath = "//*[@ng-click='cancelDelete(specialty)']")
    @CacheLookup
    WebElement cancelSpecButton;
    @FindBy(xpath = "//*[@ng-click='deleteHCPspecialty($index)']")
    @CacheLookup
    WebElement confirmSpecButton;
    @FindBy(xpath = "//*[@ng-click='addHCPsubspecialty()'and not(@disabled='disabled')]")
    @CacheLookup
    WebElement addSubButton;
    @FindBy(xpath = "//*[@ng-click='addHCPsubspecialty()'and @disabled='disabled']")
    @CacheLookup
    WebElement addSubDisButton;
    @FindBy(xpath = "//*[@ng-click='checkDelete(subspecialty)']")
    @CacheLookup
    WebElement delSubButton;
    @FindBy(xpath = "//*[@ng-click='cancelDelete(subspecialty)']")
    @CacheLookup
    WebElement cancelSubButton;
    @FindBy(xpath = "//*[@ng-click='deleteHCPsubspecialty($index)']")
    @CacheLookup
    WebElement confirmSubButton;
    @FindBy(xpath = "//*[@ng-click='addHCPtitle()'and not(@disabled='disabled')]")
    @CacheLookup
    WebElement addTitleButton;
    @FindBy(xpath = "//*[@ng-click='addHCPtitle()'and @disabled='disabled']")
    @CacheLookup
    WebElement addTitleDisButton;
    @FindBy(xpath = "//*[@ng-click='checkDelete(hcptitle)']")
    @CacheLookup
    WebElement delTitleButton;
    @FindBy(xpath = "//*[@ng-click='cancelDelete(hcptitle)']")
    @CacheLookup
    WebElement cancelTitleButton;
    @FindBy(xpath = "//*[@ng-click='deleteHCPtitle($index)']")
    @CacheLookup
    WebElement confirmTitleButton;
    @FindBy(xpath = "/*//*[@ng-click='addHCPareaofinterest()'and not(@disabled='disabled')]")
    @CacheLookup
    WebElement addAreasButton;
    @FindBy(xpath = "//*[@ng-click='addHCPareaofinterest()'and @disabled='disabled']")
    @CacheLookup
    WebElement addAreasDisButton;
    @FindBy(xpath = "//*[@ng-click='cancelDelete(areaofinterest)']")
    @CacheLookup
    WebElement cancelAreasButton;
    @FindBy(xpath = "//*[@ng-click='checkDelete(areaofinterest)']")
    @CacheLookup
    WebElement delAreasButton;
    @FindBy(xpath = "//*[@ng-click='deleteHCPareaofinterest($index)']")
    @CacheLookup
    WebElement confirmAreasButton;
    @FindBy(xpath = "/*//*[@ng-click='addHCPworkplace()'and not(@disabled='disabled')]")
    @CacheLookup
    WebElement addWorkPlacesButton;
    @FindBy(xpath = "//*[@ng-click='addHCPworkplace()'and @disabled='disabled']")
    @CacheLookup
    WebElement addWorkPlacesDisButton;
    @FindBy(xpath = "//*[@ng-click='cancelDelete(workplace)']")
    @CacheLookup
    WebElement cancelWorkPlacesButton;
    @FindBy(xpath = "//*[@ng-click='checkDelete(workplace)']")
    @CacheLookup
    WebElement delWorkPlacesButton;
    @FindBy(xpath = "//*[@ng-click='deleteHCPworkplace(workplace, $index)']")
    @CacheLookup
    WebElement confirmWorkPlacesButton;
    @FindBy(xpath = "//*[@class='btn btn-success' and @ng-click='cancel_save()']")
    @CacheLookup
    WebElement doneButton;

    //fields
    @FindBy(xpath = "//*[contains( @typeahead,'HCPspecialties')]")
    @CacheLookup
    WebElement specField;
    @FindBy(xpath = "//*[contains( @typeahead,'subspecialty')]")
    @CacheLookup
    WebElement subspecField;
    @FindBy(id = "titles")
    @CacheLookup
    WebElement titleField;
    @FindBy(xpath = "//*[contains( @typeahead,'areaofinterest')]")
    @CacheLookup
    WebElement areasField;
    @FindBy(id = "workplacename")
    @CacheLookup
    WebElement workNameField;
    @FindBy(id = "workplacelocation")
    @CacheLookup
    WebElement workLocatField;

    //toltips
    @FindBy(xpath = "//*[contains(@id,'option-0')]/a")
    @CacheLookup
    WebElement specToltip;
    @FindBy(xpath = "//*[contains(@id,'option-0')]/a")
    @CacheLookup
    WebElement areasToltip;
    @FindBy(xpath = "//*[contains(@id,'option-0')]/a")
    @CacheLookup
    WebElement worklocatToltip;


    public DocProfInfPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public DocProfInfPage waitUntilDocProfInfPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(profInfTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isOnDocProfInfPage() {
        waitUntilDocProfInfPageIsLoaded();
        return exists(profInfTitle);
    }

    public boolean isAddWorkPlacesDisButtonExists() {
        return exists(addWorkPlacesDisButton);
    }

    public boolean isAddSpecButtonDisabled() {
        return exists(addSpecDisButton);
    }

    public boolean isSpecExists() {
        return exists(specialties);
    }

    public boolean isSubspecExists() {
        return exists(subspecialties);
    }

    public boolean isTitlesExists() {
        return exists(titles);
    }

    public boolean isAreasExists() {
        return exists(areas);
    }

    public boolean isNameWPExists() {
        return exists(workName);
    }

    public boolean isLocationWPExists() {
        return exists(workLocation);
    }

    //Fill
    public DocProfInfPage fillSpecialtiesField(String specialties) {
        setElementText(specField, specialties);
        Log.info("entering specialties: " + specialties + " ");
        return this;
    }

    public DocProfInfPage fillTitlesField(String titles) {
        setElementText(titleField, titles);
        Log.info("entering titles: " + titles + " ");
        return this;
    }

    public DocProfInfPage fillSubspecialtiesField(String subspecialties) {
        setElementText(subspecField, subspecialties);
        Log.info("entering subspecialties: " + subspecialties + " ");
        return this;
    }

    public DocProfInfPage fillAreasField(String aries) {
        setElementText(areasField, aries);
        Log.info("entering aries: " + aries + " ");
        return this;
    }

    public DocProfInfPage fillWorkPlacesNameField(String workName) {
        setElementText(workNameField, workName);
        Log.info("entering workname: " + workName + " ");
        return this;
    }

    public DocProfInfPage fillWorkPlacesLocationField(String worklocation) {
        setElementText(workLocatField, worklocation);
        Log.info("entering worklocation: " + worklocation + " ");
        if (worklocation == null) {
            try {
                waitUntilElementIsLoaded(worklocatToltip);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clickElement(worklocatToltip);
        }
        return this;
    }

    //Add
    public DocProfInfPage clickOnAddSubspecialtiesButton() {
        clickElement(addSubButton);
        return this;
    }

    public DocProfInfPage clickOnAddSpecialtiesButton() {
        clickElement(addSpecButton);
        return this;
    }

    public DocProfInfPage clickOnAddSpecialtiesDisButton() {
        clickElement(addSpecDisButton);
        return this;
    }

    public DocProfInfPage clickOnAddTitlesButton() {
        clickElement(addTitleButton);
        return this;
    }

    public DocProfInfPage clickOnAddAreasButton() {
        clickElement(addAreasButton);
        return this;
    }

    public DocProfInfPage clickOnAddWorkPlacesButton() {
        clickElement(addWorkPlacesButton);
        return this;
    }

    //Tooltip
    public DocProfInfPage clickOnTooltipSpec() {
        clickElement(specToltip);
        return this;
    }

    public DocProfInfPage clickOnTooltipAreas() {
        clickElement(areasToltip);
        return this;
    }

    public DocProfInfPage clickOnTooltipWP() {
        clickElement(worklocatToltip);
        return this;
    }

    //Delete
    public DocProfInfPage clickOnDelSpecButton() {
        clickElement(delSpecButton);
        return this;
    }

    public DocProfInfPage clickOnDelSubspecButton() {
        clickElement(delSubButton);
        return this;
    }

    public DocProfInfPage clickOnDelTitleButton() {
        clickElement(delTitleButton);
        return this;
    }

    public DocProfInfPage clickOnDelAreasButton() {
        clickElement(delAreasButton);
        return this;
    }

    public DocProfInfPage clickOnDelWorkPlacesButton() {
        clickElement(delWorkPlacesButton);
        return this;
    }

    //Confirm

    public DocProfInfPage clickOnConfSpecButton() {
        clickElement(confirmSpecButton);
        return this;
    }

    public DocProfInfPage clickOnConflSubspecButton() {
        clickElement(confirmSubButton);
        return this;
    }

    public DocProfInfPage clickOnConfTitleButton() {
        clickElement(confirmTitleButton);
        return this;
    }

    public DocProfInfPage clickOnConfAreasButton() {
        clickElement(confirmAreasButton);
        return this;
    }

    public DocProfInfPage clickOnConfWorkPlacesButton() {
        clickElement(confirmWorkPlacesButton);
        return this;
    }

    //Done
    public DocProfInfPage clickOnDoneButton() {
        clickElement(doneButton);
        return this;
    }


}
