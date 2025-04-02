package application.steps;

import application.pages.CheckoutFinalPage;
import application.pages.CheckoutOnePage;
import application.utils.WebDriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOneStepDefinitionPage {

    private final CheckoutOnePage checkoutOnePage;

    public CheckoutOneStepDefinitionPage(WebDriverManager webDriverManager) {
        checkoutOnePage = new CheckoutOnePage(webDriverManager);
    }

    @Then("I am on checkout page one")
    public void iAmOnCheckoutPageOne() {
        Assert.assertEquals("https://www.saucedemo.com/checkout-step-one.html", checkoutOnePage.getUrl());
    }

    @When("I enter my {string}, {string}, {string}, and click on continue")
    public void iEnterMyAndClickOnContinue(String firstName, String lastName, String postalCode) {
        checkoutOnePage.enterCredentialsAndContinue(firstName, lastName, postalCode);
    }
}
