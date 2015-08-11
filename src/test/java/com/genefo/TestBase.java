package com.genefo;

import com.genefo.util.PropertyLoader;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Base class for all the TestNG-based test classes
 */
public class TestBase {
	protected WebDriver driver;

	protected String gridHubUrl;

	protected String baseUrl;

	@BeforeSuite(alwaysRun = true)
	public void init() {
		baseUrl = PropertyLoader.loadProperty("site.url");
		gridHubUrl = PropertyLoader.loadProperty("grid2.hub");

		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("intl.accept_languages", "ru");
		String Xport = System.getProperty("lmportal.xvfb.id", ":0");
		final File firefoxPath = new File(System.getProperty(
				"lmportal.deploy.firefox.path", "/usr/bin/firefox"));
		FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
		firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);
		PropertyConfigurator.configure("log4j.properties");
		// Start Firefox driver
		driver = new FirefoxDriver(firefoxBinary, null);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}


	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
