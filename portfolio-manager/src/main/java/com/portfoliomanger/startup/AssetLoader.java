package com.portfoliomanger.startup;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

import com.google.common.base.Strings;
import com.portfoliomanger.dao.AssetDao;
import com.portfoliomanger.entities.Asset;
import com.portfoliomanger.util.ExcelUtil;

@Order(1)
@Component
public class AssetLoader implements CommandLineRunner{


	private static final Logger logger = LoggerFactory.getLogger(AssetLoader.class);


	ExcelUtil excelUtil = new ExcelUtil();
	
	@Value("${filename}")
	private String filename;

	@Autowired
	private AssetDao assetDao;

	@Override
	public void run(String... args) throws Exception  {		

		List<Date> dates = new ArrayList<>();
		List<Asset> assetList = new ArrayList<>();
		
		
		try (InputStream inputStream = new ClassPathResource(filename, this.getClass().getClassLoader()).getInputStream();
				Workbook workbook = new XSSFWorkbook(inputStream);) {

			Sheet sheet = workbook.getSheet("asset-value");
			Iterator<Row> rowIterator = sheet.iterator();
			//rowIterator.next(); //to skip the header record
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				logger.info("currently reacding row:{} ", row.getRowNum());
				if(row.getRowNum()==0) {
					//counting number of cols
					int i=1;			
					while( excelUtil.getDate(row.getCell(i))!=null) {
						Date currentDate = excelUtil.getDate(row.getCell(i));
						dates.add(currentDate);
						logger.info(""+currentDate);
						i++;
					}				
				}else {

					String assetName = excelUtil.getString(row.getCell(0));
					if(Strings.isNullOrEmpty(assetName))
						break;

//					double value = 0.0;
					for(int m=0;m<dates.size();m++) {
						Asset asset = new Asset();
						asset.setName(assetName);
						Double amount = excelUtil.getDouble(row.getCell(m+1));

//						if(amount!=0) {
//							value = amount;
//						}

						asset.setAmount(amount);
						asset.setRecordDate(dates.get(m));
						assetList.add(asset);
						logger.info("assetName:{}  value:{}  recordDate:{}",asset.getName(),asset.getAmount(),asset.getRecordDate());

					}

				}
			}
		}

		assetDao.insertAsssetDetails(assetList);

	}



}



