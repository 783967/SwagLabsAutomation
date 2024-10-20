package swaglabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsHomePage{
	
	public SwagLabsHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css=".app_logo")
	public WebElement logoHeading;
	
	@FindBy(css = "#add-to-cart-sauce-labs-backpack")
	public WebElement addToCartOfsauceLabsBackpack;
	
	@FindBy(css = "#remove-sauce-labs-backpack")
	public WebElement removeOfsauceLabsBackpack;
	
	@FindBy(css = ".shopping_cart_badge")
	public WebElement nonEmptyCartBadge;
	
	@FindBy(css = ".shopping_cart_link")
	public WebElement emptyCartBadge;
}
	