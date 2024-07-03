package PropertyTest;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertisTest extends BaseTest {
    @Test
    public void propertisTest() throws IOException {
        String path = "D:\\GIT\\SeleniumLocastors\\src\\main\\resources\\Properti\\configuration.properties";
        FileInputStream inputStream = new FileInputStream(path);

        Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();
    }

}
