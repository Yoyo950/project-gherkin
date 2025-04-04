package application.utils;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
        WebDriver tempDriver;
        switch (System.getenv("BRWSR")) {
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if(System.getenv("HL").equals("true")){
                    edgeOptions.addArguments("--headless");
                }
                tempDriver = new EdgeDriver(edgeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if(System.getenv("HL").equals("true")){
                    firefoxOptions.addArguments("--headless");
                }
                tempDriver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                if(System.getenv("HL").equals("true")){
                    chromeOptions.addArguments("--headless");
                }
                tempDriver = new ChromeDriver(chromeOptions);
                break;
        }
        return tempDriver;
    }
}
