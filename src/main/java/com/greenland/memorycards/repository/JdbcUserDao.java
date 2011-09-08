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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
        User dbUser = new User();
        try {
            dbUser = (User) getSimpleJdbcTemplate().queryForObject(
                    "SELECT * FROM USERS "
                    + "WHERE email = '" + email + "' and password ='" + password + "'", new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error("User (" + email + ") not found exception");
            return null;
        }
        return dbUser;
    }

    @Override
    public User getUser(int userId) {
        logger.info("DB: Fetch user with id: " + userId);
        User dbUser = new User();
        try {
            dbUser = (User) getSimpleJdbcTemplate().queryForObject(
                    "SELECT * FROM USERS "
                    + "WHERE id = '" + userId + "'", new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error("User (id=" + userId + ") not found exception");
            return null;
        }
        return dbUser;
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("DB: Fetch All Users");
        List<User> allUsers = getSimpleJdbcTemplate().query(
                "select * from USERS",
                new UserMapper());
        return allUsers;
    }

    @Override
    public void updateUser(User user) {
        logger.info("Updating user on DB");
        String sql ="UPDATE USERS SET "
                + "EMAIL = :email, "
                + "PASSWORD = :password, "
                + "FNAME = :fName, "
                + "LNAME = :lName "
                + "WHERE ID=:id";
        getSimpleJdbcTemplate().update(sql, new MapSqlParameterSource().
                addValue("email", user.getEmail()).
                addValue("password", user.getPassword()).
                addValue("fName", user.getfName()).
                addValue("lName", user.getlName()).
                addValue("id", user.getId()));
    }

    @Override
    public void deleteUserWithId(Integer userId) {
        logger.warn("Deleting user from DB");
        MapSqlParameterSource mapping = new MapSqlParameterSource().addValue("id", userId);
//        getSimpleJdbcTemplate().update(""
//                + "DELETE "
//                + "FROM GROUPS, USERGROUP, USERS "
//                + "WHERE USERS.ID=USERGROUP.USERID AND USERGROUP.GROUPID=GROUPS.ID AND USERS.ID=:id", mapping);
        getSimpleJdbcTemplate().update("Delete from USERGROUP where userid=:id", mapping);
        getSimpleJdbcTemplate().update("Delete from USERS where id=:id", mapping);
    }

    @Override
    public void createNewUser(User formUser) {
        logger.info("Creating user on DB");
        String sql ="INSERT INTO USERS "
                + "(email, password, fName, lName) values"
                + "(:email, :password, :fName, :lName)";

        getSimpleJdbcTemplate().update(sql, new MapSqlParameterSource().
                addValue("email", formUser.getEmail()).
                addValue("password", formUser.getPassword()).
                addValue("fName", formUser.getfName()).
                addValue("lName", formUser.getlName()).
                addValue("id", formUser.getId()));
    }

    private static class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setfName(rs.getString("fName"));
            user.setlName(rs.getString("lName"));
            return user;
        }
    }
}
