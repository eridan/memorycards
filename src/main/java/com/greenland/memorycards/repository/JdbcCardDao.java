/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.repository;

import com.greenland.memorycards.model.Card;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

/**
 *
 * @author jurijspe
 */
public class JdbcCardDao extends SimpleJdbcDaoSupport implements CardDao{

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
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

    @Override
    public Card getCardWithId(int cardId) {
        logger.info("DB: Fetch Card with id: " + cardId);
        Card card = new Card();
        try {
            card = (Card) getSimpleJdbcTemplate().queryForObject(
                    "SELECT * FROM CARD "
                    + "WHERE id = '" + cardId + "'", new CardMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error("Card Group (id=" + cardId + ") not found exception");
            return null;
        }
        return card;
    }
    
    private static class CardMapper implements RowMapper<Card> {

        @Override
        public Card mapRow(ResultSet rs, int i) throws SQLException {
            Card aCard = new Card();
            aCard.setId(rs.getInt("id"));
            aCard.setQuestion(rs.getString("question"));
            aCard.setQuestionCode(rs.getString("questionCode"));
            aCard.setAnswer(rs.getString("answer"));
            aCard.setAnswerCode(rs.getString("answerCode"));
            return aCard;
        }
        
    }
}
