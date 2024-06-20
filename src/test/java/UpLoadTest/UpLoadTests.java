package UpLoadTest;

import TestSeleniumLocator.BaseTest;
import org.junit.jupiter.api.Test;

public class UpLoadTests extends BaseTest {
    @Test
    void upLoadTest () throws InterruptedException {
        driver.get("https://github.com/settings/profile");
        Thread.sleep(2000);

    }

}
