import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.System.in;

public class SelectorTest1 extends BaseTest {
    @Test
    public void selectorTest1() throws InterruptedException {
        driver.get("https://demoqa.com/select-menu");
        driver.manage().window().maximize();

        WebElement selectButton =driver.findElement(By.id("selectOne"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)", "");
        selectButton.click();
        List<WebElement> selectorOne = driver.findElements(By.cssSelector("div[id^=react-select-3-option-0]"));
        System.out.println("selecktorOne.size() = " + selectorOne.size());

        for (WebElement sbutton : selectorOne){
            System.out.println("sbutton.getText() = " + sbutton.getText());
        }
        int idx=0;
        for (int i = 0; i < selectorOne.size(); i++) {
            if(selectorOne.get(i).getText().equals("Mr.")){
                idx=i;
            }
        }
        String elspis = selectorOne.get(idx).getText();
        selectorOne.get(idx).click();

        Thread.sleep(1000);

        Assertions.assertEquals("Mr.",elspis);

    }
}
