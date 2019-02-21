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
import static org.hamcrest.CoreMatchers.is;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author christian larzep
 */
public class requestTests {
    
    public requestTests() {
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
    public void postRequestTest()
    {		
            RequestSpecification request = RestAssured.given();

            JSONObject requestParams = new JSONObject();
            requestParams.put("date", "Wed Oct 24 2018");
            requestParams.put("hour", "0:59:16");
            requestParams.put("subject", "test");
            requestParams.put("description",  "Test");
            requestParams.put("system",  "AdminPAQ");
            requestParams.put("status", "TAKEN");
            requestParams.put("idAdviser",  "4");
            requestParams.put("idClient",  "4");
            
            request.body(requestParams.toString());
            Response response = request.post("/request");

            int statusCode = response.getStatusCode();
            Assert.assertEquals(200, statusCode);
            ResponseBody body = response.getBody();
            String result = body.asString();
            Assert.assertTrue(result.contains("true"));
    }
    
    @Test
    public void getAllRequestsTest() {
    RequestSpecification httpRequest = RestAssured.given();
    httpRequest.header("Content-Type", "application/json");
    Response response = httpRequest.get("/request");
    response.then().assertThat()
      .body("size()", is(17));
    }
    
    @Test
    public void getRequestByAdviserTest(){
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        Response response = httpRequest.get("/request/adviser/3");
        
        ResponseBody body = response.getBody();
        String result = body.asString();
        Assert.assertTrue(result.contains("requestId"));
        Assert.assertTrue(result.contains("39"));
        Assert.assertTrue(result.contains("date"));
        Assert.assertTrue(result.contains("Wed Oct 24 2018"));
        Assert.assertTrue(result.contains("hour"));
        Assert.assertTrue(result.contains("0:59:16"));
        Assert.assertTrue(result.contains("subject"));
        Assert.assertTrue(result.contains("cc"));
        Assert.assertTrue(result.contains("description"));
        Assert.assertTrue(result.contains("cc"));
        Assert.assertTrue(result.contains("system"));
        Assert.assertTrue(result.contains("AdminPAQ"));
        Assert.assertTrue(result.contains("status"));
        Assert.assertTrue(result.contains("RESOLVED"));
        Assert.assertTrue(result.contains("idAdviser"));
        Assert.assertTrue(result.contains("3"));
        Assert.assertTrue(result.contains("idClient"));
        Assert.assertTrue(result.contains("0"));
    }
    
    @Test
    public void getRequestByClientTest(){
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        Response response = httpRequest.get("/request/client/0");
        
        ResponseBody body = response.getBody();
        String result = body.asString();
        Assert.assertTrue(result.contains("requestId"));
        Assert.assertTrue(result.contains("39"));
        Assert.assertTrue(result.contains("date"));
        Assert.assertTrue(result.contains("Wed Oct 24 2018"));
        Assert.assertTrue(result.contains("hour"));
        Assert.assertTrue(result.contains("0:59:16"));
        Assert.assertTrue(result.contains("subject"));
        Assert.assertTrue(result.contains("cc"));
        Assert.assertTrue(result.contains("description"));
        Assert.assertTrue(result.contains("cc"));
        Assert.assertTrue(result.contains("system"));
        Assert.assertTrue(result.contains("AdminPAQ"));
        Assert.assertTrue(result.contains("status"));
        Assert.assertTrue(result.contains("RESOLVED"));
        Assert.assertTrue(result.contains("idAdviser"));
        Assert.assertTrue(result.contains("3"));
        Assert.assertTrue(result.contains("idClient"));
        Assert.assertTrue(result.contains("0"));
    }
    
    
    @Test
    public void putRequestTest()
    {		
            RequestSpecification request = RestAssured.given();

            JSONObject requestParams = new JSONObject();
            requestParams.put("requestId", "35");
            requestParams.put("date", "Thu Jan 03 2019");
            requestParams.put("hour", "14:35:28");
            requestParams.put("subject", "Help me please");
            requestParams.put("description",  "Everything is broken");
            requestParams.put("system",  "Avanttia");
            requestParams.put("status", "RESOLVED");
            requestParams.put("idAdviser",  "2");
            requestParams.put("idClient",  "1");
            
            request.body(requestParams.toString());
            Response response = request.put("/request");

            int statusCode = response.getStatusCode();
            Assert.assertEquals(200, statusCode);
            ResponseBody body = response.getBody();
            String result = body.asString();
            Assert.assertTrue(result.contains("true"));
 
    }*/

}
