package org.partha.wmservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
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

    public void downloadDailyData(DownloadDailyDataRequest req) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DownloadDailyDataRequest> request = new HttpEntity<>(req, headers);
        restTemplate.postForObject(dataAnalyticsHostname + "/instruments/downloadDailyData", request, Void.class);
    }
}
