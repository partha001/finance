package org.partha.wmservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.partha.wmcommon.response.InstrumentDataDownloadResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class DataAnalyticsClientService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${dataAnalytics.HostName}")
    private String dataAnalyticsHostname;

    public String getAssetChartAssetsVsTime() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(dataAnalyticsHostname + "/assets/chart/assetsVsTime", String.class);
        return response.getBody();
    }


    public String getDividendChartByYear() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(dataAnalyticsHostname + "/dividends/dividendSummaryByYear", String.class);
        return response.getBody();
    }

    public String getDividendChartByQuarter() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(dataAnalyticsHostname + "/dividends/dividendSummaryByQuarter", String.class);
        return response.getBody();
    }

    public InstrumentDataDownloadResponseDto downloadDailyData(DownloadDailyDataRequest req) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DownloadDailyDataRequest> request = new HttpEntity<>(req, headers);
        return restTemplate.postForObject(dataAnalyticsHostname + "/instruments/downloadDailyData", request, InstrumentDataDownloadResponseDto.class);
    }

    public String getTechnicalChartData() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(dataAnalyticsHostname + "/instruments/downloadDailyData", String.class);
        return response.getBody();
    }
}
