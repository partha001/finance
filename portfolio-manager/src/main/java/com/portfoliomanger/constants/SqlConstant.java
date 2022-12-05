package com.portfoliomanger.constants;

public interface SqlConstant {
	
	public static final String  LOAD_DIVIDEND_DATA = "insert into DividendMaster (symbol,name, dividendYear, quarter, dividendAmount) values ( :symbol, :name, :dividendYear, :quarter, :dividendAmount)";

	public static final String GET_YEARLY_DIVIDEND_SUMMARY = "select  dividendYear, sum(dividendAmount) as amount , MIN(dividendAmount) as minAmount , max(dividendAmount) as maxAmount ,  avg(dividendAmount) as avgAmount from  DIVIDENDMASTER group by dividendYear order by dividendYear desc";
	
	public static final String GET_YEARLY_DIVIDEND_SUMMARY_BY_QUARTER = "select  quarter, sum(dividendAmount) as amount , MIN(dividendAmount) as minAmount , max(dividendAmount) as maxAmount ,  avg(dividendAmount) as avgAmount from  DIVIDENDMASTER group by quarter ";
	
	public static final String GET_YEARLY_DIVIDEND_SUMMARY_BY_YEAR_N_QUARTER = "select  dividendYear, quarter, sum(dividendAmount) as amount , MIN(dividendAmount) as minAmount , max(dividendAmount) as maxAmount ,  avg(dividendAmount) as avgAmount  from DIVIDENDMASTER group by dividendYear , quarter order by dividendYear desc, quarter desc";
	
	public static final String GET_YEARLY_DIVIDEND_SUMMARY_BY_EQUITY = "select  symbol, name , sum(dividendAmount) as amount , MIN(dividendAmount) as minAmount , max(dividendAmount) as maxAmount ,  avg(dividendAmount) as avgAmount ,count(1) as frequency from DIVIDENDMASTER group by  symbol, name";
	
	public static final String GET_DIVIDEND_DETAILS = "select id, symbol, name ,  dividendYear  , quarter , dividendAmount as amount   from DIVIDENDMASTER order by dividendYear desc , quarter desc ";
	
	public static final String LOAD_ASSET_DETAILS = "insert into AssetMaster (assetName, amount, recordDate) values (:name, :amount, :recordDate) ";
	
	public static final String LOAD_NSE_DATA = "insert into StockMaster(exchange, symbol,name, isinNumber, faceValue, listingDate, stockKey) values (:exchange, :symbol, :name, :isinNumber, :faceValue, :listingDate, :stockKey) ";

	public static final String UPDATE_STOCK_DETAILS = "update StockMaster set price=:price where id=:id";
	
	public static final String SELECT_FROM_STOCK_MASTER = "select * from StockMaster ";
}




