/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.repository;

import com.greenland.memorycards.model.Card;
import com.greenland.memorycards.model.CardGroup;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 *
 * @author jurijspe
 */
public class JdbcCardDao extends SimpleJdbcDaoSupport implements CardDao{

    @Override
    public List<Card> getAllCardsForGroup(int id) {
        List<Card> cardList = new ArrayList<Card>();
        String sqlString = 
                "Select CARD.* "
                + "FROM GROUPS, GROUPCARD, CARD "
                + "WHERE CARD.ID=GROUPCARD.CARDID "
                + "AND GROUPCARD.GROUPID=GROUPS.ID "
                + "AND GROUPS.ID="+id+" "
                + "ORDER BY CARD.ID";
        cardList = getJdbcTemplate().query(sqlString, new CardMapper());
        return cardList;
    }
    
    private static class CardMapper implements ParameterizedRowMapper<Card> {

        @Override
        public Card mapRow(ResultSet rs, int i) throws SQLException {
            Card aCard = new Card();
            aCard.setQuestion(rs.getString("question"));
            aCard.setQuestionCode(rs.getString("questionCode"));
            aCard.setAnswer(rs.getString("answer"));
            aCard.setAnswerCode(rs.getString("answerCode"));
            return aCard;
        }
        
    }
}
