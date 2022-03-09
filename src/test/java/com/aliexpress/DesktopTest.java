package com.aliexpress;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webPages.HomePage;
import webPages.ItemPage;
import webPages.ResultsPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class DesktopTest {

    WebDriver driver;
    Base base;
    HomePage homePage;
    ResultsPage resultsPage;
    ItemPage itemPage;
    WebDriverWait wait;

    @BeforeMethod
    public void launchBrowser() {
        base = new Base();
        this.driver = base.chromeDriverConnection();
        driver.get("https://www.aliexpress.com");
    }

    @Test
    public void searchIphone() throws InterruptedException {
        wait = new WebDriverWait(driver, 20);
        homePage = new HomePage(driver);
        homePage.ifModalExistCloseIt();
        homePage.search("Iphone");
        resultsPage = new ResultsPage(driver);
        resultsPage.goToPage("2");
        wait.until(ExpectedConditions.urlContains("default&page=2"));
        String originalWindow = driver.getWindowHandle();
        resultsPage.select2ndProduct(driver);
        wait.until(numberOfWindowsToBe(2));
        base.switchToAnotherTab(originalWindow);
        itemPage = new ItemPage(driver);
        Assert.assertTrue(itemPage.getUnitsAvailable() > 1);
    }

    @AfterMethod
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
