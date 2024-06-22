package WebTableTests;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TablesSortingTest01 extends BaseTest {
    @Test
    public void tablesSortingTest01() {
        driver.get("https://demowf.aspnetawesome.com/#Grid-search-using-parent-binding");
        driver.findElement(By.id("ContentPlaceHolder1_OCountry-awed")).click();
        driver.findElement(By.xpath("//li[.='Westfall']")).click();
        WebElement country = driver.findElement(By.cssSelector("#ContentPlaceHolder1_OCountry-awed > div.o-cptn"));
        Assertions.assertTrue(country.isDisplayed());
        driver.manage().timeouts().setScriptTimeout(3,TimeUnit.SECONDS);

        // Напишите метод, который будет принимать на вход WebElement таблицу, String имя столбца и String значение
        // A возвращать первую соответствующую строку как WebElement
        // Если id или данные в целом не поменяются, то этот assert должен работать - возможно проверку требуется обновить!

        WebElement tableElement = driver.findElement(By.cssSelector("div#ContentPlaceHolder1_Grid1"));

        Assertions.assertEquals("1609", getFirstRow(tableElement, "Person", "Luigi").findElement(By.tagName("td")).getText());
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
