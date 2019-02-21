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
import models.RequestItem;
import services.RequestDao;

/**
 *
 * @author christian larzep
 */
@WebServlet(name = "RequestController", urlPatterns = {"/request/*"})
public class RequestController extends HttpServlet {
    
    RequestDao requestdao = new RequestDao();
    private final Gson gson = new Gson();
    String requestJsonStr;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {}
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(isEmpty(request)){
            requestJsonStr = getJsonData("SELECT_ALL","","");
        } else {
            String id;
            String role = getResource(request);
            switch (role) {
                case "ADVISER":
                    id = retrieveAdviserId(request);
                    requestJsonStr = getJsonData("BY_USER_ID", id, role);
                    break;
                case "CLIENT":
                    id = retrieveClientId(request);
                    requestJsonStr = getJsonData("BY_USER_ID", id, role);
                    break;
                case "REQUEST":
                    id = retrieveRequestId(request);
                    requestJsonStr = getJsonData("BY_REQUEST_ID", id, "");
                    break;
                default:
                    break;
            }
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(requestJsonStr);
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
                int id = requestdao.insert(req);
                out.print(id);
                out.flush();
            }   
        }catch(SQLException e){
            e.printStackTrace();
            out.print(0);
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
                out.print(requestdao.update(req));
                out.flush();
            }   
        }catch(SQLException e){
            e.printStackTrace();
            String nullresponse = null;
            out.print(nullresponse);
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
               switch (query) {
                   case "SELECT_ALL":
                       reportList = requestdao.select(-1,"ALL");
                       reportJsonString = this.gson.toJson(reportList);
                       break;
                   case "BY_USER_ID":
                       reportList = requestdao.select(Integer.parseInt(id), role);
                       reportJsonString = this.gson.toJson(reportList);
                       break;
                   case "BY_REQUEST_ID":
                       RequestItem req= requestdao.searchRequest(Integer.parseInt(id)); 
                       reportJsonString = this.gson.toJson(req);
                       break;
                   default:
                       break;
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


//GET
// http://localhost:4949/api-restfull-plexus/request
// http://localhost:4949/api-restfull-plexus/request/id
// http://localhost:4949/api-restfull-plexus/request/client/id
// http://localhost:4949/api-restfull-plexus/request/adviser/id

//POST
// http://localhost:4949/api-restfull-plexus/request

//PUT
//http://localhost:4949/api-restfull-plexus/request