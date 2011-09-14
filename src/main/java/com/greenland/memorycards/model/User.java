/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    @Override
    public String toString() {
        return "User ("+ id +", " + email +", " + password +", " + fName +", " + lName +")";
    }
    
    // Not very elegant. Perhaps, there is a better way. TODO: Refactor
    @Override
    public boolean equals(Object otherUser) {

        boolean cardGroupsEqual = false;

        //check for self-comparison
        if (this == otherUser) {
            return true;
        }

        if (otherUser instanceof User) {
            User user = (User) otherUser;

            if (this.cardGroups != null && user.getCardGroups() != null) {
                CardGroup[] cardGroupArray = (CardGroup[]) this.cardGroups.toArray(new CardGroup[this.cardGroups.size()]);
                CardGroup[] otherCardGroupArray = (CardGroup[]) user.getCardGroups().toArray(new CardGroup[user.getCardGroups().size()]);
                cardGroupsEqual = Arrays.equals(cardGroupArray, otherCardGroupArray) ? true : false;
            } else if (this.cardGroups == null && user.getCardGroups() == null) {
                cardGroupsEqual = true;
            } else {
                cardGroupsEqual = false;
            }

            if ((this.id == user.getId())
                    && (this.email == null && user.getEmail() == null || this.email.equals(user.getEmail()))
                    && (this.fName == null && user.getfName() == null) || this.fName.equals(user.getfName())
                    && (this.lName == null && user.getlName() == null) || this.lName.equals(user.getlName())
                    && (cardGroupsEqual)) {
                return true;
            }
        }
        return false;
    }
}
