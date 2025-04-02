package application.steps;

import application.utils.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginStepDefinitionPage {

    private final WebDriver driver;

    public LoginStepDefinitionPage(WebDriverManager driverManager) {
        driver = driverManager.getWebDriver();
    }

    @When("I enter the username {string} and the password {string} and click on the validation button")
    public void iEnterTheUsernameAndThePasswordAndClickOnTheValidationButton(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @Given("I am on the login page of the site")
    public void iAmOnTheLoginPageOfTheSite() {
        Assert.assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
    }

    @Then("I should have an error displaying and stay on the login page.")
    public void iShouldHaveAnErrorDisplayingAndStayOnTheLoginPage() {
        Assert.assertTrue(driver.findElement(By.xpath("//h3[@data-test = 'error']")).getText().contains("Epic sadface: Username and password do not match any user in this service"));
    }
}
