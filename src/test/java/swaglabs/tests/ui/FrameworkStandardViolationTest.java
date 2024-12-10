package swaglabs.tests.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import swaglabs.common.CommonMethods;
import swaglabs.components.DriverFactory;
import swaglabs.pageobjects.CheckoutPage;
import swaglabs.pageobjects.LoginPage;
import swaglabs.pageobjects.SwagLabsHomePage;

public class FrameworkStandardViolationTest extends DriverFactory {

	CommonMethods commonMethods = new CommonMethods();

	@Test(description = "validate")
	public void checkoutProduct() throws InterruptedException {
		commonMethods.launchUrl(CommonMethods.getPropertyValue("url"));

		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		commonMethods.waitForElementContainText(loginPage.swagLabText, "Swag Labs", 20);
		loginPage.setUserName(CommonMethods.getPropertyValue("swglabsUserName"));
		loginPage.setPassword(CommonMethods.getPropertyValue("swaglabsPassword"));
		loginPage.clickLoginButton();

		SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage(DriverFactory.getDriver());
		commonMethods.waitForElementContainText(swagLabsHomePage.logoHeading, "Swag Labs", 10);

		// click add to cart of Backpack item
		swagLabsHomePage.addToCartOfsauceLabsBackpack.click();
		// Click add to cart
		swagLabsHomePage.goToCartIcon.click();
		CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());
		// click checkout button
		checkoutPage.checkoutButton.click();
		// Enter first name
		checkoutPage.firstName.sendKeys("FirstName");
		// Enter Last name
		checkoutPage.lastName.sendKeys("LastName");
		// Enter zip
		checkoutPage.zipCode.sendKeys("243243");
		// click checkout button
		checkoutPage.checkoutButton.click();
		// click finish button
		checkoutPage.finishButton.click();
		// wait
		Thread.sleep(100);
		// verify checkout is successful
		String successfulMsg = checkoutPage.successfulCheckoutMsg.getText();
		String idAttribute = checkoutPage.successfulCheckoutMsg.getAttribute("id");
		Assert.assertEquals(checkoutPage.successfulCheckoutMsg.getText(), "Thank you for your order!",
				"Product is checked out successfully");
		System.out.println(checkoutPage.successfulCheckoutMsg.getText());
	}

}
