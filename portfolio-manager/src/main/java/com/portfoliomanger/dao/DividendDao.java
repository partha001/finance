package com.portfoliomanger.dao;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.portfoliomanger.constants.SqlConstant;
import com.portfoliomanger.dto.DividendDto;

@Repository
public class DividendDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public static final String  LOAD_DIVIDEND_DATA = "insert into DividendMaster (symbol,name, dividendYear, quarter, dividendAmount) values ( :symbol, :name, :dividendYear, quarter, dividendAmount)";

	public int insertDividends(List<DividendDto> list) {
		MapSqlParameterSource[] map = new MapSqlParameterSource[list.size()];
		for(int i=0;i<list.size();i++) {
			DividendDto dto = list.get(i);
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("symbol", dto.getSymbol());
			param.addValue("name", dto.getName());
			param.addValue("dividendYear", dto.getYear());
			param.addValue("quarter", dto.getQuarter());
			param.addValue("dividendAmount", dto.getAmount());
			map[i] = param;
		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(SqlConstant.LOAD_DIVIDEND_DATA, map);
		int recordsInserted = IntStream.of(batchUpdate).sum();
		return recordsInserted;
	}


}
