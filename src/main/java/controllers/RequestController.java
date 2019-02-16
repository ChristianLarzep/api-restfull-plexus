/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Report;
import models.Request;
import services.RequestDao;

/**
 *
 * @author christian
 */
@WebServlet(name = "RequestController", urlPatterns = {"/request/*"})
public class RequestController extends HttpServlet {
    
    RequestDao requestdao = new RequestDao();
    private final Gson gson = new Gson();
    String reportJsonStr;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {}
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(isEmpty(request)){
            reportJsonStr = getJsonData("SELECT_ALL","","");
        } else {
            String id;
            String role = getResource(request);
            if(role.equals("ADVISER")){
                id = retrieveAdviserId(request);
                reportJsonStr = getJsonData("BY_ID", id, role);
            }
            else if(role.equals("CLIENT")){
                id = retrieveClientId(request);
                reportJsonStr = getJsonData("BY_ID", id, role);
            }
            else if(role.equals("REQUEST")){
                id = retrieveRequestId(request);
                reportJsonStr = getJsonData("BY_ID", id, role);
                
            }
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(reportJsonStr);
        out.flush(); 
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try{
            if(isEmpty(request)){
                BufferedReader reader = request.getReader();
                Request req = gson.fromJson(reader, Request.class);
                requestdao.insert(req);
                out.print(true);
                out.flush();
            }   
        }catch(SQLException e){
            e.printStackTrace();
            out.print(false);
            out.flush();
        }
        processRequest(request, response);
    }
    
     @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Se manda el objeto COMṔLETO desde front, con los aributos que se desean actualizar ya editados
        PrintWriter out = response.getWriter();
        try{
            if(isEmpty(request)){
                BufferedReader reader = request.getReader();
                Request req = gson.fromJson(reader, Request.class);
                requestdao.update(req);
                out.print(true);
                out.flush();
            }   
        }catch(SQLException e){
            e.printStackTrace();
            out.print(false);
            out.flush();
        }
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private static boolean isEmpty(HttpServletRequest req) {      
      try {
        String pathInfo = req.getPathInfo();
        pathInfo.substring(1);
        return false;
      } catch( NullPointerException error){
          return true;
      }
  
    }
    
    private String getResource(HttpServletRequest req){
         try {
             String pathInfo = req.getPathInfo();
             if(pathInfo.contains("adviser")){
                 return "ADVISER";
             }else if(pathInfo.contains("client")){
                 return "CLIENT";
             } else {
                 return "REQUEST";
             }
         
         } catch( NullPointerException error){}
         
         return null;
    }
   
    
     private String getJsonData(String query, String id, String role){
           List<Report> reportList;
           String reportJsonString = ""; 
        try{
            if(query.equals("SELECT_ALL")){
                reportList = requestdao.select(-1,"ALL");
                reportJsonString = this.gson.toJson(reportList);
            } else if(query.equals("BY_ID")){
                reportList = requestdao.select(Integer.parseInt(id), role);
                reportJsonString = this.gson.toJson(reportList);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return reportJsonString;
    }
     
     private static String retrieveAdviserId(HttpServletRequest req) {      
      try {
              String pathInfo = req.getPathInfo();
              String[] parts = pathInfo.split("/");
              return parts[2];

      } catch(StringIndexOutOfBoundsException error){
      
      } 
      return null;
    }
    
    private static String retrieveClientId(HttpServletRequest req) {      
      try {
          String pathInfo = req.getPathInfo();
          pathInfo = req.getPathInfo();
          String[] parts = pathInfo.split("/");
          return parts[2];  
      } catch(StringIndexOutOfBoundsException error){
      
      } 
      return null;
    }
    
    private static String retrieveRequestId(HttpServletRequest req) {      
      try {
          String pathInfo = req.getPathInfo();
          pathInfo = req.getPathInfo();
          String[] parts = pathInfo.split("/");
          return parts[1];  

      } catch(StringIndexOutOfBoundsException error){
      
      } 
      return null;
    }

}
