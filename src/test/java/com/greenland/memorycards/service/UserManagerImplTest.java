/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.service;

import com.greenland.memorycards.model.CardGroup;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import com.greenland.memorycards.model.User;
import com.greenland.memorycards.repository.UserDao;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jurijspe
 */
public class UserManagerImplTest {

    public UserManagerImplTest() {
    }
    private Mockery userDaoMock = new JUnit4Mockery();
    private Mockery cardGroupManagerMock = new JUnit4Mockery();
    private UserDao userDao = userDaoMock.mock(UserDao.class);
    private CardGroupManager cardGroupManager = cardGroupManagerMock.mock(CardGroupManager.class);
    private UserManagerImpl um = new UserManagerImpl();

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        um.setUserDao(userDao);
        um.setCardGroupManager(cardGroupManager);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getUser method, of class UserManagerImpl.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        // define expectations for mock object
        userDaoMock.checking(new Expectations() {
            {
                User aUser = new User();
                aUser.setEmail("test@mail.ru");
                aUser.setPassword("test");
                aUser.setfName("First Name");
                aUser.setlName("Last Name");
                oneOf(userDao).getUser("test@mail.ru", "test");
                will(returnValue(aUser));
            }
        });
        
        cardGroupManagerMock.checking(new Expectations() {
            {
                CardGroup group = new CardGroup();
                CardGroup group1 = new CardGroup();
                List<CardGroup> cardGroups = new ArrayList<CardGroup>();
                cardGroups.add(group);
                cardGroups.add(group1);
                oneOf(cardGroupManager).getCardGroupsForUser("test@mail.ru");
                will(returnValue(cardGroups));
            }
        });
        String email = "test@mail.ru";
        String password = "test";
        User user = um.getUser(email, password);
        assertNotNull(user);
        assertEquals("First Name", user.getfName());
        assertEquals("Last Name", user.getlName());
        assertEquals(2, user.getCardGroups().size());
    }

    /**
     * Test of getAllUsers method, of class UserManagerImpl.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        userDaoMock.checking(new Expectations() {
            {
                User aUser = new User();
                aUser.setEmail("test@mail.ru");
                aUser.setPassword("test");
                aUser.setfName("First Name");
                aUser.setlName("Last Name");
                List<User> users = new ArrayList<User>();
                users.add(aUser);
                oneOf(userDao).getAllUsers();
                will(returnValue(users));
            }
        });
        List<User> users = um.getAllUsers();
        int expResult = 1;
        assertEquals(expResult, users.size());
    }
}
