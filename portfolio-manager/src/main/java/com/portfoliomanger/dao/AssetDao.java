package com.portfoliomanger.dao;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.portfoliomanger.constants.SqlConstant;
import com.portfoliomanger.entities.Asset;
import com.portfoliomanger.util.CommonUtil;

@Repository
public class AssetDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	

	public int insertAsssetDetails(List<Asset> list) {
		MapSqlParameterSource[] map = new MapSqlParameterSource[list.size()];
		for(int i=0;i<list.size();i++) {
			Asset dto = list.get(i);
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("name", dto.getName());
			param.addValue("amount", dto.getAmount());
			param.addValue("recordDate", CommonUtil.convertUtilDateToSqlDate(dto.getRecordDate()));
			map[i] = param;
		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(SqlConstant.LOAD_ASSET_DETAILS, map);
		int recordsInserted = IntStream.of(batchUpdate).sum();
		return recordsInserted;
	}
	
	

}
