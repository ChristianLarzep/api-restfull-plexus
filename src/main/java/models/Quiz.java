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
public class Quiz {
    private int quiz_id;
    private String contact;
    private int idAdviser;
    private String time_waiting;
    private String time_to_solve;
    private String knowledge;
    private String satisfaction;
    
    public Quiz(){}

    public int getQuizId() {
        return quiz_id;
    }

    public void setQuizId(int quizId) {
        this.quiz_id = quizId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getIdAdviser() {
        return idAdviser;
    }

    public void setIdAdviser(int idAdviser) {
        this.idAdviser = idAdviser;
    }

    public String getTimeWaiting() {
        return time_waiting;
    }

    public void setTimeWaiting(String timeWaiting) {
        this.time_waiting = timeWaiting;
    }

    public String getTimeToSolve() {
        return time_to_solve;
    }

    public void setTimeToSolve(String timeToSolve) {
        this.time_to_solve = timeToSolve;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }
}
