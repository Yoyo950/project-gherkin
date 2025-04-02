package application.pages;

import application.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected final WebDriver driver;

    public BasePage(WebDriverManager webDriverManager) {
        driver = webDriverManager.getWebDriver();
        PageFactory.initElements(driver, this);
    }
}
