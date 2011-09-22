/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;

import com.greenland.memorycards.model.Card;
import com.greenland.memorycards.service.CardGroupManager;
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
public class CardManagementController implements Controller {
    
    private static int groupId;

    private CardManager cardManager;
    private CardGroupManager cardGroupManager;

    public void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Card Management Controller");

        Map<String, Object> model = new HashMap<String, Object>();
        String parameterName = "";
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            parameterName = (String) e.nextElement();
            if (parameterName.equalsIgnoreCase("form")) {
                displayForm(getFormName(request), request, model);
            }
        }
        List<Card> cardList = new ArrayList<Card>();
        cardList = cardManager.getAllCardsForGroup(groupId);
        model.put("cardList", cardList);
        return new ModelAndView("manageCards", "model", model);
    }

    private void setCardGroupId(HttpServletRequest request) {
        String parameter = "";
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            parameter = (String) e.nextElement();
            if (parameter.equalsIgnoreCase("groupId")) {
                groupId = Integer.valueOf(request.getParameter("groupId"));
            }
        }
    }

    private void displayForm(String formName, HttpServletRequest request, Map<String, Object> model) {
        if (formName.equals("allCardsForGroup")) {
            setCardGroupId(request);
        }
        if (formName.equals("edit")) {
            getCardToEdit(getCardId(request), model);
        }
    }

    private String getFormName(HttpServletRequest request) {
        return (String) request.getParameter("form");
    }

    private void getCardToEdit(int cardGroupId, Map<String, Object> model) {
        System.out.println("Adding Edit card to the model");
        model.put("cardToEdit", (Card)cardManager.getCardWithId(cardGroupId));
    }

    private int getCardId(HttpServletRequest request) {
        int id = 0;
        String parameter = "";
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            parameter = (String) e.nextElement();
            if (parameter.equalsIgnoreCase("id")) {
                id = Integer.valueOf(request.getParameter("id"));
            }
        }
        return id;
    }
}
