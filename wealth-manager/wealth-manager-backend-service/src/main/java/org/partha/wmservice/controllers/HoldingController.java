package org.partha.wmservice.controllers;

import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmservice.service.ImportService;
import org.partha.wmservice.service.domain.HoldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/holdings")
public class HoldingController {

    @Autowired
    private HoldingService holdingService;

    @Autowired
    private ImportService importService;

    @PostMapping(value = "/importHoldings",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void importHoldings(@RequestParam("file") MultipartFile multipartFile,
                               @RequestParam ExportImportFormat inputFormat,
                               @RequestParam(defaultValue = "partha") String username) throws IOException {
        importService.importHoldings(multipartFile, inputFormat, username);
    }


}
