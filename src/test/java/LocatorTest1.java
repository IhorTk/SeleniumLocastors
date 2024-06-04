import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorTest1 {
    WebDriver driver;
    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }
    @AfterEach
    void teardown() {
        driver.quit();
    }
    @Test
    public void cssTests() throws InterruptedException {
        //Открыть браузер по URL ссылке
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(2000);

        WebElement userName = driver.findElement(By.cssSelector("#user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));



        userName.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();
        Thread.sleep(2000);
        WebElement title = driver.findElement(By.cssSelector(".title"));
        String titleText = title.getText();

        Assertions.assertEquals("Products", titleText);

        Thread.sleep(2000);

    }
}
