package com.portfoliomanger.startup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.portfoliomanger.entities.Stock;
import com.portfoliomanger.service.GoogleSheetsService;
import com.portfoliomanger.service.StockService;
import com.portfoliomanger.util.StockUtil;

@Order(1)
@Component
public class StockCurrentPriceLoader implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(StockCurrentPriceLoader.class);
	
	@Value("${stockCurrentPriceLoadFlag}")
	private Boolean stockCurrentPriceLoadFlag;
	
	@Autowired
	GoogleSheetsService googleSheetService;
	
	@Autowired
	StockService stockService;
	
	@Override
	public void run(String... args) throws Exception {
		if(stockCurrentPriceLoadFlag) {
			List<Stock> currentStockDetails = googleSheetService.getCurrentStockDetails();
			Map<String, Stock> stockMap = StockUtil.stockMap;
			
			List<Stock> stockSaveList = new ArrayList<>();
			currentStockDetails.forEach(item ->{
				if(stockMap.containsKey(item.getKey())) {
					Stock stock = stockMap.get(item.getKey());
					stock.setPrice(item.getPrice());
					stockSaveList.add(stock);
				}
			});
			
			if(!stockSaveList.isEmpty()) {
				int recordCount = stockService.updateCurrentPrice(stockSaveList);
				logger.info("current price updated for recordCount:{}",recordCount);
			}
			
		}
	}

}
