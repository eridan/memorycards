/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public class User {
    private int id;
    private String userName;
    private String password;
    private List<String> cardList = new ArrayList<String>();

    public List<String> getCardList() {
        return cardList;
    }

    public void setCardList(List<String> cardList) {
        this.cardList = cardList;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
