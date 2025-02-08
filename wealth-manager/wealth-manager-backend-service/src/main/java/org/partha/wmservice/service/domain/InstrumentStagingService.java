package org.partha.wmservice.service.domain;

import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.entities.Instrument;
import org.partha.wmcommon.entities.InstrumentStaging;
import org.partha.wmservice.repositories.InstrumentStagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class InstrumentStagingService {

    @Autowired
    InstrumentStagingRepository instrumentStagingRepository;

    public int loadToInstrumentMasterStaging(List<InstrumentStaging> list) {
        return instrumentStagingRepository.loadToInstrumentMasterStaging(list);
    }


    public void clearEquityListForStaging(String sourceName) {
        instrumentStagingRepository.deleteAllBySourceName(sourceName);

    }

    public void auditData(String sourceName){
        log.info("auditing for sourceName:{}", sourceName);
        List<String> list = instrumentStagingRepository.additionalKeysInInstrumentMaster(sourceName);
        log.info("additional keys in instrument-master: {}", list);
        list =instrumentStagingRepository.additionalKeysInInstrumentMasterStaging(sourceName);
        log.info("additional keys in instrument-master-staging : {}", list);
    }
}
