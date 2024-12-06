package swaglabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css = "#user-name")
	public WebElement userName;

	@FindBy(css = "#password")
	public WebElement passWord;
	
	@FindBy(css = "#login-button")
	public WebElement logInButton;
	
	@FindBy(css = ".login_logo")
	public WebElement swagLabText;
	
}
