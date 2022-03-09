package webPages;

import com.aliexpress.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Base {

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

    @FindBy(className = "btn-close")
    WebElement btnCloseModal;

    public void ifModalExistCloseIt() {
        try {
            wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("poplayer-content")));
            wait.until(ExpectedConditions.elementToBeClickable(btnCloseModal)).click();
        } catch (Exception e) {
        }
    }

    public void search(String text) {
        searchbox.sendKeys(text);
        searchButton.click();
    }


    //MOBILE
    public void ifCouponExistCloseIt(){
        try{
            wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("coupon-poplayer-modal")));
            wait.until(ExpectedConditions.elementToBeClickable(btnCloseModal)).click();
        }catch (Exception e){}
    }

    public void searchMobile(String text, WebDriver driver){
        wait = new WebDriverWait(driver,10);
        WebElement box = driver.findElement(By.className("_3Fb4x"));
        box.findElement(By.xpath("//*[@id='header']/div/div[2]/div[2]/input")).click();

        WebElement searchEnabled = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_3vN3V")));
        if(searchEnabled.isEnabled()){
            searchEnabled.sendKeys(text);
            searchEnabled.sendKeys(Keys.ENTER);
        }
    }

}
