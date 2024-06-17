import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class NavigationTest extends BaseTest {
    @Test
    public void navigationTest() throws InterruptedException {
        driver.navigate().to("https://www.selenium.dev/");
        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)", "");

        Thread.sleep(2000);
        WebElement webDriverButton = driver.findElement(By.cssSelector("a.selenium-webdriver"));

//        Actions a= new Actions(driver);
//        a.moveToElement(webDriverButton,0,-400);

        webDriverButton.click();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);

        driver.quit();

    }
    @Test
    public void titleAndUrTest(){
        driver.navigate().to("https://www.selenium.dev/");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        driver.quit();
    }

}
