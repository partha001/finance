package wmservice.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import wmcommon.dto.DividendDto;
import wmcommon.response.DividendSummaryResponse;
import wmcommon.util.MathUtil;
import wmservice.dao.DividendDao;

import java.util.List;

@Service
public class DividendService {
		
	private static final Logger logger = LoggerFactory.getLogger(DividendService.class);
	
	@Autowired
	private DividendDao dividendDao;
	
	public ResponseEntity<DividendSummaryResponse> getDividendSummarByYear(){
		logger.info("inside DividendService.getDividendSummarByYear()");
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
		logger.info("inside DividendService.getDividendSummarByQuarter()");
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
		logger.info("inside DividendService.getDividendSummarByYearAndQuarter()");
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
		logger.info("inside DividendService.getDividendSummarByEquity()");
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
		logger.info("inside DividendService.getDividendDetails()");
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
