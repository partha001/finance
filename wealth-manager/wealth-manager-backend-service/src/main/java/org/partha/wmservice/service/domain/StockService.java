//package org.partha.wmservice.service.domain;
//
//
//import org.partha.wmservice.dao.StockDao;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
////import org.partha.wmcommon.entities.Stock;
////import org.partha.wmcommon.util.StockUtil;
//
//import java.util.*;
//
//@Service
//public class StockService {
//
//	private static final Logger logger = LoggerFactory.getLogger(StockService.class);
//
//	@Autowired
//	StockDao stockDao;
//
////	@Autowired
////	GoogleSheetsService googleSheetService;
////
//
////	public void upsertPriceToDBFromGoogleSheet() throws IOException, ParseException {
////		//record reading time in googleSheet
////		TimeZone tz = TimeZone.getTimeZone(Constants.TIMEZONE_IST);
////		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_IS08601);
////		df.setTimeZone(tz);
////		Date date = new Date();
////		String dateString = df.format(date);
////		googleSheetService.recordReadingTime(dateString);
////
////
////		//reading from googleSheet and updating in database
////		Map<String, Stock> stockMap = getStockMapFromDb();
////		List<Stock> currentStockDetails = googleSheetService.getCurrentStockDetails();
////		currentStockDetails.forEach( priceDetail -> {
////			Stock stock = stockMap.get(priceDetail.getKey());
////			stock.setPrice(priceDetail.getPrice());
////			stock.setPriceTime(dateString);
////			stock.setPriceTimeZone(Constants.TIMEZONE_IST);
////		});
////
////
////		List<Stock> priceUpdateList = stockMap.values().stream()
////				.filter( stock -> stock.getPrice()!=null )
////				.collect(Collectors.toList());
////		logger.info("priceUpdatedList size {} ",priceUpdateList.size());
////
////		int recordsInserted = stockDao.updateCurrentPriceInStockPriceMaster(priceUpdateList);
////		logger.info("records inserted into StockPriceMaster {}",recordsInserted);
////
////	}
//
////	public List<Stock> readAllStockData(){
////		return stockDao.readAllStockData();
////	}
////
////	public void refreshStockMap() {
////		List<Stock> stockList = stockDao.readAllStockData();
////		StockUtil.stockMap.clear();
////		stockList.forEach(item -> {
////			StockUtil.stockMap.put(item.getKey(), item);
////		});
////	}
////
////
////	public Map<String,Stock> getStockMapFromDb(){
////		Map<String,Stock> map = new HashMap<String, Stock>();
////		List<Stock> stockList = stockDao.readAllStockData();
////		stockList.forEach(item -> map.put(item.getKey(), item));
////		return map;
////	}
////
////	public int updateCurrentPrice(List<Stock> stockSaveList) {
////		return stockDao.updateCurrentPrice(stockSaveList);
////	}
////
////	public int loadToStockmaster(List<Stock> list) {
////		return stockDao.loadToStockmaster(list);
////	}
//
//
//
//
//
//
//
//}
