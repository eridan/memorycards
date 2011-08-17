/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.service;

import com.greenland.memorycards.model.User;
import com.greenland.memorycards.repository.UserDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public class UserManagerImpl implements UserManager{
    
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUser(String email, String password) {
        return userDao.getUser(email, password);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    
    
}
