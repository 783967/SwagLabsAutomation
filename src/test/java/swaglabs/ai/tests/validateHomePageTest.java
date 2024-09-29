package swaglabs.ai.tests;

import org.testng.annotations.Test;

import swaglabs.TestComponents.DriverFactory;
import swaglabs.stepDefinitions.HomePageSteps;

public class validateHomePageTest extends DriverFactory {

	@Test
	public void validateUserLandedInSwagLabsHomePage() {
		HomePageSteps homePageSteps = new HomePageSteps();
		homePageSteps.loginToSwagLabs();
	}
	
}
