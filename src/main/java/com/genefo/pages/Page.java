package com.genefo.pages;

import com.genefo.util.LogLog4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {
	private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
	protected WebDriver webDriver;
	protected StringBuffer verificationErrors = new StringBuffer();
	/*
	 * Constructor injecting the WebDriver interface
	 * 
	 * @param webDriver
	 */
	public Page(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}



	public void goBackBrowserButton() {
		webDriver.navigate().back();
	}

	public void goForwardBrowserButton() {
		webDriver.navigate().forward();
	}

	public void waitUntilIsLoaded(WebElement element) {
		new WebDriverWait(webDriver, 15).until(ExpectedConditions.visibilityOf(element));
	}

	public void reloadPage() {
		webDriver.navigate().refresh();
	}

	public void setElementText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
		// Assert.assertEquals(element.getAttribute("value"), text);
	}


	public void clickElement(WebElement element) {
		Log.debug("clicking on element " + element + "");
		element.click();
	}

	public void waitUntilIsLoadedCustomTime(WebElement element, int time) {
		try {
			new WebDriverWait(webDriver, time).until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public void selectValueInDropdown(WebElement dropdown, String value) {
	//   Select select = new Select(dropdown);
	//  select.selectByValue(value);
	// }

	// Returns label that we chose
	public String selectValueInDropdown(WebElement dropdown, String value) {
		Select select = new Select(dropdown);
		select.selectByValue(value);
		WebElement option = select.getFirstSelectedOption(); // Chooses label that fits the value
		return option.getText();
	}

	public boolean verifyElementIsPresent(WebElement element) {
		try {
			element.getTagName();
			return true;
		} catch (NoSuchElementException e) {
			Log.debug("---------------------------------");
			Log.debug("element " + element + " can not be found by  element.getTagName()");
			Log.debug("---------------------------------");
			return false;
		}
	}

	public void verifyText(WebElement element, String text) {
		try {
			Assert.assertEquals(text, element.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	public boolean verifyTextBoolean(WebElement element, String text) {
		Log.info("verifying that text from element " + element + " - ('" + element.getText() + "') - is equal to text '" + text + "'");
		return text.equals(element.getText());
	}

	// Verifies that we chose the label that we wanted.
	public boolean verifyTextBooleanInDropDown(String label, String chosenOption) {
		return chosenOption.equals(label);
	}

	public boolean exists(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException ignored) {
			return false;
		}
	}

	public void waitUntilElementIsLoaded(WebElement element) throws IOException, InterruptedException {
		new WebDriverWait(webDriver, 15).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElement(WebDriverWait wait, String element) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
	}

	protected boolean isElementPresent(By by) {
		try {
			webDriver.findElement(by);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Log.debug("----------ALERT-----------------");
			Log.debug("element " + by + " can not be found by ExpectedConditions.visibilityOf(element)");
			Log.debug("---------ALERT------------------");
			return false;
		}
	}

	// Pay attention: Works Only for first cell
	public boolean IsCellGreenAfterClick(WebElement locator) {
		clickElement(locator);
		// Is it Green?
		return "#008000".equals(Color.fromString(locator.getCssValue("background-color")).asHex());
	}

	public boolean IsCellColorChangedAfterClick(WebElement cell) {
		String cellColorBeforeClick = Color.fromString(cell.getCssValue("background-color")).asHex();
		clickElement(cell);
		String cellColorAfterClick = Color.fromString(cell.getCssValue("background-color")).asHex();
		return !cellColorBeforeClick.equals(cellColorAfterClick);
	}
}
