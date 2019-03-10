/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author christian larzep
 */
public class userTests {
    
    public userTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        RestAssured.baseURI = "http://localhost:8086/api-restfull-plexus";
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void loginTest()
    {		
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");

            JSONObject requestParams = new JSONObject();
            requestParams.put("password", "motita");
            requestParams.put("email", "karlag@live.com");

            request.body(requestParams.toString());
            Response response = request.post("/user/login");

            int statusCode = response.getStatusCode();
            Assert.assertEquals(200, statusCode);
            ResponseBody body = response.getBody();
            String result = body.asString();

            Assert.assertTrue(result.contains("email"));
            Assert.assertTrue(result.contains("karlag@live.com"));
            Assert.assertTrue(result.contains("password"));
            Assert.assertTrue(result.contains("motita"));
    }
    
    @Test
    public void incorrectEmailloginTest()
    {		
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");

            JSONObject requestParams = new JSONObject();
            requestParams.put("password", "motita");
            requestParams.put("email", "karlag@live.co");
            
            request.body(requestParams.toString());
            Response response = request.post("/user/login");

            int statusCode = response.getStatusCode();
            Assert.assertEquals(200, statusCode);
            ResponseBody body = response.getBody();
            String result = body.asString();
            Assert.assertTrue(result.contains("404"));
    
    }
    
    @Test
    public void incorrectPassloginTest()
    {		
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");

            JSONObject requestParams = new JSONObject();
            requestParams.put("password", "motit");
            requestParams.put("email", "karlag@live.com");
            
            request.body(requestParams.toString());
            Response response = request.post("/user/login");

            int statusCode = response.getStatusCode();
            Assert.assertEquals(200, statusCode);
            ResponseBody body = response.getBody();
            String result = body.asString();
            Assert.assertTrue(result.contains("404"));
    }
    
    @Test
    public void registerNewUserTest()
    {
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");

            JSONObject requestParams = new JSONObject();
            
            requestParams.put("username", "Test new user");
            requestParams.put("email", "testnew@live.com");
            requestParams.put("password", "new");
            requestParams.put("role", "CLIENT");
            requestParams.put("cell", "3456787654");
            requestParams.put("tel", "567654454");
            requestParams.put("rfc", "URNF756FJ");
            requestParams.put("r_social", "NewInc");
            
            request.body(requestParams.toString());
            Response response = request.post("/user/register");

            int statusCode = response.getStatusCode();
            Assert.assertEquals(200, statusCode);
            ResponseBody body = response.getBody();
            String result = body.asString();
            Assert.assertTrue(result.contains("200"));
    }
    
    @Test
    public void registerExistingUserTest()
    {		
        RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");

            JSONObject requestParams = new JSONObject();
            
            requestParams.put("username", "Test new user");
            requestParams.put("email", "carlos@live.com");
            requestParams.put("password", "new");
            requestParams.put("role", "CLIENT");
            requestParams.put("cell", "3456787654");
            requestParams.put("tel", "567654454");
            requestParams.put("rfc", "URNF756FJ");
            requestParams.put("r_social", "OtherInc");
            
            request.body(requestParams.toString());
            Response response = request.post("/user/register");

            int statusCode = response.getStatusCode();
            Assert.assertEquals(200, statusCode);
            ResponseBody body = response.getBody();
            String result = body.asString();
            Assert.assertTrue(result.contains("400"));
    }
   
}
