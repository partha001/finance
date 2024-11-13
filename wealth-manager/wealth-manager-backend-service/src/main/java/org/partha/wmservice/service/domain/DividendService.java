package org.partha.wmservice.service.domain;



import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.entities.Dividend;
import org.partha.wmcommon.enums.DividendChartType;
import org.partha.wmcommon.response.DividendChartDto;
import org.partha.wmcommon.response.ResponseDto;
import org.partha.wmservice.repositories.DividendRepository;
import org.partha.wmservice.service.DataAnalyticsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class DividendService {

	@Autowired
	DividendRepository dividendRepository;

	@Autowired
	DataAnalyticsClientService dataAnalyticsClientService;


	public ResponseDto getAllDividends() {
		log.info("inside DividendService.getAllDividends()");
		List<Dividend> dividendList = dividendRepository.findAll();
		return ResponseDto.builder()
				.data(dividendList)
				.build();
	}

	public DividendChartDto getDividendChartDetails(DividendChartType dividendChartType) {
		log.info("inside DividendService.getDividendChartDetails() . input details. dividendChartType:{}",dividendChartType );
		String imageString = null;
		if(dividendChartType.equals(DividendChartType.DividendSummmaryByYear)){
			imageString = dataAnalyticsClientService.getDividendChartByYear();
		}else if(dividendChartType.equals(DividendChartType.DividendSummaryByQuarter)) {
			imageString = dataAnalyticsClientService.getDividendChartByQuarter();
		}
		return DividendChartDto.builder()
				.imageString(imageString)
				.build();
	}
}
