///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.greenland.memorycards.repository;
//
//import com.greenland.memorycards.model.CardGroup;
//import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
//import java.util.List;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
///**
// *
// * @author jurijspe
// */
//public class JdbcCardGroupDaoTest extends AbstractTransactionalDataSourceSpringContextTests{
//    
//    public JdbcCardGroupDaoTest() {
//    }
//    
//    @Override
//    protected String[] getConfigLocations() {
//        return new String[] {"classpath:test-config.xml"};
//    }
//    
//    private CardGroupDao cardGroupDao;
//
//    public void setCardGroupDao(CardGroupDao cardGroupDao) {
//        this.cardGroupDao = cardGroupDao;
//    }
//    
////    @Override
////    protected void onSetUpInTransaction() throws Exception {
//////        super.deleteFromTables(new String[] {"users"});
//////        super.executeSqlScript("file:db/load_users.sql", true);
////    }
//
//    @BeforeClass
//    public static void setUpClass() throws Exception {
//    }
//
//    @AfterClass
//    public static void tearDownClass() throws Exception {
//    }
//
//    /**
//     * Test of getCardGroupsForUser method, of class JdbcCardGroupDao.
//     */
////    @Test
////    public void testGetCardGroupsForUser() {
////        System.out.println("getCardGroupsForUser");
////        String email = "test@mail.ru";
////        int expResult = 3;
////        List<CardGroup> result = cardGroupDao.getCardGroupsForUser(email);
////        assertEquals(expResult, result.size());
////    }
//}
