/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.repository;

import com.greenland.memorycards.model.CardGroup;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 *
 * @author jurijspe
 */
public class JdbcCardGroupDao extends SimpleJdbcDaoSupport implements CardGroupDao {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public List<CardGroup> getCardGroupsForUser(String email) {
        logger.info("DB: getting user's (" + email + ") Card Group List");
        List<CardGroup> userCardGroups = new ArrayList<CardGroup>();
        String sqlString =
                "Select GROUPS.* "
                + "FROM GROUPS, USERGROUP, USERS "
                + "WHERE USERS.ID=USERGROUP.USERID "
                + "AND USERGROUP.GROUPID=GROUPS.ID "
                + "AND USERS.EMAIL='" + email + "'";
        userCardGroups = getSimpleJdbcTemplate().query(sqlString, new CardGroupMapper());
        return userCardGroups;
    }

    @Override
    public void createNewCardGroupForUserId(int userId, CardGroup cardGroup) {
        logger.warn("Creating new Card Group on DB");
        
        CardGroup group = new CardGroup();
        String insertCardSQL = "INSERT INTO GROUPS "
                + "(groupname, description, creationDate, updateDate) values"
                + "(:groupname, :description, null, null)";

        String insertUserGroupSQL = "INSERT INTO USERGROUP "
                + "(userid, groupid) values"
                + "(:userId, :groupId)";
        try {
            getSimpleJdbcTemplate().update(insertCardSQL, new MapSqlParameterSource().addValue("groupname", cardGroup.getGroupName()).
                    addValue("description", cardGroup.getDescription()));
            
            group = getCardGroup(cardGroup.getGroupName());
            
            getSimpleJdbcTemplate().update(insertUserGroupSQL, new MapSqlParameterSource().addValue("userId", userId).
                    addValue("groupId", group.getId()));
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCardGroupWithId(Integer groupId) {
        logger.warn("Deleting Card Group from DB");
        MapSqlParameterSource mapping = new MapSqlParameterSource().addValue("id", groupId);
//        getSimpleJdbcTemplate().update(""
//                + "DELETE "
//                + "FROM GROUPS, USERGROUP, USERS "
//                + "WHERE USERS.ID=USERGROUP.USERID AND USERGROUP.GROUPID=GROUPS.ID AND USERS.ID=:id", mapping);
        getSimpleJdbcTemplate().update("Delete from GROUPCARD where groupid=:id", mapping);
        getSimpleJdbcTemplate().update("Delete from GROUPS where id=:id", mapping);
    }

    @Override
    public CardGroup getCardGroup(int cardGroupId) {
        logger.info("DB: Fetch Card Group with id: " + cardGroupId);
        CardGroup cardGroup = new CardGroup();
        try {
            cardGroup = (CardGroup) getSimpleJdbcTemplate().queryForObject(
                    "SELECT * FROM GROUPS "
                    + "WHERE id = '" + cardGroupId + "'", new CardGroupMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error("Card Group (id=" + cardGroupId + ") not found exception");
            return null;
        }
        return cardGroup;
    }

    @Override
    public CardGroup getCardGroup(String groupName) {
        logger.info("DB: Fetch Card Group with Name: " + groupName);
        CardGroup cardGroup = new CardGroup();
        try {
            cardGroup = (CardGroup) getSimpleJdbcTemplate().queryForObject(
                    "SELECT * FROM GROUPS "
                    + "WHERE groupname = '" + groupName + "'", new CardGroupMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error("Card Group (Group Name =" + groupName + ") not found exception");
            return null;
        }
        return cardGroup;
    }

    @Override
    public void updateCardGroup(CardGroup cardGroup) {
        logger.info("Updating Card Group on DB");
        String sql = "UPDATE GROUPS SET "
                + "groupname = :groupname, "
                + "description = :description "
                + "WHERE ID=:id";
        try {
            getSimpleJdbcTemplate().update(sql, new MapSqlParameterSource().addValue("groupname", cardGroup.getGroupName()).
                    addValue("description", cardGroup.getDescription()).
                    addValue("id", cardGroup.getId()));
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        }
    }

    private static class CardGroupMapper implements RowMapper<CardGroup> {

        @Override
        public CardGroup mapRow(ResultSet rs, int i) throws SQLException {
            CardGroup group = new CardGroup();
            group.setId(rs.getInt("id"));
            group.setGroupName(rs.getString("groupname"));
            group.setDescription(rs.getString("description"));
            group.setCreationDate(rs.getString("creationDate"));
            group.setUpdateDate(rs.getString("updateDate"));
            return group;
        }
    }
}
