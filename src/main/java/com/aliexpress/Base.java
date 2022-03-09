package com.aliexpress;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;

public class Base {

    WebDriver driver;

    public Base() {
    }

    public WebDriver chromeDriverConnection() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver newMobileWithDimensions(String emulation, int w, int h) {
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


    public void switchToAnotherTab(String originalWindow) {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void scrollToElement(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

}
