package webPages;

import com.aliexpress.Page;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j
public class ItemPage extends Page {

    WebDriver driver;
    WebDriverWait wait;

    public ItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "product-quantity-tip")
    WebElement unitsAvailable;

    @FindBy(className = "rate-info--num--H6GDxkh")
    WebElement unitsAvailableMobile;

    public int getUnitsAvailable() {
        String quantityUnitsAvailable = unitsAvailable.getText().split(" ")[0];
        return Integer.parseInt(quantityUnitsAvailable);
    }

    //MOBILE
    public int getUnitsAvailableMobile(){
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("rate-info--num--H6GDxkh")));
        String quantityUnitsAvailable = unitsAvailableMobile.getText().split(" ")[2];
        System.out.println("La cantidad disponibles es de: " + quantityUnitsAvailable);
        return Integer.parseInt(quantityUnitsAvailable);
    }
}
