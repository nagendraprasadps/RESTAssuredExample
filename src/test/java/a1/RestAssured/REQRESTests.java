package a1.RestAssured;

import java.util.Iterator;
import org.testng.Assert;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class REQRESTests {
	@Parameters ({"page", "per_page"})
	@Test
  public void listUsersInaPage(int page, int per_page) throws ParseException {
	  System.out.println("Started");
	 
	  RestAssured.baseURI="https://reqres.in/";
	  RequestSpecification request = RestAssured.given();
	  request.param("page", page);
	  request.param("per_page", per_page);
	  Response response = request.request("GET", "api/users");
	  
	  Headers headers = response.getHeaders();
	  System.out.println(headers);
	  System.out.println("Body");
	  System.out.println(response.getStatusCode());
	  System.out.println(response.getStatusLine());
	  System.out.println(response.getBody().asString());
	  String g=response.getBody().asString();
	  JSONParser parser = new JSONParser();  
	  JSONObject json = (JSONObject) parser.parse(g);  
	  System.out.println("Bodyend");
	  System.out.println(json);
  }
	
	@Parameters ({"id"})
	@Test
  public void listSingleUser(int id) throws ParseException {
	  System.out.println("Started");
	 
	  RestAssured.baseURI="https://reqres.in/";
	  RequestSpecification request = RestAssured.given();
	  request.param("id", id);
	  Response response = request.request("GET", "api/users");
	  
	  Headers headers = response.getHeaders();
	  System.out.println(headers);
	  System.out.println("Body");
	  System.out.println(response.getStatusCode());
	  System.out.println(response.getStatusLine());
	  System.out.println(response.getBody().asString());
	  String g=response.getBody().asString();
	  JSONParser parser = new JSONParser();  
	  JSONObject json = (JSONObject) parser.parse(g); 
	  Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
	  Assert.assertEquals(response.getStatusCode(), 200);
	  Assert.assertEquals(g.contains("bluth"),true);
	  
	  System.out.println("Bodyend");
	  System.out.println(json);
  }

	@Parameters ({"id"})
	@Test
  public void userNotFound(int id) throws ParseException {
	  System.out.println("Started");
	 
	  RestAssured.baseURI="https://reqres.in/";
	  RequestSpecification request = RestAssured.given();
	  request.param("id", id);
	  Response response = request.request("GET", "api/users");
	  
	  Headers headers = response.getHeaders();
	  System.out.println(headers);
	  System.out.println("Body");
	  System.out.println(response.getStatusCode());
	  System.out.println(response.getStatusLine());
	  System.out.println(response.getBody().asString());
	  String g=response.getBody().asString();
	  JSONParser parser = new JSONParser();  
	  JSONObject json = (JSONObject) parser.parse(g); 
	  Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 404 Not Found");
	  Assert.assertEquals(response.getStatusCode(), 404);
	  Assert.assertEquals(g.length(),2);
	  
  }
	@Parameters ({"name","job"})
  @Test
  public void createUser(String name, String job) throws ParseException {
	  System.out.println("Started");
	  RestAssured.baseURI="https://reqres.in/";
	  RequestSpecification request = RestAssured.given();
	  //request.param("page", 1);
	  JSONObject pl = new JSONObject() ;
	  pl.put("name", "Nagendra");
	  pl.put("job", "leader");
	  //request.body("{    \"name\": \"morpheus1\",    \"job\": \"leader\"}");
	  request.body(pl.toString());
	  
	  Response response = request.request("POST", "api/users");
	  
	  Headers headers = response.getHeaders();
	  System.out.println(headers);
	  System.out.println("Body");
	  System.out.println(response.getStatusCode());
	  System.out.println(response.getStatusLine());
	  System.out.println(response.getBody().asString());
	  String g=response.getBody().asString();
	  JSONParser parser = new JSONParser();  
	  JSONObject json = (JSONObject) parser.parse(g);  
	  System.out.println(json.get("id"));
	  System.out.println("Bodyend");
	  System.out.println(json);
  }
	@Test
	  public void login() throws ParseException {
		  System.out.println("Started");
		  RestAssured.baseURI="https://reqres.in/";
		  RequestSpecification request = RestAssured.given();
		  //request.param("page", 1);
		  request.body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}");
		  request.header("content-type","application/json");
		  //request.body(//arg0)
		  JSONObject rp= new JSONObject();
		  rp.put("email", "eve.holt@reqres.in");
		  rp.put("password", "cityslicka");
		  Response response = request.request("POST", "api/login");
		  
		  Headers headers = response.getHeaders();
		  System.out.println(headers);
		  System.out.println("Body");
		  System.out.println(response.getStatusCode());
		  System.out.println(response.getStatusLine());
		  System.out.println(response.getBody().asString());
		  String g=response.getBody().asString();
		    JSONParser parser = new JSONParser();  
		    JSONObject json = (JSONObject) parser.parse(g);  
		  System.out.println("Token:" + json.get("token"));
		  System.out.println(json.get("token"));
		
			  
		  
		  }
	
	@Test
	  public void updateUserRole() throws ParseException {
		  System.out.println("Started");
		  RestAssured.baseURI="https://reqres.in/";
		  RequestSpecification request = RestAssured.given();
		  request.header("content-type","application/json");
		  JSONObject rp= new JSONObject();
		  rp.put("name", "morpheus");
		  rp.put("job", "zion resident");
		  Response response = request.request("PUT", "api/users/2");
		  
		  Headers headers = response.getHeaders();
		  System.out.println(headers);
		  System.out.println("Body");
		  System.out.println(response.getStatusCode());
		  System.out.println(response.getStatusLine());
		  System.out.println(response.getBody().asString());
		  String g=response.getBody().asString();
		    JSONParser parser = new JSONParser();  
		    JSONObject json = (JSONObject) parser.parse(g);  
		  System.out.println("Token:" + json.get("token"));
		  System.out.println(json.get("token"));
		  //Nagendra Prasad -store this token in a class variable and use in subsequent calls
			  
		  
		  }
	
	@Test
	  public void deleteUsers() throws ParseException {
		  System.out.println("Started");
		  RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
		  RequestSpecification request = RestAssured.given();
		  request.header("content-type","application/json");
		  JSONObject rp= new JSONObject();
		 // rp.put("name", "morpheus");
		  //rp.put("job", "zion resident");
		  Response response = request.request("DELETE", "posts/1");
		  
		  Headers headers = response.getHeaders();
		  System.out.println(headers);
		  System.out.println("Body");
		  System.out.println(response.getStatusCode());
		  System.out.println(response.getStatusLine());
		  System.out.println(response.getBody().asString());
		  String g=response.getBody().asString();
		    JSONParser parser = new JSONParser();  
		    JSONObject json = (JSONObject) parser.parse(g);  
		  System.out.println("Token:" + json.get("token"));
		  System.out.println(json.get("token"));
		
			  
		  
		  }
	
  /*
  @Test
  public void f3() throws ParseException {
	  System.out.println("Started");
	  RestAssured.baseURI="https://reqres.in/";
	  RequestSpecification request = RestAssured.given();
	  request.param("id", 12);
	  Response response = request.request("GET", "api/users");
	  
	  Headers headers = response.getHeaders();
	  System.out.println(headers);
	  System.out.println("Body");
	  System.out.println(response.getStatusCode());
	  System.out.println(response.getStatusLine());
	  System.out.println(response.getBody().asString());
	  String g=response.getBody().asString();
	    JSONParser parser = new JSONParser();  
	    JSONObject json = (JSONObject) parser.parse(g);  
	  System.out.println("Bodyend");
	  System.out.println(json);
  }
  
	
	
	
	@Test
	  public void f4() throws ParseException {
		  System.out.println("Started");
		  RestAssured.baseURI="https://reqres.in/";
		  RequestSpecification request = RestAssured.given();
		  //request.param("page", 1);
		  request.body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}");
		  request.header("content-type","application/json");
		  //request.body(//arg0)
		  JSONObject rp= new JSONObject();
		  rp.put("email", "eve.holt@reqres.in");
		  rp.put("password", "cityslicka");
		  Response response = request.request("POST", "api/login");
		  
		  Headers headers = response.getHeaders();
		  System.out.println(headers);
		  System.out.println("Body");
		  System.out.println(response.getStatusCode());
		  System.out.println(response.getStatusLine());
		  System.out.println(response.getBody().asString());
		  String g=response.getBody().asString();
		    JSONParser parser = new JSONParser();  
		    JSONObject json = (JSONObject) parser.parse(g);  
		  System.out.println("Bodyend");
		  System.out.println(json.get("token"));
		
			  
		  
		  }
		  
		  */
	  }
	
  


