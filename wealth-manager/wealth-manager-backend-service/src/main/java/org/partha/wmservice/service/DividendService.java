package org.partha.wmservice.service;



import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.entities.Dividend;
import org.partha.wmcommon.response.ResponseDto;
import org.partha.wmservice.repositories.DividendRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.partha.wmcommon.dto.DividendDto;
import org.partha.wmcommon.response.DividendSummaryResponse;
import org.partha.wmcommon.util.MathUtil;
import org.partha.wmservice.dao.DividendDao;

import java.util.List;

@Log4j2
@Service
public class DividendService {

	@Autowired
	DividendRepository dividendRepository;


	public ResponseDto getAllDividends() {
		log.info("inside DividendService.getAllDividends()");
		List<Dividend> dividendList = dividendRepository.findAll();
		return ResponseDto.builder()
				.data(dividendList)
				.build();
	}
	
	
	
	
	

}
