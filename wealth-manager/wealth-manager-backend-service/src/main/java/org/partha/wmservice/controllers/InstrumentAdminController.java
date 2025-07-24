package org.partha.wmservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import org.partha.wmservice.service.domain.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/instruments")
public class InstrumentAdminController {

    @Autowired
    InstrumentService instrumentService;

    @Operation(summary = "download all NSE equity daily data",
            description = "downloads all NSE equity daily data since 2010 and stores in dailyPriceMaster table ")
    @GetMapping("/downloadNseEquityDailyData")
    public List<String> downloadNseEquityDailyData() throws JsonProcessingException {
        return instrumentService.downloadNseEquityDailyData();
    }
}
