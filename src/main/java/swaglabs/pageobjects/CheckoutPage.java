package swaglabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	public CheckoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
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

}
