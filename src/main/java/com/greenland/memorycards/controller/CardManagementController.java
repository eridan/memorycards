/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;

import com.greenland.memorycards.model.Card;
import com.greenland.memorycards.service.CardManager;
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
public class CardManagementController implements Controller{

    private int groupId;
    private CardManager cardManager;

    public void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Card Management Controller");

        // The below code smells. TODO: Refactor

        Map<String, Object> model = new HashMap<String, Object>();
        boolean create = false;
        boolean delete = false;
        boolean update = false;

        String actionName = "";
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            actionName = (String) e.nextElement();
            if (actionName.equalsIgnoreCase("manageCards")) {
                groupId=Integer.valueOf(request.getParameter("manageCards"));
            }
        }

        List<Card> cardList = new ArrayList<Card>();
        cardList = cardManager.getAllCardsForGroup(groupId);
        model.put("cardList", cardList);
        return new ModelAndView("manageCards", "model", model);
    }
}
