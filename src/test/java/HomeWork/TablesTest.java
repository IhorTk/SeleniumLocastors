package com.example;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TablesTest extends BaseTest {
    @Test
    public void getHeadersTest() { // - 1
        driver.get("https://the-internet.herokuapp.com/tables");
        // Вывести в консоль все хедеры и их индексы'
        List<WebElement> tableHeaders = driver.findElements(By.xpath("//*[@id='table1']//th"));
        int count=0;
        for(WebElement head:tableHeaders){
            System.out.println( count+" " + head.getText());
            count++;
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
        return driver.findElement(By.xpath(String.format("//*[@id='table1']//td[contains (text(),'%s')]/../td[4]", name))).getText();
    }

    @Test
    public void getRowIndexByLastName() { // - 3
        driver.get("https://the-internet.herokuapp.com/tables");
        // Написать метод, чтобы получить индекс строки по значению Last Name
        assertEquals(1, getRowIndexByLastName("Bach"));
        assertEquals(3, getRowIndexByLastName("Conway"));
        assertEquals(-1, getRowIndexByLastName("Buffalo"));
    }

    private int getRowIndexByLastName(String name) {
        List<WebElement> list = driver.findElements(By.xpath("//*[@id='table1']/tbody/tr"));
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).findElement(By.tagName("td")).getText().equals(name))
                return i;
        }
        return -1;
    }

    @Test
    public void getRowBySomeValue(){ // - 4
        driver.get("https://demo.aspnetawesome.com/GridFilterRowServerSideData#Grid-filter-row-server-data");
        driver.findElement(By.id("GridFrow2PageSize-awed")).click();
        driver.findElement(By.xpath("//li[.='100']")).click();
        // Найти первую строку со значением Ghidrimesti в столбце country
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("*//tr"), 99));
        List<WebElement> neededRowList = driver.findElements(By.xpath("*//td[text()='Ghidrimesti']/.."));
        WebElement neededRow = neededRowList.get(0);
        System.out.println("neededRow = " + neededRow.getText());
        assertTrue(neededRow.getText().contains("Onion"));
        // И записать все её клетки в переменную ниже
        List<WebElement> neededRowCells = neededRow.findElements(By.xpath(".//td"));

        assertEquals("2011", neededRowCells.get(0).getText());
        assertEquals("Alan", neededRowCells.get(1).getText());
        assertEquals("Banana", neededRowCells.get(2).getText());
        assertEquals("50", neededRowCells.get(3).getText());
    }
}
