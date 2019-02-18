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
import models.Quiz;
import services.QuizDao;

/**
 *
 * @author christian larzep
 */
@WebServlet(name = "QuizController", urlPatterns = {"/quiz/*"})
public class QuizController extends HttpServlet {

    private final Gson gson = new Gson();
    QuizDao quizdao = new QuizDao();
    String quizJsonString;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        if(isEmpty(request)){
            quizJsonString = getJsonData("SELECT_ALL",""); 
        } else if(retrieveAdviserId(request) != null){
            String id = retrieveAdviserId(request);
            quizJsonString = getJsonData("SELECT_QUIZES", id); 
        }
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(quizJsonString);
        out.flush();
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        Quiz quiz = gson.fromJson(reader, Quiz.class); 
        PrintWriter out = response.getWriter();
        try{
          if(isEmpty(request)){
            quizdao.insert(quiz);
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
    
    private String getJsonData(String query, String id){
           List<Quiz> reportList;
           String quizJsonString = ""; 
        try{
            if(query.equals("SELECT_ALL")){
                reportList = quizdao.select(-1,"ALL");
                quizJsonString = this.gson.toJson(reportList);
            } else if(query.equals("SELECT_QUIZES")){
                reportList = quizdao.select(Integer.parseInt(id),"QUIZ");
                quizJsonString = this.gson.toJson(reportList);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return quizJsonString;
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
    
    private static boolean isEmpty(HttpServletRequest req) {      
      try {
        String pathInfo = req.getPathInfo();
        pathInfo.substring(1);
        return false;
      } catch( NullPointerException error){
          return true;
      }
  
    }

}


// GET
// http://localhost:4949/api-restfull-plexus/quiz
// http://localhost:4949/api-restfull-plexus/quiz/id

//POST
// http://localhost:4949/api-restfull-plexus/quiz