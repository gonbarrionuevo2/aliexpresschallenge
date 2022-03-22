package webPages;

import com.aliexpress.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ResultsPage extends Page {

    WebDriverWait wait;
    public static String URL = "";

    public ResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[3]/div/div[2]/span[3]/input")
    WebElement goToPageBox;

    @FindBy(className = "jump-btn")
    WebElement goButton;

    @FindBy(className = "_18_85")
    WebElement product;

    public void goToPage(int numberOfPage){
        scrollToElement(goToPageBox);
        waitUntilIsVisible(goToPageBox,5);
        goToPageBox.sendKeys(Integer.toString(numberOfPage));
        goButton.click();
        waitUntilUrlContains("default&page="+numberOfPage);
    }

    public List<WebElement> getProductsList() {
        waitUntilIsVisible(product,5);
        List<WebElement> productsList = findElements(By.className("_18_85"));
        return productsList;
    }

    public void selectProductNumber(int number){
        String originalWindow = getCurrentTab();
        List<WebElement> productsList = getProductsList();
        scrollToElement(productsList.get(number-1));
        productsList.get(number-1).click();
        switchToAnotherTab(originalWindow);
    }

    //MOBILE

    public void select2ndProductMobile(WebDriver driver){
        wait = new WebDriverWait(driver,10);
        WebElement rightSide = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ivdeu")));
        List<WebElement> listProducts = rightSide.findElements(By.className("_3t7zg"));
        listProducts.get(0).click();
    }
}
