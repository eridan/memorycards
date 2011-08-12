/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;


import com.greenland.memorycards.model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
    
    private User user = new User();

    public void setUser(User user) {
        this.user = user;
    }
    
    protected final Log logger = LogFactory.getLog(getClass()); 
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        logger.info("Returning home page");
        String name = request.getParameter("email");
        String password = request.getParameter("password");
        
        if (name.equals("dima_ir@mail.ru") && password.equals("12345678")) {
            user.setUserName("Dima P :)");
        } 
        if (name.equals("elkrumina@gmail.com") && password.equals("12345678")) {
            user.setUserName("Sonyshka :)");
        }
        
        return new ModelAndView("home", "user", user);
    }
    
}
