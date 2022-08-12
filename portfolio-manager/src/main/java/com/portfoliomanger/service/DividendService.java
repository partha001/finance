package com.portfoliomanger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.portfoliomanger.dao.DividendDao;
import com.portfoliomanger.dto.DividendDto;
import com.portfoliomanger.responose.DividendSummaryResponse;

@Service
public class DividendService {
		
	
	@Autowired
	private DividendDao dividendDao;
	
	public ResponseEntity<DividendSummaryResponse> getDividendSummarByYear(){
		List<DividendDto> list = dividendDao.getDividendSummarByYear();		
		DividendSummaryResponse response = DividendSummaryResponse.builder()
				.list(list)
				.build();
		return new ResponseEntity<DividendSummaryResponse>(response, HttpStatus.OK);
	}
	
	
	public ResponseEntity<DividendSummaryResponse> getDividendSummarByQuarter(){
		List<DividendDto> list = dividendDao.getDividendSummarByQuarter();		
		DividendSummaryResponse response = DividendSummaryResponse.builder()
				.list(list)
				.build();
		return new ResponseEntity<DividendSummaryResponse>(response, HttpStatus.OK);
	}
	
	
	public ResponseEntity<DividendSummaryResponse> getDividendSummarByYearAndQuarter(){
		List<DividendDto> list = dividendDao.getDividendSummarByYearAndQuarter();		
		DividendSummaryResponse response = DividendSummaryResponse.builder()
				.list(list)
				.build();
		return new ResponseEntity<DividendSummaryResponse>(response, HttpStatus.OK);
	}
	
	
	
	
	

}
