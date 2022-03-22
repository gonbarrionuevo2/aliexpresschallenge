package webPages;

import com.aliexpress.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page {

    WebDriverWait wait;
    private static String URL = "https://es.aliexpress.com/";

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search-key")
    WebElement searchbox;

    @FindBy(className = "search-button")
    WebElement searchButton;

    @FindBy(className = "btn-close")
    WebElement btnCloseModal;

    @FindBy(className = "poplayer-content")
    WebElement modalDesktop;

    @FindBy(className = "coupon-poplayer-modal")
    WebElement modalMobile;


    public void search(String text) {
        waitUntilIsVisible(searchbox,5);
        searchbox.sendKeys(text);
        searchButton.click();
    }

    public void goToURL(){
        goTo(URL);
    }

    public void ifModalExistCloseIt() {
        try {
            waitUntilIsVisible(modalDesktop,6);
            waitUntilItsClickable(btnCloseModal,3);
            btnCloseModal.click();
        } catch (Exception e) {
        }
    }


    //MOBILE
    public void ifCouponExistCloseIt(){
        try{
            waitUntilIsVisible(modalMobile,6);
            waitUntilItsClickable(btnCloseModal,3);
            btnCloseModal.click();
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
