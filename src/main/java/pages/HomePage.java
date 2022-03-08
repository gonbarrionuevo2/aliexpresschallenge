package pages;

import org.openqa.selenium.By;
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
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search-key")
    WebElement searchbox;

    @FindBy(className = "search-button")
    WebElement searchButton;

    @FindBy(className = "poplayer-content")
    WebElement modal;

    @FindBy(className = "btn-close")
    WebElement btnCloseModal;

    public void ifModalExistCloseIt(){
        try {
            wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated((By) modal));
            wait.until(ExpectedConditions.elementToBeClickable(btnCloseModal)).click();
        } catch (Exception e) {
        }
    }

    public void search(String text) {
        searchbox.sendKeys(text);
        searchButton.click();
    }

}
