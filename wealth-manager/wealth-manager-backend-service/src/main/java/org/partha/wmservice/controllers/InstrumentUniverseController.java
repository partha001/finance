package org.partha.wmservice.controllers;

import org.partha.wmcommon.entities.InstrumentUniverseDetail;
import org.partha.wmcommon.request.CreateInstrumentUniverseRequest;
import org.partha.wmcommon.request.UpdateInstrumentUniverseRequest;
import org.partha.wmcommon.response.CreateInstrumentUniverseResponse;
import org.partha.wmcommon.response.DeleteInstrumentUniverseResponse;
import org.partha.wmcommon.response.UpdateInstrumentUniverseResponse;
import org.partha.wmservice.service.domain.InstrumentUniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/instrumentUniverse")
public class InstrumentUniverseController {

    @Autowired
    InstrumentUniverseService instrumentUniverseService;

    @PostMapping("/createInstrumentUniverse")
    public CreateInstrumentUniverseResponse createInstrumentUniverse(@RequestBody CreateInstrumentUniverseRequest request) {
        return instrumentUniverseService.createInstrumentUniverse(request);
    }

    @PostMapping("/updateInstrumentUniverse")
    public UpdateInstrumentUniverseResponse updateInstrumentUniverse(@RequestBody UpdateInstrumentUniverseRequest request) {
        return instrumentUniverseService.updateInstrumentUniverse(request);
    }

    @PostMapping("/deleteInstrumentUniverseByName")
    public DeleteInstrumentUniverseResponse deleteInstrumentUniverseByName(@RequestParam String universeName) {
        return instrumentUniverseService.deleteInstrumentUniverseByName(universeName);
    }

    @GetMapping("/getAllInstrumentUniverseNames")
    public Set<String> getAllInstrumentUniverseNames() {
        return instrumentUniverseService.getAllInstrumentUniverseNames();
    }

    @GetMapping("/getInstrumentUniverseDetailsByUniverseName")
    public List<InstrumentUniverseDetail> getInstrumentUniverseDetailsByUniverseName(@RequestParam String universeName) {
        return instrumentUniverseService.getInstrumentUniverseDetailsByUniverseName(universeName);
    }

}
