package org.partha.wmservice.controllers;

import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.partha.wmcommon.response.InstrumentDataDownloadResponseDto;
import org.partha.wmservice.service.domain.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instruments")
public class InstrumentController {

    @Autowired
    InstrumentService instrumentService;

    @PostMapping("/downloadInstrumentDailyData")
    public InstrumentDataDownloadResponseDto downloadInstrumentDailyData(@RequestBody DownloadDailyDataRequest request) {
        return instrumentService.downloadInstrumentDailyData(request);
    }

    @GetMapping("/instrumentKeysByType")
    public List<String> getInstrumenKeysByType(@RequestParam(name = "InstrumentType") InstrumentType instrumentType){
        return instrumentService.getInstrumentKeys(instrumentType);
    }

}
