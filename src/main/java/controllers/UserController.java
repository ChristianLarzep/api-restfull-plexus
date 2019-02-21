/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UserDao;
import java.io.BufferedReader;
import java.sql.SQLException;
import models.User;

/**
 *
 * @author christian larzep
 */
@WebServlet(name = "UserController", urlPatterns = {"/user/*"})
public class UserController extends HttpServlet {
    
    private final Gson gson = new Gson();
    UserDao userdao = new UserDao();
    String userJsonStr; 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        
        
        try (PrintWriter out = response.getWriter()) {}
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        
      
        
        if(isEmpty(request) == false){
            String resource = getResource(request);
            BufferedReader reader = request.getReader();
            User user = gson.fromJson(reader, User.class);
            userJsonStr = getJsonData(resource, user);
            
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userJsonStr);
        out.flush();
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

     private String getJsonData(String query, User user){
           String reportJsonString = ""; 
        try{
            if(query.equals("LOGIN")){
                reportJsonString = this.gson.toJson(userdao.login(user));
            } else if(query.equals("REGISTER")){
                reportJsonString = this.gson.toJson(userdao.register(user));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return reportJsonString;
    }
    
    private String getResource(HttpServletRequest req){
         try {
             String pathInfo = req.getPathInfo();
             if(pathInfo.contains("login")){
                 return "LOGIN";
             }else if(pathInfo.contains("register")){
                 return "REGISTER";
             }
         
         } catch( NullPointerException error){}
         
         return null;
    }
    
    private static boolean isEmpty(HttpServletRequest req) {      
      try {
        String pathInfo = req.getPathInfo();
        pathInfo.substring(1);
        return false;
      } catch( NullPointerException error){
          return true;
      }
  
    }
    
    private void setAccessControlHeaders(HttpServletResponse resp) {
      resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8100/");
      resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
      resp.setHeader("Access-Control-Allow-Methods", "POST");
  }
}


//POST
//http://localhost:4949/api-restfull-plexus/user/login
//http://localhost:4949/api-restfull-plexus/user/register

//Cross-Origin Tomcat
//http://tomcat.apache.org/tomcat-8.0-doc/config/filter.html#CORS_Filter
//https://stackoverflow.com/questions/30171694/enable-cors-apache-tomcat-7-0-52
