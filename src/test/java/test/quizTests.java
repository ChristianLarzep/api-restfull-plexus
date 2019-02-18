/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.CoreMatchers.is;
import org.json.JSONObject;
import org.junit.Assert;

/**
 *
 * @author christian larzep
 */
public class quizTests {
    
    public quizTests() {
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
    
     @Test
    public void postQuizTest()
    {		
            RequestSpecification request = RestAssured.given();

            JSONObject requestParams = new JSONObject();
            requestParams.put("contact", "test");
            requestParams.put("idAdviser", "4");
            requestParams.put("timeWaiting", "30,60");
            requestParams.put("timeToSolve", "0,30");
            requestParams.put("knowledge",  "80");
            requestParams.put("satisfaction",  "80");
            
            request.body(requestParams.toString());
            Response response = request.post("/quiz");

            int statusCode = response.getStatusCode();
            Assert.assertEquals(200, statusCode);
            ResponseBody body = response.getBody();
            String result = body.asString();
            Assert.assertTrue(result.contains("true"));
    }
    
    @Test
    public void getAllQuizesTest() {
    RequestSpecification httpRequest = RestAssured.given();
    httpRequest.header("Content-Type", "application/json");
    Response response = httpRequest.get("/quiz");
    response.then().assertThat()
      .body("size()", is(21));
    }
    
    @Test
    public void getQuizesByAdviserTest(){
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        Response response = httpRequest.get("/quiz/adviser/3");
        
        ResponseBody body = response.getBody();
        String result = body.asString();
        Assert.assertTrue(result.contains("quizId"));
        Assert.assertTrue(result.contains("29"));
        Assert.assertTrue(result.contains("contact"));
        Assert.assertTrue(result.contains("plexuss"));
        Assert.assertTrue(result.contains("idAdviser"));
        Assert.assertTrue(result.contains("3"));
        Assert.assertTrue(result.contains("timeWaiting"));
        Assert.assertTrue(result.contains("30,60"));
        Assert.assertTrue(result.contains("timeToSolve"));
        Assert.assertTrue(result.contains("0,30"));
        Assert.assertTrue(result.contains("knowledge"));
        Assert.assertTrue(result.contains("80"));
        Assert.assertTrue(result.contains("satisfaction"));
        Assert.assertTrue(result.contains("80")); 
    }
  
}
