/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jurijspe
 */
public class Card implements Serializable {
    
    private int id;
    private String question;
    private String questionCode;
    private String answer;
    private String answerCode;
    private Date creationDate;
    private Date updateDate;

    public Card(String question, String questionCode, String answer, String answerCode, Date creationDate, Date updateDate) {
        this.question = question;
        this.questionCode = questionCode;
        this.answer = answer;
        this.answerCode = answerCode;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }
    
    public Card() {}

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerCode() {
        return answerCode;
    }

    public void setAnswerCode(String answerCode) {
        this.answerCode = answerCode;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
}
