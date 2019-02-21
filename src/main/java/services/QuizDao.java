/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Quiz;

/**
 *
 * @author christian larzep
 */
public class QuizDao implements IDao{
    private Connection userConn;
    private final String INSERT_QUIZ = "INSERT INTO quiz(contact, knowledge, satisfaction, time_to_solve, time_waiting, adviser_user_id) VALUES(?,?,?,?,?,?)";
    private final String SELECT_QUIZ = "SELECT * FROM quiz WHERE adviser_user_id = ?";
    private final String SELECT_ALL = "SELECT * FROM quiz";

    public QuizDao(){}
    
    public QuizDao(Connection userConn){
        this.userConn = userConn;
    }
    
    @Override
    public int insert(Object quiz) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try{
            Quiz q = (Quiz) quiz;
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = conn.prepareStatement(INSERT_QUIZ); 
            int index = 1;
            stmt.setString(index++, q.getContact()); 
            stmt.setString(index++, q.getKnowledge());
            stmt.setString(index++, q.getSatisfaction());
            stmt.setString(index++, q.getTimeToSolve());
            stmt.setString(index++, q.getTimeWaiting());
            stmt.setInt(index++, q.getIdAdviser());  
            System.out.println("Executing query: "+INSERT_QUIZ);
            rows = stmt.executeUpdate();
            System.out.println("Affected records: "+rows);
        }finally{
            DBConnection.close(stmt);
            if(this.userConn == null){
                DBConnection.close(conn);
            }
        }
        return rows;
    }

    @Override
    public Object update(Object quiz) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Object quiz) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List select(int id, String by) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        Quiz quiz;
        ResultSet rs = null;
        List<Quiz> quizes = new ArrayList<>();
        String query = getQuery(by);
        try{
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            if(id != -1){
                stmt.setInt(1, id); 
            }
            rs = stmt.executeQuery();
            while(rs.next()){
                int idQuiz = rs.getInt(1);
                String contact = rs.getString(2);
                String knowledge = rs.getString(3);
                String satisfaction = rs.getString(4);
                String time_to_solve = rs.getString(5);
                String time_waiting = rs.getString(6);
                int adviser_user_id = rs.getInt(7);

                quiz = new Quiz();
                quiz.setQuizId(idQuiz);
                quiz.setContact(contact);
                quiz.setKnowledge(knowledge);
                quiz.setSatisfaction(satisfaction);
                quiz.setTimeToSolve(time_to_solve);
                quiz.setTimeWaiting(time_waiting);
                quiz.setIdAdviser(adviser_user_id);
               
                quizes.add(quiz);
            }
        }finally{
            DBConnection.close(rs);
            DBConnection.close(stmt);
            if(this.userConn == null){
                DBConnection.close(conn);
            }
        }
        return quizes;
    }
    
    public String getQuery(String role){
        String query = "";
        if(role.equals("QUIZ")){
            query = SELECT_QUIZ;
        } else if(role.equals("ALL")){
            query = SELECT_ALL;
        }
      return query;
         
    }
    
}
