/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.repository;

import com.greenland.memorycards.model.CardGroup;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public interface CardGroupDao {

    public List<CardGroup> getCardGroupsForUser(String email);

    public void createNewCardGroup(CardGroup cardGroup);

    public void deleteCardGroupWithId(Integer groupId);

    public CardGroup getCardGroup(int cardGroupId);

    public void updateCardGroup(CardGroup cardGroup);

}
