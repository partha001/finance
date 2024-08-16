package org.partha.wmfrontend.restcontrollers;

import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.response.ResponseDto;
import org.partha.wmfrontend.service.WmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@RestController
public class WmRestController {

    @Autowired
    WmService wmService;

    @PostMapping("/dividendDetails")
    public ResponseDto getDividendDetails(@RequestBody String  payload){
        return wmService.getDividendDetails(payload);
    }



    @PostMapping(value="/holdings/import"
            ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String importFile(@RequestParam("importFormat") String importFormat,
                             @RequestParam("file") MultipartFile file) throws IOException {
        log.info("importFormat:{}",importFormat);
        return wmService.importFile(file);
    }


}