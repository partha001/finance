package org.partha.wmcommon.util;

import com.google.common.base.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

public class ExcelUtil {

	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	public Object readCell(Cell cell) {
		switch (cell.getCellType()) {
		case STRING: 
			return cell.getStringCellValue();
			//break;
		case NUMERIC:
			return cell.getNumericCellValue();
			//break;
		case BOOLEAN: 
			return cell.getBooleanCellValue();
			//break;
		case FORMULA:
			return cell.getCellFormula();
			//break;
		default :
			logger.info("rowIndex:{} colIndex:{}",cell.getRowIndex(),cell.getColumnIndex());
			return null;
		}
	}
	
	
	public Double getDouble(Cell cell) throws ParseException {
		String strVal = cell.getStringCellValue();
		if(Strings.isNullOrEmpty(strVal))
			return null;
		Number number = NumberFormat.getInstance().parse(strVal);
		return number.doubleValue();
	}
	
	public String getString(Cell cell) {
		return cell.getStringCellValue();
	}
	
	
	public Boolean getBoolean(Cell cell) {
		return cell.getBooleanCellValue();
	}
	
	public Integer getInt(Cell cell) throws ParseException {
		String strVal = cell.getStringCellValue();
		if(Strings.isNullOrEmpty(strVal))
			return null;
		Number number = NumberFormat.getInstance().parse(strVal);
		return number.intValue();
	}
	
	
	public Date getDate(Cell cell) {
		return cell.getDateCellValue();
	}


}
