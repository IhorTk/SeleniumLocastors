package ProbaTest;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProbaTestRew1 extends BaseTest {
    @Test
    //логирование на сайте
    public void probaTestRew1() {
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.cssSelector("#login2")).click();
        driver.findElement(By.cssSelector("#loginusername")).sendKeys("GrauWolf");
        driver.findElement(By.cssSelector("#loginpassword")).sendKeys("TraTaTa");
        driver.findElement(By.cssSelector("button.btn-primary[onclick='logIn()']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#nameofuser")));
        assertTrue(driver.findElement(By.cssSelector("#nameofuser")).isDisplayed());
        assertEquals("Welcome "+"GrauWolf",driver.findElement(By.cssSelector("#nameofuser")).getText());
        driver.findElement(By.cssSelector("#logout2")).click();
        assertTrue(driver.findElement(By.cssSelector("#signin2")).isDisplayed());
        assertTrue(driver.findElement(By.cssSelector("#login2")).isDisplayed());



    }
    @Test
    public void createNewUser(){
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.cssSelector("#signin2")).click();
        driver.findElement(By.cssSelector("#sign-username")).sendKeys("Bobobo");
        driver.findElement(By.cssSelector("#sign-password")).sendKeys("Halava");
        driver.findElement(By.cssSelector("button.btn-primary[onclick='register()']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        if(alert.getText().equals("Sign up successful.")){
            alert.accept();
        }
        if(alert.getText().equals("This user already exist.")){
            alert.accept();
            driver.findElement(By.xpath("//*[@id=\"signInModal\"]//button[text()='Close']")).click();
        }

        driver.findElement(By.cssSelector("#login2")).click();
        driver.findElement(By.cssSelector("#loginusername")).sendKeys("Bobobo");
        driver.findElement(By.cssSelector("#loginpassword")).sendKeys("Halava");
        driver.findElement(By.cssSelector("button.btn-primary[onclick='logIn()']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#nameofuser")));
        assertTrue(driver.findElement(By.cssSelector("#nameofuser")).isDisplayed());
        assertEquals("Welcome "+"Bobobo",driver.findElement(By.cssSelector("#nameofuser")).getText());

    }

    @Test
    //Обращение в службы сайта
    public void messageSupport() {
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.cssSelector("a.nav-link[data-target=\"#exampleModal\"]")).click();
        driver.findElement(By.cssSelector("#recipient-email")).sendKeys("grayWolf@forest.com");
        driver.findElement(By.cssSelector("#recipient-name")).sendKeys("Gray Wolf");
        driver.findElement(By.cssSelector("#message-text")).sendKeys("Ежики - самые прекрассные существа, а тигры лишь большие кошки");
        driver.findElement(By.cssSelector("button.btn-primary[onclick=\"send()\"]")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        assertEquals("Thanks for the message!!", alert.getText());
        alert.accept();
        ////*[@id="exampleModal"]//button[text()='Close'] - кнопка закрыть
    }

    @Test
    //поиск елементов
    public void sortArticle() {
        driver.get("https://www.demoblaze.com/");
        List<WebElement>  amountArticle = driver.findElements(By.cssSelector("a.hrefch"));
        assertEquals(9,amountArticle.size());
        driver.findElement(By.cssSelector("#itemc[onclick=\"byCat('phone')\"]")).click();
        wait.until(ExpectedConditions.stalenessOf(amountArticle.get(0)));
        List<WebElement>  amountArticlePhone = driver.findElements(By.cssSelector("a.hrefch"));
        assertEquals(7,amountArticlePhone.size());
        driver.findElement(By.cssSelector("#itemc[onclick=\"byCat('notebook')\"]")).click();
        wait.until(ExpectedConditions.stalenessOf(amountArticlePhone.get(0)));
        List<WebElement>  amountArticleNotebook = driver.findElements(By.cssSelector("a.hrefch"));
        assertEquals(6,amountArticleNotebook.size());
        driver.findElement(By.cssSelector("#itemc[onclick=\"byCat('monitor')\"]")).click();
        wait.until(ExpectedConditions.stalenessOf(amountArticleNotebook.get(0)));
        List<WebElement>  amountArticleMonitor = driver.findElements(By.cssSelector("a.hrefch"));
        assertEquals(2,amountArticleMonitor.size());


    }
    @Test
    //проверка количества элементов
    public void amountAllArtikle(){
        String url = "https://www.demoblaze.com/";
        assertEquals(15,provButton(url));
    }
    public int provButton(String url){
        driver.get(url);
        List<WebElement>  amountArticle = driver.findElements(By.cssSelector("a.hrefch"));
        int amountAll =amountArticle.size();
        assertEquals(9,amountAll);
        WebElement nexPageButton = driver.findElement(By.cssSelector(".page-link#next2"));
        while (nexPageButton.isDisplayed()){
            nexPageButton.click();
            wait.until(ExpectedConditions.stalenessOf(amountArticle.get(0)));
            List<WebElement> newPageArticle = driver.findElements(By.cssSelector("a.hrefch"));
            amountAll = amountAll + newPageArticle.size();
        }
        return amountAll;
    }

    @Test
    //добавление товара в корзину
    public void addArtickeToCart() {
        driver.get("https://www.demoblaze.com/");
        List<WebElement>  amountArticle = driver.findElements(By.cssSelector("a.hrefch"));
        driver.findElement(By.cssSelector("#itemc[onclick=\"byCat('phone')\"]")).click();
        wait.until(ExpectedConditions.stalenessOf(amountArticle.get(0)));
        List<WebElement>  amountArticlePhone = driver.findElements(By.cssSelector("a.hrefch"));
        amountArticlePhone.get(2).click();
        driver.findElement(By.cssSelector(".btn-success")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        assertEquals("Product added", alert.getText());
        alert.accept();
        driver.findElement(By.cssSelector("#cartur")).click();
        WebElement tableCart = driver.findElement(By.cssSelector(".table-responsive"));
        List<WebElement> rowTableCart = tableCart.findElements(By.cssSelector("#tbodyid>tr"));
        assertEquals(1, rowTableCart.size());
    }

    @Test
    //добавление нескольких товаров в корзину
    public void addArtickesToCart(){
        driver.get("https://www.demoblaze.com/");
        List<WebElement>  amountArticle = driver.findElements(By.cssSelector("a.hrefch"));

        //сортируем по телефонам
        driver.findElement(By.cssSelector("#itemc[onclick=\"byCat('phone')\"]")).click();
        wait.until(ExpectedConditions.stalenessOf(amountArticle.get(0)));
        List<WebElement>  amountArticlePhone = driver.findElements(By.cssSelector("a.hrefch"));

        //добавляем телефон в корзину
        amountArticlePhone.get(6).click();
        driver.findElement(By.cssSelector(".btn-success")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        assertEquals("Product added", alert.getText());
        alert.accept();

        //возвращаемся на главную страницу
       driver.findElement(By.cssSelector("a.nav-link[href='index.html']")).click();
        List<WebElement>  amountArticle1 = driver.findElements(By.cssSelector("a.hrefch"));

        //сортируем по ноутбукам
        driver.findElement(By.cssSelector("#itemc[onclick=\"byCat('notebook')\"]")).click();
        wait.until(ExpectedConditions.stalenessOf(amountArticle1.get(0)));
        List<WebElement>  amountArticleNotebook = driver.findElements(By.cssSelector("a.hrefch"));

        //добавляем нооутбук в корзину
        amountArticleNotebook.get(3).click();
        driver.findElement(By.cssSelector(".btn-success")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        assertEquals("Product added", alert.getText());
        alert.accept();

        //переходим в корзину проверяем наличие товаров и сумму
        driver.findElement(By.cssSelector("#cartur")).click();
        WebElement tableCart = driver.findElement(By.cssSelector(".table-responsive"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tbodyid>tr")));
        List<WebElement> rowTableCart = tableCart.findElements(By.cssSelector("#tbodyid>tr"));
        assertEquals(2, rowTableCart.size());
        assertEquals("1400",driver.findElement(By.cssSelector("#totalp")).getText());

        //удаление товара из корзины
        List<WebElement> deleteButton = tableCart.findElements(By.cssSelector("td>a[onclick^='deleteItem']"));
        deleteButton.get(1).click();
        wait.until(ExpectedConditions.stalenessOf(rowTableCart.get(0)));
        WebElement tableCart1 = driver.findElement(By.cssSelector(".table-responsive"));
        List<WebElement> rowTableCart1 = tableCart1.findElements(By.cssSelector("#tbodyid>tr"));
        assertEquals(1, rowTableCart1.size());
        assertEquals("700",driver.findElement(By.cssSelector("#totalp")).getText());

        //оформление заказа
        driver.findElement(By.cssSelector("button.btn-success")).click();


        driver.findElement(By.cssSelector("#name")).sendKeys("Moriarty");
        driver.findElement(By.cssSelector("#country")).sendKeys("England");
        driver.findElement(By.cssSelector("#city")).sendKeys("London");
        driver.findElement(By.cssSelector("#card")).sendKeys("4552 3689 7526 8990");
        driver.findElement(By.cssSelector("#month")).sendKeys("05");
        driver.findElement(By.cssSelector("#year")).sendKeys("2030");
        driver.findElement(By.cssSelector("button.btn-primary[onclick='purchaseOrder()']")).click();



        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.sweet-alert.showSweetAlert" +
                ".visible")));
        WebElement itogPurschase = driver.findElement(By.cssSelector(".sweet-alert.showSweetAlert"));
        Assertions.assertTrue(itogPurschase.getText().contains("Thank you for your purchase!"));
        Assertions.assertTrue(itogPurschase.getText().contains("Name: "+"Moriarty"));
        Assertions.assertTrue(itogPurschase.getText().contains("Card Number: "+"4552 3689 7526 8990"));
        Assertions.assertTrue(itogPurschase.getText().contains("Amount: "+"700"));

        driver.findElement(By.cssSelector("button.confirm.btn-primary")).click();

        List<WebElement>  amountArticle2 = driver.findElements(By.cssSelector("a.hrefch"));
        assertEquals(9,amountArticle2.size());

    }


}
