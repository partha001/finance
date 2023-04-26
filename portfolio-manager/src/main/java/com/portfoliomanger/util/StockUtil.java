package com.portfoliomanger.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.portfoliomanger.entities.Stock;

public class StockUtil {
	
	public static Map<String, Stock> stockMap ;
	
	static {	
		stockMap = new ConcurrentHashMap<>();
	}
	
	public static boolean checkIfValidSymbol(String symbol) {
		if(stockMap.containsKey("NSE:"+symbol) || stockMap.containsKey("BSE:"+symbol))
			return true;
		else
			return false;
	}
	
	
	public static boolean checkIfValidSymbol(String exchange,String symbol) {
		if(stockMap.containsKey(exchange+":"+symbol)) {
			return true;
		}else {
			return false;
		}
	}

}
