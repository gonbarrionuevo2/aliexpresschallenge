package com.aliexpress;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import webPages.HomePage;
import webPages.ItemPage;
import webPages.ResultsPage;

public class MobileTest {

    WebDriver driver;
    Base base;
    HomePage homePage;
    ResultsPage resultsPage;
    ItemPage itemPage;
    WebDriverWait wait;

    @DataProvider
    public Object[][] mobileEmulations() {
        return new Object[][]{
                //{"iPad Pro", 420,600},
                //{"Nexus 5", 360, 640},
                {"iPhone X", 375,812},
                //{"Pixel 2", 411,731},
        };
    }

    @Test(dataProvider = "mobileEmulations")
    public void validateResponsive(String emulation, int w, int h) {
        base = new Base();
        this.driver = base.newMobileWithDimensions(emulation,w,h);
        driver.get("https://www.aliexpress.com");
        wait = new WebDriverWait(driver, 20);
        homePage = new HomePage(driver);
        homePage.ifCouponExistCloseIt();
        homePage.searchMobile("Iphone",driver);
        resultsPage = new ResultsPage(driver);
        resultsPage.select2ndProductMobile(driver);
        itemPage = new ItemPage(driver);
        Assert.assertTrue(itemPage.getUnitsAvailableMobile() > 1);
    }

    @AfterMethod
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
