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
        RestAssured.baseURI = "http://localhost:4949/api-restfull-plexus";
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
    
    /*
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
            Assert.assertTrue(result.contains("userId"));
            Assert.assertTrue(result.contains("6"));
            Assert.assertTrue(result.contains("username"));
            Assert.assertTrue(result.contains("Karla Gabrielle"));
            Assert.assertTrue(result.contains("email"));
            Assert.assertTrue(result.contains("karlag@live.com"));
            Assert.assertTrue(result.contains("password"));
            Assert.assertTrue(result.contains("motita"));
            Assert.assertTrue(result.contains("role"));
            Assert.assertTrue(result.contains("CLIENT"));
            Assert.assertTrue(result.contains("cell"));
            Assert.assertTrue(result.contains("23345656"));
            Assert.assertTrue(result.contains("tel"));
            Assert.assertTrue(result.contains("24354675"));
            Assert.assertTrue(result.contains("rfc"));
            Assert.assertTrue(result.contains("URNF756FJ"));
            Assert.assertTrue(result.contains("r_social"));
            Assert.assertTrue(result.contains("KGInc"));
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
            Assert.assertTrue(result.contains("null"));
    
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
            Assert.assertTrue(result.contains("null"));
    
    }*/
   
}
