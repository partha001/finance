package org.partha.wmclient.client;

import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "InstrumentControllerClient", url = "${feign.url}/instruments")
public interface InstrumentControllerClient {

    @GetMapping
    public List<String> getInstrumenKeys(@RequestParam(name = "InstrumentType") InstrumentType instrumentType);

    @PostMapping("/downloadInstrumentDailyData")
    public ResponseEntity<?> downloadInstrumentDailyData(@RequestBody DownloadDailyDataRequest request) ;

}
