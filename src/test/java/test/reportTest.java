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
    public void postReportTest()
    {		
            RequestSpecification request = RestAssured.given();

            JSONObject requestParams = new JSONObject();
            requestParams.put("client_user_id", "5");
            requestParams.put("adviser_user_id", "5");
            requestParams.put("system", "AdminPAQ");
            requestParams.put("init_date", "Wed Oct 24 2018 12:23:54");
            requestParams.put("end_date",  "Wed Oct 24 2018 13:35:16");
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
      .body("size()", is(4));
    }
    
    @Test
    public void getReportByAdviserTest(){
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        Response response = httpRequest.get("/report/adviser/1");
        
        ResponseBody body = response.getBody();
        String result = body.asString();
        Assert.assertTrue(result.contains("report_id"));
        Assert.assertTrue(result.contains("35"));
        Assert.assertTrue(result.contains("client_user_id"));
        Assert.assertTrue(result.contains("3"));
        Assert.assertTrue(result.contains("adviser_user_id"));
        Assert.assertTrue(result.contains("1"));
        Assert.assertTrue(result.contains("system"));
        Assert.assertTrue(result.contains("Comercial Premium"));
        Assert.assertTrue(result.contains("init_date"));
        Assert.assertTrue(result.contains("Wed Feb 20 2019 20:13:7"));
        Assert.assertTrue(result.contains("end_date"));
        Assert.assertTrue(result.contains("Wed Feb 20 2019 20:50:57"));
        Assert.assertTrue(result.contains("status"));
        Assert.assertTrue(result.contains("RESOLVED"));
        Assert.assertTrue(result.contains("time"));
        Assert.assertTrue(result.contains("20 min"));
        Assert.assertTrue(result.contains("amount"));
        Assert.assertTrue(result.contains("30"));
        Assert.assertTrue(result.contains("description"));
        Assert.assertTrue(result.contains("Listones"));
    }
    
    @Test
    public void getReportByClientTest(){
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        Response response = httpRequest.get("/report/client/7");
        
        ResponseBody body = response.getBody();
        String result = body.asString();
        Assert.assertTrue(result.contains("report_id"));
        Assert.assertTrue(result.contains("4"));
        Assert.assertTrue(result.contains("client_user_id"));
        Assert.assertTrue(result.contains("7"));
        Assert.assertTrue(result.contains("adviser_user_id"));
        Assert.assertTrue(result.contains("2"));
        Assert.assertTrue(result.contains("system"));
        Assert.assertTrue(result.contains("Mantenimiento correctivo"));
        Assert.assertTrue(result.contains("init_date"));
        Assert.assertTrue(result.contains("Wed Oct 24 2018 14:11:26"));
        Assert.assertTrue(result.contains("end_date"));
        Assert.assertTrue(result.contains("Wed Oct 24 2018 14:12:59"));
        Assert.assertTrue(result.contains("status"));
        Assert.assertTrue(result.contains("RESOLVED"));
        Assert.assertTrue(result.contains("time"));
        Assert.assertTrue(result.contains("20 min"));
        Assert.assertTrue(result.contains("amount"));
        Assert.assertTrue(result.contains("30"));
        Assert.assertTrue(result.contains("description"));
        Assert.assertTrue(result.contains("Ya murio su equipo"));
    }
   
}
