/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;

import com.greenland.memorycards.model.Card;
import com.greenland.memorycards.model.CardGroup;
import com.greenland.memorycards.model.User;
import com.greenland.memorycards.service.CardGroupManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.springframework.mock.web.MockHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private CardGroupManager cardGroupManager = cardGroupManagerMock.mock(CardGroupManager.class);
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
                cardGroup.setGroupName("name1");
                cardGroup.setCardList(cardList);
                CardGroup cardGroup1 = new CardGroup();
                cardGroup1.setGroupName("name2");
                cardGroup1.setCardList(cardList1);
                List<CardGroup> cardGroups = new ArrayList<CardGroup>();
                cardGroups.add(cardGroup);
                cardGroups.add(cardGroup1);
                oneOf(cardGroupManager).getCardGroupsForUser("test@mail.ru");
                will(returnValue(cardGroups));
            }
        });
        
        System.out.println("handleRequest");
        HttpServletRequest req = request;
        req.getSession().setAttribute("user", new User("test@mail.ru", ""));
        HttpServletResponse response = null;
        ModelAndView mav = controller.handleRequest(req, response);
        Assert.assertEquals("manageCardGroups", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        List<CardGroup> cardGroups = (ArrayList<CardGroup>) mav.getModel().get("cardGroups");
        Assert.assertNotNull(cardGroups);
        Assert.assertEquals(2, cardGroups.size());
        Assert.assertEquals(5, cardGroups.get(0).getCardList().size());
        Assert.assertEquals("name1", cardGroups.get(0).getGroupName());
        Assert.assertEquals(3, cardGroups.get(1).getCardList().size());
        Assert.assertEquals("name2", cardGroups.get(1).getGroupName());
    }
}
