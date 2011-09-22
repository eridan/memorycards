/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.service;

import com.greenland.memorycards.model.Card;
import com.greenland.memorycards.repository.CardDao;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author jurijspe
 */
public class CardManagerImpl implements CardManager{
    
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    private CardDao cardDao;

    public void setCardDao(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    @Override
    public List<Card> getAllCardsForGroup(int id) {
//        logger.info("Getting cards for group id: "+id);
        return cardDao.getAllCardsForGroup(id);
    }

    @Override
    public Card getCardWithId(int cardId) {
        return cardDao.getCardWithId(cardId);
    }
    
    
}
