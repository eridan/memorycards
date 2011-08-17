/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.repository;

import com.greenland.memorycards.model.User;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jurijspe
 */
public class JdbcUserDaoTest extends AbstractTransactionalDataSourceSpringContextTests{
    
    public JdbcUserDaoTest() {
    }
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] {"classpath:test-config.xml"};
    }
    
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.deleteFromTables(new String[] {"users"});
        super.executeSqlScript("file:db/load_users.sql", true);
    }
    
    

    /**
     * Test of getUser method, of class JdbcUserDao.
     */
    @Test
    public void testGetUser() {
        System.out.println("Test user who exists on the db");
        String email = "dima_ir@mail.ru";
        String password = "12345678";
        User result = userDao.getUser(email, password);
        String expResult = "12345678";
        assertEquals(expResult, result.getPassword());
    }
    
    @Test
    public void testInvalidUser() {
        System.out.println("Test user who doesnt exist on the db");
        String email = "invalidUser@mail.ru";
        String password = "invalid";
        User result = userDao.getUser(email, password);
        assertNull(result);
    }

    /**
     * Test of getAllUsers method, of class JdbcUserDao.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        List result = userDao.getAllUsers();
        int usersInDB = 4;
        assertEquals(usersInDB, result.size());
    }
}
