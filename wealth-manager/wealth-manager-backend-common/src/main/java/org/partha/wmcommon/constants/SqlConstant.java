package org.partha.wmcommon.constants;

public interface SqlConstant {
	
	public static final String  LOAD_DIVIDEND_DATA = "insert into wealthmanager.DividendMaster (symbol,name, dividendYear, quarter, dividendAmount) values ( :symbol, :name, :dividendYear, :quarter, :dividendAmount)";

	public static final String GET_YEARLY_DIVIDEND_SUMMARY = "select  dividendYear, sum(dividendAmount) as amount , MIN(dividendAmount) as minAmount , max(dividendAmount) as maxAmount ,  avg(dividendAmount) as avgAmount from  wealthmanager.DividendMaster group by dividendYear order by dividendYear desc";
	
	public static final String GET_YEARLY_DIVIDEND_SUMMARY_BY_QUARTER = "select  quarter, sum(dividendAmount) as amount , MIN(dividendAmount) as minAmount , max(dividendAmount) as maxAmount ,  avg(dividendAmount) as avgAmount from  wealthmanager.DividendMaster group by quarter ";
	
	public static final String GET_YEARLY_DIVIDEND_SUMMARY_BY_YEAR_N_QUARTER = "select  dividendYear, quarter, sum(dividendAmount) as amount , MIN(dividendAmount) as minAmount , max(dividendAmount) as maxAmount ,  avg(dividendAmount) as avgAmount  from wealthmanager.DividendMaster group by dividendYear , quarter order by dividendYear desc, quarter desc";
	
	public static final String GET_YEARLY_DIVIDEND_SUMMARY_BY_EQUITY = "select  symbol, name , sum(dividendAmount) as amount , MIN(dividendAmount) as minAmount , max(dividendAmount) as maxAmount ,  avg(dividendAmount) as avgAmount ,count(1) as frequency from wealthmanager.DividendMaster group by  symbol, name";
	
	public static final String GET_DIVIDEND_DETAILS = "select id, symbol, name ,  dividendYear  , quarter , dividendAmount as amount   from wealthmanager.DividendMaster order by dividendYear desc , quarter desc ";
	
	public static final String LOAD_ASSET_DETAILS = "insert into wealthmanager.AssetMaster (assetName, amount, valueationDate) values (:name, :amount, :valueationDate) ";
	
	public static final String LOAD_NSE_DATA = "insert into wealthmanager.InstrumentMaster (instrumentType,name, exchange, key, symbol, isin, faceValue,  listingDate, yahooFinanceTicker, sourceName, createdDate) values (:instrumentType,:name, :exchange, :key, :symbol, :isin, :faceValue,  :listingDate, :yahooFinanceTicker, :sourceName, :createdDate) on conflict (key) do nothing";

	public static final String UPDATE_STOCK_DETAILS = "update wealthmanager.StockMaster set price=:price where id=:id";
	
	public static final String UPDATE_STOCKPRICEMASTER = "insert into  wealthmanager.StockPriceMaster (stockMasterId, stockKey, price, priceTime, priceTimeZone ) values (:stockMasterId , :stockKey , :price , :priceTime ,:priceTimeZone)";
	
	public static final String SELECT_FROM_STOCK_MASTER = "select * from wealthmanager.StockMaster ";
}




