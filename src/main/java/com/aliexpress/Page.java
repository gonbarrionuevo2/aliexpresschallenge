package com.aliexpress;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

@AllArgsConstructor
public abstract class Page {

    WebDriver driver;

    public static WebDriver chromeDriverConnection() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver newMobileWithDimensions(String emulation, int w, int h) {
        WebDriverManager.chromedriver().setup();
        Map<String, String> deviceMobEmu = new HashMap<>();
        deviceMobEmu.put("deviceName", emulation);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", deviceMobEmu);
        WebDriver driver = new ChromeDriver(chromeOptions);
        Dimension d = new Dimension(w, h);
        driver.manage().window().setSize(d);
        return driver;
    }

    public void goTo(String url){
        driver.get(url);
    }

    public void quitDriver(){
        if (driver != null) {
            driver.quit();
        }
    }

    public void switchToAnotherTab(String originalWindow) {
        WebDriverWait wait = new WebDriverWait(driver,6);
        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public String getCurrentTab(){
        return driver.getWindowHandle();
    }

    public void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void waitUntilPageItsLoaded( ){
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("next-overlay-wrapper")));
    }

    public void scrollToBottom(){
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitUntilPageItsLoaded();
    }

    public void waitUntilItsClickable( WebElement elemento, int segundos) {
        WebDriverWait wait = new WebDriverWait(driver, segundos);
        wait.until(ExpectedConditions.elementToBeClickable(elemento));
    }

    public void waitUntilIsVisible(WebElement element, int segundos) {
        WebDriverWait wait = new WebDriverWait(driver, segundos);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilIsVisible(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilUrlToBe(String url) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void waitUntilUrlContains(String url) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlContains(url));
    }

    public List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

}
