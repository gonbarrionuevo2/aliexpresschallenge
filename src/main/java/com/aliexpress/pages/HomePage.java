package com.aliexpress.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.urlContains("aliexpress.com/"));
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "search-key")
    WebElement searchbox;

    @FindBy(className = "search-button")
    WebElement searchButton;

    public void search(String text){
        searchbox.sendKeys(text);
        searchButton.click();
    }


}
