package com.qa.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.testdata.Payloads;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DynamicJsonHandle {
	
	String bookId;
	@Test(enabled = false)
	public void checkAddBookWithStaticPaylod() throws IOException {
		RestAssured.baseURI = "http://216.10.245.166";
		
		/*String res = given().log().all().header("Content-Type","application/json").body(Payloads.addBook(isbn,asile ))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().asString();*/
		
		String res = given().log().all().header("Content-Type","application/json")
				.body(
						new String(
								Files.readAllBytes(
										Paths.get("D:\\Selenium\\APITesting\\src\\main\\java\\com\\qa\\testdata\\Jsons\\AddBook.json")
										)
								 ))
				.when().post("Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract().asString();
				
				
		
		JsonPath js =new JsonPath(res);
		bookId = js.getString("ID");
	}
	

	
	
	@Test(dataProvider="BookData", enabled = true)
	public void checkAddBookUsingDataProviderMethod(String isbn, String asile) throws IOException {
		RestAssured.baseURI = "http://216.10.245.166";
		String res = given().log().all().header("Content-Type","application/json").body(Payloads.addBook(isbn,asile ))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().asString();			
		
		//Store Response and get ID from it
		JsonPath js =new JsonPath(res);
		bookId = js.getString("ID");
	}
	
	
	@Test(enabled = false)
	public void deleteBook() {
		given().log().all().header("Content-Type","application/json").body(Payloads.deleteBook(bookId))
		.when().post("Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).extract().asString();
	}

//Using DataProvider Method - Multidimensional array
	@DataProvider(name = "BookData")
	public Object[][] getBookData() {
		return new Object[][]{{"111","test1"},{"112","test2"},{"113","test3"}};
	}
	
}
