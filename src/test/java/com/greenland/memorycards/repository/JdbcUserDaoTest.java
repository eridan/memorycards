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
public class JdbcUserDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    public JdbcUserDaoTest() {
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-config.xml"};
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
//        super.deleteFromTables(new String[] {"users"});
//        super.executeSqlScript("file:db/test/del_utest_user.sql", true);
    }

    /**
     * Test of getUser method, of class JdbcUserDao.
     */
    @Test
    public void testGetUser() {
        System.out.println("Test user who exists on the db");
        String email = "test@mail.ru";
        String password = "test";
        User dbUser = userDao.getUser(email, password);
        String expPassword = "test";
        assertEquals(expPassword, dbUser.getPassword());
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

    /**
     * Test of createNewUser method, of class JdbcUserDao.
     */
    @Test
    public void testCreateNewUser() {
        System.out.println("createNewUser");
        User user = new User();
        user.setEmail("utestUser@yahoo.com");
        user.setPassword("utest");
        user.setfName("uTest fName");
        user.setlName("uTest lName");
        userDao.createNewUser(user);
        User dbUser = userDao.getUser("utestUser@yahoo.com", "utest");
        assertNotNull(dbUser);
        assertEquals("utestUser@yahoo.com", dbUser.getEmail());
        assertEquals("utest", dbUser.getPassword());
        assertEquals("uTest fName", dbUser.getfName());
        assertEquals("uTest lName", dbUser.getlName());
    }

    /**
     * Test of updateUser method, of class JdbcUserDao.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        testCreateNewUser();
        int expId = userDao.getUser("utestUser@yahoo.com", "utest").getId();
        User user = new User();
        user.setId(expId);
        user.setEmail("newUtestUser@yahoo.com");
        user.setPassword("utest");
        user.setfName("fName");
        user.setlName("lName");
        userDao.updateUser(user);
        User nullUser = userDao.getUser("utestUser@yahoo.com", "utest");
        assertNull(nullUser);
        User dbUser = userDao.getUser("newUtestUser@yahoo.com", "utest");
        assertEquals("newUtestUser@yahoo.com", dbUser.getEmail());
        assertEquals("utest", dbUser.getPassword());
        assertEquals("fName", dbUser.getfName());
        assertEquals("lName", dbUser.getlName());
        // Making sure its the same user
        assertEquals(expId, dbUser.getId());
    }
    /**
     * Test of deleteUserWithId method, of class JdbcUserDao.
     */
    @Test
    public void testDeleteUserWithId() {
        System.out.println("deleteUserWithId");
        testCreateNewUser();
        int id = userDao.getUser("utestUser@yahoo.com", "utest").getId();
        userDao.deleteUserWithId(id);
        User dbUser = userDao.getUser("utestUser@yahoo.com", "utest");
        assertNull(dbUser);
    }
}
