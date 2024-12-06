package swaglabs.common;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import swaglabs.components.BasePage;
import swaglabs.components.DriverFactory;
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
	 * 
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
	 * 
	 * @param <T>
	 * @param element
	 * @param text
	 * @param timeoutSeconds
	 */
	public <T extends WebElement> void waitForElementContainText(T element, String text,
			Integer timeoutSeconds) {
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

	/**
	 * Add and remove from cart and verify cart number
	 * @param <T>
	 * @param addToCartElement
	 * @param removeFromCartElement
	 */
	public <T extends WebElement> void addToCartAndVerifyItemAdded(T addToCartElement, T removeFromCartElement) {
		SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage(driver);
		int cartCount = 0;
		if (!swagLabsHomePage.getValueFromEmptyCartBadge().isEmpty())
			cartCount = Integer.parseInt(swagLabsHomePage.emptyCartBadge.getText());

		click(addToCartElement);
		waitForElementContainText(removeFromCartElement, "Remove", 10);
		logger.info("Element is added into Cart and Cart Value is " + cartCount);
		waitForElementContainText(swagLabsHomePage.nonEmptyCartBadge, String.valueOf(cartCount + 1), 10);
	}

	/**
	 * Verify Products are sorted in provided order
	 * @param sortingOrder
	 * @param productPrices
	 */
	public void verifyProductsAreSorted(String sortingOrder, List<String> productPrices) {
		if (sortingOrder.equalsIgnoreCase("asc")) {
			boolean isAscending = IntStream.range(0, productPrices.size() - 1)
					.allMatch(i -> Double.parseDouble(productPrices.get(i).replace("$", "").trim()) <= Double
							.parseDouble(productPrices.get(i + 1).replace("$", "").trim()));
			Assert.assertTrue(isAscending, "Products are sorting in ascending order");
			logger.info("Products are sorting in ascending order");
		} else {
			boolean isDescending = IntStream.range(0, productPrices.size() - 1)
					.allMatch(i -> Double.parseDouble(productPrices.get(i).replace("$", "").trim()) >= Double
							.parseDouble(productPrices.get(i + 1).replace("$", "").trim()));
			Assert.assertTrue(isDescending, "Products are sorting in Descending order");
			logger.info("Products are sorting in Descending order");
		}

	}
}
