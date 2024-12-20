package org.partha.wmservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.partha.wmservice.service.DataAnalyticsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instruments")
public class InstrumentController {

    @Autowired
    DataAnalyticsClientService service;

    @PostMapping("/downloadInstrumentData")
    public ResponseEntity<?> downloadResponseEntity(@RequestBody DownloadDailyDataRequest request) throws JsonProcessingException {
        service.downloadDailyData(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
