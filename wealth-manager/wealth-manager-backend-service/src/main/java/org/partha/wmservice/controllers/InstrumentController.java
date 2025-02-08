package org.partha.wmservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.partha.wmcommon.response.InstrumentDataDownloadResponseDto;
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
    InstrumentService instrumentService;

    @Operation(summary = "download daily data for a given yfinancy ticker",
            description = "download daily data for a given yfinance ticker and stores in the table wealthmanager.dailypricemaster d")
    @PostMapping("/downloadInstrumentDailyData")
    public InstrumentDataDownloadResponseDto downloadInstrumentDailyData(@RequestBody DownloadDailyDataRequest request) {
        return instrumentService.downloadInstrumentDailyData(request);
    }

    @GetMapping("/instrumentKeysByType")
    public List<String> getInstrumenKeysByType(@RequestParam(name = "InstrumentType") InstrumentType instrumentType){
        return instrumentService.getInstrumentKeys(instrumentType);
    }


    @GetMapping
    public List<String> downloadAllData() throws JsonProcessingException {
        return instrumentService.downloadAllData();
    }


    @GetMapping("/exportInstrumentDailyData")
    public ResponseEntity exportInstrumentDailyData(){
        instrumentService.exportInstrumentDailyData();
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/importInstrumentDailyData")
    public ResponseEntity importInstrumentDailyData(){
        instrumentService.importInstrumentDailyData();
        return new ResponseEntity(HttpStatus.OK);
    }





}
