package HomeWork;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestCheckBoxSemiAppearance extends BaseTest {
    @Test
    public void testCheckBoxSemiAppearance1(){
        driver.get("https://demoqa.com/checkbox");
        // Раскройте дерево (Home -> Documents) и кликните Office
        // Проверьте, что у оффиса "полная галочка", а у Documents и Home "половинчатая"
        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)", "");

        WebElement branchHome = driver.findElement(By.cssSelector(".rct-icon-expand-close"));
        branchHome.click();

        List<WebElement> branchcheckBoxHome = driver.findElements(By.cssSelector("button[aria-label=Toggle]"));

        branchcheckBoxHome.get(2).click();

        WebElement officeButton =driver.findElement(By.cssSelector("label[for=tree-node-office]"));
        officeButton.click();
        WebElement homeButtonAssert = driver.findElement(By.id("tree-node-home"));
        WebElement documentsButtonAssert = driver.findElement(By.id("tree-node-documents"));
        WebElement officeButtonAssert = driver.findElement(By.id("tree-node-office"));

        Assertions.assertTrue(homeButtonAssert.isEnabled(),"Элемент должен быть активен");
        Assertions.assertFalse(homeButtonAssert.isSelected(),"Элемент не должен быть выбран");
        Assertions.assertTrue(documentsButtonAssert.isEnabled(),"Элемент должен быть активен");
        Assertions.assertFalse(documentsButtonAssert.isSelected(),"Элемент не должен быть выбран");
        Assertions.assertTrue(officeButtonAssert.isEnabled(),"Элемент должен быть активен");
        Assertions.assertTrue(officeButtonAssert.isSelected(),"Элемент должен быть выбран");


    }
}
