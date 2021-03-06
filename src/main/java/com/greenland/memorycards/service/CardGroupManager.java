/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.service;

import com.greenland.memorycards.model.CardGroup;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public interface CardGroupManager {
    
    List<CardGroup> getCardGroupsForUser (String email);

    public CardGroup getCardGroupWithId(int cardGroupId);

    public void updateCardGroup(CardGroup updatedCardGroup);

    public void deleteCardGroupWithId(Integer valueOf);

    public void createNewCardGroupForUserId(int userId, CardGroup formCardGroup);
}
