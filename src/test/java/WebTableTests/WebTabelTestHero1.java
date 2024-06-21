package WebTableTests;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTabelTestHero1 extends BaseTest {
    @Test
    public void getHeadersTest() { // - 1
        driver.get("https://the-internet.herokuapp.com/tables");
        // Вывести в консоль все хедеры и их индексы
        List<WebElement> table1Header = driver.findElements(By.xpath("//*[@id=\"table1\"]/thead/tr/th"));

        for (int i = 0; i < table1Header.size(); i++) {
            System.out.println(i + " " + table1Header.get(i).getText());
        }

    }


    @Test
    public void getDueByFirstName() { // - 2
        driver.get("https://the-internet.herokuapp.com/tables");
        // Написать метод, чтобы получить из таблицы значение Due по имени, например для Frank должно быть 51

        assertEquals("$51.00", getDueByFirstName("Frank"));
        assertEquals("$100.00", getDueByFirstName("Jason"));

    }
    private String getDueByFirstName(String name) {
        int rowIndex = 0; //индекс строки
        int colIndex = 0; //индекс Столбца
        //ищем индекс Столбца
        List<WebElement> tabHeader = driver.findElements(By.xpath("//*[@id=\"table1\"]/thead/tr/th"));
        for (WebElement head:tabHeader) {
            if(head.getText().equals("Due")) break;
            colIndex++;
        }

        //ищем индекс строки
        List<WebElement> tabRows = driver.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr"));
        for (WebElement row:tabRows) {
            if(row.getText().contains(name)) break;
            rowIndex++;
        }
        // получаем результат
        WebElement due = tabRows.get(rowIndex).findElements(By.xpath(".//td")).get(colIndex);
        String result = due.getText();

        return result;
    }



    //    @Test
//    public void getRowIndexByLastName() { // - 3
//        driver.get("https://the-internet.herokuapp.com/tables");
//        // Написать метод, чтобы получить индекс строки по значению Last Name
//        assertEquals(1, getRowIndexByLastName("Bach"));
//        assertEquals(3, getRowIndexByLastName("Conway"));
//    }
//
//    private int getRowIndexByLastName(String name) {
//        return 0;
//    }
}
