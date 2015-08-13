package com.genefo;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
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
		baseUrl = "http://52.10.6.51:8080";
		PropertyConfigurator.configure("log4j.properties");
		DesiredCapabilities dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		dCaps.setCapability("takesScreenshot", true);
		driver = new PhantomJSDriver(dCaps);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	/*@AfterTest(alwaysRun = true)
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
	}*/

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
