package application.hooks;

import application.api.ApiRequests;
import application.utils.ConfigReader;
import application.utils.Unzipper;
import application.utils.WebDriverManager;
import io.cucumber.java.*;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

public class Hook {

    private final WebDriverManager webDriverManager;

    public Hook(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    @AfterStep
    public void afterStep(Scenario scenario) throws InterruptedException {
        Thread.sleep(500);
    }

    @After
    public void tearDown() throws IOException {
        webDriverManager.quit();
    }
}
