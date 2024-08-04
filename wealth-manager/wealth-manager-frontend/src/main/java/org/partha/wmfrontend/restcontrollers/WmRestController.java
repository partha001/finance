package org.partha.wmfrontend.restcontrollers;

import org.partha.wmcommon.response.ResponseDto;
import org.partha.wmfrontend.service.WmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WmRestController {

    @Autowired
    WmService wmService;

    @PostMapping("/dividendDetails")
    public ResponseDto getDividendDetails(@RequestBody String  payload){
        return wmService.getDividendDetails(payload);
    }
}
