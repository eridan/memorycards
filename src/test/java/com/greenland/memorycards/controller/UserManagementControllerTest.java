/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;

import com.greenland.memorycards.model.User;
import com.greenland.memorycards.service.UserManager;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.springframework.mock.web.MockHttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.Assert;
import org.jmock.Expectations;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jurijspe
 */
public class UserManagementControllerTest {

    private static MockHttpServletRequest request;
    private static MockHttpServletResponse response;
    private UserManager userManager;
    private UserManagementController controller;
    private Mockery userManagerMock;

    public UserManagementControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        userManagerMock = new JUnit4Mockery();
        userManager = userManagerMock.mock(UserManager.class);
        controller = new UserManagementController();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        // define expectations for mock object
        userManagerMock.checking(new Expectations() {

            {
                User user = new User();
                User user1 = new User();
                User user2 = new User();
                User user3 = new User();
                user.setId(0);
                user1.setId(1);
                user2.setId(2);
                user3.setId(3);
                user.setEmail("email@mail.com");
                user1.setEmail("email@mail1.com");
                user2.setEmail("email@mail2.com");
                user3.setEmail("email@mail3.com");
                user.setPassword("password");
                user1.setPassword("password1");
                user2.setPassword("password2");
                user3.setPassword("password3");
                user.setfName("fName");
                user1.setfName("fName1");
                user2.setfName("fName2");
                user3.setfName("fName3");
                user.setlName("lName");
                user1.setlName("lName1");
                user2.setlName("lName2");
                user3.setlName("lName3");
                List<User> users = new ArrayList<User>();
                users.add(user);
                users.add(user1);
                users.add(user2);
                users.add(user3);

                allowing(userManager).getAllUsers();
                will(returnValue(users));
                allowing(userManager).getUser(0);
                will(returnValue(user));
                allowing(userManager).getUser(2);
                will(returnValue(user2));
            }
        });
        controller.setUserManager(userManager);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of testDisplayAllUsers method, of class UserManagementController.
     */
    @Test
    public void testDisplayAllUsers() throws Exception {

        System.out.println("Testing All DB users displayed");
        request.setMethod("POST");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageUsers", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = (HashMap<String, Object>) mav.getModel().get("model");
        List<User> users = (List<User>) model.get("userList");
        Assert.assertNotNull(users);
        Assert.assertEquals(4, users.size());
        Assert.assertEquals("fName2", users.get(2).getfName());
        userManagerMock.assertIsSatisfied();
    }

    /**
     * Test of testShowUserToEdit.
     */
    @Test
    public void testShowUserToEdit() throws Exception {

        System.out.println("Testing Edit user displayed");
        request.setMethod("POST");
        request.setParameter("edit", "0");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageUsers", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = (HashMap<String, Object>) mav.getModel().get("model");
        User user = (User) model.get("userToEdit");
        Assert.assertNotNull(user);
        Assert.assertEquals("email@mail.com", user.getEmail());
        Assert.assertEquals("password", user.getPassword());
        Assert.assertEquals("fName", user.getfName());
        Assert.assertEquals("lName", user.getlName());
        userManagerMock.assertIsSatisfied();
    }

    /**
     * Test of testShowUserToDelete.
     */
    @Test
    public void testShowUserToDelete() throws Exception {

        System.out.println("Testing Delete user displayed");
        request.setMethod("POST");
        request.setParameter("delete", "2");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageUsers", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = (HashMap<String, Object>) mav.getModel().get("model");
        User user = (User) model.get("userToDelete");
        Assert.assertNotNull(user);
        Assert.assertEquals("email@mail2.com", user.getEmail());
        Assert.assertEquals("password2", user.getPassword());
        Assert.assertEquals("fName2", user.getfName());
        Assert.assertEquals("lName2", user.getlName());
        userManagerMock.assertIsSatisfied();
    }

    /**
     * Test of testShowUserToCreate.
     */
    @Test
    public void testShowUserToCreate() throws Exception {

        System.out.println("Testing Create user form displayed");
        request.setMethod("POST");
        request.setParameter("create", "true");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageUsers", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = (HashMap<String, Object>) mav.getModel().get("model");
        User user = (User) model.get("userToCreate");
        Assert.assertNotNull(user);
        userManagerMock.assertIsSatisfied();
    }

    /**
     * Test of testEditUser.
     */
//    @Test
//    public void testEditUser() throws Exception {
//        userManagerMock = new JUnit4Mockery();
//        userManager = userManagerMock.mock(UserManager.class);
//        controller = new UserManagementController();
//        request = new MockHttpServletRequest();
//        response = new MockHttpServletResponse();
//
//        // define expectations for mock object
//        userManagerMock.checking(new Expectations() {
//
//            {
//                User user1 = new User();
//                user1.setId(1);
//                user1.setEmail("email@mail1.com");
//                user1.setPassword("password1");
//                user1.setfName("fName1");
//                user1.setlName("lName1");
//
//                User updatedUser = new User();
//                updatedUser.setEmail("abc@gmail.com");
//                updatedUser.setPassword("abc");
//                updatedUser.setfName("abcName");
//                updatedUser.setlName("abcLName");
//
//                allowing(userManager).combineUsers(user1, updatedUser);
//                will(returnValue(updatedUser));
//                allowing(userManager).updateUser(updatedUser);
//            }
//        });
//
//        controller.setUserManager(userManager);
//        
//        System.out.println("Testing Users actionupdate");
//        request.setMethod("POST");
//        request.setParameter("actionupdate", "1");
//        request.setParameter("email", "abc@gmail.com");
//        request.setParameter("password", "abc");
//        request.setParameter("userFName", "abcName");
//        request.setParameter("userLName", "abcLName");
//
//        ModelAndView mav = controller.handleRequest(request, response);
//        Assert.assertEquals("manageUsers", mav.getViewName());
//        Assert.assertNotNull(mav.getModel());
//        Map<String, Object> model = (HashMap<String, Object>) mav.getModel().get("model");
//        User user = (User) model.get("userToEdit");
//        Assert.assertNull(user);
//        userManagerMock.assertIsSatisfied();
//    }
}