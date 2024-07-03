package ProbaTest;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Probatest1 extends BaseTest {
    @Test
    public void proba(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("");
        driver.findElement(By.cssSelector("#password")).sendKeys("");
        driver.findElement(By.cssSelector("#login-button")).click();
        System.out.println("driver.findElement(By.cssSelector(\".error-message-container\")).getText() = " + driver.findElement(By.cssSelector(".error-message-container")).getText());


    }


}
