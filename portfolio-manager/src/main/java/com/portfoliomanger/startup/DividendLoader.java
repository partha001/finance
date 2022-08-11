package com.portfoliomanger.startup;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.portfoliomanger.dao.DividendDao;
import com.portfoliomanger.dto.DividendDto;
import com.portfoliomanger.util.ExcelUtil;

@Order(0)
@Component
public class DividendLoader implements CommandLineRunner{
	
	
	private static final Logger logger = LoggerFactory.getLogger(DividendLoader.class);

	
	ExcelUtil excelUtil = new ExcelUtil();
	ClassLoader classLoader = getClass().getClassLoader();
	private static final String INVESTMENT_DATA = "mydata/invested.xlsx";
	
	@Autowired
	private DividendDao dividendDao;

	@Override
	public void run(String... args) throws Exception  {		
		Resource resource = new ClassPathResource(INVESTMENT_DATA);
		File file = resource.getFile();
		List<DividendDto> dividendList = new ArrayList<>();
		try (Workbook workbook = new XSSFWorkbook(file);) {

			Sheet sheet = workbook.getSheet("dividend");
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next(); //to skip the header record
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				DividendDto dto = new DividendDto();
				dto.setYear(excelUtil.getInt(row.getCell(1)));
				dto.setQuarter(excelUtil.getInt(row.getCell(2)));
				dto.setSymbol(excelUtil.getString(row.getCell(3)));
				dto.setName(excelUtil.getString(row.getCell(4)));
				dto.setAmount(excelUtil.getDouble(row.getCell(6)));
				dividendList.add(dto);
				
				logger.info("rowIndex:{} year:{}  quarter:{}  symbol:{}  name:{}  amount:{}",row.getRowNum()+1, dto.getYear(),dto.getQuarter(),dto.getSymbol(),dto.getName(),dto.getAmount());
				//System.out.print(""+ excelDataUtil.readCell(cell) + "  ");
			}
		}
		
		logger.info("total number of dividentRecordsRead:{}",dividendList.size());
		int recordsInserted = dividendDao.insertDividends(dividendList);
		logger.info("total number of inserted:{}",recordsInserted);
		

	}



}


