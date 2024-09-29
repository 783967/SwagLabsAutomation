package swaglabs.stepDefinitions;

import swaglabs.common.CommonMethods;

public class HomePageSteps{
	
	CommonMethods commonMethods = new CommonMethods();
	
	public void loginToSwagLabs() {
		commonMethods.launchUrl("https://www.saucedemo.com/");
		commonMethods.logInSwagLabs(commonMethods.getPropertyValue("swglabsUserName"), commonMethods.getPropertyValue("swaglabsPassword"));
	}
	
	
}
