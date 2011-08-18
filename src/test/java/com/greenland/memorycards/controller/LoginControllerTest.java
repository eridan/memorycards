/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;

import com.greenland.memorycards.model.Card;
import com.greenland.memorycards.model.CardGroup;
import org.jmock.Expectations;
import com.greenland.memorycards.repository.UserDao;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.Mockery;
import com.greenland.memorycards.model.User;
import com.greenland.memorycards.repository.CardGroupDao;
import com.greenland.memorycards.service.CardGroupManagerImpl;
import junit.framework.Assert;
import com.greenland.memorycards.service.UserManagerImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpServletRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jurijspe
 */
public class LoginControllerTest {

    private Mockery userDaoMock = new JUnit4Mockery();
    private Mockery cardGroupDaoMock = new JUnit4Mockery();
    
    //mock object : ContactDAO (using JMock)
    private UserDao userDao = userDaoMock.mock(UserDao.class);
    private CardGroupDao cardGroupDao = cardGroupDaoMock.mock(CardGroupDao.class);
    
    private LoginController controller = new LoginController();
    private UserManagerImpl um = new UserManagerImpl();
    private CardGroupManagerImpl cgm = new CardGroupManagerImpl();
    private static MockHttpServletRequest request;
    private static MockHttpServletResponse response;

    public LoginControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        um.setUserDao(userDao);
        cgm.setCardGroupDao(cardGroupDao);
        um.setCardGroupManager(cgm);
        controller.setUserManager(um);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void validLoginControllerTest() throws Exception {
        // define expectations for mock object
        userDaoMock.checking(new Expectations() {

            {
                User aUser = new User();
                aUser.setEmail("test@mail.ru");
                aUser.setPassword("test");
                oneOf(userDao).getUser("test@mail.ru", "test");
                will(returnValue(aUser));
            }
        });

        System.out.println("Testing with valid data: user exists on the database");
        request.setMethod("POST");
        request.addParameter("email", "test@mail.ru");
        request.addParameter("password", "test");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("home", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        User controllerUser = (User) mav.getModel().get("user");
        Assert.assertNotNull(controllerUser);
        System.out.println(controllerUser);
        Assert.assertEquals("test@mail.ru", controllerUser.getEmail());
        Assert.assertEquals("test", controllerUser.getPassword());
    }
    
    @Test
    public void invalidLoginControllerTest() throws Exception {
        // define expectations for mock object
        userDaoMock.checking(new Expectations() {

            {
                User aUser = new User();
                aUser.setEmail("test@mail.ru");
                aUser.setPassword("test");
                oneOf(userDao).getUser("invalidUser@mail.ru", "invalid");
                will(returnValue(null));
            }
        });

        System.out.println("Testing with invalid data: user doesnt exist on the database");
        request.setMethod("POST");
        request.addParameter("email", "invalidUser@mail.ru");
        request.addParameter("password", "invalid");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("home", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        User controllerUser = (User) mav.getModel().get("user");
        Assert.assertNull(controllerUser);
    }
    
    @Test
    public void getUserCardsTest() throws Exception {
        // define expectations for mock object
        cardGroupDaoMock.checking(new Expectations() {

            {
                Card card = new Card("2+2=?", "int 2+2", "4", "int 4", new Date(), new Date());
                Card card1 = new Card("2+2=?", "int 2+2", "4", "int 4", new Date(), new Date());
                Card card2 = new Card("2+2=?", "int 2+2", "4", "int 4", new Date(), new Date());
                Card card3 = new Card("2+2=?", "int 2+2", "4", "int 4", new Date(), new Date());
                Card card4 = new Card("2+2=?", "int 2+2", "4", "int 4", new Date(), new Date());
                List<Card> cardList = new ArrayList<Card>();
                cardList.add(card);
                cardList.add(card1);
                cardList.add(card2);
                cardList.add(card3);
                cardList.add(card4);
                CardGroup cardGroup = new CardGroup();
                cardGroup.setCadList(cardList);
                CardGroup cardGroup1 = new CardGroup();
                cardGroup1.setCadList(cardList);
                List<CardGroup> cardGroups = new ArrayList<CardGroup>();
                cardGroups.add(cardGroup);
                cardGroups.add(cardGroup1);
                oneOf(cardGroupDao).getCardGroupsForUser("test@mail.ru");
                will(returnValue(cardGroups));
            }
        });
        
        um.setUserDao(userDao);
        cgm.setCardGroupDao(cardGroupDao);
        um.setCardGroupManager(cgm);
        controller.setUserManager(um);

        System.out.println("Testing User Groups");
        request.setMethod("POST");
        request.addParameter("email", "test@mail.ru");
        request.addParameter("password", "test");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("home", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        User controllerUser = (User) mav.getModel().get("user");
        System.out.println(controllerUser);
        Assert.assertEquals("test@mail.ru", controllerUser.getEmail());
        Assert.assertEquals("test", controllerUser.getPassword());
        Assert.assertEquals(2, controllerUser.getCardGroups());
        Assert.assertEquals(5, controllerUser.getCardGroups().get(0).getCadList().size());
    }
}
