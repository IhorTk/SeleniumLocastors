import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DisabledTest extends BaseTest {
    @Test
    public void disabledTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebElement input =driver.findElement(By.cssSelector("#input-example>input"));

        Assertions.assertFalse(input.isEnabled(), "Поле должно быть disabled");
        WebElement enableButton = driver.findElement(By.cssSelector("#input-example>button"));
        enableButton.click();

        Thread.sleep(5000);

        Assertions.assertTrue(input.isEnabled(), "Поле должно быть enabled");
    }
}
