package org.partha.wmclient.client;

import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmcommon.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@FeignClient(name = "InstrumentControllerClient", url = "${feign.url}/instruments")
public interface InstrumentControllerClient {

    @GetMapping
    public List<String> getInstrumenKeys(@RequestParam(name = "InstrumentType") InstrumentType instrumentType);

}
