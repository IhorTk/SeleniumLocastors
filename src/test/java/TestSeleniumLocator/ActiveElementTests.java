package TestSeleniumLocator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActiveElementTests extends BaseTest{
    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }

    @Test
    public void activeElementTest(){
        driver.get("http://www.google.com");
        driver.findElement(By.cssSelector("[name='q']")).sendKeys("searching text");

        // Get attribute of current active element
        String attr = driver.switchTo().activeElement().getAttribute("title");
        System.out.println(attr);
    }
}
