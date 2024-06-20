import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class WaitTest extends BaseTest {
    @Test
    public void addRedBox(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.id("adder")).click();
//        WebElement box = driver.findElement(By.id("box0"));
        assertTrue(driver.findElement(By.id("box0")).isDisplayed());// или так
        driver.findElement(By.id("adder")).click();
//        WebElement box1 = driver.findElement(By.id("box1"));
        assertTrue(driver.findElement(By.id("box1")).isDisplayed());// или так
        driver.findElement(By.id("adder")).click();
//        WebElement box2 = driver.findElement(By.id("box2"));
        assertTrue(driver.findElement(By.id("box2")).isDisplayed());// или так

        List<WebElement> countBox = driver.findElements(By.cssSelector(".redbox"));
        assertEquals(3,countBox.size());

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

        assertEquals(3, countRedBoxes);
        driver.quit();
    }
    @Test
    public void revealNewInput(){

        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.id("reveal")).click();

        WebElement relevaled = driver.findElement(By.id("revealed"));

        Wait<WebDriver> wait = new WebDriverWait(driver,Duration.ofSeconds(2));
        wait.until(d->relevaled.isDisplayed());


        relevaled.sendKeys("Hallo Weirdos!!!");

        Assertions.assertEquals("Hallo Weirdos!!!", relevaled.getDomProperty("value"));
        driver.quit();

    }
    @Test
    public void FluentWaitTest(){
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        WebElement revealed = driver.findElement(By.id("revealed"));
        driver.findElement(By.id("reveal")).click();

        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(2))
                        .pollingEvery(Duration.ofMillis(300))
                        .ignoring(ElementNotInteractableException.class);

        wait.until(d -> {
                    revealed.sendKeys("Hallo Weirdos!!!");
                    return true;
                });
        Assertions.assertEquals("Hallo Weirdos!!!", revealed.getDomProperty("value"));

    }



}
