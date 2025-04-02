package application.pages;

import application.utils.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutFinalPage extends BasePage{

    @FindBy(xpath = "//h2[@class = 'complete-header']")
    public WebElement checkoutHeader;

    @FindBy(id = "back-to-products")
    public WebElement backToProducts;

    public CheckoutFinalPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public String getCheckoutHeaderText() {
        return checkoutHeader.getText();
    }

    public void clickBackToProducts() {
        backToProducts.click();
    }
}
