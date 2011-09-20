/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;

import com.greenland.memorycards.model.Card;
import com.greenland.memorycards.model.CardGroup;
import com.greenland.memorycards.model.User;
import com.greenland.memorycards.service.CardGroupManager;
import com.greenland.memorycards.service.UserManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.springframework.mock.web.MockHttpServletRequest;
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
public class CardGroupsControllerTest {

    private Mockery cardGroupManagerMock = new JUnit4Mockery();
    private Mockery userManagerMock = new JUnit4Mockery();
    private CardGroupManager cardGroupManager = cardGroupManagerMock.mock(CardGroupManager.class);
    private UserManager userManager = userManagerMock.mock(UserManager.class);
    private CardGroupsController controller = new CardGroupsController();
    private static MockHttpServletRequest request;
    private static MockHttpServletResponse response;

    public CardGroupsControllerTest() {
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

        userManagerMock.checking(new Expectations() {

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
                List<Card> cardList1 = new ArrayList<Card>();
                cardList1.add(card);
                cardList1.add(card1);
                cardList1.add(card2);
                CardGroup cardGroup = new CardGroup();
                cardGroup.setGroupName("name1");
                cardGroup.setCardList(cardList);
                CardGroup cardGroup1 = new CardGroup();
                cardGroup1.setGroupName("name2");
                cardGroup1.setCardList(cardList1);
                List<CardGroup> cardGroups = new ArrayList<CardGroup>();
                cardGroups.add(cardGroup);
                cardGroups.add(cardGroup1);
                User aUser = new User();
                aUser.setCardGroups(cardGroups);
                aUser.setEmail("test@mail.ru");
                oneOf(userManager).getUser(0);
                will(returnValue(aUser));
            }
        });

