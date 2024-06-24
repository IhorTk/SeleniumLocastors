package HomeWork;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class DemoWebtableTestHW extends BaseTest {
    @Test
    public void tablesAssertionsTest() {
        driver.get("https://www.techlistic.com/2017/02/automate-demo-web-table-with-selenium.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // Работаем со следующей таблицей - Demo Webtable 2 (Dynamic Table)
        // Добавьте assert, что в таблице присутствуют только 4 строки зданий
        // NOTE: задачу можно решить разными способами, поэтому помните, что один бизнес assert может физически представлять
        // из себя комплексную проверку, т.е. несколько assert-ов
        // Добавьте assert, что в 6-й строке таблицы (последняя строка) есть только два столбца (без привязки к номеру,
        // именно в последней строке)
        // Выведите в консоль всю информацию о втором самом высоком здании в таблице (не привязывайтесь к порядку строк)

        //проверяем что в таблице присутствуют только 4 строки зданий

        Assertions.assertEquals(4, getCountBuilding(), "Должно быть 4");
        Assertions.assertEquals(4, getCountBuilding1(), "Должно быть 4");

        // Добавьте assert, что в 6-й строке таблицы (последняя строка) есть только два столбца (без привязки к номеру,
        // именно в последней строке)

        Assertions.assertEquals(2, getCountColLastRow(),"Должно быть 2");

        // Выведите в консоль всю информацию о втором самом высоком здании в таблице (не привязывайтесь к порядку строк)
        String rankBuilding = "2";
        System.out.println("Информация о здании  " + rankBuilding + "  по высоте :");
        System.out.println(getInformBuilding(rankBuilding).getText());
        Assertions.assertEquals("Clock Tower Hotel Saudi Arabia Mecca 601m 2012 2",getInformBuilding(rankBuilding).getText());
    }
    private int getCountBuilding( ){
        WebElement table = driver.findElement(By.cssSelector("table.tsc_table_s13"));
        List<WebElement> tabRows = table.findElements(By.xpath(".//tr"));
        int count = tabRows.size();
        for(WebElement row:tabRows){
            if(row.getText().contains("Total") || row.getText().contains("Structure")){
                count--;
            }
        }
        return count;
    }
    private int getCountBuilding1( ){
        WebElement table = driver.findElement(By.cssSelector("table.tsc_table_s13"));
        List<WebElement> tabRowsBuild = table.findElements(By.xpath(".//tr[td]"));
        int count = 0;
        for(WebElement row:tabRowsBuild){
            List<WebElement> countCol = row.findElements(By.xpath(".//td"));
            if (countCol.size()==6) {
                count++;
            }
        }
        return count;
    }
    private int getCountColLastRow(){
        WebElement table = driver.findElement(By.cssSelector("table.tsc_table_s13"));
        List<WebElement> tabRowsBuild = table.findElements(By.xpath(".//tr[td]"));
        int count=0;
        for (WebElement row:tabRowsBuild){
            if(row.getText().contains("Total")){
                List<WebElement> colTd = row.findElements(By.xpath(".//td"));
                List<WebElement> colTh = row.findElements(By.xpath(".//th"));
                count=colTd.size()+colTh.size();
            }
            break;
        }
        return count;
    }

    private WebElement getInformBuilding(String rank){
        WebElement inform = null;
        WebElement table = driver.findElement(By.cssSelector("table.tsc_table_s13"));
        List<WebElement> tabRowsBuild = table.findElements(By.xpath(".//tr[td]"));
        for (WebElement row:tabRowsBuild){
            List<WebElement> tabCollsBuild = row.findElements(By.xpath(".//td"));
            for(WebElement coll:tabCollsBuild){
                if(coll.getText().equals(rank)){
                    inform = row;
                    break;
                }
            }
        }
        return inform;
    }
}
