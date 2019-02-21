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
public class RequestItem {
    private long request_id;
    private String date;
    private String hour;
    private String subject;
    private String description;
    private String system;
    private String status;
    private User adviser;
    private User client;

    public RequestItem(){}

    public long getId_request() {
        return request_id;
    }

    public void setId_request(long id_request) {
        this.request_id = id_request;
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

    public User getAdviser() {
        return adviser;
    }

    public void setAdviser(User adviser) {
        this.adviser = adviser;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
    
}
