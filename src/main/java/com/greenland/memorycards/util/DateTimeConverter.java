/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greenland.memorycards.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author jurijspe
 */
public abstract class DateTimeConverter {
    
    protected final static Log logger = LogFactory.getLog("");
    
    public static String parseDate(String date) {
        if (date != null) {
//            logger.info("Starting with date: " + date);
            SimpleDateFormat newFormat = new SimpleDateFormat("EEE, d MMM ''yy HH:mm");
//            logger.info("Final Creation Date: " + newFormat.format(parse(date).getTime()));
            return newFormat.format(parse(date).getTime()).toString();
        } else {
            return date;
        }
    }
    
    private static Calendar parse(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date aDate = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(aDate);
            return cal;
        } catch (ParseException e) {
            logger.error("Date parsing didnt work");
            e.printStackTrace();
            return Calendar.getInstance();
        }
    }
}
