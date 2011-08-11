/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;

import com.greenland.memorycards.model.User;
import junit.framework.Assert;
import com.greenland.memorycards.controller.LoginController;
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
    
//    Mockery context = new JUnit4Mockery();
    private LoginController controller = new LoginController();
    private static MockHttpServletRequest request;
    private static MockHttpServletResponse response;
    private User user;

    
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
        user = new User();
        user.setUserName("testEmail");
        user.setPassword("12345678");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void HomePage() throws Exception
    {
        request.setMethod("GET");
        request.addParameter("email", "testEmail");
        request.addParameter("password", "12345678");
        controller.setUser(user);
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("home", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        User controllerUser = (User)mav.getModel().get("user");
        Assert.assertNotNull(controllerUser);
        Assert.assertEquals("testEmail", controllerUser.getUserName());
        Assert.assertEquals("12345678", controllerUser.getPassword());
    }
    
    
}
