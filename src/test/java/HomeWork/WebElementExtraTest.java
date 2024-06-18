package HomeWork;

import TestSeleniumLocator.BaseTest;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class WebElementExtraTest extends BaseTest {
    @Test
    public void testMenuItemsCount() { // 1
        driver.get("https://demoqa.com/elements");
        // Проверить что на верхнем уровне в левом меню ровно шесть элементов и первый из них Elements

         List<WebElement> selectElementsButton = driver.findElements(By.cssSelector("div.header-text"));

        Assertions.assertEquals(6,selectElementsButton.size(), "Должно быть 6 элементов");
        Assertions.assertEquals("Elements", selectElementsButton.get(0).getText(),"Должно быть Elements");
        driver.quit();
    }

    @Test
    public void testRandomItemVisibility() { // 2
        driver.get("https://the-internet.herokuapp.com/disappearing_elements");

        // Проверить есть ли на странице и отображается ли элемент меню Gallery, вывести это в консоль
        // Если отображает то ассерт что элементов меню 5, если нет, то 4
        List<WebElement> elementsMenu = driver.findElements(By.cssSelector("li>a[href]"));
        for(WebElement menu:elementsMenu) {
            if (menu.getText().equals("Gallery")) {
                System.out.println("Элемент меню " + menu.getText() + " найден");
                Assertions.assertEquals(5, elementsMenu.size(), "Должно быть 5 элементов");
                break;
            }
        }
        if(elementsMenu.size()<5) Assertions.assertEquals(4,elementsMenu.size(),"должно быть 4 элемента");
        System.out.println("elementsMenu.size() = " + elementsMenu.size());
        driver.quit();

    }

    @Test
    public void testCheckBoxSemiAppearance() throws InterruptedException { // 3
        driver.get("https://demoqa.com/checkbox");
        // Раскройте дерево (Home -> Documents) и кликните Office
        // Проверьте, что у оффиса "полная галочка", а у Documents и Home "половинчатая"
        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)", "");

        WebElement branchHome = driver.findElement(By.cssSelector(".rct-icon-expand-close"));
        branchHome.click();

        Thread.sleep(5000);
        List<WebElement> branchcheckBoxHome = driver.findElements(By.cssSelector("button[aria-label=Toggle]"));

        branchcheckBoxHome.get(2).click();

        WebElement officeButton =driver.findElement(By.cssSelector("label[for=tree-node-office]"));
        officeButton.click();
        WebElement homeButtonAssert = driver.findElement(By.id("tree-node-home"));
        WebElement documentsButtonAssert = driver.findElement(By.id("tree-node-documents"));
        WebElement officeButtonAssert = driver.findElement(By.id("tree-node-office"));

        Assertions.assertFalse(homeButtonAssert.isSelected(),"Элемент не должен быть выбран");
        Assertions.assertTrue(homeButtonAssert.isEnabled(),"Элемент должен быть активен");
        Assertions.assertFalse(documentsButtonAssert.isSelected(),"Элемент не должен быть выбран");
        Assertions.assertTrue(documentsButtonAssert.isEnabled(),"Элемент должен быть активен");
        Assertions.assertTrue(officeButtonAssert.isSelected(),"Элемент должен быть выбран");
        Assertions.assertTrue(officeButtonAssert.isEnabled(),"Элемент должен быть выбран");

//        WebElement branchDocuments = driver.findElement(By.cssSelector())

        driver.quit();
    }


    @Test
    public void testSumAndOrderMessageE2E() { // 5
        driver.get("https://www.saucedemo.com/");
        // Войдите в систему как standard_user, отсортируйте товары по цене от маленькой до большой
        // Добавьте в корзину самый дешевый и второй самый дорогой товар, передите в корзину продолжите с заказом
        // Введите данные -> далее; проверьте что общая сумма 41.02 -> Завершите заказ; проверьте что сообщения:
        // "Thank you for your order!" и "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
        // Нажмите "Back Home" и каким хотите способом убедитесь, что вы на главной странице
    }
}
