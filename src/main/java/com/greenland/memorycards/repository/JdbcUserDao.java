/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.repository;

import com.greenland.memorycards.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 *
 * @author jurijspe
 */
public class JdbcUserDao extends SimpleJdbcDaoSupport implements UserDao {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public User getUser(String email, String password) {
        logger.info("DB: Fetch user with email: " + email + " and password: " + password);
        return (User) getSimpleJdbcTemplate().queryForObject(
			"SELECT id, email, password FROM USERS " +
			"WHERE email = '" + email + "' and password ='" + password + "'", new UserMapper());
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("DB: Fetch All Users");
        List<User> allUsers = getSimpleJdbcTemplate().query(
                "select id, email, password from USERS",
                new UserMapper());
        return allUsers;
    }

    private static class UserMapper implements ParameterizedRowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
}
