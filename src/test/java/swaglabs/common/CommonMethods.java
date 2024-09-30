package swaglabs.common;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import swaglabs.TestComponents.BasePage;
import swaglabs.TestComponents.DriverFactory;
import swaglabs.pageobjects.LoginPage;
import swaglabs.pageobjects.SwagLabsHomePage;

public class CommonMethods extends BasePage {
	
	private static WebDriver driver;
     public CommonMethods() { 
    	 try { 
    		 driver = new DriverFactory().getDriver(); 
    	 } 
    	 catch(Exception e) {
    		 Assert.assertTrue(false, "Driver not initialised.");
    	 }
	  }
		
	public void launchUrl(String url) {
		driver.get(url);
		Assert.assertEquals(driver.getCurrentUrl(), url);
	}
	
	public <T extends WebElement> void click(T element) {
		try {
			element.click();
		}
		catch(Exception e) {
			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("arguments[0].click();", element);
		}
	}
	
	public <T extends WebElement> void waitForElementContainText(T element, String text, Integer timeoutSeconds) {
		boolean check = false;
		for(int i=0; i<timeoutSeconds ; i++) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				check = wait.until(ExpectedConditions.textToBePresentInElement(element, text));
			}
			catch(Exception e) {
				continue;
			}
			if(check)
				break;
		}
		Assert.assertEquals(element.getText(), text);
	}

	public void logInSwagLabs(String userName, String passWord) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.userName.sendKeys(userName);
		loginPage.passWord.sendKeys(passWord);
		click(loginPage.logInButton);
		
		SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage(driver);
		waitForElementContainText(swagLabsHomePage.logoHeading, "Swag Labs", 10);
	}
	
	public <T extends WebElement> void addToCartAndVerifyItemAdded(T addToCartElement, T removeFromCartElement) {
		SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage(driver);
		int cartCount = 0;
		if(!swagLabsHomePage.emptyCartBadge.getText().isEmpty())
		  cartCount = Integer.parseInt(swagLabsHomePage.emptyCartBadge.getText());
		
		click(addToCartElement);
		waitForElementContainText(removeFromCartElement, "Remove", 10);
		
		waitForElementContainText(swagLabsHomePage.nonEmptyCartBadge, String.valueOf(cartCount+1), 10);
	}
	
	
}
