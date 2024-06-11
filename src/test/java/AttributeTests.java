import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AttributeTests extends BaseTest {
    @Test
    public void attributeTest(){
        driver.get("https://demoqa.com/text-box");

        WebElement fullName = driver.findElement(By.cssSelector("#userName"));
        System.out.println("autocomplete:" + fullName.getAttribute("autocomplete"));
        System.out.println("placeholder:" + fullName.getAttribute("placeholder"));
        System.out.println("type:" + fullName.getAttribute("type"));
        System.out.println("id:" + fullName.getAttribute("id"));
        System.out.println("class:" + fullName.getAttribute("class"));


    }

}
