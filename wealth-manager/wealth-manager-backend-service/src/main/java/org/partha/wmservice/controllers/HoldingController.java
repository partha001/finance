package org.partha.wmservice.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmservice.service.ImportService;
import org.partha.wmservice.service.domain.HoldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/holdings")
public class HoldingController {

    @Autowired
    private HoldingService holdingService;

    @Autowired
    private ImportService importService;

    @PostMapping(value="/importHoldings", consumes = "multipart/form-data")
    public void importHoldings(@RequestParam("file") MultipartFile multipartFile,
                               @RequestParam ExportImportFormat inputFormat,
                               @RequestParam(defaultValue = "partha") String username) throws IOException {
        importService.importHoldings(multipartFile, inputFormat,username);
    }



}
