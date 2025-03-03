package org.partha.wmclient.client;

import org.partha.wmcommon.entities.InstrumentUniverseDetail;
import org.partha.wmcommon.request.CreateInstrumentUniverseRequest;
import org.partha.wmcommon.request.UpdateInstrumentUniverseRequest;
import org.partha.wmcommon.response.CreateInstrumentUniverseResponse;
import org.partha.wmcommon.response.UpdateInstrumentUniverseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(name = "InstrumentUniverserControllerClient", url = "${feign.url}/instrumentUniverse")
public interface InstrumentUniverserControllerClient {

    @PostMapping("/createInstrumentUniverse")
    CreateInstrumentUniverseResponse createInstrumentUniverse(@RequestBody CreateInstrumentUniverseRequest request);

    @PostMapping("/updateInstrumentUniverse")
    UpdateInstrumentUniverseResponse updateInstrumentUniverse(@RequestBody UpdateInstrumentUniverseRequest request);

    @GetMapping("/getAllInstrumentUniverseNames")
    Set<String> getAllInstrumentUniverseNames();

    @GetMapping("/getInstrumentUniverseDetailsByUniverseName")
    List<InstrumentUniverseDetail> getInstrumentUniverseDetailsByUniverseName(@RequestParam String universeName);

}
