package com.genefo;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.internal.TestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
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
		baseUrl = "http://52.10.6.51:8080";
		PropertyConfigurator.configure("log4j.properties");
		DesiredCapabilities dCaps = new DesiredCapabilities();
		dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		dCaps.setCapability("takesScreenshot", true);
		driver = new PhantomJSDriver(dCaps);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterTest(alwaysRun = true)
	public void takescreen(TestResult result) throws IOException {
		if (!result.isSuccess()) {
			File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String failureImageFileName = result.getMethod().getMethodName() + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + ".png";

			// It will store all the screenshots in test-output/screenshots folder
			String destDir = System.getProperty("user.dir") + "/" + "target/surefire-reports/screenshots";
			new File(destDir).mkdirs();
			String destFile = destDir + "/" + failureImageFileName;
			FileUtils.copyFile(imageFile, new File(destFile));
		}
	}
	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
