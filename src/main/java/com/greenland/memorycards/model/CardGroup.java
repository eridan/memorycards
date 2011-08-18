/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jurijspe
 */
public class CardGroup implements Serializable{
    
    private int id;
    private String groupName;
    private String description;
    private List<Card> cadList;
    private Date creationDate;
    private Date updateDate;

    public List<Card> getCadList() {
        return cadList;
    }

    public void setCadList(List<Card> cadList) {
        this.cadList = cadList;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
    
}
