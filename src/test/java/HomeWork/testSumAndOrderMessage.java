package HomeWork;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testSumAndOrderMessage extends BaseTest {
    @Test
    public void testSumAndOrderMessage() throws InterruptedException {
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

        sortProducts.selectByValue("lohi");

        WebElement aSortL = driver.findElement(By.className("active_option"));
        Assertions.assertEquals("Price (low to high)", aSortL.getText());

        // Добавьте в корзину самый дешевый и второй самый дорогой товар, передите в корзину продолжите с заказом
        List<WebElement> listPriceProduckts = driver.findElements(By.cssSelector("button[id^=add-to-cart]"));
        listPriceProduckts.get(0).click();
        listPriceProduckts.get(listPriceProduckts.size()-2).click();

        WebElement goToCart = driver.findElement(By.className("shopping_cart_link"));
        goToCart.click();

        Assertions.assertEquals("Sauce Labs Onesie", driver.findElements(By.className("inventory_item_name")).get(0).getText());
        Assertions.assertEquals("Sauce Labs Backpack", driver.findElements(By.className("inventory_item_name")).get(1).getText());
        Thread.sleep(3000);

        driver.findElement(By.id("checkout")).click();

        // Введите данные -> далее; проверьте что общая сумма 41.02 -> Завершите заказ; проверьте что сообщения:
        // "Thank you for your order!" и "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("Wolf");

        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Grau");

        WebElement postCode = driver.findElement(By.id("postal-code"));
        postCode.sendKeys("112233");

        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        WebElement priceTotal = driver.findElement(By.className("summary_total_label"));
        Assertions.assertEquals("Total: $41.02", priceTotal.getText());

        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        WebElement thankYouText = driver.findElement(By.className("complete-header"));
        Assertions.assertTrue(thankYouText.isDisplayed());
        WebElement completeText = driver.findElement(By.className("complete-text"));
        Assertions.assertTrue(completeText.isDisplayed());

        // Нажмите "Back Home" и каким хотите способом убедитесь, что вы на главной странице
        WebElement backHomeButton = driver.findElement(By.id("back-to-products"));
        backHomeButton.click();

        WebElement homePage = driver.findElement(By.className("title"));
        Assertions.assertTrue(homePage.isDisplayed());

        String urlHomePage = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", urlHomePage);

        Thread.sleep(2000);
        driver.quit();
    }
}
