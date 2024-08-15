package org.partha.wmservice.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.partha.wmcommon.constants.SqlConstant;
import org.partha.wmcommon.entities.Asset;
import org.partha.wmcommon.util.DateUtil;

import java.util.List;
import java.util.stream.IntStream;

@Repository
public class AssetDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	

	public int insertAsssetDetails(List<Asset> list) {
		MapSqlParameterSource[] map = new MapSqlParameterSource[list.size()];
		for(int i=0;i<list.size();i++) {
			Asset dto = list.get(i);
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("name", dto.getAssetName());
			param.addValue("amount", dto.getAmount());
			param.addValue("valueationDate", DateUtil.convertUtilDateToSqlDate(dto.getValuationdDate()));
			map[i] = param;
		}
		int[] batchUpdate = jdbcTemplate.batchUpdate(SqlConstant.LOAD_ASSET_DETAILS, map);
		int recordsInserted = IntStream.of(batchUpdate).sum();
		return recordsInserted;
	}
	
	

}
