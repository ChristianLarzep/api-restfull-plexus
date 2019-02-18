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

/**
 *
 * @author christian larzep
 */
public class reportTest {
    
    public reportTest() {
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
    public void postReportTest()
    {		
            RequestSpecification request = RestAssured.given();

            JSONObject requestParams = new JSONObject();
            requestParams.put("idClient", "5");
            requestParams.put("idAdviser", "5");
            requestParams.put("system", "AdminPAQ");
            requestParams.put("initDate", "Wed Oct 24 2018 12:23:54");
            requestParams.put("endDate",  "Wed Oct 24 2018 13:35:16");
            requestParams.put("status",  "RESOLVED");
            requestParams.put("time", "20 min");
            requestParams.put("amount",  "30");
            requestParams.put("description",  "Test");
            
            request.body(requestParams.toString());
            Response response = request.post("/report");

            int statusCode = response.getStatusCode();
            Assert.assertEquals(200, statusCode);
            ResponseBody body = response.getBody();
            String result = body.asString();
            Assert.assertTrue(result.contains("true"));
    }
    
    @Test
    public void getAllReportsTest() {
    RequestSpecification httpRequest = RestAssured.given();
    httpRequest.header("Content-Type", "application/json");
    Response response = httpRequest.get("/report");
    response.then().assertThat()
      .body("size()", is(15));
    }
    
    @Test
    public void getReportByAdviserTest(){
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        Response response = httpRequest.get("/report/adviser/1");
        
        ResponseBody body = response.getBody();
        String result = body.asString();
        Assert.assertTrue(result.contains("reportId"));
        Assert.assertTrue(result.contains("16"));
        Assert.assertTrue(result.contains("idClient"));
        Assert.assertTrue(result.contains("1"));
        Assert.assertTrue(result.contains("idAdviser"));
        Assert.assertTrue(result.contains("1"));
        Assert.assertTrue(result.contains("system"));
        Assert.assertTrue(result.contains("AdminPAQ"));
        Assert.assertTrue(result.contains("initDate"));
        Assert.assertTrue(result.contains("Wed Oct 24 2018 12:23:54"));
        Assert.assertTrue(result.contains("endDate"));
        Assert.assertTrue(result.contains("Wed Oct 24 2018 13:35:16"));
        Assert.assertTrue(result.contains("status"));
        Assert.assertTrue(result.contains("RESOLVED"));
        Assert.assertTrue(result.contains("time"));
        Assert.assertTrue(result.contains("20 min"));
        Assert.assertTrue(result.contains("amount"));
        Assert.assertTrue(result.contains("30"));
        Assert.assertTrue(result.contains("description"));
        Assert.assertTrue(result.contains("No habia luz"));
    }
    
    @Test
    public void getReportByClientTest(){
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        Response response = httpRequest.get("/report/client/4");
        
        ResponseBody body = response.getBody();
        String result = body.asString();
        Assert.assertTrue(result.contains("idClient"));
        Assert.assertTrue(result.contains("4"));
        Assert.assertTrue(result.contains("idAdviser"));
        Assert.assertTrue(result.contains("4"));
        Assert.assertTrue(result.contains("system"));
        Assert.assertTrue(result.contains("AdminPAQ"));
        Assert.assertTrue(result.contains("initDate"));
        Assert.assertTrue(result.contains("Wed Oct 24 2018 12:23:54"));
        Assert.assertTrue(result.contains("endDate"));
        Assert.assertTrue(result.contains("Wed Oct 24 2018 13:35:16"));
        Assert.assertTrue(result.contains("status"));
        Assert.assertTrue(result.contains("RESOLVED"));
        Assert.assertTrue(result.contains("time"));
        Assert.assertTrue(result.contains("20 min"));
        Assert.assertTrue(result.contains("amount"));
        Assert.assertTrue(result.contains("30"));
        Assert.assertTrue(result.contains("description"));
        Assert.assertTrue(result.contains("Description"));
    }
   
}
