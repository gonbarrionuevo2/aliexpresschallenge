package com.aliexpress;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Base {

    WebDriver driver;

    public Base() {
    }

    public WebDriver chromeDriverConnection(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public void switchToAnotherTab(String originalWindow){
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
