/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import models.Request;
import models.RequestItem;

/**
 *
 * @author christian larzep
 */
public class RequestDao implements IDao{
     private Connection userConn;
    private final String INSERT_REQUEST = "INSERT INTO request(date, description, hour, status, subject, system, adviser_user_id, client_user_id) VALUES(?,?,?,?,?,?,?,?)";
    private final String SELECT_CLIENT_REQUEST = "SELECT * FROM request WHERE client_user_id = ?";
    private final String SELECT_ADVISER_REQUEST = "SELECT * FROM request WHERE adviser_user_id = ?";
    private final String SELECT_REQUEST = "SELECT * FROM request WHERE request_id = ?";
    private final String SELECT_ALL = "SELECT * FROM request";
    private final String UPDATE_REQUEST = "UPDATE request SET status=?, adviser_user_id=? WHERE request_id=?";
    
    public RequestDao(){}
    
    public RequestDao(Connection userConn){
        this.userConn = userConn;
    }
    
    @Override
    public int insert(Object request) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int id = 0;
        try{
            Request r = (Request) request;
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = conn.prepareStatement(INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS); 
            int index = 1;
            stmt.setString(index++, r.getDate());
            stmt.setString(index++, r.getDescription());
            stmt.setString(index++, r.getHour()); 
            stmt.setString(index++, r.getStatus());
            stmt.setString(index++, r.getSubject());
            stmt.setString(index++, r.getSystem());
            stmt.setLong(index++, r.getIdAdviser());
            stmt.setLong(index++, r.getIdClient());
           
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next())
            {
              id = rs.getInt(1);
            }
        }finally{
            DBConnection.close(stmt);
            if(this.userConn == null){
                DBConnection.close(conn);
            }
        }
        return id;
    
    }

    @Override
    public Request update(Object request) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        Request req = (Request) request;

        try{
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = conn.prepareStatement(UPDATE_REQUEST);
            int index = 1;
            stmt.setString(index++, req.getStatus());
            stmt.setLong(index++, req.getIdAdviser());
            stmt.setLong(index++, req.getRequestId());
            stmt.executeUpdate();
        }finally{
            DBConnection.close(stmt);
            if(this.userConn == null){
                DBConnection.close(conn);
            }
        }
        return req;
    }

    @Override
    public int delete(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List select(int id, String by) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        RequestItem item;
        UserDao userdao = new UserDao();
        ResultSet rs = null;
        List<RequestItem> requests = new ArrayList<>();
        
        String query = getSelectQuery(by);
        try{
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            if(id != -1){
                stmt.setInt(1, id); 
            }
         
            rs = stmt.executeQuery();
            while(rs.next()){
                long idRequest = rs.getLong(1);
                String date = rs.getString(2);
                String description  = rs.getString(3);
                String hour  = rs.getString(4);
                String status = rs.getString(5);
                String subject = rs.getString(6);
                String  system = rs.getString(7);
                long idAdviser = rs.getLong(8);
                long idClient = rs.getLong(9);
               
                item = new RequestItem();
                item.setId_request(idRequest);
                item.setDate(date);
                item.setDescription(description);
                item.setHour(hour);
                item.setStatus(status);
                item.setSubject(subject);
                item.setSystem(system);
                item.setAdviser(userdao.selectUser(idAdviser));
                item.setClient(userdao.selectUser(idClient));
                    
                requests.add(item);
            }
        }finally{
            DBConnection.close(rs);
            DBConnection.close(stmt);
            if(this.userConn == null){
                DBConnection.close(conn);
            }
        }
        return requests;
    }
    
    public RequestItem searchRequest(long id) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        RequestItem result = null;
        ResultSet rs = null;
        try{
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = conn.prepareStatement(SELECT_REQUEST);
             stmt.setLong(1, id); 
            rs = stmt.executeQuery();
            while(rs.next()){
                long idRequest = rs.getLong(1);
                String date = rs.getString(2);
                String description  = rs.getString(3);
                String hour  = rs.getString(4);
                String status = rs.getString(5);
                String subject = rs.getString(6);
                String  system = rs.getString(7);
                long idAdviser = rs.getLong(8);
                long idClient = rs.getLong(9);

                UserDao userdao = new UserDao();
                result = new RequestItem();
                result.setId_request(idRequest);
                result.setDate(date);
                result.setDescription(description);
                result.setHour(hour);
                result.setStatus(status);
                result.setSubject(subject);
                result.setSystem(system);
                result.setAdviser(userdao.selectUser(idAdviser));
                result.setClient(userdao.selectUser(idClient));
      
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
          
    public String getSelectQuery(String by){
        String query = "INVALID";
         switch (by) {
             case "ADVISER":
                 query = SELECT_ADVISER_REQUEST;
                 break;
             case "CLIENT":
                 query = SELECT_CLIENT_REQUEST;
                 break;
             case "ALL":
                  query = SELECT_ALL;
                  break;
             default:
                 break;
         }
      return query;
         
    }
    
}
