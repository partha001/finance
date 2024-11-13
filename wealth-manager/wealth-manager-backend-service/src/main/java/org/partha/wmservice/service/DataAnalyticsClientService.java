package org.partha.wmservice.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class DataAnalyticsClientService {

    @Value("${dataAnalytics.HostName}")
    private String dataAnalyticsHostname;

    public String getAssetChart(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(dataAnalyticsHostname + "/assets", String.class);
        return response.getBody();
    }


    public String getDividendChartByYear(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(dataAnalyticsHostname + "/dividends/dividendSummaryByYear", String.class);
        return response.getBody();
    }

    public String getDividendChartByQuarter(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(dataAnalyticsHostname + "/dividends/dividendSummaryByQuarter", String.class);
        return response.getBody();
    }
}
