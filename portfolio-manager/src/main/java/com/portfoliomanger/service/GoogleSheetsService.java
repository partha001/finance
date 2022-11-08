package com.portfoliomanger.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.portfoliomanger.entities.Stock;

@Component
public class GoogleSheetsService {
	
	private static String financeSheetId ="1zI0xbc_wDrECMi9IVX_wPNj3kECiq0NMXYFB0-FFFnU";
	
	private static final Logger logger = LoggerFactory.getLogger(GoogleSheetsService.class);
	
	
	
	
	
	@Autowired
	@Qualifier("googleSheets")
	private Sheets googleSheets;
	
	
	public List<Stock> getCurrentStockDetails() throws IOException{
		String range ="StockData!A3:N";
		ValueRange response = googleSheets.spreadsheets().values().get(financeSheetId, range)
				.execute();
		
		List<Stock> stockList = new ArrayList<>();
		List<List<Object>> values = response.getValues();
		if(values==null || values.isEmpty()) {
			logger.info("no data found");
		}else {
			for(List row: values) {
				//System.out.println(row.get(2));
				Stock stock = new Stock();
				stock.setExchange((String)row.get(0));
				stock.setSymbol((String)row.get(1));
				stock.setKey((String)row.get(2));
				stock.setPrice(new Double((String)row.get(3)));
//				logger.info("row exchange:{} symbol:{} price:{} ",new Object[] {
//						row.get(0), //exchange
//						row.get(1), //symbol
//						row.get(3) //price
//						
//				});
				stockList.add(stock);
			}
		}
		return stockList;
	}
	

}
