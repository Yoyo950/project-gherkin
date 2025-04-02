package application.steps;

import application.pages.CartPage;
import application.utils.WebDriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

public class CartStepDefinitionPage {

    private final CartPage cartPage;

    public CartStepDefinitionPage(WebDriverManager webDriverManager) {
        cartPage = new CartPage(webDriverManager);
    }

    @Then("The product {string} is added to the cart")
    public void theProductSauceLabsBackpackIsAddedToTheCart(String productName) {
        Assert.assertEquals(productName, cartPage.getNameProduct());
    }

    @When("I click on checkout")
    public void iClickOnCheckout() {
        cartPage.clickOnCheckout();
    }
}
