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
    private int quizId;
    private String contact;
    private int idAdviser;
    private String timeWaiting;
    private String timeToSolve;
    private String knowledge;
    private String satisfaction;
    
    public Quiz(){}

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
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
        return timeWaiting;
    }

    public void setTimeWaiting(String timeWaiting) {
        this.timeWaiting = timeWaiting;
    }

    public String getTimeToSolve() {
        return timeToSolve;
    }

    public void setTimeToSolve(String timeToSolve) {
        this.timeToSolve = timeToSolve;
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
