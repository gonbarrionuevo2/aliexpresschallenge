package webPages;

import com.aliexpress.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ResultsPage extends Base{

    WebDriver driver;
    WebDriverWait wait;

    public ResultsPage(WebDriver driver) {
        super();
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.urlContains("wholesale"));
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[3]/div/div[2]/span[3]/input")
    WebElement goToPageBox;

    @FindBy(className = "jump-btn")
    WebElement goButton;

    public void goToPage(String numberOfPage) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-500)", "");
        Thread.sleep(1500);
        goToPageBox.sendKeys(numberOfPage);
        goButton.click();
    }

    public List<WebElement> getProductsList(WebDriver driver) {
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_18_85")));
        List<WebElement> productsList = driver.findElements(By.className("_18_85"));
        return productsList;
    }

    public void select2ndProduct(WebDriver driver) throws InterruptedException {
        List<WebElement> productsList = getProductsList(driver);
        Thread.sleep(2000);
        scrollToElement(productsList.get(1), driver);
        productsList.get(1).click();
    }

    //MOBILE

    public void select2ndProductMobile(WebDriver driver){
        wait = new WebDriverWait(driver,10);
        WebElement rightSide = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ivdeu")));
        List<WebElement> listProducts = rightSide.findElements(By.className("_3t7zg"));
        listProducts.get(0).click();
    }
}
