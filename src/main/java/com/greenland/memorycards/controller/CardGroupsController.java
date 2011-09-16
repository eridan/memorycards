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
        boolean create = false;
        boolean delete = false;
        boolean update = false;
        boolean addNewGroup = false;

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
            if (actionName.equalsIgnoreCase("addGroup")) {
                addNewGroup = true;
            }
//            logger.info("Action name: " + actionName);
        }

        // Displaying forms

        if (actionName.equalsIgnoreCase("edit")) {
            logger.info("Editing cardGroup (id=" + request.getParameter(actionName) + ")");
            int cardGroupId = Integer.valueOf(request.getParameter(actionName));
            CardGroup cardGroupToEdit = new CardGroup();
            cardGroupToEdit = cardGroupManager.getCardGroupWithId(cardGroupId);
            model.put("cardGroupToEdit", (CardGroup) cardGroupToEdit);
        }

        if (actionName.equalsIgnoreCase("delete")) {
            logger.info("Deleting cardGroup (id=" + request.getParameter(actionName) + ")");
            int cardGroupId = Integer.valueOf(request.getParameter(actionName));
            CardGroup cardGroupToDelete = new CardGroup();
            cardGroupToDelete = cardGroupManager.getCardGroupWithId(cardGroupId);
            model.put("cardGroupToDelete", (CardGroup) cardGroupToDelete);
        }

        if (actionName.equalsIgnoreCase("create")) {
            logger.info("Creating New cardGroup");
            model.put("cardGroupToCreate", new CardGroup());
        }

        // Form actions

        if (update) {
            logger.info("Updating ... ");
            CardGroup cardGroupToBeUpdated = new CardGroup();
            cardGroupToBeUpdated = cardGroupManager.getCardGroupWithId(Integer.valueOf(request.getParameter("actionupdate")));

            // Could use formBacking Object instead. TODO: Refactor
            CardGroup formCardGroup = new CardGroup();
            formCardGroup.setId(cardGroupToBeUpdated.getId());
            formCardGroup.setGroupName((String) request.getParameter("groupName"));
            formCardGroup.setDescription((String) request.getParameter("description"));
            cardGroupManager.updateCardGroup(formCardGroup);
        }

        if (delete) {
            logger.warn("Deleting ...");
            logger.warn("CardGroup to delete: " + model.get("cardGroupToDelete"));
            logger.warn("ID = " + Integer.valueOf(request.getParameter("actiondelete")));
            cardGroupManager.deleteCardGroupWithId(Integer.valueOf(request.getParameter("actiondelete")));
        }

        if (create) {
            logger.info("Creating ...");
            CardGroup formCardGroup = new CardGroup();
            formCardGroup.setGroupName((String) request.getParameter("groupName"));
            formCardGroup.setDescription((String) request.getParameter("description"));
            cardGroupManager.createNewCardGroupForUserId(appUser.getId(), formCardGroup);
        }

        if (addNewGroup) {
            logger.info("Redirecting to Manage CardGroups ...");
        }

        List<CardGroup> cardGroups = new ArrayList<CardGroup>();
        cardGroups = cardGroupManager.getCardGroupsForUser(appUser.getEmail());
        model.put("cardGroups", cardGroups);
        return new ModelAndView("manageCardGroups", "model", model);
    }
}
