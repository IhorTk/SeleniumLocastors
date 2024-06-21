package WebTableTests;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTabelTestHero extends BaseTest {
    @Test
    public void webTabeTestHero1(){
        driver.get("https://the-internet.herokuapp.com/tables");

        List<WebElement> tableColumns = driver.findElements(By.xpath("//*[@id=\"table1\"]/thead/tr/th"));
        for(WebElement columns:tableColumns){
            System.out.print("    " + columns.getText());
        }
        System.out.println();
        List<WebElement> tableRows = driver.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr"));
        for(WebElement rows:tableRows){
            System.out.println("   " + rows.getText());
        }

        WebElement table = driver.findElement(By.xpath("//*[@id=\"table1\"]"));
        List<WebElement> tRows = table.findElements(By.xpath(".//tr"));
        for (WebElement row : tRows) {
            List<WebElement> cells = row.findElements(By.xpath(".//td"));
            for (WebElement cell : cells) {
                System.out.print("  " +cell.getText());
            }
            System.out.println();
        }

        WebElement table2 = driver.findElement(By.xpath("//*[@id=\"table2\"]"));
        List<WebElement> tRows2 = table2.findElements(By.xpath(".//tr"));
        for (WebElement row : tRows2) {
            List<WebElement> cells = row.findElements(By.xpath(".//td"));
            for (WebElement cell : cells) {
                System.out.print("  " +cell.getText());
            }
            System.out.println();
        }
    }

    @Test
    public void webTabeTestHero2 (){
        driver.get("https://the-internet.herokuapp.com/tables");

        List<WebElement> table2 = driver.findElements(By.cssSelector("#table2"));

        for (WebElement table:table2){
            System.out.print("  " + table.getText());
        }
        System.out.println();
        System.out.println("============================================================================");

        // Поиск элемента таблицы по индексам
        WebElement table = driver.findElement(By.xpath("//*[@id=\"table2\"]"));
        List<WebElement> tabRows = table.findElements(By.xpath(".//tr"));
        int rowIndex= 2;
        int colIndex =3;
        WebElement cell = tabRows.get(rowIndex).findElements(By.xpath(".//td")).get(colIndex);

        String cellValue = cell.getText();
        System.out.println("cellValue = " + cellValue);

    }

}
