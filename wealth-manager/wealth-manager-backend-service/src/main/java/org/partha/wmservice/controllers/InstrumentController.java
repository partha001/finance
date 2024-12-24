package org.partha.wmservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.partha.wmservice.service.DataAnalyticsClientService;
import org.partha.wmservice.service.domain.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instruments")
public class InstrumentController {

    @Autowired
    DataAnalyticsClientService dataAnalyticsClientservice;

    @Autowired
    InstrumentService instrumentService;

    @PostMapping("/downloadInstrumentDailyData")
    public ResponseEntity<?> downloadInstrumentDailyData(@RequestBody DownloadDailyDataRequest request) {
        dataAnalyticsClientservice.downloadDailyData(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<String> getInstrumenKeys(@RequestParam(name = "InstrumentType") InstrumentType instrumentType){
        return instrumentService.getInstrumentKeys(instrumentType);
    }
}
