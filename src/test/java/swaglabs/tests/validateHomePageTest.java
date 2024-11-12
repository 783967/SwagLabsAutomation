package swaglabs.tests;

import org.testng.annotations.Test;

import swaglabs.components.DriverFactory;
import swaglabs.steps.HomePageSteps;

public class validateHomePageTest extends DriverFactory {

	@Test
	public void validateUserLandedInSwagLabsHomePage() {
		HomePageSteps homePageSteps = new HomePageSteps();
		homePageSteps.loginToSwagLabs();
	}
	
	@Test
	public void validateOfAddSauceLabsBackPackIntoCartFromHomePageTest() {
		HomePageSteps homePageSteps = new HomePageSteps();
		homePageSteps.loginToSwagLabs();
		homePageSteps.addSauceLabsBackpackIntoCartAndVerify();
	}
	
}
