/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.service;

import com.greenland.memorycards.model.User;

/**
 *
 * @author jurijspe
 */
public class UserManagerImpl implements UserManager{

    @Override
    public User getUser(String email, String password) {
        User aUser = new User(email, password);
        return aUser;
    }
    
}
