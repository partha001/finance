package com.portfoliomanger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.portfoliomanger.dao.DividendDao;
import com.portfoliomanger.dto.DividendDto;
import com.portfoliomanger.responose.DividendSummaryResponse;
import com.portfoliomanger.util.MathUtil;

@Service
public class DividendService {
		
	
	@Autowired
	private DividendDao dividendDao;
	
	public ResponseEntity<DividendSummaryResponse> getDividendSummarByYear(){        
		List<DividendDto> list = dividendDao.getDividendSummarByYear();	
		list.forEach(item -> {
			item.setAvgAmount(MathUtil.roundDouble(item.getAvgAmount()));
		});
		DividendSummaryResponse response = DividendSummaryResponse.builder()
				.list(list)
				.build();
		
		return new ResponseEntity<DividendSummaryResponse>(response,HttpStatus.OK);
	}
	
	
	public ResponseEntity<DividendSummaryResponse> getDividendSummarByQuarter(){
		List<DividendDto> list = dividendDao.getDividendSummarByQuarter();	
		list.forEach(item -> {
			item.setAvgAmount(MathUtil.roundDouble(item.getAvgAmount()));
		});
		
		DividendSummaryResponse response = DividendSummaryResponse.builder()
				.list(list)
				.build();
		return new ResponseEntity<DividendSummaryResponse>(response, HttpStatus.OK);
	}
	
	
	public ResponseEntity<DividendSummaryResponse> getDividendSummarByYearAndQuarter(){
		List<DividendDto> list = dividendDao.getDividendSummarByYearAndQuarter();
		list.forEach(item -> {
			item.setAvgAmount(MathUtil.roundDouble(item.getAvgAmount()));
		});
		DividendSummaryResponse response = DividendSummaryResponse.builder()
				.list(list)
				.build();
		return new ResponseEntity<DividendSummaryResponse>(response, HttpStatus.OK);
	}


	public ResponseEntity<DividendSummaryResponse> getDividendSummarByEquity() {
		List<DividendDto> list = dividendDao.getDividendSummarByEquity();
		list.forEach(item -> {
			item.setAvgAmount(MathUtil.roundDouble(item.getAvgAmount()));
		});
		DividendSummaryResponse response = DividendSummaryResponse.builder()
				.list(list)
				.build();
		return new ResponseEntity<DividendSummaryResponse>(response, HttpStatus.OK);
	}


	public ResponseEntity<DividendSummaryResponse> getDividendDetails() {
		List<DividendDto> list = dividendDao.getDividendDetails();
		list.forEach(item -> {
			item.setAvgAmount(MathUtil.roundDouble(item.getAvgAmount()));
		});
		DividendSummaryResponse response = DividendSummaryResponse.builder()
				.list(list)
				.build();
		return new ResponseEntity<DividendSummaryResponse>(response, HttpStatus.OK);
	}
	
	
	
	
	

}
