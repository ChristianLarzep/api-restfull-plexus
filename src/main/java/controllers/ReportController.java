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
import services.ReportDao;

/**
 *
 * @author christian
 */
@WebServlet(name = "ReportController", urlPatterns = {"/report/*"})
public class ReportController extends HttpServlet {

    private final Gson gson = new Gson();
    ReportDao reportdao = new ReportDao();
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
                id = retrieveClientId(request,"GET");
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
          String advId = retrieveAdviserId(request);
          String clintId =retrieveClientId(request,"POST");
          if(advId != null && clintId != null){
            BufferedReader reader = request.getReader();
            Report report = gson.fromJson(reader, Report.class);
            report.setIdAdviser(Integer.parseInt(advId));
            report.setIdClient(Integer.parseInt(clintId));
            reportdao.insert(report);
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
    
     private String getJsonData(String query, String id, String role){
           List<Report> reportList;
           String reportJsonString = ""; 
        try{
            if(query.equals("SELECT_ALL")){
                reportList = reportdao.select(-1,"ALL");
                reportJsonString = this.gson.toJson(reportList);
            } else if(query.equals("BY_ID")){
                reportList = reportdao.select(Integer.parseInt(id), role);
                reportJsonString = this.gson.toJson(reportList);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return reportJsonString;
    }
     
     private String getResource(HttpServletRequest req){
         try {
             String pathInfo = req.getPathInfo();
             if(pathInfo.contains("adviser")){
                 return "ADVISER";
             }else if(pathInfo.contains("client")){
                 return "CLIENT";
             }
         
         } catch( NullPointerException error){}
         
         return null;
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
    
    private static String retrieveClientId(HttpServletRequest req, String method) {      
      try {
          String pathInfo = req.getPathInfo();
          if(method.equals("GET")){
            return pathInfo.substring(8);   
          } else if(method.equals("POST")){
              String[] parts = pathInfo.split("/");
              return parts[4];
          }

      } catch(StringIndexOutOfBoundsException error){
      
      } 
      return null;
    }

}
