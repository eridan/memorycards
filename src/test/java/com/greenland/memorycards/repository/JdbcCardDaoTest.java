/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.repository;

import com.greenland.memorycards.model.Card;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jurijspe
 */
public class JdbcCardDaoTest extends AbstractTransactionalDataSourceSpringContextTests{
    
    public JdbcCardDaoTest() {
    }
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] {"classpath:test-config.xml"};
    }
    
    private CardDao cardDao;

    public void setCardDao(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getAllCardsForGroup method, of class JdbcCardDao.
     */
    @Test
    public void testGetAllCardsForGroup() {
        System.out.println("getAllCardsForGroup");
        int groupId = 1;
        int numberOfCards = 7;
        List<Card> result = cardDao.getAllCardsForGroup(groupId);
        assertEquals(numberOfCards, result.size());
    }
}
