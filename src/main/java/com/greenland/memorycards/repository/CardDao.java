/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.repository;

import com.greenland.memorycards.model.Card;
import com.greenland.memorycards.model.CardGroup;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public interface CardDao {
    
    List<Card> getCardListForUser(String email);
    
}
