package com.aliexpress;

import com.aliexpress.pages.HomePage;
import com.aliexpress.pages.ItemPage;
import com.aliexpress.pages.ResultsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class SearchTest {

    WebDriver driver;
    HomePage homePage;
    ResultsPage resultsPage;
    ItemPage itemPage;
    WebDriverWait wait;

    @BeforeMethod
    public void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.aliexpress.com");
        try{
            wait = new WebDriverWait(driver,20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("poplayer-content")));
            wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-close"))).click();
        }catch (Exception e){}
    }

    @Test
    public void searchIphone() throws InterruptedException {
        wait = new WebDriverWait(driver,20);
        homePage = new HomePage(driver);
        homePage.search("Iphone");
        resultsPage = new ResultsPage(driver);
        resultsPage.goToPage("2");
        wait.until(ExpectedConditions.urlContains("default&page=2"));
        List<WebElement> productsList = resultsPage.getProductsList();
        String originalWindow = driver.getWindowHandle();
        productsList.get(1).click();
        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        itemPage = new ItemPage(driver);
        Assert.assertTrue(itemPage.getUnitsAvailable()>1);

    }

    @AfterMethod
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
