package com.portfoliomanger.constants;

public interface SqlConstant {
	
	public static final String  LOAD_DIVIDEND_DATA = "insert into DividendMaster (symbol,name, dividendYear, quarter, dividendAmount) values ( :symbol, :name, :dividendYear, :quarter, :dividendAmount)";

	public static final String GET_YEARLY_DIVIDEND_SUMMARY = "select  dividendYear, sum(dividendAmount) as amount , MIN(dividendAmount) as minAmount , max(dividendAmount) as maxAmount ,  avg(dividendAmount) as avgAmount from  DIVIDENDMASTER group by dividendYear ";
	
	public static final String GET_YEARLY_DIVIDEND_SUMMARY_BY_QUARTER = "select  quarter, sum(dividendAmount) as amount , MIN(dividendAmount) as minAmount , max(dividendAmount) as maxAmount ,  avg(dividendAmount) as avgAmount from  DIVIDENDMASTER group by quarter ";
	
	public static final String GET_YEARLY_DIVIDEND_SUMMARY_BY_YEAR_N_QUARTER = "select  dividendYear, quarter, sum(dividendAmount) as amount , MIN(dividendAmount) as minAmount , max(dividendAmount) as maxAmount ,  avg(dividendAmount) as avgAmount  from DIVIDENDMASTER group by dividendYear , quarter ";
	
	public static final String GET_YEARLY_DIVIDEND_SUMMARY_BY_EQUITY = "select  symbol, name , sum(dividendAmount) as amount , MIN(dividendAmount) as minAmount , max(dividendAmount) as maxAmount ,  avg(dividendAmount) as avgAmount  from DIVIDENDMASTER group by  symbol, name";
	
}




