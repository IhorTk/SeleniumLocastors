package TestSeleniumLocator;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeAll
    static void setupAll() {

        WebDriverManager.chromedriver().browserVersion("126").setup();
        driver = new ChromeDriver();
    }

    @BeforeEach
    void setup() {

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
