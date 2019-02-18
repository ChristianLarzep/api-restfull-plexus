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
import models.Report;

/**
 *
 * @author christian larzep
 */
public class ReportDao implements IDao{
    private Connection userConn;
    private final String INSERT_REPORT = "INSERT INTO report(amount, description, end_date, init_date, status, system, time, adviser_user_id, client_user_id) VALUES(?,?,?,?,?,?,?,?,?)";
    private final String SELECT_CLIENT_REPORT = "SELECT * FROM report WHERE client_user_id = ?";
    private final String SELECT_ADVISER_REPORT = "SELECT * FROM report WHERE adviser_user_id = ?";
    private final String SELECT_ALL = "SELECT * FROM report";

    public ReportDao(){}
    
    public ReportDao(Connection userConn){
        this.userConn = userConn;
    }

    @Override
    public int insert(Object report) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try{
            Report r = (Report) report;
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = conn.prepareStatement(INSERT_REPORT); 
            int index = 1;
            stmt.setFloat(index++, r.getAmount());
            stmt.setString(index++, r.getDescription());
            stmt.setString(index++, r.getEndDate()); 
            stmt.setString(index++, r.getInitDate());
            stmt.setString(index++, r.getStatus());
            stmt.setString(index++, r.getSystem());
            stmt.setString(index++, r.getTime());
            stmt.setLong(index++, r.getIdAdviser());
            stmt.setLong(index++, r.getIdClient());
            
            System.out.println("Executing query: "+INSERT_REPORT);
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
    public int update(Object report) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int delete(Object report) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List select(int id, String by) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        Report report;
        ResultSet rs = null;
        List<Report> reports = new ArrayList<>();
        String query = getQuery(by);
        try{
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            if(id != -1){
                stmt.setInt(1, id); 
            }
            rs = stmt.executeQuery();
            while(rs.next()){
                int idReport = rs.getInt(1);
                float amount  = rs.getFloat(2);
                String description = rs.getString(3);
                String endDate = rs.getString(4);
                String  initDate = rs.getString(5);
                String  status = rs.getString(6);
                String system = rs.getString(7);
                String time = rs.getString(8);
                long adviserId = rs.getLong(9);
                long clientId  = rs.getLong(10);

                report = new Report();
                report.setReportId(idReport);
                report.setAmount(amount);
                report.setDescription(description);
                report.setEndDate(endDate);
                report.setInitDate(initDate);
                report.setStatus(status);
                report.setSystem(system);
                report.setTime(time);
                report.setIdAdviser(adviserId);
                report.setIdClient(clientId);
               
                reports.add(report);
            }
        }finally{
            DBConnection.close(rs);
            DBConnection.close(stmt);
            if(this.userConn == null){
                DBConnection.close(conn);
            }
        }
        return reports;
    }
    
    private String getQuery(String by){
        switch (by) {
            case "ADVISER":
                return SELECT_ADVISER_REPORT;
            case "CLIENT":
                return SELECT_CLIENT_REPORT;
            case "ALL":
                return SELECT_ALL;
            default:
                break;
        }
        
        return "INVALID";  
    }
}
