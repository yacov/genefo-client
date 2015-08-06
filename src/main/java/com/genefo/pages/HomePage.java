package com.genefo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * aka Landing page - first page of the website -  in our case http://52.10.6.51:8080
 * Oleg
 */
public class HomePage extends Page {

  @FindBy(how = How.TAG_NAME, using = "h1")
  @CacheLookup
  public WebElement header;

    //buttons
  @FindBy(xpath = "//*[@id='home_banner_small']//a[@class='landing_button']")
  WebElement freeSignUpButton;

    //lables
    @FindBy(xpath = "//*[@id='home_banner_small']/h1")
    WebElement titleLable;

  @FindBy(xpath = "//*[@class='btn btn-header btn-md navbar-btn' and contains(.,'Login')]")
  WebElement loginButton;

//  @FindBy(xpath = "//*[@class='col-md-6']//a[contains(text(),'Sign Up as a Healthcare Professional')]")
//    WebElement doctorButton;

    //private String label;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public HomePage openHomePage() {
        return this;
    }

    public void waitUntilHomePageIsLoaded() {
        try {
            waitUntilElementIsLoaded(titleLable);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean isOnHomePage() {
        return exists(titleLable);
    }

    public HomePage clickOnLogin(){
        clickElement(loginButton);
        return this;
    }

//    public HomePage clickOnSignUpDoctorButton() {
//        clickElement(doctorButton);
//        return this;
//    }


}
