package wmservice.loaders;


import com.google.common.base.Strings;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import wmcommon.entities.Asset;
import wmcommon.util.ExcelUtil;
import wmservice.dao.AssetDao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Component
public class AssetLoader {


	private static final Logger logger = LoggerFactory.getLogger(AssetLoader.class);


	ExcelUtil excelUtil = new ExcelUtil();
	
	@Value("${filename}")
	private String filename;

	@Autowired
	private AssetDao assetDao;


	public void run()  {		

		List<Date> dates = new ArrayList<>();
		List<Asset> assetList = new ArrayList<>();
		
		
		try (InputStream inputStream = new ClassPathResource(filename, this.getClass().getClassLoader()).getInputStream();
				Workbook workbook = new XSSFWorkbook(inputStream);) {

			Sheet sheet = workbook.getSheet("asset-value");
			Iterator<Row> rowIterator = sheet.iterator();
			//rowIterator.next(); //to skip the header record
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				//logger.info("currently reading row:{} ", row.getRowNum());
				if(row.getRowNum()==0) {
					//counting number of cols
					int i=1;			
					while( excelUtil.getDate(row.getCell(i))!=null) {
						Date currentDate = excelUtil.getDate(row.getCell(i));
						dates.add(currentDate);
						//logger.info(""+currentDate);
						i++;
					}				
				}else {

					String assetName = excelUtil.getString(row.getCell(0));
					if(Strings.isNullOrEmpty(assetName))
						break;

					for(int m=0;m<dates.size();m++) {
						Asset asset = new Asset();
						asset.setName(assetName);
						Double amount = excelUtil.getDouble(row.getCell(m+1));

						asset.setAmount(amount);
						asset.setRecordDate(dates.get(m));
						assetList.add(asset);
						//logger.info("assetName:{}  value:{}  recordDate:{}",asset.getName(),asset.getAmount(),asset.getRecordDate());

					}

				}
			}
		} catch (Exception e) {
			logger.error("exception occured",e);
		}

		int recordsInserted = assetDao.insertAsssetDetails(assetList);
		logger.info("asset count loaded into database:{}",recordsInserted);
	}



}



