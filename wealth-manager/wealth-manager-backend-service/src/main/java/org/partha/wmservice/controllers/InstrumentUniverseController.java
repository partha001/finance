package org.partha.wmservice.controllers;

import org.partha.wmcommon.entities.InstrumentUniverse;
import org.partha.wmcommon.request.CreateInstrumentUniverseRequest;
import org.partha.wmcommon.response.CreateInstrumentUniverseResponse;
import org.partha.wmservice.service.domain.InstrumentUniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/instrumentUniverse")
public class InstrumentUniverseController {

    @Autowired
    InstrumentUniverseService instrumentUniverseService;

    @PostMapping("/createInstrumentUniverse")
    public CreateInstrumentUniverseResponse createInstrumentUniverse(@RequestBody CreateInstrumentUniverseRequest request){
        return instrumentUniverseService.createInstrumentUniverse(request);
    }

    @GetMapping("/getAllInstrumentUniverseNames")
    public Set<String> getAllInstrumentUniverseNames(){
        return instrumentUniverseService.getAllInstrumentUniverseNames();
    }

}
