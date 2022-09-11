package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.testdata.Payloads;

import io.restassured.path.json.JsonPath;

public class ScenariosUsingTestNG {
	
 @Test
 public void sumOfCourseValidation() 
 {

	 JsonPath js = new JsonPath(Payloads.getPriceResponse());
		
		
		//1. Print No of courses returned by API
		int countOfCourse = js.getInt("courses.size()");
		System.out.println("no. of courses: "+ countOfCourse);

		
		
		//2.Print Purchase Amount
		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println("purchase Amount : " + purchaseAmt);

		int totalPrice = 0;
		for(int i=0; i<countOfCourse;i++) {
				int coursePrice = js.getInt("courses["+i+"].price");
				int noOfCopies = js.getInt("courses["+i+"].copies");
				totalPrice = totalPrice + (coursePrice*noOfCopies);
		}
		System.out.println("Total Price of purchased price: " + totalPrice);
		Assert.assertEquals(totalPrice, purchaseAmt,"total price is equal to Purchase Amt");
		System.out.println("Assertion Passed ");
		Assert.assertEquals(totalPrice, purchaseAmt,"total price is equal to Purchase Amt");
		System.out.println("Assertion Passed ");
	 
 }

}


