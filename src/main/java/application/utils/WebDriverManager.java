package application.utils;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    private final WebDriver driver;

    public WebDriverManager() {
        driver = this.getDriverConfig();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void quit() {
        driver.quit();
    }

    private WebDriver getDriverConfig() {
        return new ChromeDriver();
    }
}
