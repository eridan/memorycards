/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;

import org.jmock.Expectations;
import com.greenland.memorycards.repository.UserDao;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.Mockery;
import com.greenland.memorycards.model.User;
import junit.framework.Assert;
import com.greenland.memorycards.controller.LoginController;
import com.greenland.memorycards.repository.JdbcUserDao;
import com.greenland.memorycards.service.UserManager;
import com.greenland.memorycards.service.UserManagerImpl;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpServletRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.junit.Assert.*;

/**
 *
 * @author jurijspe
 */
public class LoginControllerTest {

    private Mockery context = new JUnit4Mockery();
    //mock object : ContactDAO (using JMock)
    UserDao userDao = context.mock(UserDao.class);
    private LoginController controller = new LoginController();
    private static MockHttpServletRequest request;
    private static MockHttpServletResponse response;
    private UserManagerImpl um = new UserManagerImpl();

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
        controller.setUserManager(um);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void validLoginControllerTest() throws Exception {
        // define expectations for mock object
        context.checking(new Expectations() {

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
        context.checking(new Expectations() {

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
}
