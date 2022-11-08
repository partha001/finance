package com.portfoliomanger.startup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.portfoliomanger.dao.StockDao;
import com.portfoliomanger.entities.Stock;
import com.portfoliomanger.service.GoogleSheetsService;
import com.portfoliomanger.service.StockService;

@Order(1)
@Component
public class StockCurrentPriceLoader implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(StockCurrentPriceLoader.class);
	
	@Value("${stockCurrentPriceLoadFlag}")
	private Boolean stockCurrentPriceLoadFlag;
	
	@Autowired
	GoogleSheetsService googleSheetService;
	
	@Autowired
	StockDao stockDao;
	
//	@Autowired
//	StockService stockService;
	
//	@Autowired
//	StockUtil stockUtil;
	
	@Override
	public void run(String... args) throws Exception {
		if(stockCurrentPriceLoadFlag) {
			List<Stock> currentStockDetails = googleSheetService.getCurrentStockDetails();
			
			List<Stock> readAllStockData = stockDao.readAllStockData();
			Map<String,Stock> stockMap = new HashMap<>();
			readAllStockData.forEach(item -> {
				stockMap.put(item.getExchange()+":"+item.getSymbol(), item);
			});
			
			List<Stock> stockSaveList = new ArrayList<>();
			currentStockDetails.forEach(item ->{
				if(stockMap.containsKey(item.getKey())) {
					Stock stock = stockMap.get(item.getKey());
					stock.setPrice(item.getPrice());
					stockSaveList.add(stock);
				}
			});
			
			if(!stockSaveList.isEmpty()) {
				int recordCount = stockDao.updateCurrentPrice(stockSaveList);
				logger.info("current price updated for recordCount:{}",recordCount);
			}
			
		}
		//stockService.refreshStockMap();
	}

}
