package com.aliexpress.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ResultsPage {

    WebDriver driver;
    WebDriverWait wait;

    public ResultsPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.urlContains("wholesale"));
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "list-pagination")
    WebElement pagination;

    @FindBy(className = "_3t7zg _2f4Ho")
    List<WebElement> listProducts;

    public List<WebElement> getPagesList() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);

        scrollToElement(pagination);
        Thread.sleep(1000);
        List<WebElement> pagesList = pagination.findElements(By.className("next-btn"));
        Thread.sleep(500);

        return pagesList;
    }

    private void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public List<WebElement> getProductsList() {
        return listProducts;
    }
}
