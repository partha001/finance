package com.portfoliomanger.dao;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.portfoliomanger.constants.SqlConstant;
import com.portfoliomanger.entities.Stock;

@Repository
public class StockDao {
	

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public int loadToStockmaster(List<Stock> list) {
		MapSqlParameterSource[] map = new MapSqlParameterSource[list.size()];
		for(int i=0;i<list.size();i++) {
			Stock dto = list.get(i);
			MapSqlParameterSource param = new MapSqlParameterSource();
			//public static final String LOAD_NSE_DATA = "insert into StockMaster(symbol,name, isinNumber, faceValue, listingDate) values (:symbol, :name, :isinNumber, :faceValue, :listingDate) ";

			param.addValue("exchange", dto.getExchange());
			param.addValue("symbol", dto.getSymbol());
			param.addValue("name", dto.getName());
			param.addValue("isinNumber", dto.getIsin());
			param.addValue("faceValue", dto.getFaceValue());
			param.addValue("listingDate", dto.getListingDate());
			map[i] = param;
		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(SqlConstant.LOAD_NSE_DATA, map);
		int recordsInserted = IntStream.of(batchUpdate).sum();
		return recordsInserted;
	}
	
//	public int insertDividends(List<Dividend> list) {
//		MapSqlParameterSource[] map = new MapSqlParameterSource[list.size()];
//		for(int i=0;i<list.size();i++) {
//			Dividend dto = list.get(i);
//			MapSqlParameterSource param = new MapSqlParameterSource();
//			param.addValue("symbol", dto.getSymbol());
//			param.addValue("name", dto.getName());
//			param.addValue("dividendYear", dto.getDividendYear());
//			param.addValue("quarter", dto.getQuarter());
//			param.addValue("dividendAmount", dto.getAmount());
//			map[i] = param;
//		}
//		int[] batchUpdate = jdbcTemplate.batchUpdate(SqlConstant.LOAD_DIVIDEND_DATA, map);
//		int recordsInserted = IntStream.of(batchUpdate).sum();
//		return recordsInserted;
//	}


}
