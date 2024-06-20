package ActionTest;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GoolTest extends BaseTest {
    @Test
    public void goolTest() throws InterruptedException {
        driver.get("https://learn.javascript.ru/article/mouse-drag-and-drop/ball4/");
        WebElement ball = driver.findElement(By.id("ball"));
        WebElement gate = driver.findElement(By.id("gate"));
        Actions builder = new Actions(driver);
        builder.dragAndDrop(ball,gate).perform();

        WebElement pinkGate = driver.findElement(By.cssSelector("#gate[style='background: pink;']"));
        Assertions.assertTrue(pinkGate.isDisplayed());
        driver.quit();

    }

}
