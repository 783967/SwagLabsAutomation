package swaglabs.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import swaglabs.common.CommonMethods;
import swaglabs.components.BasePage;

public class CheckoutPage {

	CommonMethods commonMethods = new CommonMethods();
	private static Logger logger;

	public CheckoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		BasePage.setLogger(CheckoutPage.class);
		logger = BasePage.getLogger();
	}

	@FindBy(css = "#checkout")
	public static WebElement checkoutButton;

	@FindBy(css = "#first-name")
	public static WebElement firstName;

	@FindBy(css = "#last-name")
	public static WebElement lastName;

	@FindBy(css = "#postal-code")
	public static WebElement zipCode;

	@FindBy(css = "#continue")
	public static WebElement continueButton;

	@FindBy(css = "#finish")
	public static WebElement finishButton;

	@FindBy(css = ".complete-header")
	public static WebElement successfulCheckoutMsg;

	/**
	 * Click Checkout Button
	 */
	public void clickCheckoutButton() {
		commonMethods.click(checkoutButton);
		logger.info("Checkout Button is clicked");
	}

	/**
	 * Set value in First Name
	 * 
	 * @param value
	 */
	public void setValueInFirstName(String value) {
		firstName.sendKeys(value);
		logger.info(value + " is set in First Name");
	}

	/**
	 * Set value in Last Name
	 * 
	 * @param value
	 */
	public void setValueInLastName(String value) {
		lastName.sendKeys(value);
		logger.info(value + " is set in Last Name");
	}

	/**
	 * Set value in zip code
	 * 
	 * @param value
	 */
	public void setValueInZipCode(String value) {
		zipCode.sendKeys(value);
		logger.info(value + " is set in Zip Code");
	}

	/**
	 * Click finish button
	 */
	public void clickFinishButton() {
		commonMethods.click(finishButton);
		logger.info("Finish Button is clicked");
	}
}
