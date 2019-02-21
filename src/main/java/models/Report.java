/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author christian larzep
 */
public class Report {
    private long reportId;
    private long client_user_id;
    private long adviser_user_id;
    private String system;
    private String init_date;
    private String end_date;
    private String status;
    private String time;
    private float amount;
    private String description;
    
    public Report(){}

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    public long getIdClient() {
        return client_user_id;
    }

    public void setIdClient(long idClient) {
        this.client_user_id = idClient;
    }

    public long getIdAdviser() {
        return adviser_user_id;
    }

    public void setIdAdviser(long idAdviser) {
        this.adviser_user_id = idAdviser;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getInitDate() {
        return init_date;
    }

    public void setInitDate(String initDate) {
        this.init_date = initDate;
    }

    public String getEndDate() {
        return end_date;
    }

    public void setEndDate(String endDate) {
        this.end_date = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
