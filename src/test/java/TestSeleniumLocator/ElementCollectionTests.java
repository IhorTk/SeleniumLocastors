package TestSeleniumLocator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementCollectionTests extends BaseTest{
    @BeforeEach
    public void setup(){
        // driver = new ChromeDriver();
        //Открыть браузер по URL ссылке
        driver.get("https://www.saucedemo.com/");

        WebElement userName = driver.findElement(By.cssSelector("#user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id='login-button']"));

        userName.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();
    }

    @Test
    public void collectionTest() throws InterruptedException {
        List<WebElement> addItems = driver.findElements(By.cssSelector("button.btn_inventory"));

        for(WebElement element : addItems){
            element.click();
            Thread.sleep(1000);
        }

        WebElement shoppingCart = driver.findElement(By.cssSelector("span[data-test='shopping-cart-badge']"));
        String itemsCount = shoppingCart.getText();
        Assertions.assertEquals(addItems.size(), Integer.parseInt(itemsCount));
    }
}
