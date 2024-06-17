import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

public class CookieTest extends BaseTest {

    @Test
    void cookieTest(){
        driver.get("http://example.com");
        driver.manage().addCookie(new Cookie("name", "Cookie value"));
        Cookie cookie =driver.manage().getCookieNamed("name");
        System.out.println(cookie);
//        driver.manage().window().maximize();

    }

}
