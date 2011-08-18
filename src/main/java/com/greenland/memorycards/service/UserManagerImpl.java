/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.service;

import com.greenland.memorycards.model.User;
import com.greenland.memorycards.repository.UserDao;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public class UserManagerImpl implements UserManager{
    
    private UserDao userDao;
    private CardGroupManager cardGroupManager;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setCardGroupManager(CardGroupManager cardGroupManager) {
        this.cardGroupManager = cardGroupManager;
    }

    @Override
    public User getUser(String email, String password) {
        User aUser = new User();
        aUser = userDao.getUser(email, password);
        aUser.setCardGroups(cardGroupManager.getCardGroupsForUser(email));
        return aUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    
    
}
