package com.web.automation.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtils {

	public Response getRequest() {
		Response response = RestAssured.get("");
		System.out.println("Status Code :"+response.getStatusCode());
		return response;
		
	}

	public void postRequest() {

	}
}
