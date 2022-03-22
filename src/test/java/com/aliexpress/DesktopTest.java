package com.aliexpress;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webPages.HomePage;
import webPages.ItemPage;
import webPages.ResultsPage;

@Log4j
public class DesktopTest {

    HomePage homePage;
    ResultsPage resultsPage;
    ItemPage itemPage;

    @BeforeMethod
    public void launchBrowser() {
        WebDriver driver = Page.chromeDriverConnection();
        homePage = new HomePage(driver);
        resultsPage = new ResultsPage(driver);
        itemPage = new ItemPage(driver);
    }

    @Test
    public void searchIphone() {
        homePage.goToURL();
        homePage.ifModalExistCloseIt();
        homePage.search("Iphone");
        resultsPage.scrollToBottom();
        resultsPage.goToPage(2);
        resultsPage.selectProductNumber(2);
        log.info("Expected units to be greater or equal than 1: " + itemPage.getUnitsAvailable());
        Assert.assertTrue(itemPage.getUnitsAvailable() >= 1);
    }

    @AfterMethod
    public void quit() {
        itemPage.quitDriver();
    }
}
