package org.partha.wmclient.client;

import org.partha.wmcommon.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.partha.wmcommon.response.DividendSummaryResponse;

@FeignClient(name = "DividendControllerClient", url = "${feign.url}")
public interface DividendControllerClient {


    @GetMapping("/dividend/getAllDividends")
    public ResponseDto getAllDividends() ;
}
