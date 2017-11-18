package com.qalabs.pageobjects;

import com.qalabs.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;
    protected String email = "juan.tester.123@gmail.com";
    protected String password = "tester1234";

    @BeforeMethod
    @Parameters("browser")
    public void beforeTest(String browser) {
        System.out.println("Getting web driver instance");
        driver = WebDriverFactory.getDriver(browser);
    }

    @AfterMethod
    public void afterTest() {
        System.out.println("Killing web river instance");
        driver.quit();
    }
}
