/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.repository;

import com.greenland.memorycards.model.User;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public interface UserDao {
    
    User getUser(String email, String password);
    List<User> getAllUsers();
    
}
