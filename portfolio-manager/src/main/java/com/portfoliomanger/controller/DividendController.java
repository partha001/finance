package com.portfoliomanger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfoliomanger.responose.DividendSummaryResponse;
import com.portfoliomanger.service.DividendService;

@RestController
public class DividendController {
	
	@Autowired
	private DividendService service;
	
	@GetMapping("/dividendSummaryByYear")
	public ResponseEntity<DividendSummaryResponse> getDividendSummarByYear(){
		return service.getDividendSummarByYear();		
	}
	
	
	@GetMapping("/dividendSummaryByQuarter")
	public ResponseEntity<DividendSummaryResponse> getDividendSummarByQuarter(){
		return service.getDividendSummarByQuarter();		
	}
	
	@GetMapping("/dividendSummaryByYearAndQuarter")
	public ResponseEntity<DividendSummaryResponse> getDividendSummarByYearAndQuarter(){
		return service.getDividendSummarByYearAndQuarter();		
	}
	
	
	@GetMapping("/dividendSummaryByEquity")
	public ResponseEntity<DividendSummaryResponse> getDividendSummarByEquity(){
		return service.getDividendSummarByEquity();		
	}
	
	

	@GetMapping("/dividendDetails")
	public ResponseEntity<DividendSummaryResponse> getDividendDetails() throws InterruptedException{
		Thread.currentThread().sleep(1000);
		return service.getDividendDetails();		
	}

}
