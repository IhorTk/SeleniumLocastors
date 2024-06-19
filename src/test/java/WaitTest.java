import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitTest extends BaseTest {
    @Test
    public void addRedBox(){
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("adder")).click();
        Assertions.assertTrue(driver.findElement(By.id("box0")).isDisplayed());
        driver.findElement(By.id("adder")).click();
        Assertions.assertTrue(driver.findElement(By.id("box1")).isDisplayed());
        driver.findElement(By.id("adder")).click();
        Assertions.assertTrue(driver.findElement(By.id("box2")).isDisplayed());

        List<WebElement> countBox = driver.findElements(By.cssSelector(".redbox"));
        Assertions.assertEquals(3,countBox.size());

        driver.quit();
    }
    @Test
    public void addBoxImplicityWaitTest() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.id("adder")).click();
        driver.findElement(By.id("adder")).click();
        driver.findElement(By.id("adder")).click();
        Thread.sleep(1000);
        List<WebElement> redBoxes = driver.findElements(By.cssSelector(".redbox"));
        int countRedBoxes = redBoxes.size();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));//.implicitlyWait(Duration.ofSeconds(100));

        Assertions.assertEquals(3, countRedBoxes);
        driver.quit();
    }
}
