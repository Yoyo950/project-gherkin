package application.pages;

import application.utils.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(xpath = "//div[@class = 'inventory_item_name']")
    private WebElement nameProduct;

    @FindBy(id = "checkout")
    private WebElement checkout;

    public CartPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public String getNameProduct() {
        return nameProduct.getText();
    }

    public void clickOnCheckout() {
        checkout.click();
    }

}
