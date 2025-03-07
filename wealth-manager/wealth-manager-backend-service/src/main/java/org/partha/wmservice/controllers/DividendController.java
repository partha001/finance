package org.partha.wmservice.controllers;


import org.partha.wmcommon.enums.DividendChartType;
import org.partha.wmcommon.response.DividendChartDto;
import org.partha.wmcommon.response.ResponseDto;
import org.partha.wmservice.service.domain.DividendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/dividend")
public class DividendController {

    @Autowired
    private DividendService service;


    @GetMapping("/getAllDividends")
    public ResponseDto getAllDividends() {
        return service.getAllDividends();
    }


    @GetMapping("/getDividendChartDetails")
    public DividendChartDto getDividendChartDetails(@RequestParam(name = "dividendChartType")  DividendChartType dividendChartType){
        return service.getDividendChartDetails(dividendChartType);
    }

}
