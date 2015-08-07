package com.genefo.pages;

import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * Created by Yuri on 6/8/2015.
 */
public class GrafsPage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    //elements of dropdown list from medical facilities
    @FindBy(xpath = "//*[contains(text(),'Rating')]/..//*[contains(text(),'Facilities')]")
    WebElement facilitiesLink;

    @FindBy(xpath = "//*[contains(text(),'Rating')]/..//*[contains(text(),'Procedures')]")
    WebElement proceduresLink;

    @FindBy(xpath = "//*[contains(text(),'Rating')]/..//*[contains(text(),'Professionals')]")
    WebElement professionalsLink;

    //elements of dropdown list from Miscellaneous
    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Location')]")
    WebElement locationLink;

    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Gender')]")
    WebElement genderLink;

    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Age')]")
    WebElement ageLink;

    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Race')]")
    WebElement raceLink;

    @FindBy(xpath = "//*[contains(text(),'Miscellaneous')]/..//*[contains(text(),'Genes')]")
    WebElement genesLink;

    //elements of dropdown list from What Works For Me
    @FindBy(xpath = "//*[contains(text(),'What')]/..//*[contains(text(),'What Works Best')]")
    WebElement whatWorksBestLink;

    @FindBy(xpath = "//*[contains(text(),'What')]/..//*[contains(text(),'Therapy')]")
    WebElement therapyLink;

    @FindBy(xpath = "//*[contains(text(),'What')]/..//*[contains(text(),'Equipment')]")
    WebElement equipmentLink;

    @FindBy(xpath = "//*[contains(text(),'What')]/..//*[contains(text(),'Nutrition')]")
    WebElement nutritionlink;

    @FindBy(xpath = "//*[contains(text(),'What')]/..//*[contains(text(),'Exercises')]")
    WebElement exercisesLink;

    @FindBy(xpath = "//*[contains(text(),'What')]/..//*[contains(text(),'Alternative Medicine')]")
    WebElement alternativeMedicineLink;

    //elements of dropdown list from Milestones
    @FindBy(xpath = "//*[contains(text(),'Milestones')]/..//*[contains(text(),'Language Milestones')]")
    WebElement languageMilestonesLink;

    @FindBy(xpath = "//*[contains(text(),'Milestones')]/..//*[contains(text(),'Movement Milestones')]")
    WebElement movementMilestonesLink;

    @FindBy(xpath = "//*[contains(text(),'Milestones')]/..//*[contains(text(),'Toileting Milestones')]")
    WebElement toiletingMilestonesLink;

    @FindBy(xpath = "//*[contains(text(),'Milestones')]/..//*[contains(text(),'Eating Milestones')]")
    WebElement eatingMilestonesLink;

    //elements of dropdown list from Medicines

    @FindBy(xpath = "//*[contains(text(),'Medicines')]/..//*[text()='Medicine']")
    WebElement medicineLink;

    @FindBy(xpath = "//*[contains(text(),'Medicines')]/..//*[text()='Medicine Reasons']")
    WebElement medicineReasonsLink;

    @FindBy(xpath = "//*[contains(text(),'Medicines')]/..//*[text()='Medicine Effectiveness']")
    WebElement medicineEffectivenessLink;

    @FindBy(xpath = "//*[contains(text(),'Medicines')]/..//*[text()='Across Conditions']")
    WebElement acrossConditionsLink;


    public GrafsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    // Waits until 'Medicine' Panel appears on the screen pp

    public GrafsPage waitUntilGrafsPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(facilitiesLink);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }


    // Checks that title of our 'Medicine' Panel have appeared on the screen so we can work with it.
    public boolean isGrafsPage() {
        waitUntilGrafsPageIsLoaded();
        return exists(facilitiesLink);
    }


    // Click on the third star
    public GrafsPage clikOnFacilities() {
        clickElement(facilitiesLink);
        return this;
    }


    public GrafsPage clikOnProceduresLink() {
        clickElement(proceduresLink);
        return this;
    }

    public GrafsPage clikOnProfessionalsLink() {
        clickElement(professionalsLink);
        return this;
    }

    public GrafsPage clikOnLocationLink() {
        clickElement(locationLink);
        return this;
    }

    public GrafsPage clikOnGenderLink() {
        clickElement(genderLink);
        return this;
    }

    public GrafsPage clikOnAgeLink() {
        clickElement(ageLink);
        return this;
    }

    public GrafsPage clikOnRaceLink() {
        clickElement(raceLink);
        return this;
    }

    public GrafsPage clikOnGenesLink() {
        clickElement(genesLink);
        return this;
    }

    public GrafsPage clikOnWhatWorksBestLink() {
        clickElement(whatWorksBestLink);
        return this;
    }

    public GrafsPage clikOnTherapyLink() {
        clickElement(therapyLink);
        return this;
    }

    public GrafsPage clikOnEquipmentLink() {
        clickElement(equipmentLink);
        return this;
    }

    public GrafsPage clikOnNutritionLink() {
        clickElement(nutritionlink);
        return this;
    }

    public GrafsPage clikOnExercisesLink() {
        clickElement(exercisesLink);
        return this;
    }

    public GrafsPage clikOnAlternativeMedicineLink() {
        clickElement(alternativeMedicineLink);
        return this;
    }

    public GrafsPage clikOnLanguageMilestonesLink() {
        clickElement(languageMilestonesLink);
        return this;
    }

    public GrafsPage clikOnMovementMilestonesLink() {
        clickElement(movementMilestonesLink);
        return this;
    }

    public GrafsPage clikOnToiletingMilestonesLink() {
        clickElement(toiletingMilestonesLink);
        return this;
    }

    public GrafsPage clikOnMedicineLink() {
        clickElement(medicineLink);
        return this;
    }

    public GrafsPage clikOnMedicineReasonsLink() {
        clickElement(medicineReasonsLink);
        return this;
    }

    public GrafsPage clikOnMedicineEffectivenessLink() {
        clickElement(medicineEffectivenessLink);
        return this;
    }

    public GrafsPage clikOnEatingMiletonesLink() {
        clickElement(eatingMilestonesLink);
        return this;
    }

    public boolean isGraphLoaded(String element) throws IOException, InterruptedException {
        String xpath = "xxx";


        switch (element) {

            case "Medicine Reasons":
                xpath = "Reason medicine";

                break;
            case "Medicine Effectiveness":
                xpath = "Medicine reviews across";
                break;
            case "Across Conditions":
                xpath = "Across Conditions";
                break;
            case "Language Milestones":
                xpath = "Milestones - Language";
                break;
            case "Movement Milestones":
                xpath = "Developmental Milestones - Movement";
                break;
            case "Eating Milestones":
                xpath = "Developmental Milestones - Eating";
                break;
            case "Toileting Milestones":
                xpath = "Developmental Milestones - Toileting";
                break;
            case "What Works Best":
                xpath = "What works best";
                break;
            case "Therapy":
                xpath = "Psychotherapy";
                break;
            case "Equipment":
                xpath = "Splints";
                break;
            case "Nutrition":
                xpath = "Vitamin E";
                break;
            case "Exercises":
                xpath = "'Yoga";
                break;
            case "Alternative Medicine":
                xpath = "Pets";
                break;
            case "Medical Facilities":
                xpath = "Medical Facility";
                break;
            case "Procedures":
                xpath = "Medical Procedures";
                break;
            case "Healthcare Professionals":
                xpath = "Medical Healthcare";
                break;
            case "Location":
                xpath = "Location share in Aldosteronism, glucocorticoid-remediable";
                break;
            case "Gender":
                xpath = "Gender share in Aldosteronism, glucocorticoid-remediable";
                break;
            case "Age":
                xpath = "Birthday";
                break;
            case "Race":
                xpath = "Race share in Aldosteronism, glucocorticoid-remediable";
                break;
            case "Genes":
                xpath = "Genes in Aldosteronism, glucocorticoid-remediable";
                break;

            case "Medicine":
                xpath = "Medicine used";
                break;
        }

        waitUntilIsLoaded(webDriver.findElement(By.xpath("//*[contains(text(), '" + xpath + "')]")));
        return exists(webDriver.findElement(By.xpath("//*[contains(text(), '" + xpath + "')]")));
    }

    public void loadGraphs(String element) {

        switch (element) {

            case "Medicine Reasons":
                clikOnMedicineReasonsLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Medicine Effectiveness":
                clikOnMedicineEffectivenessLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Across Conditions":
                clikOnAcrossConditionsLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Language Milestones":
                clikOnLanguageMilestonesLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Movement Milestones":
                clikOnMovementMilestonesLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Toileting Milestones":
                clikOnToiletingMilestonesLink();
                Log.info("Clicking " + element + " link");
                break;
            case "What Works Best":
                clikOnWhatWorksBestLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Therapy":
                clikOnTherapyLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Equipment":
                clikOnEquipmentLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Nutrition":
                clikOnNutritionLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Exercises":
                clikOnExercisesLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Alternative Medicine":
                clikOnAlternativeMedicineLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Medical Facilities":
                clikOnFacilities();
                Log.info("Clicking " + element + " link");
                break;
            case "Procedures":
                clikOnProceduresLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Healthcare Professionals":
                clikOnProfessionalsLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Location":
                clikOnLocationLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Gender":
                clikOnGenderLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Age":
                clikOnAgeLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Race":
                clikOnRaceLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Genes":
                clikOnGenesLink();
                Log.info("Clicking " + element + " link");
                break;
            case "Eating Milestones":
                clikOnEatingMiletonesLink();
                Log.info("Clicking " + element + " link");
                break;

            case "Medicine":
                clikOnMedicineLink();
                Log.info("Clicking " + element + " link");
                break;
        }


    }


    public GrafsPage clikOnAcrossConditionsLink() {
        clickElement(acrossConditionsLink);
        return this;
    }
}
