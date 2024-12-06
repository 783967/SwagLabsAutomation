package swaglabs.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import swaglabs.common.CommonMethods;
import swaglabs.components.BasePage;

public class SwagLabsHomePage {

	CommonMethods commonMethods = new CommonMethods();
	private static Logger logger;

	public SwagLabsHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		BasePage.setLogger(SwagLabsHomePage.class);
		logger = BasePage.getLogger();
	}

	@FindBy(css = ".app_logo")
	public WebElement logoHeading;

	@FindBy(css = "#add-to-cart-sauce-labs-backpack")
	public WebElement addToCartOfsauceLabsBackpack;

	@FindBy(css = "#remove-sauce-labs-backpack")
	public WebElement removeOfsauceLabsBackpack;

	@FindBy(css = ".shopping_cart_badge")
	public WebElement nonEmptyCartBadge;

	@FindBy(css = ".shopping_cart_link")
	public WebElement emptyCartBadge;

	@FindBy(css = ".product_sort_container")
	public WebElement selectDropdown;

	@FindBy(css = ".inventory_item_price")
	public List<WebElement> productPrice;

	@FindBy(css = ".shopping_cart_link")
	public WebElement goToCartIcon;

	/**
	 * Click in Add to cart button
	 */
	public void clickAddToCart() {
		commonMethods.click(addToCartOfsauceLabsBackpack);
		logger.info("Add to cart button is clicked");
	}

	/**
	 * Click in go to cart icon
	 */
	public void clickGoToCart() {
		commonMethods.click(goToCartIcon);
		logger.info("Add to cart button is clicked");
	}

	/**
	 * Click remove from cart button
	 */
	public void clickRemoveFromCart() {
		commonMethods.click(removeOfsauceLabsBackpack);
		logger.info("Remove from Cart is clicked");
	}

	/**
	 * Get value from non empty cart badge
	 * 
	 * @return
	 */
	public String getValueFromNonEmptyCartBadge() {
		return nonEmptyCartBadge.getText();
	}

	/**
	 * Get value from empty cart badge
	 * 
	 * @return
	 */
	public String getValueFromEmptyCartBadge() {
		return emptyCartBadge.getText();
	}

	/**
	 * Select provided option in sorting dropdown
	 * 
	 * @param option
	 */
	public void selectOptionInSortingDropdown(String option) {
		Select select = new Select(selectDropdown);
		select.selectByVisibleText(option);
		logger.info(option + " is selected");
	}

	/**
	 * get product prices
	 * 
	 * @return
	 */
	public List<String> getProductPrices() {
		return productPrice.stream().map(WebElement::getText).toList();
	}

	/**
	 * Wait for Swaglabs home page to be fully loaded
	 */
	public void waitForPageToBeFullyLoaded() {
		commonMethods.waitForElementContainText(logoHeading, "Swag Labs", 10);
	}
}
