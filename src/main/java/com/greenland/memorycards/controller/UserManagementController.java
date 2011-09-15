/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;

/**
 *
 * @author jurijspe
 */
import com.greenland.memorycards.model.User;

import com.greenland.memorycards.service.UserManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class UserManagementController implements Controller {

    private User user;
    private UserManager userManager;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // The below code smells. TODO: Refactor
        
        logger.info("User Management Controller");
        Map<String, Object> model = new HashMap<String, Object>();
        boolean create = false;
        boolean delete = false;
        boolean update = false;

        String actionName = "";
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            actionName = (String) e.nextElement();
            if (actionName.equalsIgnoreCase("actioncreate")) {
                create = true;
            }
            if (actionName.equalsIgnoreCase("actiondelete")) {
                delete = true;
            }
            if (actionName.equalsIgnoreCase("actionupdate")) {
                update = true;
            }
//            logger.info("Action name: " + actionName);
        }

        // Displaying forms

        if (actionName.equalsIgnoreCase("edit")) {
            logger.info("Editing user (id=" + request.getParameter(actionName) + ")");
            int userId = Integer.valueOf(request.getParameter(actionName));
            User userToEdit = new User();
            userToEdit = userManager.getUser(userId);
            model.put("userToEdit", (User) userToEdit);
        }

        if (actionName.equalsIgnoreCase("delete")) {
            logger.info("Deleting user (id=" + request.getParameter(actionName) + ")");
            int userId = Integer.valueOf(request.getParameter(actionName));
            User userToDelete = new User();
            userToDelete = userManager.getUser(userId);
            model.put("userToDelete", (User) userToDelete);
        }

        if (actionName.equalsIgnoreCase("create")) {
            logger.info("Creating New user");
            model.put("userToCreate", new User());
        }

        // Form actions

        if (update) {
            logger.info("Updating ... ");
            User userToBeUpdated = new User();
            userToBeUpdated = userManager.getUser(Integer.valueOf(request.getParameter("actionupdate")));

            // Could use formBacking Object instead. TODO: Refactor
            User formUser = new User();
            formUser.setEmail((String) request.getParameter("email"));
            formUser.setPassword((String) request.getParameter("password"));
            formUser.setfName((String) request.getParameter("userFName"));
            formUser.setlName((String) request.getParameter("userLName"));
            User updatedUser = new User();
            updatedUser = userManager.combineUsers(userToBeUpdated, formUser);
            userManager.updateUser(updatedUser);
        }

        if (delete) {
            logger.info("Deleting ...");
            userManager.deleteUserWithId(Integer.valueOf(request.getParameter("actiondelete")));
        }

        if (create) {
            logger.info("Creating ...");
            User formUser = new User();
            formUser.setEmail((String) request.getParameter("email"));
            formUser.setPassword((String) request.getParameter("password"));
            formUser.setfName((String) request.getParameter("userFName"));
            formUser.setlName((String) request.getParameter("userLName"));
            userManager.createNewUser(formUser);
        }

        // Display all USERS
        user = (User) request.getSession().getAttribute("user");
        
        List<User> userList = new ArrayList<User>();
        userList = userManager.getAllUsers();
        model.put("userList", userList);
        return new ModelAndView("manageUsers", "model", model);
    }
}