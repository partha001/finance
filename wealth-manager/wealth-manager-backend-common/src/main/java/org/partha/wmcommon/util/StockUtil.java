//package org.partha.wmcommon.util;
//
//
//
//import org.partha.wmcommon.entities.Stock;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class StockUtil {
//
//	public static Map<String, Stock> stockMap ;
//
//	static {
//		stockMap = new ConcurrentHashMap<>();
//	}
//
//	public static boolean checkIfValidSymbol(String symbol) {
//		if(stockMap.containsKey("NSE:"+symbol) || stockMap.containsKey("BSE:"+symbol))
//			return true;
//		else
//			return false;
//	}
//
//
//	public static boolean checkIfValidSymbol(String exchange,String symbol) {
//		if(stockMap.containsKey(exchange+":"+symbol)) {
//			return true;
//		}else {
//			return false;
//		}
//	}
//
//}
