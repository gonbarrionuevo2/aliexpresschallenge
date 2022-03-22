package webPages;

import com.aliexpress.Page;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j
public class ItemPage extends Page {


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
        waitUntilIsVisible(unitsAvailableMobile,5);
        String quantityUnitsAvailable = unitsAvailableMobile.getText().split(" ")[2];
        return Integer.parseInt(quantityUnitsAvailable);
    }
}
