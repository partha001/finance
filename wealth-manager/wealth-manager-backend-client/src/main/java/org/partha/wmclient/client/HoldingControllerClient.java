package org.partha.wmclient.client;

import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmcommon.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "HoldingControllerClient", url = "${feign.url}/holdings")
public interface HoldingControllerClient {


    @PostMapping(value = "/importHoldings",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void importHoldings(@RequestPart("file") MultipartFile multipartFile,
                               @RequestParam ExportImportFormat inputFormat,
                               @RequestParam(defaultValue = "partha") String username,
                               @RequestParam String filePassword) throws IOException;

    @GetMapping(value="/getHoldings")
    public ResponseDto getHoldings(@RequestParam("users") String users);


}
