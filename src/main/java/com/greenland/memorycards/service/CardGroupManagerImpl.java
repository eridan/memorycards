/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.service;

import com.greenland.memorycards.model.CardGroup;
import com.greenland.memorycards.repository.CardGroupDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author jurijspe
 */
public class CardGroupManagerImpl implements CardGroupManager{
    
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    private CardGroupDao cardGroupDao;

    public void setCardGroupDao(CardGroupDao cardGroupDao) {
        this.cardGroupDao = cardGroupDao;
    }
    
    private CardManager cardManager;

    public void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }

    @Override
    public List<CardGroup> getCardGroupsForUser(String email) {
        logger.info("Getting user groups");
        List<CardGroup> cardGroups = new ArrayList<CardGroup>();
        cardGroups = cardGroupDao.getCardGroupsForUser(email);
        logger.info("Populating groups with cards");
        for (CardGroup cardGroup : cardGroups) {
            cardGroup.setCardList(cardManager.getAllCardsForGroup(cardGroup.getId()));
        }
        return cardGroups;
    }

    @Override
    public void createNewCardGroupForUserId(int userId, CardGroup cardGroup) {
        cardGroupDao.createNewCardGroupForUserId(userId, cardGroup);
    }

    @Override
    public void deleteCardGroupWithId(Integer groupId) {
        cardGroupDao.deleteCardGroupWithId(groupId);
    }

    @Override
    public CardGroup getCardGroup(int cardGroupId) {
        CardGroup cardGroup = new CardGroup();
        cardGroup = cardGroupDao.getCardGroup(cardGroupId);
        cardGroup.setCardList(cardManager.getAllCardsForGroup(cardGroupId));
        return cardGroup;
    }

    @Override
    public void updateCardGroup(CardGroup cardGroup) {
        cardGroupDao.updateCardGroup(cardGroup);
    }
    
}
