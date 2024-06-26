package FrameTest;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class IFrameTest extends BaseTest {

    @Test
    public void iFrameTest(){
        driver.get("https://the-internet.herokuapp.com/iframe");

        driver.switchTo().frame("mce_0_ifr");

        WebElement textArea = driver.findElement(By.id("tinymce"));

        System.out.println(textArea.getText());
        driver.switchTo().parentFrame();

        WebElement iFrameElement =driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iFrameElement);

        driver.quit();

    }

}
