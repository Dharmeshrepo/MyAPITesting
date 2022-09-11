package com.qa.utilities;

import java.util.Random;

import io.restassured.path.json.JsonPath;

public class reUsableMethods {

	public static JsonPath rawToJson(String response) {
	JsonPath js = new JsonPath(response);
	return js;
	}

	public static String getRandomString(int n) {
		String randomStr="test";
		int testNum = 0;
		Random random = new Random();
		
		System.out.println("Test" + random.nextInt(n));
			for(int i=0;i<n;i++) {	
				testNum = testNum + random.nextInt(i);
				System.out.println("TestNum" + testNum);
			}
		String newstr= 	randomStr + testNum;
		return randomStr + testNum;
		
	}
	
	public static void main(String[] args) {
		String s = getRandomString(3);
		System.out.println(s);
	}
	
}

