package UpLoadTest;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class UpLoadTests extends BaseTest {
    @Test
    public void upLoadTestDemoqa () throws InterruptedException {
        driver.get("https://demoqa.com/upload-download");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        String pathFile = "D:\\TestFileUpDownLoad\\160521.jpg";
        WebElement upLoadButton = driver.findElement(By.cssSelector("input#uploadFile"));
        Actions actions = new Actions(driver);
        actions.scrollToElement(upLoadButton);
        upLoadButton.sendKeys(pathFile);
        if(driver.findElement(By.id("uploadedFilePath")).isDisplayed()){
            Assertions.assertTrue(true,"Gut gemacht!!!");
        }else{
            Assertions.assertFalse(false,"Was ist los?");
        }
    }

    @Test
    public void upLoadTestHerokuApp() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/upload");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        String pathFile = "D:\\TestFileUpDownLoad\\143048.jpg";
        WebElement browseFile = driver.findElement(By.cssSelector("input#file-upload"));
        browseFile.sendKeys(pathFile);
        driver.findElement(By.cssSelector("input#file-submit")).click();

        if(driver.findElement(By.cssSelector("div#uploaded-files")).isDisplayed()){
            Assertions.assertTrue(true,"Gut gemacht!!!");
        }else{
            Assertions.assertFalse(false,"Was ist los?");
        }
    }

}
