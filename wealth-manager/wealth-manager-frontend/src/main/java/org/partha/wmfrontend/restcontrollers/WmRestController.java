package org.partha.wmfrontend.restcontrollers;

import org.partha.wmcommon.response.ResponseDto;
import org.partha.wmfrontend.service.WmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public String importFile(Model model, @RequestParam("file") MultipartFile file, @RequestHeader HttpHeaders headers) throws IOException {
        return wmService.importFile(file,headers);
    }


}
