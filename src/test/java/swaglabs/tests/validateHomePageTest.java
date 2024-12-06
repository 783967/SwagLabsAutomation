package swaglabs.tests;

import org.testng.annotations.Test;

import swaglabs.common.CommonMethods;
import static swaglabs.common.CommonMethods.loginPageButton;
import swaglabs.components.DriverFactory;
import swaglabs.steps.HomePageSteps;
import static swaglabs.common.CommonMethods.loginPageCancelButton;




public class validateHomePageTest extends DriverFactory {
	
	//Delete below

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
	
	@Test
	public void VALIDATELoginPage(){
		HomePageSteps homePageSteps = new HomePageSteps();
		homePageSteps.loginToSwagLabs();
		CommonMethods.loginPageCancelButton(1);
	}
	
}
