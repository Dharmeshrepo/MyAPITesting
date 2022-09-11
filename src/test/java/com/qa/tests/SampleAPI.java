package com.qa.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import com.qa.testdata.Payloads;
import com.qa.utilities.reUsableMethods;


public class SampleAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Given - All input header, query parameters
		//When - http method and resouce
		//Then - validate Respnose
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";

/*		given().log().all().queryParam("Key","qaclick123").header("Content-Type","application/json")
		.body(Payloads.getAddPlaceBody())
		.when().post("maps/api/place/add/json")
		.then().log().all().statusCode(200).body("scope", equalTo("APP"))
		.header("server","Apache/2.4.41 (Ubuntu)"); */

		//Add Place
		
		String response = given().log().all().queryParam("Key","qaclick123").header("Content-Type","application/json")
		.body(Payloads.getAddPlaceBody())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println("Response :: " + response);
		
		JsonPath js =reUsableMethods.rawToJson(response);
		String placeId= js.getString("place_id");
		System.out.println("Place ID : " + placeId);
		
		
		//Update Place
		String address = "70 winter walk, UK";
		
		given().log().all().queryParam("Key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+ placeId +"\",\r\n"
				+ "\"address\":\""+address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		String getResponse= given().log().all().queryParam("key","qaclick123")
				.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		System.out.println("Print Response");
		System.out.println(getResponse);
		
		JsonPath js1 =reUsableMethods.rawToJson(getResponse);
		String actualAddress = js1.getString("address");
		
		System.out.println(actualAddress + " is equals to actual:  " + address); 
		Assert.assertEquals(actualAddress, address);
		
	}

}
