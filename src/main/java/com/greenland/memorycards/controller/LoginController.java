/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;


import com.greenland.memorycards.model.User;

import com.greenland.memorycards.service.UserManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author jurijspe
 */
public class LoginController implements Controller{
    
    private UserManager userManager;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    
    protected final Log logger = LogFactory.getLog(getClass()); 
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        logger.info("Returning home page");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User appUser = userManager.getUser(email, password);
                
        return new ModelAndView("home", "user", appUser);
    }
    
}
