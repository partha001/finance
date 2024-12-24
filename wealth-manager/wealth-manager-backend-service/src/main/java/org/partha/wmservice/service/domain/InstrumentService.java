package org.partha.wmservice.service.domain;

import com.google.common.base.Strings;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.entities.Instrument;
import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.partha.wmservice.repositories.IndexMasterRepository;
import org.partha.wmservice.repositories.StockMasterRepository;
import org.partha.wmservice.service.DataAnalyticsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class InstrumentService {

    @Autowired
    IndexMasterRepository indexMasterRepository;

    @Autowired
    StockMasterRepository stockMasterRepository;

    @Autowired
    DataAnalyticsClientService dataAnalyticsClientService;

    public List<String> getInstrumentKeys(InstrumentType instrumentType) {
        List<String> instrumentKeys = new ArrayList<>();
        if (instrumentType.equals(InstrumentType.Index)) {
            instrumentKeys = indexMasterRepository.findAll().stream().map(item -> item.getKey()).collect(Collectors.toList());
        }
        else if(instrumentType.equals(InstrumentType.Equity)){
            instrumentKeys=  stockMasterRepository.findAll().stream().map(item-> item.getKey()).collect(Collectors.toList());
        }
        Collections.sort(instrumentKeys);
        return instrumentKeys;
    }

    public void downloadInstrumentDailyData(DownloadDailyDataRequest request){
        if(Strings.isNullOrEmpty(request.getTicker())){
            if(request.getKey().startsWith("NSE")){


            }else if(request.getKey().startsWith("INDEX")){

            }
        }
        dataAnalyticsClientService.downloadDailyData(request);
    }


    public Instrument getInstrumentDetails(String key){
        if(key.startsWith("NSE:")){
            return stockMasterRepository
                    .findByKey(key)
                    .orElseThrow(()-> new EntityNotFoundException(String.format("entity not found with key: %s",key)));
        }else if(key.startsWith("INDEX:")){
            return indexMasterRepository
                    .findByKey(key)
                    .orElseThrow(()-> new EntityNotFoundException(String.format("entity not found with key: %s",key)));

        }
        throw new RuntimeException(String.format("Not a valid key: %s",key));
    }


}
