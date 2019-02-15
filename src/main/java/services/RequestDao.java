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
import java.util.ArrayList;
import models.Request;

/**
 *
 * @author christian larzep
 */
public class RequestDao implements IDao{
     private Connection userConn;
    private final String INSERT_REQUEST = "INSERT INTO request(date, description, hour, status, subject, system, adviser_user_id, client_user_id) VALUES(?,?,?,?,?,?,?,?,?)";
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
        int rows = 0;
        try{
            Request r = (Request) request;
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = conn.prepareStatement(INSERT_REQUEST); 
            int index = 1;
            stmt.setString(index++, r.getDate());
            stmt.setString(index++, r.getDescription());
            stmt.setString(index++, r.getHour()); 
            stmt.setString(index++, r.getStatus());
            stmt.setString(index++, r.getSubject());
            stmt.setString(index++, r.getSystem());
            stmt.setLong(index++, r.getIdAdviser());
            stmt.setLong(index++, r.getIdClient());
            
            System.out.println("Executing query: "+INSERT_REQUEST);
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
    public int update(Object request) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        Request req = (Request) request;

        try{
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = conn.prepareStatement(UPDATE_REQUEST);
            int index = 1;
            stmt.setString(index++, req.getStatus());
            stmt.setLong(index++, req.getIdAdviser());
            stmt.setLong(index++, req.getRequestId());
            rows = stmt.executeUpdate();
        }finally{
            DBConnection.close(stmt);
            if(this.userConn == null){
                DBConnection.close(conn);
            }
        }
        return rows;
    }

    @Override
    public int delete(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List select(int id, String by) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        Request request;
        ResultSet rs = null;
        List<Request> requests = new ArrayList<>();
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
               
                request = new Request();
                request.setRequestId(idRequest);
                request.setDate(date);
                request.setDescription(description);
                request.setHour(hour);
                request.setStatus(status);
                request.setSubject(subject);
                request.setSystem(system);
                request.setIdAdviser(idAdviser);
                request.setIdClient(idClient);
                    
                requests.add(request);
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
          
    public String getSelectQuery(String by){
        String query = "INVALID";
         switch (by) {
             case "ADVISER":
                 query = SELECT_ADVISER_REQUEST;
                 break;
             case "CLIENT":
                 query = SELECT_CLIENT_REQUEST;
                 break;
             case "REQUEST":
                 query = SELECT_REQUEST;
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
