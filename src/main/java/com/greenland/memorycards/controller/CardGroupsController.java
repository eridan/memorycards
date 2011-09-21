/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;

import com.greenland.memorycards.model.CardGroup;
import com.greenland.memorycards.model.User;
import com.greenland.memorycards.service.CardGroupManager;
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

/**
 *
 * @author jurijspe
 */
public class CardGroupsController implements Controller {

    private CardGroupManager cardGroupManager;

    public void setCardGroupManager(CardGroupManager cardGroupManager) {
        this.cardGroupManager = cardGroupManager;
    }
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Card Group Manager Controller");

        // The below code smells. TODO: Refactor

        User appUser = (User) request.getSession().getAttribute("user");

        Map<String, Object> model = new HashMap<String, Object>();
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String parameterName = (String) e.nextElement();
            if (parameterName.equalsIgnoreCase("action")) {
                executeAction(request, getActionName(request), appUser.getId());
            }
            if (parameterName.equalsIgnoreCase("form")) {
                displayForm(getFormName(request), getCardGroupId(request), model);
            }
        }

        // Displaying forms

//        if (actionName.equalsIgnoreCase("edit")) {
//            logger.info("Editing cardGroup (id=" + request.getParameter(actionName) + ")");
//            int cardGroupId = Integer.valueOf(request.getParameter(actionName));
//            CardGroup cardGroupToEdit = new CardGroup();
//            cardGroupToEdit = cardGroupManager.getCardGroupWithId(cardGroupId);
//            model.put("cardGroupToEdit", (CardGroup) cardGroupToEdit);
//        }

//        if (actionName.equalsIgnoreCase("delete")) {
//            logger.info("Deleting cardGroup (id=" + request.getParameter(actionName) + ")");
//            int cardGroupId = Integer.valueOf(request.getParameter(actionName));
//            CardGroup cardGroupToDelete = new CardGroup();
//            cardGroupToDelete = cardGroupManager.getCardGroupWithId(cardGroupId);
//            model.put("cardGroupToDelete", (CardGroup) cardGroupToDelete);
//        }
//
//        if (actionName.equalsIgnoreCase("create")) {
//            logger.info("Creating New cardGroup");
//            model.put("cardGroupToCreate", new CardGroup());
//        }
//
//        // Form actions
//
//        if (update) {
//            logger.info("Updating ... ");
//            CardGroup cardGroupToBeUpdated = new CardGroup();
//            cardGroupToBeUpdated = cardGroupManager.getCardGroupWithId(Integer.valueOf(request.getParameter("actionupdate")));
//
//            // Could use formBacking Object instead. TODO: Refactor
//            CardGroup formCardGroup = new CardGroup();
//            formCardGroup.setId(cardGroupToBeUpdated.getId());
//            formCardGroup.setGroupName((String) request.getParameter("groupName"));
//            formCardGroup.setDescription((String) request.getParameter("description"));
//            cardGroupManager.updateCardGroup(formCardGroup);
//        }
//
//        if (delete) {
//            logger.warn("Deleting ...");
//            cardGroupManager.deleteCardGroupWithId(Integer.valueOf(request.getParameter("actiondelete")));
//        }
//
//        if (create) {
//            logger.info("Creating ...");
//            CardGroup formCardGroup = new CardGroup();
//            formCardGroup.setGroupName((String) request.getParameter("groupName"));
//            formCardGroup.setDescription((String) request.getParameter("description"));
//            cardGroupManager.createNewCardGroupForUserId(appUser.getId(), formCardGroup);
//        }

        List<CardGroup> cardGroups = new ArrayList<CardGroup>();
        cardGroups = cardGroupManager.getCardGroupsForUser(appUser.getEmail());
        model.put("cardGroups", cardGroups);
        return new ModelAndView("manageCardGroups", "model", model);
    }

    private String getFormName(HttpServletRequest request) {
        return (String) request.getParameter("form");
    }

    private String getActionName(HttpServletRequest request) {
        return (String) request.getParameter("action");
    }

    private void executeAction(HttpServletRequest request, String actionName, int cardGroupId) {
        if (actionName.equalsIgnoreCase("create")) {
            createNewCardGroup(request, cardGroupId);
        }
        if (actionName.equalsIgnoreCase("delete")) {
            deleteCardGroupWithId(getCardGroupId(request));
        }
        if (actionName.equalsIgnoreCase("update")) {
            updateCardGroupWithId(getCardGroupId(request), request);
        }
    }

    private void createNewCardGroup(HttpServletRequest request, int userId) {
        logger.info("Creating ...");
        CardGroup formCardGroup = new CardGroup();
        formCardGroup.setGroupName((String) request.getParameter("groupName"));
        formCardGroup.setDescription((String) request.getParameter("description"));
        cardGroupManager.createNewCardGroupForUserId(userId, formCardGroup);
    }

    private void deleteCardGroupWithId(int cardGroupId) {
        logger.warn("Deleting ...");
        cardGroupManager.deleteCardGroupWithId(cardGroupId);
    }

    private void updateCardGroupWithId(int cardGroupId, HttpServletRequest request) {
        logger.info("Updating ... ");
        CardGroup cardGroupToBeUpdated = new CardGroup();
        cardGroupToBeUpdated = cardGroupManager.getCardGroupWithId(cardGroupId);

        // Could use formBacking Object instead. TODO: Refactor
        CardGroup formCardGroup = new CardGroup();
        formCardGroup.setId(cardGroupToBeUpdated.getId());
        formCardGroup.setGroupName((String) request.getParameter("groupName"));
        formCardGroup.setDescription((String) request.getParameter("description"));
        cardGroupManager.updateCardGroup(formCardGroup);
    }

    private void displayForm(String formName, int cardGroupId, Map<String, Object> model) {
        logger.info("Displaying form " + formName);
        if (formName.equalsIgnoreCase("edit")) {
            getCardGroupToEdit(cardGroupId, model);
        }
        if (formName.equalsIgnoreCase("delete")) {
            getCardGroupToDelete(cardGroupId, model);
        }
        if (formName.equalsIgnoreCase("create")) {
            logger.info("Creating New cardGroup");
            model.put("cardGroupToCreate", new CardGroup());
        }
    }

    private void getCardGroupToEdit(int cardGroupId, Map<String, Object> model) {
        logger.info("Show cardGroup to Edit (id=" + cardGroupId + ")");
        CardGroup cardGroupToEdit = cardGroupManager.getCardGroupWithId(cardGroupId);
        model.put("cardGroupToEdit", (CardGroup) cardGroupToEdit);
    }

    private void getCardGroupToDelete(int cardGroupId, Map<String, Object> model) {
        logger.info("Deleting cardGroup (id=" + cardGroupId + ")");
        CardGroup cardGroupToDelete = cardGroupManager.getCardGroupWithId(cardGroupId);
        model.put("cardGroupToDelete", (CardGroup) cardGroupToDelete);
    }

    private int getCardGroupId(HttpServletRequest request) {
        int cardGroupId = -1;
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String parameterName = (String) e.nextElement();
            if (parameterName.equalsIgnoreCase("id")) {
                cardGroupId = Integer.valueOf((String) request.getParameter("id"));
            }
        }
        return cardGroupId;
    }
}
