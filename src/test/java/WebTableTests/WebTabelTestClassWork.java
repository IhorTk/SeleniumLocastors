package WebTableTests;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebTabelTestClassWork extends BaseTest {
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
        WebElement table = driver.findElement(By.xpath("//*[@id=\"table1\"]"));

        //ищем индекс строки
        List<WebElement> tabRows = table.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr"));
        for (WebElement row:tabRows) {
            if(row.getText().contains(name)){
                break;
            }
            rowIndex++;
        }

        //ищем индекс Столбца
        List<WebElement> tabHeader = table.findElements(By.xpath("//*[@id=\"table1\"]/thead/tr/th"));
        for (WebElement head:tabHeader) {
            if(head.getText().equals("Due")){
                break;
            }
            colIndex++;
        }

        // получаем результат
        WebElement due = tabRows.get(rowIndex).findElements(By.xpath(".//td")).get(colIndex);
        String result = due.getText();

        return result;
    }




        @Test
    public void getRowIndexByLastName() { // - 3
        driver.get("https://the-internet.herokuapp.com/tables");
        // Написать метод, чтобы получить индекс строки по значению Last Name

        assertEquals(1, getRowIndexByLastName("Bach"));
        assertEquals(3, getRowIndexByLastName("Conway"));
    }

    private int getRowIndexByLastName(String name) {
        int rowIndex = 0; //индекс строки
        List<WebElement> tabRows = driver.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr"));
        for (WebElement row:tabRows) {
            if(row.getText().contains(name)){
                break;
            }
            rowIndex++;
        }
        return rowIndex;
    }

    @Test
    public void getRowBySomeValue() { // - 4
        driver.get("https://demo.aspnetawesome.com/GridFilterRowServerSideData#Grid-filter-row-server-data");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        driver.findElement(By.id("GridFrow2PageSize-awed")).click();
        driver.findElement(By.xpath("//li[.='100']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("*//tr"),99));
        // Найти первую строку со значением Ghidrimesti в столбце country
        WebElement neededRow = getRowValue("Ghidrimesti");
        // Показать что мы нашли верную строку
        // NOTE: возможно у вас будут другие данные
        assertTrue(neededRow.getText().contains("Onion, Broccoli, Carrot"));
        // И записать все её клетки в переменную ниже
        List<WebElement> neededRowCells = getValueCell(neededRow);

        // NOTE: возможно у вас будут другие данные
        assertEquals("1977", neededRowCells.get(0).getText());
        assertEquals("Trey", neededRowCells.get(1).getText());
        assertEquals("Hot Beverage", neededRowCells.get(2).getText());
        assertEquals("45", neededRowCells.get(3).getText());
    }

    private WebElement getRowValue(String name){
        WebElement rowValue = null;
        List<WebElement> tableRows = driver.findElements(By.xpath("//*[@id=\"GridFrow2\"]//tr"));
        for(WebElement row:tableRows){
            if(row.getText().contains(name)){
                rowValue = row;
                break;
            }
        }
        return rowValue;
    }

    private List<WebElement> getValueCell(WebElement element){
       List<WebElement> valueCell = element.findElements(By.xpath(".//td"));
       return  valueCell;
    }
}
