/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.model;

import com.greenland.memorycards.util.DateTimeConverter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public class CardGroup implements Serializable {

    private int id;
    private String groupName;
    private String description;
    private List<Card> cardList;
    private boolean defaultGrp;
    private String creationDate;
    private String updateDate;

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public String getCreationDate() {
        return DateTimeConverter.parseDate(this.creationDate);
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdateDate() {
        return DateTimeConverter.parseDate(this.updateDate);
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isDefaultGrp() {
        return defaultGrp;
    }

    public void setDefaultGrp(boolean defaultGrp) {
        this.defaultGrp = defaultGrp;
    }

    @Override
    public String toString() {
        return "Card Group (" + id + ", " + groupName + ", " + description + ", " + creationDate + ", " + updateDate + ")";
    }

    // Not very elegant. Perhaps, there is a better way. TODO: Refactor
    @Override
    public boolean equals(Object otherCardGroup) {

        boolean cardListsEqual = false;

        //check for self-comparison
        if (this == otherCardGroup) {
            return true;
        }

        if (otherCardGroup instanceof CardGroup) {
            CardGroup cardGroup = (CardGroup) otherCardGroup;

            if (this.cardList != null && cardGroup.getCardList() != null) {
                Card[] cardListArray = (Card[]) this.cardList.toArray(new Card[this.cardList.size()]);
                Card[] otherListArray = (Card[]) cardGroup.getCardList().toArray(new Card[cardGroup.getCardList().size()]);
                cardListsEqual = Arrays.equals(cardListArray, otherListArray) ? true : false;
            } else if (this.cardList == null && cardGroup.getCardList() == null) {
                cardListsEqual = true;
            } else {
                cardListsEqual = false;
            }

            if ((this.id == cardGroup.getId())
                    && (this.groupName == null && cardGroup.getGroupName() == null || this.groupName.equals(cardGroup.getGroupName()))
                    && (this.description == null && cardGroup.getDescription() == null) || this.description.equals(cardGroup.getDescription())
                    && (this.creationDate == null && cardGroup.getCreationDate() == null) || this.creationDate.equals(cardGroup.getCreationDate())
                    && (this.updateDate == null && cardGroup.getUpdateDate() == null || this.updateDate.equals(cardGroup.getUpdateDate()))
                    && (cardListsEqual)) {
                return true;
            }
        }
        return false;
    }
}
