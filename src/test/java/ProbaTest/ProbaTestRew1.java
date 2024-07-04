package ProbaTest;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProbaTestRew1 extends BaseTest {
    @Test
    //логирование на саайте
    public void probaTestRew1() {
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.cssSelector("#login2")).click();
        driver.findElement(By.cssSelector("#loginusername")).sendKeys("GrauWolf");
        driver.findElement(By.cssSelector("#loginpassword")).sendKeys("TraTaTa");
        driver.findElement(By.cssSelector("button.btn-primary[onclick='logIn()']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#nameofuser")));
        Assertions.assertTrue(driver.findElement(By.cssSelector("#nameofuser")).isDisplayed());
    }
    @Test
    //поиск елементов
    public void poiskElem() {
        driver.get("https://www.demoblaze.com/");
        List<WebElement>  amountArticle = driver.findElements(By.cssSelector("a.hrefch"));
        Assertions.assertEquals(9,amountArticle.size());
        driver.findElement(By.cssSelector("#itemc[onclick=\"byCat('phone')\"]")).click();
        wait.until(ExpectedConditions.stalenessOf(amountArticle.get(0)));
        List<WebElement>  amountArticlePhone = driver.findElements(By.cssSelector("a.hrefch"));
        Assertions.assertEquals(7,amountArticlePhone.size());
        driver.findElement(By.cssSelector("#itemc[onclick=\"byCat('notebook')\"]")).click();
        wait.until(ExpectedConditions.stalenessOf(amountArticlePhone.get(0)));
        List<WebElement>  amountArticleNotebook = driver.findElements(By.cssSelector("a.hrefch"));
        Assertions.assertEquals(6,amountArticleNotebook.size());
        driver.findElement(By.cssSelector("#itemc[onclick=\"byCat('monitor')\"]")).click();
        wait.until(ExpectedConditions.stalenessOf(amountArticleNotebook.get(0)));
        List<WebElement>  amountArticleMonitor = driver.findElements(By.cssSelector("a.hrefch"));
        Assertions.assertEquals(2,amountArticleMonitor.size());


    }
//    @Test
//    //проверка количества элементов
}
