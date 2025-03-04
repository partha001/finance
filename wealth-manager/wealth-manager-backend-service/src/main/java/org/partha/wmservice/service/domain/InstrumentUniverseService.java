package org.partha.wmservice.service.domain;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.constants.Constants;
import org.partha.wmcommon.entities.InstrumentUniverse;
import org.partha.wmcommon.entities.InstrumentUniverseDetail;
import org.partha.wmcommon.request.CreateInstrumentUniverseRequest;
import org.partha.wmcommon.request.UpdateInstrumentUniverseRequest;
import org.partha.wmcommon.response.CreateInstrumentUniverseResponse;
import org.partha.wmcommon.response.DeleteInstrumentUniverseResponse;
import org.partha.wmcommon.response.InstrumentDataDownloadResponseDto;
import org.partha.wmcommon.response.UpdateInstrumentUniverseResponse;
import org.partha.wmservice.repositories.InstrumentUniverseDetailRepository;
import org.partha.wmservice.repositories.InstrumentUniverseMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
public class InstrumentUniverseService {

    @Autowired
    private InstrumentUniverseMasterRepository instrumentUniverseMasterRepository;

    @Autowired
    private InstrumentUniverseDetailRepository instrumentUniverseDetailRepository;

    public CreateInstrumentUniverseResponse createInstrumentUniverse(CreateInstrumentUniverseRequest request) {
        InstrumentUniverse instrumentUniverse = null;
        try {
            if (Strings.isNullOrEmpty(request.getName())) {
                return CreateInstrumentUniverseResponse.builder()
                        .operationStatus(Constants.FAILED)
                        .message("please provide a universe-name")
                        .build();
            }
            Optional<InstrumentUniverse> byName = instrumentUniverseMasterRepository.findByName(request.getName());
            if (byName.isPresent()) {
                return CreateInstrumentUniverseResponse.builder()
                        .operationStatus(Constants.FAILED)
                        .message("universe name already exists. enter a different name")
                        .build();
            }
            instrumentUniverse = InstrumentUniverse.builder()
                    .name(request.getName())
                    .createdDate(new Date())
                    .build();
            instrumentUniverse = instrumentUniverseMasterRepository.save(instrumentUniverse);
            return CreateInstrumentUniverseResponse.builder()
                    .message("universe creation successful")
                    .operationStatus(Constants.SUCCESS)
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

    public UpdateInstrumentUniverseResponse updateInstrumentUniverse(UpdateInstrumentUniverseRequest request) {
        InstrumentUniverse universe = instrumentUniverseMasterRepository.findByName(request.getName()).get();
        instrumentUniverseDetailRepository.deleteByInstrumentUniverseMasterId(universe.getId());
        Date createdDate = new Date();
        List<InstrumentUniverseDetail> universeDetailList = request.getInstrumentKeyList().stream()
                .map(item -> InstrumentUniverseDetail.builder()
                        .instrumentKey(item)
                        .instrumentUniverseMasterId(universe.getId())
                        .createdDate(createdDate)
                        .build()).collect(Collectors.toList());
        universeDetailList = instrumentUniverseDetailRepository.saveAll(universeDetailList);
        return UpdateInstrumentUniverseResponse.builder()
                .message("instrument universe updated successfully")
                .operationStatus(Constants.SUCCESS)
                .build();
    }

    public List<InstrumentUniverseDetail> getInstrumentUniverseDetailsByUniverseName(String universeName) {
        InstrumentUniverse universe = instrumentUniverseMasterRepository.findByName(universeName).get();
        return instrumentUniverseDetailRepository.findByInstrumentUniverseMasterId(universe.getId());
    }

    public DeleteInstrumentUniverseResponse deleteInstrumentUniverseByName(String universeName) {
        if(Strings.isNullOrEmpty(universeName)){
            return DeleteInstrumentUniverseResponse.builder()
                    .operationStatus(Constants.FAILED)
                    .message("select universe name to delete")
                    .build();
        }
        Optional<InstrumentUniverse> universeOptional = instrumentUniverseMasterRepository.findByName(universeName);
        if(universeOptional.isEmpty()){
            return DeleteInstrumentUniverseResponse.builder()
                    .operationStatus(Constants.FAILED)
                    .message(String.format("universe-name: %s not found", universeName))
                    .build();
        }
        instrumentUniverseDetailRepository.deleteByInstrumentUniverseMasterId(universeOptional.get().getId());
        instrumentUniverseMasterRepository.delete(universeOptional.get());

        return DeleteInstrumentUniverseResponse.builder()
                .operationStatus(Constants.SUCCESS)
                .message(String.format("universe-name: %s deleted successfully", universeName))
                .build();
    }

    public InstrumentDataDownloadResponseDto downloadUniverseDailyData(String universeName) {
        InstrumentUniverse instrumentUniverse = instrumentUniverseMasterRepository.findByName(universeName).get();
        for (InstrumentUniverseDetail instrumentUniverseDetail : instrumentUniverseDetailRepository.findByInstrumentUniverseMasterId(instrumentUniverse.getId())) {

        }
        return null;
    }
}
