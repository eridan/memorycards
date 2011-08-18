/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public class User implements Serializable{
    private int id;
    private String email;
    private String password;
    private String fName;
    private String lName;
    private List<CardGroup> cardGroups = new ArrayList<CardGroup>();
    
    public User() { }
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public List<CardGroup> getCardGroups() {
        return cardGroups;
    }

    public void setCardGroups(List<CardGroup> cardGroups) {
        this.cardGroups = cardGroups;
    }
    
    public String toString() {
        return "User ("+ id +", " + email +", " + password +", " + fName +", " + lName +")";
    }
    
}
