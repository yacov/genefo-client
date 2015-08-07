package com.genefo;

import com.genefo.pages.HomePage;
import org.testng.Assert;

public class SampleTestNgTest {

    private HomePage homepage;

    // @BeforeMethod
    public void initPageObjects() {

    }

    //@Test
    public void testHomePageHasAHeader() {
        //   driver.get(baseUrl);
        Assert.assertFalse("".equals(homepage.header.getText()));
    }
}
