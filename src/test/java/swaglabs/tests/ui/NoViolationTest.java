package swaglabs.tests.ui;

import org.testng.annotations.Test;

import swaglabs.common.CommonMethods;
import swaglabs.components.DriverFactory;
import swaglabs.pageobjects.LoginPage;
import swaglabs.pageobjects.SwagLabsHomePage;

public class NoViolationTest extends DriverFactory {

	CommonMethods commonMethods = new CommonMethods();

	@Test(priority = 1, description = "This test validate the sorting in swag lab home page")
	public void validateSortingInSwagLabsPage() {
		commonMethods.launchUrl(CommonMethods.getPropertyValue("url"));

		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.waitForPageToBeFullyLoaded();
		loginPage.setUserName(CommonMethods.getPropertyValue("swglabsUserName"));
		loginPage.setPassword(CommonMethods.getPropertyValue("swaglabsPassword"));
		loginPage.clickLoginButton();

		SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage(DriverFactory.getDriver());
		swagLabsHomePage.waitForPageToBeFullyLoaded();
		swagLabsHomePage.selectOptionInSortingDropdown("Price (low to high)");
		commonMethods.verifyProductsAreSorted("ASC", swagLabsHomePage.getProductPrices());
		swagLabsHomePage.selectOptionInSortingDropdown("Price (high to low)");
		commonMethods.verifyProductsAreSorted("DESC", swagLabsHomePage.getProductPrices());
	}
}
