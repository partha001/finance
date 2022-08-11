package com.portfoliomanger.util;

import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	
	public Double getDouble(Cell cell) {
		return cell.getNumericCellValue();
	}
	
	public String getString(Cell cell) {
		return cell.getStringCellValue();
	}
	
	
	public Boolean getBoolean(Cell cell) {
		return cell.getBooleanCellValue();
	}
	
	public Integer getInt(Cell cell) {
		Double value = new Double(cell.getNumericCellValue());
		return value.intValue();
	}


}
