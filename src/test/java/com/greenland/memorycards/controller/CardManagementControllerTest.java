/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.controller;

import com.greenland.memorycards.model.Card;
import com.greenland.memorycards.service.CardManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import junit.framework.Assert;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jurijspe
 */
public class CardManagementControllerTest {

    public CardManagementControllerTest() {
    }
    private Mockery cardManagerMock = new JUnit4Mockery();
    private CardManager cardManager = cardManagerMock.mock(CardManager.class);
    private CardManagementController controller = new CardManagementController();
    private static MockHttpServletRequest request;
    private static MockHttpServletResponse response;

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

        cardManagerMock.checking(new Expectations() {

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
                oneOf(cardManager).getAllCardsForGroup(0);
                will(returnValue(cardList));
            }
        });

        controller.setCardManager(cardManager);

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
        request.setParameter("form", "allCardsForGroup");
        request.setParameter("id", "0");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageCards", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = new HashMap<String, Object>();
        model = (HashMap<String, Object>) mav.getModel().get("model");
        List<Card> cardList = (ArrayList<Card>) model.get("cardList");
        Assert.assertNotNull(cardList);
        Assert.assertEquals(5, cardList.size());
        Assert.assertEquals("2+2=?", cardList.get(0).getQuestion());
        Assert.assertEquals("int 2+2", cardList.get(0).getQuestionCode());
        Assert.assertEquals("int 4", cardList.get(1).getAnswerCode());
        Assert.assertEquals("4", cardList.get(3).getAnswer());
    }
    
        @Test
    public void testShowCardToEdit() throws Exception {

        cardManagerMock.checking(new Expectations() {

            {
                Card card = new Card("2+2=?", "int 2+2", "4", "int 4", new Date(), new Date());
                oneOf(cardManager).getCardWithId(1);
                will(returnValue(card));
            }
        });

        System.out.println("Testing if Card To Edit is shown");
        request.setParameter("form", "edit");
        request.setParameter("id", "1");
        ModelAndView mav = controller.handleRequest(request, response);
        Assert.assertEquals("manageCards", mav.getViewName());
        Assert.assertNotNull(mav.getModel());
        Map<String, Object> model = new HashMap<String, Object>();
        model = (HashMap<String, Object>) mav.getModel().get("model");
        List<Card> cardList = (ArrayList<Card>) model.get("cardList");
        Assert.assertNotNull(cardList);
        Assert.assertEquals(5, cardList.size());
        Assert.assertEquals("2+2=?", cardList.get(0).getQuestion());
        Assert.assertEquals("int 2+2", cardList.get(0).getQuestionCode());
        Assert.assertEquals("int 4", cardList.get(1).getAnswerCode());
        Assert.assertEquals("4", cardList.get(3).getAnswer());
        Card card = new Card();
        card = (Card) model.get("cardToEdit");
        Assert.assertNotNull(card);
        Assert.assertEquals("2+2=?", card.getQuestion());
        Assert.assertEquals("4", card.getAnswer());
        cardManagerMock.assertIsSatisfied();
    }
}
