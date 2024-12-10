package swaglabs.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swaglabs.common.CommonMethods;
import swaglabs.components.BasePage;

public class LoginPage {

	CommonMethods commonMethods = new CommonMethods();
	private static Logger logger;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		BasePage.setLogger(CheckoutPage.class);
		logger = BasePage.getLogger();
	}

	@FindBy(css = "#user-name")
	public WebElement userName;

	@FindBy(css = "#password")
	public WebElement passWord;

	@FindBy(css = "#login-button")
	public WebElement logInButton;

	@FindBy(css = ".login_logo")
	public WebElement swagLabText;

	/**
	 * Set user name
	 * 
	 * @param value
	 */
	public void setUserName(String value) {
		userName.sendKeys(value);
		logger.info(value + " is set in User Name");
	}

	/**
	 * Set password
	 * 
	 * @param value
	 */
	public void setPassword(String value) {
		passWord.sendKeys(value);
		logger.info(value + " is set in PassWord");
	}

	/**
	 * Click in Login Button
	 */
	public void clickLoginButton() {
		commonMethods.click(logInButton);
		logger.info("Login button is clicked");
	}

	/**
	 * Wait for Login Page to be fully loaded
	 */
	public void waitForPageToBeFullyLoaded() {
		commonMethods.waitForElementContainText(swagLabText, "Swag Labs", 20);
	}

}
