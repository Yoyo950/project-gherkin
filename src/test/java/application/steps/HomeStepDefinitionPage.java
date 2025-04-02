package application.steps;

import application.utils.WebDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomeStepDefinitionPage {

    private final WebDriver driver;

    public HomeStepDefinitionPage(WebDriverManager driverManager) {
        driver = driverManager.getWebDriver();
    }

    @Then("I am on the homepage of the site")
    public void iAmOnTheHomepageOfTheSite() {
        Assert.assertEquals("Products", driver.findElement(By.xpath("//span[@class = 'title']")).getText());
    }

    @When("I click on the Add to cart button on {string}")
    public void iClickOnTheAddToCartButtonOnSauceLabsBackpack(String productName) {
        driver.findElement(By.xpath("//div[@class ='inventory_item_name ' and text()= '" + productName + "']/../../../div[@class = 'pricebar']/button")).click();
    }

    @And("I go to the cart page")
    public void iGoToTheCartPage() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }

    @When("I select sort price from low to high")
    public void iSelectSortPriceFromLowToHigh() {
        Select select = new Select(driver.findElement(By.xpath("//select[@class = 'product_sort_container']")));
        select.selectByValue("lohi");
    }

    @Then("The product are sorted from their prices lowest to highest")
    public void theProductAreSortedFromTheirPricesLowestToHighest() {
        List<WebElement> prices = driver.findElements(By.xpath("//div[@class = 'inventory_item_price']"));
        double pricenum1, pricenum2;
        pricenum1 = 0;
        for(WebElement price : prices) {
            System.out.println(price.getText());
            pricenum2 = Double.parseDouble(price.getText().substring(1));
            if(pricenum1 <= pricenum2) {
                pricenum1 = pricenum2;
            } else {
                Assert.fail();
            }
        }
    }

    @When("I click on the burger menu")
    public void iClickOnTheBurgerMenu() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
    }


    @And("I click on logout")
    public void iClickOnLogout() {
        driver.findElement(By.id("logout_sidebar_link")).click();
    }
}
