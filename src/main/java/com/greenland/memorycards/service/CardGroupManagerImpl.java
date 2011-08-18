/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.service;

import com.greenland.memorycards.model.CardGroup;
import com.greenland.memorycards.repository.CardGroupDao;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public class CardGroupManagerImpl implements CardGroupManager{
    
    private CardGroupDao cardGroupDao;

    public void setCardGroupDao(CardGroupDao cardGroupDao) {
        this.cardGroupDao = cardGroupDao;
    }

    @Override
    public List<CardGroup> getCardGroupsForUser(String email) {
        return cardGroupDao.getCardGroupsForUser(email);
    }
    
}
