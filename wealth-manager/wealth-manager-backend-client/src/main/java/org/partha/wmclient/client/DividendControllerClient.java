package org.partha.wmclient.client;

import org.partha.wmcommon.response.DividendChartDto;
import org.partha.wmcommon.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "DividendControllerClient", url = "${feign.url}/dividend")
public interface DividendControllerClient {

    @GetMapping("/getAllDividends")
    ResponseDto getAllDividends();

    @GetMapping("/getDividendChartDetails")
    DividendChartDto getDividendChartDetails();
}
