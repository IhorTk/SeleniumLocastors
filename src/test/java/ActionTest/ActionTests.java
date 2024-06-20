package ActionTest;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ActionTests extends BaseTest {
    @Test
    public void actionTest() throws InterruptedException {
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        Thread.sleep(3000);

        WebElement rightClikButton = driver.findElement(By.cssSelector("span[class^='context-menu-one']"));

        Actions builder = new Actions(driver);
        builder.contextClick(rightClikButton).perform();

        WebElement contestMenu = driver.findElement(By.cssSelector("ul[class^='context-menu-list'"));
        Assertions.assertTrue(contestMenu.isDisplayed());

        List<WebElement> selectMenuOptons = driver.findElements(By.cssSelector("li[class^='context-menu-item']"));
        selectMenuOptons.get(3).click();

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("clicked: paste", alert.getText());
        alert.accept();


        WebElement dubleClicks = driver.findElement(By.cssSelector("button[ondBlclick='myFunction()']"));
        builder.doubleClick(dubleClicks).perform();

        Alert alert1 = driver.switchTo().alert();
        Assertions.assertEquals("You double clicked me.. Thank You..", alert1.getText());
        alert.accept();

        driver.quit();
    }
}
