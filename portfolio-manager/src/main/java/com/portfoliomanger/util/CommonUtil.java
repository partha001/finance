package com.portfoliomanger.util;

public class CommonUtil {
	
	public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate){
		return new java.sql.Date(utilDate.getTime());
	}

}
