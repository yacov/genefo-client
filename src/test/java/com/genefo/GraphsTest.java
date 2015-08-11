

package com.genefo;

import com.genefo.pages.DataProviders;
import com.genefo.pages.GrafsPage;
import com.genefo.pages.LoginPage;
import com.genefo.pages.MainPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/*
*
 * Created by Yura on 19.06.2015.


*/


public class GraphsTest extends TestBase {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    public WebDriverWait wait;
    public LoginPage loginPage;
    public MainPage mainPage;
    public GrafsPage grafsPage;

    private boolean acceptNextAlert = true;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        grafsPage = PageFactory.initElements(driver, GrafsPage.class);


        try {
            loginPage.openLoginPage(driver, baseUrl)
                    .waitUntilLoginPageIsLoaded()
                    .login("jakoff+444@gmail.com", "111111");
            mainPage.waitUntilMainPageIsLoaded()
                    .clikToSeeMoreGraphsButton();
            grafsPage.waitUntilGrafsPageIsLoaded();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadGrafFromFile")
    public void TestGraphsLink(String graph) {
        grafsPage.loadGraphs(graph);
        Log.info("Checking " + graph + " link");
        try {
            assertTrue("Graph element isn't found", grafsPage.isGraphLoaded(graph));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.info("Hurra!!! Graph " + graph + " is presented!");
        Reporter.log("Graph " + graph + " is presented");

    }


    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}