        cardGroupManagerMock.checking(new Expectations() {

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
                List<Card> cardList1 = new ArrayList<Card>();
                cardList1.add(card);
                cardList1.add(card1);
                cardList1.add(card2);
                CardGroup cardGroup = new CardGroup();
                cardGroup.setId(1);
                cardGroup.setGroupName("name1");
                cardGroup.setDescription("Description");
                cardGroup.setCardList(cardList);
                CardGroup cardGroup1 = new CardGroup();
                cardGroup1.setId(2);
                cardGroup1.setGroupName("name2");
                cardGroup1.setCardList(cardList1);
                List<CardGroup> cardGroups = new ArrayList<CardGroup>();
                cardGroups.add(cardGroup);
                cardGroups.add(cardGroup1);
                oneOf(cardGroupManager).getCardGroupsForUser("test@mail.ru");
                will(returnValue(cardGroups));
                oneOf(cardGroupManager).getCardGroupWithId(1);
                will(returnValue(cardGroup));
                oneOf(cardGroupManager).getCardGroupWithId(2);
                will(returnValue(cardGroup1));
            }
        });

        controller.setCardGroupManager(cardGroupManager);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of handleRequest method, of class CardGroupsController.
     */
    @Test
    public void testHandleRequest() throws Exception {

        System.out.println("handleRequest");
        request.getSession().setAttribute("user", userManager.getUser(0));
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageCardGroups", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = new HashMap<String, Object>();
        model = (HashMap<String, Object>) mav.getModel().get("model");
        List<CardGroup> cardGroups = (ArrayList<CardGroup>) model.get("cardGroups");
        Assert.assertNotNull(cardGroups);
        Assert.assertEquals(2, cardGroups.size());
        Assert.assertEquals(5, cardGroups.get(0).getCardList().size());
        Assert.assertEquals("name1", cardGroups.get(0).getGroupName());
        Assert.assertEquals(3, cardGroups.get(1).getCardList().size());
        Assert.assertEquals("name2", cardGroups.get(1).getGroupName());
    }

    @Test
    public void testShowCardGroupToEdit() throws Exception {

        System.out.println("Testing if Card Group To Edit is shown");
        request.getSession().setAttribute("user", userManager.getUser(0));
        request.setParameter("edit", "1");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageCardGroups", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = new HashMap<String, Object>();
        model = (HashMap<String, Object>) mav.getModel().get("model");
        List<CardGroup> cardGroups = (ArrayList<CardGroup>) model.get("cardGroups");
        // Checking if all user CardGroups are displayed
        Assert.assertNotNull(cardGroups);
        Assert.assertEquals(2, cardGroups.size());
        Assert.assertEquals(5, cardGroups.get(0).getCardList().size());
        Assert.assertEquals("name1", cardGroups.get(0).getGroupName());
        Assert.assertEquals(3, cardGroups.get(1).getCardList().size());
        Assert.assertEquals("name2", cardGroups.get(1).getGroupName());
        CardGroup group = new CardGroup();
        group = (CardGroup) model.get("cardGroupToEdit");
        Assert.assertNotNull(group);
        Assert.assertEquals(1, group.getId());
        Assert.assertEquals("name1", group.getGroupName());
        Assert.assertEquals("Description", group.getDescription());
        Assert.assertEquals(5, group.getCardList().size());
    }

    @Test
    public void testShowCardGroupToDelete() throws Exception {

        System.out.println("Testing if Card Group To Delete is shown");
        request.getSession().setAttribute("user", userManager.getUser(0));
        request.setParameter("delete", "1");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageCardGroups", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = new HashMap<String, Object>();
        model = (HashMap<String, Object>) mav.getModel().get("model");
        List<CardGroup> cardGroups = (ArrayList<CardGroup>) model.get("cardGroups");
        // Checking if all user CardGroups are displayed
        Assert.assertNotNull(cardGroups);
        Assert.assertEquals(2, cardGroups.size());
        Assert.assertEquals(5, cardGroups.get(0).getCardList().size());
        Assert.assertEquals("name1", cardGroups.get(0).getGroupName());
        Assert.assertEquals(3, cardGroups.get(1).getCardList().size());
        Assert.assertEquals("name2", cardGroups.get(1).getGroupName());
        CardGroup group = new CardGroup();
        group = (CardGroup) model.get("cardGroupToEdit");
        Assert.assertNull(group);
        group = (CardGroup) model.get("cardGroupToDelete");
        Assert.assertNotNull(group);
        Assert.assertEquals(1, group.getId());
        Assert.assertEquals("name1", group.getGroupName());
        Assert.assertEquals("Description", group.getDescription());
        Assert.assertEquals(5, group.getCardList().size());
    }

    @Test
    public void testShowCardGroupToCreate() throws Exception {

        System.out.println("Testing if Card Group To Create is shown");
        request.getSession().setAttribute("user", userManager.getUser(0));
        request.setParameter("create", "1");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageCardGroups", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = new HashMap<String, Object>();
        model = (HashMap<String, Object>) mav.getModel().get("model");
        List<CardGroup> cardGroups = (ArrayList<CardGroup>) model.get("cardGroups");
        // Checking if all user CardGroups are displayed
        Assert.assertNotNull(cardGroups);
        Assert.assertEquals(2, cardGroups.size());
        Assert.assertEquals(5, cardGroups.get(0).getCardList().size());
        Assert.assertEquals("name1", cardGroups.get(0).getGroupName());
        Assert.assertEquals(3, cardGroups.get(1).getCardList().size());
        Assert.assertEquals("name2", cardGroups.get(1).getGroupName());
        CardGroup group = new CardGroup();
        group = (CardGroup) model.get("cardGroupToEdit");
        Assert.assertNull(group);
        group = (CardGroup) model.get("cardGroupToDelete");
        Assert.assertNull(group);
        group = (CardGroup) model.get("cardGroupToCreate");
        Assert.assertNotNull(group);
    }

    @Test
    public void testCardGroupActionCreate() throws Exception {

        cardGroupManagerMock.checking(new Expectations() {

            {
                CardGroup newCardGroup = new CardGroup();
                newCardGroup.setGroupName("New Group Name");
                newCardGroup.setDescription("New Group Description");
                //checking if Create Method was called
                oneOf(cardGroupManager).createNewCardGroupForUserId(with(equal(0)), with(equal(newCardGroup)));

            }
        });

        System.out.println("Testing if Card Group IS Created");
        request.getSession().setAttribute("user", userManager.getUser(0));
        request.setParameter("actioncreate", "true");
        request.setParameter("groupName", "New Group Name");
        request.setParameter("description", "New Group Description");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageCardGroups", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = new HashMap<String, Object>();
        model = (HashMap<String, Object>) mav.getModel().get("model");
        List<CardGroup> cardGroups = (ArrayList<CardGroup>) model.get("cardGroups");
        // Checking if all user CardGroups are displayed
        Assert.assertNotNull(cardGroups);
        Assert.assertEquals(2, cardGroups.size());
        Assert.assertEquals(5, cardGroups.get(0).getCardList().size());
        Assert.assertEquals("name1", cardGroups.get(0).getGroupName());
        Assert.assertEquals(3, cardGroups.get(1).getCardList().size());
        Assert.assertEquals("name2", cardGroups.get(1).getGroupName());
        CardGroup group = new CardGroup();
        group = (CardGroup) model.get("cardGroupToEdit");
        Assert.assertNull(group);
        group = (CardGroup) model.get("cardGroupToDelete");
        Assert.assertNull(group);
        group = (CardGroup) model.get("cardGroupToCreate");
        Assert.assertNull(group);
    }

    @Test
    public void testCardGroupActionUpdate() throws Exception {

        cardGroupManagerMock.checking(new Expectations() {

            {
                CardGroup newCardGroup = new CardGroup();
                newCardGroup.setId(2);
                newCardGroup.setGroupName("New Group Name");
                newCardGroup.setDescription("New Group Description");
                //checking if Update Method was called
                oneOf(cardGroupManager).updateCardGroup(with(equal(newCardGroup)));

            }
        });

        System.out.println("Testing if Card Group IS Updated");
        request.getSession().setAttribute("user", userManager.getUser(0));
        request.setParameter("actionupdate", "2");
        request.setParameter("groupName", "New Group Name");
        request.setParameter("description", "New Group Description");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageCardGroups", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = new HashMap<String, Object>();
        model = (HashMap<String, Object>) mav.getModel().get("model");
        List<CardGroup> cardGroups = (ArrayList<CardGroup>) model.get("cardGroups");
        // Checking if all user CardGroups are displayed
        Assert.assertNotNull(cardGroups);
        Assert.assertEquals(2, cardGroups.size());
        Assert.assertEquals(5, cardGroups.get(0).getCardList().size());
        Assert.assertEquals("name1", cardGroups.get(0).getGroupName());
        Assert.assertEquals(3, cardGroups.get(1).getCardList().size());
        Assert.assertEquals("name2", cardGroups.get(1).getGroupName());
        CardGroup group = new CardGroup();
        group = (CardGroup) model.get("cardGroupToEdit");
        Assert.assertNull(group);
        group = (CardGroup) model.get("cardGroupToDelete");
        Assert.assertNull(group);
        group = (CardGroup) model.get("cardGroupToCreate");
        Assert.assertNull(group);
    }

    @Test
    public void testCardGroupActionDelete() throws Exception {

        cardGroupManagerMock.checking(new Expectations() {

            {
                //checking if Delete Method was called
                oneOf(cardGroupManager).deleteCardGroupWithId(with(equal(2)));

            }
        });

        System.out.println("Testing if Card Group IS Deleted");
        request.getSession().setAttribute("user", userManager.getUser(0));
        request.setParameter("actiondelete", "2");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageCardGroups", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = new HashMap<String, Object>();
        model = (HashMap<String, Object>) mav.getModel().get("model");
        List<CardGroup> cardGroups = (ArrayList<CardGroup>) model.get("cardGroups");
        // Checking if all user CardGroups are displayed
        Assert.assertNotNull(cardGroups);
        Assert.assertEquals(2, cardGroups.size());
        Assert.assertEquals(5, cardGroups.get(0).getCardList().size());
        Assert.assertEquals("name1", cardGroups.get(0).getGroupName());
        Assert.assertEquals(3, cardGroups.get(1).getCardList().size());
        Assert.assertEquals("name2", cardGroups.get(1).getGroupName());
        CardGroup group = new CardGroup();
        group = (CardGroup) model.get("cardGroupToEdit");
        Assert.assertNull(group);
        group = (CardGroup) model.get("cardGroupToDelete");
        Assert.assertNull(group);
        group = (CardGroup) model.get("cardGroupToCreate");
        Assert.assertNull(group);
//        cardGroupManagerMock.assertIsSatisfied();
    }
}