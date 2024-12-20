package org.partha.wmclient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MyTestClass {

    public static void main(String[] args) {
        String text = "19-SEP-2019";
        Date date = parseToUtilDate(text, "dd-MMM-yyyy");
        System.out.println(date);

    }


    public static Date parseToUtilDate(String input, String dateformat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateformat);
        try {
//            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
//            Date date = formatter.parse(input);
//            return date;
            DateFormat format = new SimpleDateFormat(dateformat, Locale.ENGLISH);
            Date date = format.parse(input);
            return date;
        }catch(ParseException e) {
            //logger.error("error occured",e);
            return null;
        }
    }
}
