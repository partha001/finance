package org.partha.wmclient.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.partha.wmcommon.entities.Instrument;
import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmcommon.request.ChartDataRequest;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.partha.wmcommon.response.InstrumentDataDownloadResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "InstrumentControllerClient", url = "${feign.url}/instruments")
public interface InstrumentControllerClient {

    @GetMapping("/instrumentKeysByType")
    public List<String> getInstrumenKeysByType(@RequestParam(name = "InstrumentType") InstrumentType instrumentType);

    @GetMapping("/instrumentsByType")
    public List<Instrument> getInstrumentsByType(@RequestParam(name = "InstrumentType") InstrumentType instrumentType);

    @PostMapping("/downloadInstrumentDailyData")
    public InstrumentDataDownloadResponseDto downloadInstrumentDailyData(@RequestBody DownloadDailyDataRequest request);

    @PostMapping("/getTechnicalChartDate")
    public String getTechnicalChartData(@RequestBody ChartDataRequest chartDataRequest) throws JsonProcessingException;

}
