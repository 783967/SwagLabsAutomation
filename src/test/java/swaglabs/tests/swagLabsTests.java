package swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import swaglabs.components.DriverFactory;
import swaglabs.pageobjects.CheckoutPage;
import swaglabs.pageobjects.SwagLabsHomePage;
import swaglabs.steps.HomePageSteps;
import swaglabs.common.CommonMethods;

public class swagLabsTests extends DriverFactory {

	@Test(priority = 1, description = "This test validate the sorting in swag lab home page")
	public void validateSorting() {
		HomePageSteps homePageSteps = new HomePageSteps();
		homePageSteps.loginToSwagLabs();
		homePageSteps.selectOptionInSortingDropdown("Price (low to high)");
		homePageSteps.verifyProductsAreSorted("ASC");
		homePageSteps.selectOptionInSortingDropdown("Price (high to low)");
		homePageSteps.verifyProductsAreSorted("DESC");
	}

	@Test(description = "validate")
	public void checkoutProduct() throws InterruptedException {
		HomePageSteps homePageSteps = new HomePageSteps();
		homePageSteps.loginToSwagLabs();
		SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage(DriverFactory.getDriver());
		//click add to cart of Backpack item
		swagLabsHomePage.addToCartOfsauceLabsBackpack.click();
		//Click add to cart
		swagLabsHomePage.goToCartIcon.click();
		CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());
		//click checkout button
		checkoutPage.checkoutButton.click();
		//Enter first name
		checkoutPage.firstName.sendKeys("FirstName");
		//Enter Last name
		checkoutPage.lastName.sendKeys("LastName");
		//Enter zip
		checkoutPage.zipCode.sendKeys("243243");
		//click checkout button
		checkoutPage.checkoutButton.click();
		//click finish button
		checkoutPage.finishButton.click();
		//wait
		Thread.sleep(100);
		//verify checkout is successful
		String successfulMsg = checkoutPage.successfulCheckoutMsg.getText();
		String idAttribute = checkoutPage.successfulCheckoutMsg.getAttribute("id");
		Assert.assertEquals(checkoutPage.successfulCheckoutMsg.getText(), "Thank you for your order!",
				"Product is checked out successfully");
		System.out.println(checkoutPage.successfulCheckoutMsg.getText());
	}

}
