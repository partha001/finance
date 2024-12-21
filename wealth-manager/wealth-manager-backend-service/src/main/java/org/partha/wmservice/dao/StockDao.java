package org.partha.wmservice.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.partha.wmcommon.constants.Constants;
import org.partha.wmcommon.constants.SqlConstant;
import org.partha.wmcommon.entities.Stock;
import org.partha.wmcommon.util.DateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class StockDao {
	

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public int loadToStockmaster(List<Stock> list) {
		MapSqlParameterSource[] map = new MapSqlParameterSource[list.size()];
		for(int i=0;i<list.size();i++) {
			Stock dto = list.get(i);
			MapSqlParameterSource param = new MapSqlParameterSource();
			//public static final String LOAD_NSE_DATA = "insert into StockMaster(symbol,name, isinNumber, faceValue, listingDate) values (:symbol, :name, :isinNumber, :faceValue, :listingDate) on conflict (exchange,symbol) do nothing";

			param.addValue("exchange", dto.getExchange());
			param.addValue("symbol", dto.getSymbol());
			param.addValue("name", dto.getName());
			param.addValue("isinNumber", dto.getIsin());
			param.addValue("faceValue", dto.getFaceValue());
			param.addValue("listingDate", dto.getListingDate());
			param.addValue("key", dto.getKey());
			map[i] = param;
		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(SqlConstant.LOAD_NSE_DATA, map);
		int recordsInserted = IntStream.of(batchUpdate).sum();
		return recordsInserted;
	}
	
	
	
	public List<Stock> readAllStockData(){
		return jdbcTemplate.query(SqlConstant.SELECT_FROM_STOCK_MASTER, new RowMapper<Stock>() {

			@Override
			public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
				Stock stock = new Stock();
				stock.setId(rs.getInt("id"));
				stock.setExchange(rs.getString("exchange"));
				stock.setSymbol(rs.getString("symbol"));
				stock.setName(rs.getString("name"));
				stock.setFaceValue(rs.getDouble("faceValue"));
				stock.setListingDate(rs.getDate("listingDate"));
				stock.setKey(rs.getString("key"));
				stock.setIsin(rs.getString("isinNumber"));
				return stock;
			}
		});
	}
	
	
	public int updateCurrentPriceInStockPriceMaster(List<Stock> list) throws ParseException {
		MapSqlParameterSource[] map = new MapSqlParameterSource[list.size()];
		for(int i=0;i<list.size();i++) {
			Stock dto = list.get(i);
			MapSqlParameterSource param = new MapSqlParameterSource();
			//public static final String UPDATE_STOCK_DETAILS = "update StockMaster set price=:price where id=:id";
			//public static final String UPDATE_STOCKPRICEMASTER = "insert into  StockPriceMaster (stockMasterId, stockKey, price, priceTime, timeZoneName ) 
			//values (:stockMasterId , :stockKey , :price , :priceTime ,:timeZoneName)";
			
			
			
			param.addValue("id", dto.getId());
			param.addValue("stockMasterId", dto.getId());
			param.addValue("stockKey", dto.getKey());
//			param.addValue("price", dto.getPrice());
//			param.addValue("priceTime", DateUtil.convertStringToSqlDate(dto.getPriceTime(), Constants.DATE_FORMAT_IS08601));
//			param.addValue("priceTimeZone",dto.getPriceTimeZone());
			map[i] = param;
		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(SqlConstant.UPDATE_STOCKPRICEMASTER, map);
		int recordsInserted = IntStream.of(batchUpdate).sum();
		return recordsInserted;
	}
	
	
	
	
	

	
	
	public int updateCurrentPrice(List<Stock> list) {
		MapSqlParameterSource[] map = new MapSqlParameterSource[list.size()];
		for(int i=0;i<list.size();i++) {
			Stock dto = list.get(i);
			MapSqlParameterSource param = new MapSqlParameterSource();
			//public static final String UPDATE_STOCK_DETAILS = "update StockMaster set price=:price where id=:id";
			
			param.addValue("id", dto.getId());
			//param.addValue("price", dto.getPrice());
			map[i] = param;
		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(SqlConstant.UPDATE_STOCK_DETAILS, map);
		int recordsInserted = IntStream.of(batchUpdate).sum();
		return recordsInserted;
	}





}
