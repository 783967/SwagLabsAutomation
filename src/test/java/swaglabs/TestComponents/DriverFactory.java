package swaglabs.TestComponents;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class DriverFactory {
	
	private static WebDriver driver = null;
	BasePage basePage = new BasePage();
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	@BeforeTest
	public void initializeDriver() throws IOException
	{
		String browserName = basePage.getPropertyValue("browser");
		
		try {
			if (browserName.contains("chrome")) {
				driver = new ChromeDriver();
				
			} else if (browserName.equalsIgnoreCase("edge")) {
				// Edge
				driver = new EdgeDriver();
			}
		}
		catch(Exception e)
		{
			Assert.assertTrue(false, "Browser not launched");
		}
		driver.manage().window().maximize();
	}
	
	
	@AfterTest()
	public void tearDown()
	{
		driver.quit();
	}
}
