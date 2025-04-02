package application.steps;

import application.utils.WebDriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutTwoStepDefinitionPage {

    private final WebDriver driver;

    public CheckoutTwoStepDefinitionPage(WebDriverManager driverManager) {
        driver = driverManager.getWebDriver();
    }

    @Then("I am on checkout page two")
    public void iAmOnCheckoutPageTwo() {
        Assert.assertEquals("https://www.saucedemo.com/checkout-step-two.html", driver.getCurrentUrl());
    }

    @When("I click on finish")
    public void iClickOnFinish() {
        driver.findElement(By.id("finish")).click();
    }
}
