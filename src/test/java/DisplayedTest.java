import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DisplayedTest extends BaseTest {
    @Test
    public void displayedTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        WebElement startButton = driver.findElement(By.cssSelector("#start>button"));
        WebElement helloText =driver.findElement(By.cssSelector("#finish>h4"));

        Assertions.assertFalse(helloText.isDisplayed());
        startButton.click();

        Thread.sleep(6000);
        Assertions.assertTrue(helloText.isDisplayed());

        String text= helloText.getText();
        Assertions.assertEquals("Hello World!",text);

        System.out.println("helloText.getText() = " + helloText.getText());

    }
}
