package com.aliexpress;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import webPages.HomePage;
import webPages.ItemPage;
import webPages.ResultsPage;

@Log4j
public class MobileTest {

    HomePage homePage;
    ResultsPage resultsPage;
    ItemPage itemPage;

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
        WebDriver driver = Page.newMobileWithDimensions(emulation,w,h);
        homePage = new HomePage(driver);
        resultsPage = new ResultsPage(driver);
        itemPage = new ItemPage(driver);
        homePage.goToURL();
        homePage = new HomePage(driver);
        homePage.ifCouponExistCloseIt();
        homePage.searchMobile("Iphone");
        resultsPage = new ResultsPage(driver);
        resultsPage.select2ndProductMobile(driver);
        itemPage = new ItemPage(driver);
        log.info("Mobile - Expected units to be greater or equal than 1: " + itemPage.getUnitsAvailableMobile());
        Assert.assertTrue(itemPage.getUnitsAvailableMobile() >= 1);
    }

    @AfterMethod
    public void quit() {
        itemPage.quitDriver();
    }
}
