package WebTableTests;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Futures.withTimeout;

public class TablesSortingTest02 extends BaseTest {
    @Test
    public void tablesSortingTest02() {
        driver.get("https://demowf.aspnetawesome.com/#Grid-search-using-parent-binding");
        driver.findElement(By.id("ContentPlaceHolder1_OCountry-awed")).click();
        driver.findElement(By.xpath("//li[.='Ghidrimesti']")).click();
        driver.findElement(By.id("ContentPlaceHolder1_Grid1PageSize-awed")).click();
        driver.findElement(By.xpath("//li[.=100]")).click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("tr[data-g='ContentPlaceHolder1_Grid1']"), 11));



        WebElement tableElement = driver.findElement(By.cssSelector("div#ContentPlaceHolder1_Grid1"));


        // NOTE: Идентичен tablesSortingTest01, но благодаря другим предварительным действиям и данным возвращает другие результаты, обратите на это внимание!
        // Напишите метод, который будет принимать на вход WebElement таблицу, String имя столбца и String значение
        // A возвращать первую соответствующую строку как WebElement
        // Если id или данные в целом не поменяются, то этот assert должен работать - возможно проверку требуется обновить!
        Assertions.assertEquals("947", getFirstRow(tableElement, "Person", "Morgan").findElement(By.tagName("td")).getText());
    }

    private WebElement getFirstRow(WebElement tableElement, String nameColumn, String value) {
        int collIndex =0;
        WebElement resultSearch = null;
        List<WebElement> tableHeaders = tableElement.findElements(By.cssSelector("td>div.awe-col"));
        for (int i = 0; i < tableHeaders.size(); i++) {
            if(tableHeaders.get(i).getText().equals(nameColumn)){
                collIndex=i;
                break;
            }
        }
        List<WebElement> tableRows = tableElement.findElements(By.cssSelector("tbody>tr"));
        for(WebElement row:tableRows){
            List<WebElement> collValue = row.findElements(By.cssSelector("tr>td"));
            if(collValue.get(collIndex).getText().equals(value)){
                resultSearch=row;
                break;
            }
        }
        return resultSearch;
    }
}
