package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemPage {

    WebDriver driver;
    WebDriverWait wait;

    public ItemPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.urlContains("item"));
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "product-quantity-tip")
    WebElement unitsAvailable;

    public int getUnitsAvailable() {
        String quantityUnitsAvailable = unitsAvailable.getText().split(" ")[0];
        System.out.println("La cantidad disponibles es de: " + quantityUnitsAvailable);
        return Integer.parseInt(quantityUnitsAvailable);
    }
}
