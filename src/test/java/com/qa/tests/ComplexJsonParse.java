package com.qa.tests;

import com.qa.testdata.Payloads;
import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	
	public static void main(String[] args) {
	JsonPath js = new JsonPath(Payloads.getPriceResponse());
	
	
	//1. Print No of courses returned by API
	int countOfCourse = js.getInt("courses.size()");
	System.out.println("no. of courses: "+ countOfCourse);

	
	
	//2.Print Purchase Amount
	int purchaseAmt = js.getInt("dashboard.purchaseAmount");
	System.out.println("purchase Amount : " + purchaseAmt);

	//3. Print Title of the first course
	String firstCourseTitle = js.getString("courses[0].title");
	System.out.println("first course Title: " + firstCourseTitle);

	//4. Print All course titles and their respective Prices
	for(int i=0; i<countOfCourse;i++) {
		String courseTitle = js.getString("courses["+i+"].title");
		int coursePrice = js.getInt("courses["+i+"].price");
		System.out.println("course Title : " + courseTitle + " Price: " + coursePrice);
	}

	//5. Print no of copies sold by RPA Course
	for(int i=0; i<countOfCourse;i++) {
		String courseTitle = js.getString("courses["+i+"].title");
		if(courseTitle.equals("RPA")) {
			int coursePrice = js.getInt("courses["+i+"].price");
			System.out.println("RPA course's Price: " + coursePrice);
		}
	}
	

	//6. Verify if Sum of all Course prices matches with Purchase Amount
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
