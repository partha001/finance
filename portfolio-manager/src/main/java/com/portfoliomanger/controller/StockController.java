package com.portfoliomanger.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfoliomanger.dao.StockDao;
import com.portfoliomanger.entities.Stock;
import com.portfoliomanger.service.GoogleSheetsService;
import com.portfoliomanger.util.StockUtil;

@RestController
public class StockController {
	
	private static final Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	StockDao dao;
	
//	@Autowired
//	GoogleService service;
	
	
	@Autowired
	GoogleSheetsService googleSheetService;
	
//	@Autowired
//	StockUtil stockUtil;
	
//	private static String sheetId ="1zI0xbc_wDrECMi9IVX_wPNj3kECiq0NMXYFB0-FFFnU";
	
	
	@GetMapping("/getStockDetails")
	public ResponseEntity<List<Stock>> getStockDetails(){
		//Collection<Stock> values = StockUtil.stockMap.values();
		List<Stock> list = StockUtil.stockMap.keySet().stream().map(item -> StockUtil.stockMap.get(item)).collect(Collectors.toList());
		//stockUtil.stockMap.keySet().stream().map(item -> )
		return new ResponseEntity<List<Stock>>(list,  HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getFromGoogleSpreadSheet")
	public ResponseEntity<List<Stock>> getFromGoogleSpreadSheet() throws IOException, GeneralSecurityException{
//		//Collection<Stock> values = StockUtil.stockMap.values();
//		//List<Stock> list = StockUtil.stockMap.keySet().stream().map(item -> StockUtil.stockMap.get(item)).collect(Collectors.toList());
//		Sheets sheetsService = service.getSheetsService();
//		String range ="StockData!A3:N";
//		ValueRange response = sheetsService.spreadsheets().values().get(sheetId, range)
//				.execute();
//		
//		List<List<Object>> values = response.getValues();
//		if(values==null || values.isEmpty()) {
//			logger.info("no data found");
//		}else {
//			for(List row: values) {
//				//System.out.println(row.get(2));
//				logger.info("row exchange:{} symbol:{} price:{} ",new Object[] {
//						row.get(0), //exchange
//						row.get(1), //symbol
//						row.get(3) //price
//						
//				});
//			}
//		}
//		return new ResponseEntity<String>("Success",  HttpStatus.OK);
		
		return new ResponseEntity<List<Stock>>(googleSheetService.getCurrentStockDetails(),  HttpStatus.OK);
	}

}
