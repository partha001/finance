package org.partha.wmservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.partha.wmcommon.constants.SqlConstant;
import org.partha.wmcommon.dto.DividendDto;
import org.partha.wmcommon.entities.Dividend;

import java.util.List;
import java.util.stream.IntStream;

@Repository
public class DividendDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public int insertDividends(List<Dividend> list) {
		MapSqlParameterSource[] map = new MapSqlParameterSource[list.size()];
		for(int i=0;i<list.size();i++) {
			Dividend dto = list.get(i);
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("symbol", dto.getSymbol());
			param.addValue("name", dto.getName());
			param.addValue("dividendYear", dto.getDividendYear());
			param.addValue("quarter", dto.getQuarter());
			param.addValue("dividendAmount", dto.getAmount());
			map[i] = param;
		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(SqlConstant.LOAD_DIVIDEND_DATA, map);
		int recordsInserted = IntStream.of(batchUpdate).sum();
		return recordsInserted;
	}



	public List<DividendDto> getDividendSummarByYear(){
		return jdbcTemplate.query(SqlConstant.GET_YEARLY_DIVIDEND_SUMMARY, new BeanPropertyRowMapper(DividendDto.class));

	}
	
	
	public List<DividendDto> getDividendSummarByQuarter(){
		return jdbcTemplate.query(SqlConstant.GET_YEARLY_DIVIDEND_SUMMARY_BY_QUARTER, new BeanPropertyRowMapper(DividendDto.class));

	}
	
	public List<DividendDto> getDividendSummarByYearAndQuarter(){
		return jdbcTemplate.query(SqlConstant.GET_YEARLY_DIVIDEND_SUMMARY_BY_YEAR_N_QUARTER, new BeanPropertyRowMapper(DividendDto.class));

	}


	public List<DividendDto> getDividendSummarByEquity() {
		return jdbcTemplate.query(SqlConstant.GET_YEARLY_DIVIDEND_SUMMARY_BY_EQUITY, new BeanPropertyRowMapper(DividendDto.class));
	}



	public List<DividendDto> getDividendDetails() {
		return jdbcTemplate.query(SqlConstant.GET_DIVIDEND_DETAILS, new BeanPropertyRowMapper(DividendDto.class));
		}


}
