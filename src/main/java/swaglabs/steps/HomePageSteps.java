package swaglabs.steps;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import swaglabs.components.BasePage;
import swaglabs.components.DriverFactory;
import swaglabs.common.CommonMethods;
import swaglabs.pageobjects.SwagLabsHomePage;

public class HomePageSteps{
	
	CommonMethods commonMethods = new CommonMethods();
	private static Logger logger;
	private static WebDriver driver = DriverFactory.getDriver();
	
	public HomePageSteps(){
		BasePage.setLogger(HomePageSteps.class);
		logger = BasePage.getLogger();
	}
	
	/**
	 * Logged in to Swaglabs
	 */
	public void loginToSwagLabs() {
		String applicationUrl = commonMethods.getPropertyValue("url");
		commonMethods.launchUrl(applicationUrl);
		commonMethods.logInSwagLabs(commonMethods.getPropertyValue("swglabsUserName"), commonMethods.getPropertyValue("swaglabsPassword"));
		logger.info("Completed loginToSwagLabs");
	}
	
	public void addSauceLabsBackpackIntoCartAndVerify() {
		SwagLabsHomePage SwagLabsHomePage = new SwagLabsHomePage(driver);
		commonMethods.addToCartAndVerifyItemAdded(SwagLabsHomePage.addToCartOfsauceLabsBackpack, SwagLabsHomePage.removeOfsauceLabsBackpack);
		logger.info("Completed addSauceLabsBackpackIntoCartAndVerify");
	}
	
	
}
