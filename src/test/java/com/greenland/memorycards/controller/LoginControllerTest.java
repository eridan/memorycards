/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;

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
    
    private LoginController controller = new LoginController();
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
        UserManagerImpl um = new UserManagerImpl();
        um.setUserDao(new JdbcUserDao());
        controller.setUserManager(um);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void HomePage() throws Exception
    {
        request.setMethod("POST");
        request.addParameter("email", "test@mail.ru");
        request.addParameter("password", "test");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("home", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        User controllerUser = (User)mav.getModel().get("user");
        Assert.assertNotNull(controllerUser);
        Assert.assertEquals("test@mail.ru", controllerUser.getUserName());
        Assert.assertEquals("test", controllerUser.getPassword());
    }
    
    
}
