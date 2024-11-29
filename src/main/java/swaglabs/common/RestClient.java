package swaglabs.common;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;

import swaglabs.components.BasePage;

public class RestClient {

	private static Logger logger = BasePage.getLogger();

	/**
	 * Validate Get Call
	 * @param url
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void get(String url) throws ClientProtocolException, IOException {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000) // Connection timeout
				.setSocketTimeout(10000) // Socket timeout
				.build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse closableHttpResponse = httpClient.execute(httpGet);

		// Get Status Code
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		logger.info("Status Code -> " + statusCode);
		Assert.assertEquals(200, 200);

		// Get response body
				String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		logger.info("Response JSON from API -> " + responseJson);

		// Get headers
		Header[] headerArray = closableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array -> " + allHeaders);
	}
	
	/**
	 * Validate Post Call
	 * @param url
	 * @param jsonPayload
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void post(String url, JSONObject jsonPayload) throws ClientProtocolException, IOException {
	    RequestConfig requestConfig = RequestConfig.custom()
	            .setConnectTimeout(10000) // Connection timeout
	            .setSocketTimeout(10000) // Socket timeout
	            .build();
	    CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
	    HttpPost httpPost = new HttpPost(url);

	    // Set the payload
	    StringEntity entity = new StringEntity(jsonPayload.toString());
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Content-type", "application/json");

	    CloseableHttpResponse closableHttpResponse = httpClient.execute(httpPost);

	    // Get Status Code
	    int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
	    logger.info("Status Code -> " + statusCode);
	    Assert.assertEquals(200, statusCode);

	    // Get response body
	    String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
	    JSONObject responseJson = new JSONObject(responseString);
	    logger.info("Response JSON from API -> " + responseJson);

	    // Get headers
	    Header[] headerArray = closableHttpResponse.getAllHeaders();
	    HashMap<String, String> allHeaders = new HashMap<String, String>();

	    for (Header header : headerArray) {
	        allHeaders.put(header.getName(), header.getValue());
	    }

	    System.out.println("Headers Array -> " + allHeaders);
	}


}