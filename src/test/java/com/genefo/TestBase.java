package com.genefo;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

/**
 * Base class for all the TestNG-based test classes
 */
public class TestBase {
	protected WebDriver driver;

	protected String gridHubUrl;

	protected String baseUrl;

	@BeforeClass(alwaysRun = true)
	public void init() {
		baseUrl = "http://assertselenium.com/";
		PropertyConfigurator.configure("log4j.properties");
		DesiredCapabilities dCaps = new DesiredCapabilities();
		dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		dCaps.setCapability("takesScreenshot", false);
		driver = new PhantomJSDriver(dCaps);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}


	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
