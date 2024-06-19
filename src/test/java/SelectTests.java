import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectTests extends BaseTest {


        @Test
        public void SelectTest() throws InterruptedException {
            driver.get("https://demoqa.com/select-menu");

            WebElement colorDrpDwn = driver.findElement(By.cssSelector("#oldSelectMenu"));
            Select selectColor = new Select(colorDrpDwn);
            List<WebElement> colorListOptions = selectColor.getOptions();

            System.out.println("Количество элементов в меню:" + colorListOptions.size());

            String activeColor = selectColor.getFirstSelectedOption().getText();
            Assertions.assertEquals("Red", activeColor);
            Thread.sleep(2000);

            selectColor.selectByVisibleText("Yellow");
            activeColor = selectColor.getFirstSelectedOption().getText();

            Assertions.assertEquals("Yellow",activeColor);
            Thread.sleep(2000);

            selectColor.selectByIndex(6);
            activeColor = selectColor.getFirstSelectedOption().getText();
            Assertions.assertEquals("White",activeColor);
            Thread.sleep(2000);

            selectColor.selectByValue("7");
            activeColor = selectColor.getFirstSelectedOption().getText();
            Assertions.assertEquals("Voilet",activeColor);
            Thread.sleep(2000);
        }
}
