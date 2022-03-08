package com.aliexpress;

import com.aliexpress.pages.HomePage;
import com.aliexpress.pages.ResultsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class SearchTest {

    WebDriver driver;
    HomePage homePage;
    ResultsPage resultsPage;
    WebDriverWait wait;

    @BeforeMethod
    public void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.aliexpress.com");
    }

    @Test
    public void searchIphone() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.search("Iphone");
        resultsPage = new ResultsPage(driver);
        List<WebElement> pagesList = resultsPage.getPagesList();
        pagesList.get(2).click();
        wait.until(ExpectedConditions.urlContains("default&page=2"));
    }

    @AfterMethod
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
