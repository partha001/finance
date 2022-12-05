package com.portfoliomanger.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.portfoliomanger.dao.StockDao;
import com.portfoliomanger.entities.Stock;

public class StockUtil {
	
	public static Map<String, Stock> stockMap ;
	
	static {	
		stockMap = new HashMap<>();
	}
	
//	@Autowired
//	private StockDao stockDao;
	
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
	

//	public Map<String, Stock> getStockMap() {
//		return stockMap;
//	}
//
//
//	public void setStockMap(Map<String, Stock> stockMap) {
//		this.stockMap = stockMap;
//	}
//	
	

}
