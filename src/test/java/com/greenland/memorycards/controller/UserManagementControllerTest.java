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

                oneOf(userManager).getAllUsers();
                will(returnValue(users));
                oneOf(userManager).getUser(0);
                will(returnValue(user));
                oneOf(userManager).getUser(1);
                will(returnValue(user1));
                oneOf(userManager).getUser(2);
                will(returnValue(user2));
                oneOf(userManager).getUser(3);
                will(returnValue(user3));
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
        Assert.assertEquals("lName", users.get(0).getlName());
        Assert.assertEquals("password3", users.get(3).getPassword());
        Assert.assertEquals("fName1", users.get(1).getfName());
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
        List<User> users = (List<User>) model.get("userList");
        Assert.assertNotNull(users);
        Assert.assertEquals(4, users.size());
        Assert.assertEquals("fName2", users.get(2).getfName());
        Assert.assertEquals("lName", users.get(0).getlName());
        Assert.assertEquals("password3", users.get(3).getPassword());
        Assert.assertEquals("fName1", users.get(1).getfName());
        User user = (User) model.get("userToEdit");
        Assert.assertNotNull(user);
        Assert.assertEquals("email@mail.com", user.getEmail());
        Assert.assertEquals("password", user.getPassword());
        Assert.assertEquals("fName", user.getfName());
        Assert.assertEquals("lName", user.getlName());
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
        List<User> users = (List<User>) model.get("userList");
        Assert.assertNotNull(users);
        Assert.assertEquals(4, users.size());
        Assert.assertEquals("fName2", users.get(2).getfName());
        Assert.assertEquals("lName", users.get(0).getlName());
        Assert.assertEquals("password3", users.get(3).getPassword());
        Assert.assertEquals("fName1", users.get(1).getfName());
        User user = (User) model.get("userToDelete");
        Assert.assertNotNull(user);
        Assert.assertEquals("email@mail2.com", user.getEmail());
        Assert.assertEquals("password2", user.getPassword());
        Assert.assertEquals("fName2", user.getfName());
        Assert.assertEquals("lName2", user.getlName());
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
        List<User> users = (List<User>) model.get("userList");
        Assert.assertNotNull(users);
        Assert.assertEquals(4, users.size());
        Assert.assertEquals("fName2", users.get(2).getfName());
        Assert.assertEquals("lName", users.get(0).getlName());
        Assert.assertEquals("password3", users.get(3).getPassword());
        Assert.assertEquals("fName1", users.get(1).getfName());
        User user = (User) model.get("userToCreate");
        Assert.assertNotNull(user);
    }

    @Test
    public void testUserActionCreate() throws Exception {

        userManagerMock.checking(new Expectations() {

            {
                User newUser = new User();
                newUser.setEmail("NewEmail@mail.com");
                newUser.setPassword("newpassw0rd");
                newUser.setfName("New First Name");
                newUser.setlName("New Last Name");
                //checking if Create Method was called
                oneOf(userManager).createNewUser(with(equal(newUser)));

            }
        });

        System.out.println("Testing if User IS Created");
        request.getSession().setAttribute("user", userManager.getUser(0));
        request.setParameter("actioncreate", "true");
        request.setParameter("email", "NewEmail@mail.com");
        request.setParameter("password", "newpassw0rd");
        request.setParameter("userFName", "New First Name");
        request.setParameter("userLName", "New Last Name");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageUsers", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = new HashMap<String, Object>();
        model = (HashMap<String, Object>) mav.getModel().get("model");
        // Checking if ALL Users are displayed
        List<User> users = (List<User>) model.get("userList");
        Assert.assertNotNull(users);
        Assert.assertEquals(4, users.size());
        Assert.assertEquals("fName2", users.get(2).getfName());
        Assert.assertEquals("lName", users.get(0).getlName());
        User user = new User();
        user = (User) model.get("userToEdit");
        Assert.assertNull(user);
        user = (User) model.get("userToDelete");
        Assert.assertNull(user);
        user = (User) model.get("userToCreate");
        Assert.assertNull(user);
    }

    @Test
    public void testUserActionUpdate() throws Exception {

        userManagerMock.checking(new Expectations() {

            {
                User user2 = new User();
                user2.setId(2);
                user2.setEmail("email@mail2.com");
                user2.setPassword("password2");
                user2.setfName("fName2");
                user2.setlName("lName2");
                User newUser = new User();
                newUser.setId(2);
                newUser.setEmail("NewEmail@mail.com");
                newUser.setPassword("password2");
                newUser.setfName("New First Name");
                newUser.setlName("New Last Name");
                //checking if Create Method was called
                oneOf(userManager).updateUser(with(equal(newUser)));
                oneOf(userManager).combineUsers(with(equal(user2)), with(equal(newUser)));
                will(returnValue(newUser));

            }
        });

        System.out.println("Testing if User IS Updated");
        request.getSession().setAttribute("user", userManager.getUser(0));
        request.setParameter("actionupdate", "2");
        request.setParameter("email", "NewEmail@mail.com");
        request.setParameter("userFName", "New First Name");
        request.setParameter("userLName", "New Last Name");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageUsers", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = new HashMap<String, Object>();
        model = (HashMap<String, Object>) mav.getModel().get("model");
        // Checking if ALL Users are displayed
        List<User> users = (List<User>) model.get("userList");
        Assert.assertNotNull(users);
        Assert.assertEquals(4, users.size());
        Assert.assertEquals("fName2", users.get(2).getfName());
        Assert.assertEquals("lName", users.get(0).getlName());
        User user = new User();
        user = (User) model.get("userToEdit");
        Assert.assertNull(user);
        user = (User) model.get("userToDelete");
        Assert.assertNull(user);
        user = (User) model.get("userToCreate");
        Assert.assertNull(user);
    }

    @Test
    public void testUserActionDelete() throws Exception {

        userManagerMock.checking(new Expectations() {

            {
                //checking if Create Method was called
                oneOf(userManager).deleteUserWithId(with(equal(3)));

            }
        });

        System.out.println("Testing if User IS Deleted");
        request.getSession().setAttribute("user", userManager.getUser(0));
        request.setParameter("actiondelete", "3");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageUsers", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = new HashMap<String, Object>();
        model = (HashMap<String, Object>) mav.getModel().get("model");
        // Checking if ALL Users are displayed
        List<User> users = (List<User>) model.get("userList");
        Assert.assertNotNull(users);
        Assert.assertEquals(4, users.size());
        Assert.assertEquals("fName2", users.get(2).getfName());
        Assert.assertEquals("lName", users.get(0).getlName());
        User user = new User();
        user = (User) model.get("userToEdit");
        Assert.assertNull(user);
        user = (User) model.get("userToDelete");
        Assert.assertNull(user);
        user = (User) model.get("userToCreate");
        Assert.assertNull(user);
    }
}