/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.service;

import com.greenland.memorycards.model.User;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public interface UserManager {
    
    User getUser(String email, String password);
    List<User> getAllUsers();
}
