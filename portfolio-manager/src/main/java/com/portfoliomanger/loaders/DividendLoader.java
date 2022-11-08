package com.portfoliomanger.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.portfoliomanger.dao.DividendDao;
import com.portfoliomanger.entities.Dividend;
import com.portfoliomanger.entities.Stock;
import com.portfoliomanger.service.StockService;
import com.portfoliomanger.util.ExcelUtil;
import com.portfoliomanger.util.StockUtil;


@Component
public class DividendLoader {
	
	
	private static final Logger logger = LoggerFactory.getLogger(DividendLoader.class);
	
	ExcelUtil excelUtil = new ExcelUtil();
	
	@Value("${filename}")
	private String filename;
	
	@Autowired
	private DividendDao dividendDao;
	
	
//	@Autowired
//	private StockUtil stockUtil;
	
	
	@Autowired
	private StockService stockService;

	public void run()  {		
		List<Dividend> dividendList = new ArrayList<>();
		try (InputStream inputStream = new ClassPathResource(filename, this.getClass().getClassLoader()).getInputStream();
				Workbook workbook = new XSSFWorkbook(inputStream);) {

			
			Map<String, Stock> stockMap = stockService.getStockMapFromDb();
			
			Sheet sheet = workbook.getSheet("dividend");
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next(); //to skip the header record
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Dividend dto = new Dividend();
				dto.setDividendYear(excelUtil.getInt(row.getCell(1)));
				dto.setQuarter(excelUtil.getInt(row.getCell(2)));
				dto.setSymbol(excelUtil.getString(row.getCell(3)));
				dto.setName(excelUtil.getString(row.getCell(4)));
				dto.setAmount(excelUtil.getDouble(row.getCell(6)));
				dividendList.add(dto);
								
				//logger.info("rowIndex:{} year:{}  quarter:{}  symbol:{}  name:{}  amount:{}",row.getRowNum()+1, dto.getDividendYear(),dto.getQuarter(),dto.getSymbol(),dto.getName(),dto.getAmount());
				if(!checkIfValidSymbol(dto.getSymbol(),stockMap)) {
					logger.info("rowIndex:{} symbol:{} is not valid",row.getRowNum()+1,dto.getSymbol());
				}
					
						
			}
		} catch (IOException e) {
			logger.error("exception occured",e);
		}
		
		logger.info("total number of dividentRecordsRead:{}",dividendList.size());
		int recordsInserted = dividendDao.insertDividends(dividendList);
		logger.info("total number of inserted:{}",recordsInserted);
		

	}
	
	
	
	public boolean checkIfValidSymbol(String symbol,Map<String, Stock> stockMap) {
		if(stockMap.containsKey("NSE:"+symbol) || stockMap.containsKey("BSE:"+symbol))
			return true;
		else
			return false;
	}



}



