package swaglabs.tests;

import org.testng.annotations.Test;

import swaglabs.components.DriverFactory;
import swaglabs.steps.HomePageSteps;

public class swagLabsTests extends DriverFactory {

	@Test(description = "This test validate the sorting in swag lab home page")
	public void validateSorting() {
		HomePageSteps homePageSteps = new HomePageSteps();
		homePageSteps.loginToSwagLabs();
		homePageSteps.selectOptionInSortingDropdown("Price (low to high)");
		homePageSteps.verifyProductsAreSorted("ASC");
		homePageSteps.selectOptionInSortingDropdown("Price (high to low)");
		homePageSteps.verifyProductsAreSorted("DESC");
	}
	
}
