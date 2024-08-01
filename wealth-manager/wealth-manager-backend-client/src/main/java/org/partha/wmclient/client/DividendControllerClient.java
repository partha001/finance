package org.partha.wmclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.partha.wmcommon.response.DividendSummaryResponse;

//@FeignClient(name = "${feign.name}", url = "${feign.url}")
@FeignClient(name = "DividendControllerClient", url = "${feign.url}")
public interface DividendControllerClient {

    @GetMapping("/dividendSummaryByYear")
    public ResponseEntity<DividendSummaryResponse> getDividendSummarByYear();


    @GetMapping("/dividendSummaryByQuarter")
    public ResponseEntity<DividendSummaryResponse> getDividendSummarByQuarter();

    @GetMapping("/dividendSummaryByYearAndQuarter")
    public ResponseEntity<DividendSummaryResponse> getDividendSummarByYearAndQuarter();


    @GetMapping("/dividendSummaryByEquity")
    public ResponseEntity<DividendSummaryResponse> getDividendSummarByEquity();


    @GetMapping("/dividendDetails")
    public ResponseEntity<DividendSummaryResponse> getDividendDetails() throws InterruptedException;
}
