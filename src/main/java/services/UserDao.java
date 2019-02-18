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
import models.User;

/**
 *
 * @author christian larzep
 */
public class UserDao {
    private Connection userConn;
    private final String INSERT_USER = "INSERT INTO user(cell, email, password, r_social, rfc, role, tel, username) VALUES(?,?,?,?,?,?,?,?)";
    private final String FIND_USER ="SELECT * FROM user WHERE password = ? AND email = ?";

   public UserDao(){}
    
    public UserDao(Connection userConn){
        this.userConn = userConn;
    }
    
    public User login(User user) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        User result = null;
        ResultSet rs = null;
        try{
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = conn.prepareStatement(FIND_USER);
             stmt.setString(1, user.getPassword());
             stmt.setString(2, user.getEmail()); 
            rs = stmt.executeQuery();
            while(rs.next()){
                long idUser = rs.getLong(1);
                long cell = rs.getLong(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                String r_soscial = rs.getString(5);
                String rfc = rs.getString(6);
                String role = rs.getString(7);
                long tel = rs.getLong(8);
                String username = rs.getString(9);

                result = new User();
                result.setUserId(idUser);
                result.setCell(cell);
                result.setEmail(email);
                result.setPassword(password);
                result.setR_social(r_soscial);
                result.setRfc(rfc);
                result.setRole(role);
                result.setTel(tel);
                result.setUsername(username);
      
            }
            
        }finally{
            DBConnection.close(rs);
            DBConnection.close(stmt);
            if(this.userConn == null){
                DBConnection.close(conn);
            }
        }
       return result;
    }
    
    public boolean register(User user) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean registred = false;
        try{
            User u = (User) user;
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = conn.prepareStatement(INSERT_USER); 
            int index = 1;
            stmt.setLong(index++, u.getCell());
            stmt.setString(index++, u.getEmail());
            stmt.setString(index++, u.getPassword());
            stmt.setString(index++, u.getR_social());
            stmt.setString(index++, u.getRfc());
            stmt.setString(index++, u.getRole());
            stmt.setLong(index++, u.getTel());
            stmt.setString(index++, u.getUsername()); 
           
            stmt.executeUpdate();
            
            registred = true;
        }finally{
            DBConnection.close(stmt);
            if(this.userConn == null){
                DBConnection.close(conn);
            }
        }
        return registred;
    }
}
