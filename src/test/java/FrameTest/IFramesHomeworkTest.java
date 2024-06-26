package com.example;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IFramesHomeworkTest extends BaseTest {
    @Test
    public void nestedFramesTest() {// - 1
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame("frame-top").switchTo().frame("frame-middle");
        WebElement middleContent = driver.findElement(By.xpath("//*[@id='content']"));
        assertEquals("MIDDLE", middleContent.getText());
    }

    @Test
    public void elementsInFrames() throws InterruptedException {// - 2
        driver.get("https://selectorshub.com/iframe-scenario/");

        driver.switchTo().frame("pact1").switchTo().frame("pact2");
        WebElement currentCrushNameInput = driver.findElement(By.xpath("//*[@id='jex']"));
        assertEquals("Current Crush Name", currentCrushNameInput.getAttribute("placeholder"));

        driver.switchTo().frame("pact3");
        WebElement closeItButton = driver.findElement(By.xpath("//button[@id='close']"));
        closeItButton.click();
        Thread.sleep(500);
        assertEquals(Color.fromString("#c36"), Color.fromString(closeItButton.getCssValue("background-color")));
    }

    @Test
    public void shadowDomElement() {// - 3
        driver.get("https://selectorshub.com/shadow-dom-in-iframe/");
        WebElement firstCollegeInput = null;
        assertEquals("First College", firstCollegeInput.getAttribute("placeholder"));
    }
}
