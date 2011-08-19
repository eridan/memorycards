/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.service;

import com.greenland.memorycards.model.Card;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public interface CardManager {

    List<Card> getAllCardsForGroup(int id);
}
