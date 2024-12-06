package swaglabs.steps;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import swaglabs.common.CommonMethods;
import swaglabs.components.BasePage;
import swaglabs.components.DriverFactory;
import swaglabs.pageobjects.CheckoutPage;
import swaglabs.pageobjects.SwagLabsHomePage;

public class CheckoutPageSteps {

	CommonMethods commonMethods = new CommonMethods();
	private static Logger logger;
	private static WebDriver driver = DriverFactory.getDriver();
	private static SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage(driver);
	private static CheckoutPage checkoutPage = new CheckoutPage(driver);

	public CheckoutPageSteps() {
		BasePage.setLogger(CheckoutPageSteps.class);
		logger = BasePage.getLogger();
	}

	/**
	 * Method is used to add item into add to cart and navigate to cart page
	 */
	public void addItemToCart() {
		swagLabsHomePage.addToCartOfsauceLabsBackpack.click();
		commonMethods.click(swagLabsHomePage.goToCartIcon);
		commonMethods.waitForElementContainText(checkoutPage.checkoutButton, "Checkout", 10);
		logger.info("Cart page is displayed");
	}
	
	/**
	 * Method is used to perform checkout of the item in cart
	 * @param customerDetails
	 */
	public void buyItem(Map<String, String> customerDetails) {
		addItemToCart();
		commonMethods.click(checkoutPage.checkoutButton);
		checkoutPage.firstName.sendKeys(customerDetails.get("FirstName"));
		checkoutPage.lastName.sendKeys(customerDetails.get("LastName"));
		checkoutPage.zipCode.sendKeys(customerDetails.get("ZipCode"));
		commonMethods.click(checkoutPage.checkoutButton);
		commonMethods.waitForElementContainText(checkoutPage.finishButton, "Finish", 10);
		commonMethods.click(checkoutPage.finishButton);
		Assert.assertEquals(checkoutPage.successfulCheckoutMsg.getText(), "Thank you for your order!",
				"Product is checked out successfully");
		logger.info("Product is checked out successfully");
	}

}
