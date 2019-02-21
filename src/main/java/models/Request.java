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
public class Request {
    private long request_id;
    private String date;
    private String hour;
    private String subject;
    private String description;
    private String system;
    private String status;
    private long adviser_user_id;
    private long client_user_id;

    public Request(){}
    
    public long getRequestId() {
        return request_id;
    }

    public void setRequestId(long requestId) {
        this.request_id = requestId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getIdAdviser() {
        return adviser_user_id;
    }

    public void setIdAdviser(long idAdviser) {
        this.adviser_user_id = idAdviser;
    }

    public long getIdClient() {
        return client_user_id;
    }

    public void setIdClient(long idClient) {
        this.client_user_id = idClient;
    }
}
