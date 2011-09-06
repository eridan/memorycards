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
import java.util.List;
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
public class CardGroupsController implements Controller{
    
    private CardGroupManager cardGroupManager;

    public void setCardGroupManager(CardGroupManager cardGroupManager) {
        this.cardGroupManager = cardGroupManager;
    }
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        logger.info("Card Group Manager");
        User appUser = (User)request.getSession().getAttribute("user");
        List<CardGroup> cardGroups = new ArrayList<CardGroup>();
        cardGroups = cardGroupManager.getCardGroupsForUser(appUser.getEmail());
        
        for (CardGroup cardGroup : cardGroups) {
            System.out.println(cardGroup.getGroupName());
        }
        return new ModelAndView("manageCardGroups", "cardGroups", cardGroups);
    }
    
}

