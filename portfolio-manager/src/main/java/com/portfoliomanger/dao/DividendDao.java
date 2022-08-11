package com.portfoliomanger.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DividendDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	

}
