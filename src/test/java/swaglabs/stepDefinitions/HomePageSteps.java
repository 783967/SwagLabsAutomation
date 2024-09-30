package swaglabs.stepDefinitions;

import org.openqa.selenium.WebDriver;

import swaglabs.TestComponents.DriverFactory;
import swaglabs.common.CommonMethods;
import swaglabs.pageobjects.SwagLabsHomePage;

public class HomePageSteps{
	
	CommonMethods commonMethods = new CommonMethods();
	
	private static WebDriver driver = DriverFactory.getDriver();
	
	public void loginToSwagLabs() {
		String applicationUrl = commonMethods.getPropertyValue("url");
		commonMethods.launchUrl(applicationUrl);
		commonMethods.logInSwagLabs(commonMethods.getPropertyValue("swglabsUserName"), commonMethods.getPropertyValue("swaglabsPassword"));
	}
	
	public void addSauceLabsBackpackIntoCartAndVerify() {
		SwagLabsHomePage SwagLabsHomePage = new SwagLabsHomePage(driver);
		commonMethods.addToCartAndVerifyItemAdded(SwagLabsHomePage.addToCartOfsauceLabsBackpack, SwagLabsHomePage.removeOfsauceLabsBackpack);
	}
	
	
}
