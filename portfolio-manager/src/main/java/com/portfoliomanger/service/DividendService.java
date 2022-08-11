package com.portfoliomanger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfoliomanger.dao.DividendDao;
import com.portfoliomanger.dto.DividendDto;

@Service
public class DividendService {
	
	
	@Autowired
	private DividendDao dividendDao;
	
	
	

}
