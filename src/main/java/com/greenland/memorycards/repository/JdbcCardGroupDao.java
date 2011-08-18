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
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 *
 * @author jurijspe
 */
public class JdbcCardGroupDao extends SimpleJdbcDaoSupport implements CardGroupDao{
    
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public List<CardGroup> getCardGroupsForUser(String email) {
        logger.info("DB: getting user's ("+email+") Card Group List");
        List<CardGroup> userCardGroups = new ArrayList<CardGroup>();
        String sqlString = 
                "Select GROUPS.* "
                + "FROM GROUPS, USERGROUP, USERS "
                + "WHERE USERS.ID=USERGROUP.USERID "
                + "AND USERGROUP.GROUPID=GROUPS.ID "
                + "AND USERS.EMAIL='"+email+"'";
        userCardGroups = getSimpleJdbcTemplate().query(sqlString, new CardGroupMapper());
        return userCardGroups;
    }
    
    private static class CardGroupMapper implements ParameterizedRowMapper<CardGroup> {

        @Override
        public CardGroup mapRow(ResultSet rs, int i) throws SQLException {
            CardGroup group = new CardGroup();
            group.setId(rs.getInt("id"));
            group.setGroupName(rs.getString("groupname"));
            group.setDescription(rs.getString("description"));
            return group;
        }
        
    }
}
