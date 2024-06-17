import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.System.in;
public class SelectorTest2 extends BaseTest{
    @Test
    public void selectorTest2() throws InterruptedException {
        driver.get("https://demoqa.com/select-menu");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)", "");

        WebElement selectButton =driver.findElement(By.id("selectOne"));

        selectButton.click();
        List<WebElement> selectorOne = driver.findElements(By.cssSelector("div[id^=react-select-3-option-0]"));
        System.out.println("selecktorOne.size() = " + selectorOne.size());

        for (WebElement sbutton : selectorOne){
            System.out.println("sbutton.getText() = " + sbutton.getText());
        }

        for (WebElement sbutton : selectorOne){
            if (sbutton.getText().equals("Mr.")){
                sbutton.click();
                break;
            }
        }

        Thread.sleep(5000);

    }
}
