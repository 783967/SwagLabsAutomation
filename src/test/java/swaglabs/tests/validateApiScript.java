package swaglabs.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import swaglabs.common.CommonMethods;
import swaglabs.common.RestClient;

public class validateApiScript {

	@Test(priority = 1, description = "Validate Get Call")
	public void validateGetCall() throws ClientProtocolException, IOException {
		RestClient restClient = new RestClient();
		String apiURL = CommonMethods.getPropertyValue("apiURL") + CommonMethods.getPropertyValue("serviceURL");
		restClient.get(apiURL);
	}

	@Test(priority = 2, description = "Validate Post Call")
	public void validatePostCall() throws ClientProtocolException, IOException {
		RestClient restClient = new RestClient();
		String apiURL = CommonMethods.getPropertyValue("apiURL") + CommonMethods.getPropertyValue("serviceURL");
		JSONObject jsonPayload = new JSONObject();
		jsonPayload.put("title", "foo");
		jsonPayload.put("body", "bar");
		jsonPayload.put("userId", 1);
		restClient.post(apiURL, jsonPayload);
	}
}
