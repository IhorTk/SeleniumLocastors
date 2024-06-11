package TestSeleniumLocator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LocatorsTests extends BaseTest{
    @Test
    public void tests() throws InterruptedException {
        //Открыть браузер по URL ссылке
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(1000);

        WebElement userName = driver.findElement(By.cssSelector("#user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id='login-button']"));

        userName.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();
        WebElement title = driver.findElement(By.cssSelector(".title"));
        String titleText = title.getText();

        Assertions.assertEquals("Products", titleText);

        Thread.sleep(2000);
    }
}
