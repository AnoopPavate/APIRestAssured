package com.api;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

//import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class testCases {
	
	@Test(priority=1)
	public void testGetUsers()
	{
		given()
			.auth().none()
			.param("page", "1")
			.header("Content-Type", "application/json")
		.when()
			.get("https://reqres.in/api/users")
		.then()
			.statusCode(200)
			.log().body()
			.body("page", equalTo(1))
			.body("per_page", equalTo(6)); 
		
		
		/*Response res= given()
			.auth().none()
			.param("page", "2")
			.header("Content-Type", "application/json")
		.when()
			.get("https://reqres.in/api/users");
		
		res.prettyPrint(); */		
		
	}
	
	
	@Test(priority=2)
	public void addnewUser()
	{
		HashMap<String, Object> data=new HashMap<String, Object>();		
		
		//JSONObject request=new JSONObject();

		data.put("name", "Lakshman");
		data.put("job", "Engineer");
		data.put("goal", "Army");
		
		//Response res=
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.log().body();
		//	.extract().response();
		
		//res.prettyPrint();
			
		}
	
	@Test(priority=3)
	public void getUser()
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users/2")
		.then()
			.statusCode(200)
			.log().body()
			.body("data.id", equalTo(2))
			.body("data.email", equalTo("janet.weaver@reqres.in"));		
		
	}
	
	@Test(priority=4)
	public void testDelete()
	{				
		given()
		.when()
			.delete("https://reqres.in/api/users/2")
		.then()
			.statusCode(204)
			//.log().body();
			.log().all();
	}
		
	@Test(priority=5)	
	public void updateUser()
	{
		HashMap<String, Object> data=new HashMap<String, Object>();
		
		data.put("name", "Anoop");
		data.put("job", "Enginee");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/375")
		.then()
			.statusCode(200)
			.log().body();
	}
		


}
