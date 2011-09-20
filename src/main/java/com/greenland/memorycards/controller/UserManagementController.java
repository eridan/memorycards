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

    private UserManager userManager;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("User Management Controller");
        Map<String, Object> model = new HashMap<String, Object>();
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String parameterName = (String) e.nextElement();
            if (parameterName.equalsIgnoreCase("action")) {
                executeAction(request, getActionName(request));
            }
            if (parameterName.equalsIgnoreCase("form")) {
                displayForm(getFormName(request), getUserId(request), model);
            }
        }

        // Display all USERS
        List<User> userList = userManager.getAllUsers();
        model.put("userList", userList);
        return new ModelAndView("manageUsers", "model", model);
    }

    private String getFormName(HttpServletRequest request) {
        return (String) request.getParameter("form");
    }

    private String getActionName(HttpServletRequest request) {
        return (String) request.getParameter("action");
    }

    private void executeAction(HttpServletRequest request, String actionName) {
        if (actionName.equalsIgnoreCase("create")) {
            createNewUser(request);
        }
        if (actionName.equalsIgnoreCase("delete")) {
            deleteUserWithId(getUserId(request));
        }
        if (actionName.equalsIgnoreCase("update")) {
            updateUserWithId(getUserId(request), request);
        }
    }

    private void createNewUser(HttpServletRequest request) {
        logger.info("Creating ...");
        // Could use formBacking Object instead. TODO: Refactor
        User formUser = new User();
        formUser.setEmail((String) request.getParameter("email"));
        formUser.setPassword((String) request.getParameter("password"));
        formUser.setfName((String) request.getParameter("userFName"));
        formUser.setlName((String) request.getParameter("userLName"));
        userManager.createNewUser(formUser);
    }

    private void deleteUserWithId(int userId) {
        logger.info("Deleting ...");
        userManager.deleteUserWithId(userId);
    }

    private void updateUserWithId(int userId, HttpServletRequest request) {
        logger.info("Updating ... ");
        User userToBeUpdated = userManager.getUser(userId);

        // Could use formBacking Object instead. TODO: Refactor
        User formUser = new User();
        formUser.setEmail((String) request.getParameter("email"));
        formUser.setPassword((String) request.getParameter("password"));
        formUser.setfName((String) request.getParameter("userFName"));
        formUser.setlName((String) request.getParameter("userLName"));
        User updatedUser = userManager.combineUsers(userToBeUpdated, formUser);
        userManager.updateUser(updatedUser);
    }

    private void displayForm(String formName, int userId, Map<String, Object> model) {
        logger.info("Displaying form " + formName);
        if (formName.equalsIgnoreCase("edit")) {
            getUserToEdit(userId, model);
        }
        if (formName.equalsIgnoreCase("delete")) {
            getUserToDelete(userId, model);
        }
        if (formName.equalsIgnoreCase("create")) {
            logger.info("Show Create New User Form");
            model.put("userToCreate", new User());
        }
    }

    private void getUserToEdit(int userId, Map<String, Object> model) {
        logger.info("Show user to Edit (id=" + userId + ")");
        User userToEdit = userManager.getUser(userId);
        model.put("userToEdit", (User) userToEdit);
    }

    private void getUserToDelete(int userId, Map<String, Object> model) {
        logger.info("Show user to delete (id=" + userId + ")");
        User userToDelete = userManager.getUser(userId);
        model.put("userToDelete", (User) userToDelete);
    }

    private int getUserId(HttpServletRequest request) {
        int userId = -1;
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String parameterName = (String) e.nextElement();
            if (parameterName.equalsIgnoreCase("id")) {
                userId = Integer.valueOf((String) request.getParameter("id"));
            }
        }
        return userId;
    }
}