package org.partha.wmclient.client;

import org.partha.wmcommon.request.CreateInstrumentUniverseRequest;
import org.partha.wmcommon.response.CreateInstrumentUniverseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@FeignClient(name = "InstrumentUniverserControllerClient", url = "${feign.url}/instrumentUniverse")
public interface InstrumentUniverserControllerClient {

    @PostMapping("/createInstrumentUniverse")
    public CreateInstrumentUniverseResponse createInstrumentUniverse(@RequestBody CreateInstrumentUniverseRequest request);

    @GetMapping("/getAllInstrumentUniverseNames")
    public Set<String> getAllInstrumentUniverseNames();

}
