package TestSeleniumLocator;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeAll
    static void setupAll() {

//        WebDriverManager.chromedriver().browserVersion("125").setup();
//        driver = new ChromeDriver();
    }

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().browserVersion("125").setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(chromeOptions);

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
