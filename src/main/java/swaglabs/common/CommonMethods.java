package swaglabs.common;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import swaglabs.components.BasePage;
import swaglabs.components.DriverFactory;
import swaglabs.pageobjects.LoginPage;
import swaglabs.pageobjects.SwagLabsHomePage;

public class CommonMethods extends BasePage {

	private static WebDriver driver;
	private static Logger logger;
		
	@SuppressWarnings("static-access")
	public CommonMethods() {
		try {
			DriverFactory driverFactory = new DriverFactory();
			driver = driverFactory.getDriver();
			BasePage.setLogger(CommonMethods.class);
			logger = BasePage.getLogger();
		} catch (Exception e) {
			Assert.assertTrue(false, "Driver not initialised.");
		}
	}

	/**
	 * Launch the provided URL
	 */
	public void launchUrl(String url) {
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		Assert.assertEquals(driver.getCurrentUrl(), url);
		logger.info(url + " is launched succesfully");
	}

	/**
	 * Click on any provided element
	 * @param <T>
	 * @param element
	 */
	public <T extends WebElement> void click(T element) {
		try {
			element.click();
		} catch (Exception e) {
			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("arguments[0].click();", element);
		}
		logger.info(element + " is clicked successfully");
	}

	/**
	 * Wait for provided element to contain a specific text
	 * @param <T>
	 * @param element
	 * @param text
	 * @param timeoutSeconds
	 */
	public static <T extends WebElement> void waitForElementContainText(T element, String text, Integer timeoutSeconds) {
		boolean check = false;
		for (int i = 0; i < timeoutSeconds; i++) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				check = wait.until(ExpectedConditions.textToBePresentInElement(element, text));
			} catch (Exception e) {
				continue;
			}
			if (check)
				break;
		}
		Assert.assertEquals(element.getText(), text);
		logger.info(element.getText() + " is present.");
	}

	public void logInSwagLabs(String userName, String passWord) {
		LoginPage loginPage = new LoginPage(driver);
		waitForElementContainText(loginPage.swagLabText, "Swag Labs", 20);
		loginPage.userName.sendKeys(userName);
		loginPage.passWord.sendKeys(passWord);
		click(loginPage.logInButton);

		SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage(driver);
		waitForElementContainText(swagLabsHomePage.logoHeading, "Swag Labs", 10);
		logger.info("Logged in to SwagLabs");
	}

	public <T extends WebElement> void addToCartAndVerifyItemAdded(T addToCartElement, T removeFromCartElement) {
		SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage(driver);
		int cartCount = 0;
		if (!swagLabsHomePage.emptyCartBadge.getText().isEmpty())
			cartCount = Integer.parseInt(swagLabsHomePage.emptyCartBadge.getText());

		click(addToCartElement);
		waitForElementContainText(removeFromCartElement, "Remove", 10);
		logger.info("Element is added into Cart and Cart Value is " + cartCount);
		waitForElementContainText(swagLabsHomePage.nonEmptyCartBadge, String.valueOf(cartCount + 1), 10);
	}
	
	public static void loginPageButton(String buttonType) {
		long l=3000000000l;
		SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage(driver);
		if(buttonType.equalsIgnoreCase("Login")) waitForElementContainText(swagLabsHomePage.logoHeading, "Swag Labs", 10);
		try {
			loginPageCancelButton(1);
		  } catch (Exception e) {}
		
		
	}
	//Test
	//Test1
	//Test2
	public static void loginPageCancelButton(int input) {
		switch(input) {
		case 1:
		case 2:
		case 3:
			System.out.println("No default");
		}
	}
	
	public void TestTheSwagLabsByLoginIntoItAndThenValidateUserIsSuccessfullyLandedIntoItAfterThatLogoutFromAppilication() {
		
	}
}
