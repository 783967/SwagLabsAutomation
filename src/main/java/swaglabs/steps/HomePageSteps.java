package swaglabs.steps;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import swaglabs.components.BasePage;
import swaglabs.components.DriverFactory;
import swaglabs.common.CommonMethods;
import swaglabs.pageobjects.SwagLabsHomePage;

public class HomePageSteps {

	CommonMethods commonMethods = new CommonMethods();
	private static Logger logger;
	private static WebDriver driver = DriverFactory.getDriver();
	private static SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage(driver);

	public HomePageSteps() {
		BasePage.setLogger(HomePageSteps.class);
		logger = BasePage.getLogger();
	}

	/**
	 * Logged in to Swaglabs
	 */
	public void loginToSwagLabs() {
		String applicationUrl = commonMethods.getPropertyValue("url");
		commonMethods.launchUrl(applicationUrl);
		commonMethods.logInSwagLabs(commonMethods.getPropertyValue("swglabsUserName"),
				commonMethods.getPropertyValue("swaglabsPassword"));
		logger.info("Completed loginToSwagLabs");
	}

	public void addSauceLabsBackpackIntoCartAndVerify() {
		commonMethods.addToCartAndVerifyItemAdded(swagLabsHomePage.addToCartOfsauceLabsBackpack,
				swagLabsHomePage.removeOfsauceLabsBackpack);
		logger.info("Completed addSauceLabsBackpackIntoCartAndVerify");
	}

	/**
	 * Select provided option in sorting dropdown
	 * 
	 * @param option
	 */
	public void selectOptionInSortingDropdown(String option) {
		Select select = new Select(swagLabsHomePage.selectDropdown);
		select.selectByVisibleText(option);
		logger.info(option + " is selected");
	}

	/**
	 * Verify products are sorted in corresponding order
	 * 
	 * @param sortingOrder
	 */
	public void verifyProductsAreSorted(String sortingOrder) {
		List<String> productPrices = swagLabsHomePage.productPrice.stream().map(WebElement::getText).toList();

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
