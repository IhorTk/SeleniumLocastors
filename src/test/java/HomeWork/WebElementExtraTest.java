package HomeWork;

import TestSeleniumLocator.BaseTest;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    }


    @Test
    public void testSumAndOrderMessageE2E() throws InterruptedException { // 5
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        // Войдите в систему как standard_user, отсортируйте товары по цене от маленькой до большой
        // Добавьте в корзину самый дешевый и второй самый дорогой товар, передите в корзину продолжите с заказом
        // Введите данные -> далее; проверьте что общая сумма 41.02 -> Завершите заказ; проверьте что сообщения:
        // "Thank you for your order!" и "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
        // Нажмите "Back Home" и каким хотите способом убедитесь, что вы на главной странице

        //Войдите в систему как standard_user, отсортируйте товары по цене от маленькой до большой
        WebElement userNameInput = driver.findElement(By.cssSelector("input#user-name"));
        userNameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.cssSelector("input#password"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.cssSelector("input#login-button"));
        loginButton.click();

        WebElement productsDrDw = driver.findElement(By.className("product_sort_container"));
        Select sortProducts = new Select(productsDrDw);
        List<WebElement> sortListOptions = sortProducts.getOptions();

        sortProducts.selectByVisibleText("Price (low to high)");

//        String aSort = sortProducts.getFirstSelectedOption().getText();
//        Assertions.assertEquals("Price (low to high)", aSort);

        // Добавьте в корзину самый дешевый и второй самый дорогой товар, передите в корзину продолжите с заказом
//        List<WebElement> listPriceProduckts = driver.findElements(By.className("pricebar"));
//        Actions a= new Actions(driver);

        WebElement sauseLabsOnesi = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        sauseLabsOnesi.click();
        WebElement sauseLabsBackpack = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        sauseLabsBackpack.click();

        WebElement goToCart = driver.findElement(By.className("shopping_cart_link"));
        goToCart.click();

        assertEquals("Sauce Labs Onesie", driver.findElements(By.className("inventory_item_name")).get(0).getText());
        assertEquals("Sauce Labs Backpack", driver.findElements(By.className("inventory_item_name")).get(1).getText());

        driver.findElement(By.id("checkout")).click();

        // Введите данные -> далее; проверьте что общая сумма 41.02 -> Завершите заказ; проверьте что сообщения:
        // "Thank you for your order!" и "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
        WebElement firstName =driver.findElement(By.id("first-name"));
        firstName.sendKeys("Wolf");

        WebElement lastName =driver.findElement(By.id("last-name"));
        lastName.sendKeys("Grau");

        WebElement postCode =driver.findElement(By.id("postal-code"));
        postCode.sendKeys("112233");

        WebElement continueButton =driver.findElement(By.id("continue"));
        continueButton.click();

        WebElement priceTotal =driver.findElement(By.className("summary_total_label"));
        Assertions.assertEquals("Total: $41.02", priceTotal.getText());

        WebElement finishButton =driver.findElement(By.id("finish"));
        finishButton.click();

        WebElement thankYouText =driver.findElement(By.className("complete-header"));
        Assertions.assertTrue(thankYouText.isDisplayed());
        WebElement completeText =driver.findElement(By.className("complete-text"));
        Assertions.assertTrue(completeText.isDisplayed());

        // Нажмите "Back Home" и каким хотите способом убедитесь, что вы на главной странице
        WebElement backHomeButton = driver.findElement(By.id("back-to-products"));
        backHomeButton.click();

        WebElement homePage = driver.findElement(By.className("title"));
        Assertions.assertTrue(homePage.isDisplayed());

        String urlHomePage = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", urlHomePage);

        Thread.sleep(2000);
    }
}