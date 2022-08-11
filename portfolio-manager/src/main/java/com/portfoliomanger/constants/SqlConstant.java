package com.portfoliomanger.constants;

public interface SqlConstant {
	
	public static final String  LOAD_DIVIDEND_DATA = "insert into DividendMaster (symbol,name, dividendYear, quarter, dividendAmount) values ( :symbol, :name, :dividendYear, :quarter, :dividendAmount)";
			

	
//		select  dividendyear, quarter, sum(dividendamount)
//		from DIVIDENDMASTER  
//		group by dividendyear, quarter
//		order by dividendyear, quarter
	
	
	
	
	
}




