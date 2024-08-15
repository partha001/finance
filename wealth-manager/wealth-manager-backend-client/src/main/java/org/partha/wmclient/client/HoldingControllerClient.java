package org.partha.wmclient.client;

import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmcommon.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "HoldingControllerClient", url = "${feign.url}")
public interface HoldingControllerClient {


    @PostMapping(value="/holdings/importHoldings",
            //consumes = "multipart/form-data",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void importHoldings(@RequestParam("file") MultipartFile multipartFile,
                               @RequestParam ExportImportFormat inputFormat,
                               @RequestParam(defaultValue = "partha") String username,
                               @RequestHeader HttpHeaders headers) throws IOException;

}
