package com.portfoliomanger.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate){
		return new java.sql.Date(utilDate.getTime());
	}
	
	
	public static java.util.Date parseToUtilDate(String input,String dateformat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateformat);
		try {
			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date date = formatter.parse(input);
			return date;
		}catch(ParseException e) {
			logger.error("error occured",e);
			return null;
		}
	}

}
