/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.repository;

import com.greenland.memorycards.model.Card;
import com.greenland.memorycards.model.CardGroup;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 *
 * @author jurijspe
 */
public class JdbcCardDao extends SimpleJdbcDaoSupport implements CardDao{

    @Override
    public List<Card> getCardListForUser(String email) {
        List<Card> cardList = new ArrayList<Card>();
        return cardList;
    }

    @Override
    public List<CardGroup> getCardGroupsForUser(String email) {
        List<CardGroup> cardGroupList = new ArrayList<CardGroup>();
        return cardGroupList;
    }
    
}
