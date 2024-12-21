package org.partha.wmservice.service.domain;

import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmservice.repositories.IndexMasterRepository;
import org.partha.wmservice.repositories.StockMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class InstrumentService {

    @Autowired
    IndexMasterRepository indexMasterRepository;

    @Autowired
    StockMasterRepository stockMasterRepository;

    public List<String> getInstrumentKeys(InstrumentType instrumentType) {
        List<String> instrumentKeys = new ArrayList<String>();
        if (instrumentType.equals(InstrumentType.Index)) {
            instrumentKeys = indexMasterRepository.findAll().stream().map(item -> item.getIndexKey()).collect(Collectors.toList());
        }
        else if(instrumentType.equals(InstrumentType.Equity)){
            instrumentKeys=  stockMasterRepository.findAll().stream().map(item-> item.getKey()).collect(Collectors.toList());
        }
        return instrumentKeys;
    }


}
