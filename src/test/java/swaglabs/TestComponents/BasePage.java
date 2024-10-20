package swaglabs.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BasePage {

	private static Logger logger;
	
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Class<?> clazz) {
		BasePage.logger = Logger.getLogger(clazz);
	}

	public String getPropertyValue(String propertyKey) {
		String value = "";
		try {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "//src//main//java//swaglabs//resources//GlobalData.properties");
			prop.load(fis);
			value = prop.getProperty(propertyKey);
		} catch (IOException e) {
			Assert.assertTrue(false, "Property not loaded for " + propertyKey);
		}
		return value;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

}
