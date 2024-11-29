package swaglabs.common;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import swaglabs.components.BasePage;

public class RestClient {

	private static Logger logger = BasePage.getLogger();

	/**
	 * Validate Get Call
	 * @param url
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void get(String url, String serviceUrl) {
		HttpHost proxy = new HttpHost("proxy.cognizant.com", 8080); 
        RequestConfig config = RequestConfig.custom()
                .setProxy(proxy)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(config)
                .build();
		// Base URI
       RestAssured.baseURI = url;
       
        // GET request
        Response response = RestAssured
                .given()
                .when()
                .get(serviceUrl)
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Print status code
        Assert.assertEquals(200, response.getStatusCode());
        logger.info("Status Code -> " + response.getStatusCode());

        // Print response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body -> " + responseBody);

        // Convert response body to JSON
        JSONObject responseJson = new JSONObject(responseBody);
        System.out.println("Response JSON -> " + responseJson.toString(4));
	}
	
	public void post(String url, String serviceUrl) {
		// Proxy configuration
        HttpHost proxy = new HttpHost("proxy.cognizant.com", 8080);
        RequestConfig config = RequestConfig.custom()
                .setProxy(proxy)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(config)
                .build();

        // Base URI
        RestAssured.baseURI = url; // Replace with your URL

        // Request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "leader");
        
        Response response = RestAssured
                .given()
                .body(requestBody.toString())
                .post(serviceUrl)
                .then()
                .statusCode(201)
                .extract()
                .response();
   
        // Print status code
        Assert.assertEquals(201, response.getStatusCode());
        logger.info("Status Code -> " + response.getStatusCode());

        // Print response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body -> " + responseBody);

        // Convert response body to JSON
        JSONObject responseJson = new JSONObject(responseBody);
        System.out.println("Response JSON -> " + responseJson.toString(4));
	}
	
	

}