package swaglabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(css = "#user-name")
	public WebElement userName;

	@FindBy(css = "#password")
	public WebElement passWord;
	
	@FindBy(css = "#login-button")
	public WebElement logInButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	
}
