package swaglabs.tests;

import org.testng.annotations.Test;

import swaglabs.common.CommonMethods;
import swaglabs.common.RestClient;

public class validateApiScript {

	@Test(priority = 1, description = "Validate Get Call")
	public void validateGetCall() {
		RestClient restClient = new RestClient();
		restClient.get(CommonMethods.getPropertyValue("apiURL"), CommonMethods.getPropertyValue("serviceURL"));
	}
	
	@Test
	public void validatePostCall() {
		RestClient restClient = new RestClient();
		restClient.post(CommonMethods.getPropertyValue("apiURL"), CommonMethods.getPropertyValue("serviceURL"));
	}

	
}