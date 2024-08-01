package org.partha.wmfrontend.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.partha.wmclient.client.DividendControllerClient;
import org.partha.wmcommon.response.DividendSummaryResponse;

@RestController
public class TestController {

    @Autowired
    DividendControllerClient dividendControllerClient;

    @GetMapping(value = "/test")
    public DividendSummaryResponse test(){
        return dividendControllerClient.getDividendSummarByYear().getBody();
    }
}
