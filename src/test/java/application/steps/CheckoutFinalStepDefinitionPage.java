package application.steps;

import application.pages.CartPage;
import application.pages.CheckoutFinalPage;
import application.utils.WebDriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutFinalStepDefinitionPage {

    private final CheckoutFinalPage checkoutFinalPage;

    public CheckoutFinalStepDefinitionPage(WebDriverManager webDriverManager) {
        checkoutFinalPage = new CheckoutFinalPage(webDriverManager);
    }

    @Then("I am on checkout complete page")
    public void iAmOnCheckoutCompletePage() {
        Assert.assertEquals("Thank you for your order!", checkoutFinalPage.getCheckoutHeaderText());
    }

    @When("I click on back home")
    public void iClickOnBackHome() {
        checkoutFinalPage.clickBackToProducts();
    }
}
