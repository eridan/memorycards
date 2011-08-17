/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.service;

import com.greenland.memorycards.model.Card;
import com.greenland.memorycards.model.CardGroup;
import com.greenland.memorycards.repository.CardDao;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public class CardManagerImpl implements CardManager{
    
    private CardDao cardDao;

    public void setCardDao(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    @Override
    public List<Card> getCardListForUser(String email) {
        return cardDao.getCardListForUser(email);
    }

    @Override
    public List<CardGroup> getCardGroupsForUser(String email) {
        return cardDao.getCardGroupsForUser(email);
    }
    
}
