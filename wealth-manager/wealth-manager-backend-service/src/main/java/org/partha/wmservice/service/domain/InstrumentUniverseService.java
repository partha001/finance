package org.partha.wmservice.service.domain;

import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.constants.Constants;
import org.partha.wmcommon.entities.InstrumentUniverse;
import org.partha.wmcommon.request.CreateInstrumentUniverseRequest;
import org.partha.wmcommon.response.CreateInstrumentUniverseResponse;
import org.partha.wmservice.repositories.InstrumentUniverseMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
public class InstrumentUniverseService {

    @Autowired
    private InstrumentUniverseMasterRepository instrumentUniverseMasterRepository;

    public CreateInstrumentUniverseResponse createInstrumentUniverse(CreateInstrumentUniverseRequest request) {
        InstrumentUniverse instrumentUniverse = null;
        try {
            instrumentUniverse = InstrumentUniverse.builder()
                    .name(request.getName())
                    .createdDate(new Date())
                    .build();
            instrumentUniverse = instrumentUniverseMasterRepository.save(instrumentUniverse);
            return CreateInstrumentUniverseResponse.builder()
                    .message(Constants.SUCCESS)
                    .instrumentUniverse(instrumentUniverse)
                    .build();
        } catch (Exception ex) {
            return CreateInstrumentUniverseResponse.builder()
                    .message(Constants.FAILED)
                    .instrumentUniverse(null)
                    .build();
        }
    }

    public Set<String> getAllInstrumentUniverseNames() {
        return instrumentUniverseMasterRepository
                .findAll()
                .stream()
                .map(item -> item.getName())
                .collect(Collectors.toSet());
    }
}
