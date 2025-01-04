package org.partha.wmservice.service.domain;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.constants.Constants;
import org.partha.wmcommon.entities.Instrument;
import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.partha.wmcommon.response.InstrumentDataDownloadResponseDto;
import org.partha.wmservice.repositories.InstrumentRepository;
import org.partha.wmservice.service.DataAnalyticsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Log4j2
@Service
public class InstrumentService {

    @Autowired
    InstrumentRepository instrumentRepository;

    @Autowired
    DataAnalyticsClientService dataAnalyticsClientService;

    public List<String> getInstrumentKeys(InstrumentType instrumentType) {
        List<String> instrumentKeys = instrumentRepository.findByInstrumentType(instrumentType.name())
                .stream()
                .map(item -> item.getKey())
                .collect(Collectors.toList());
        Collections.sort(instrumentKeys);
        return instrumentKeys;
    }

    public InstrumentDataDownloadResponseDto downloadInstrumentDailyData(DownloadDailyDataRequest request){
        Instrument instrument = instrumentRepository
                .findByKey(request.getKey())
                .orElseThrow(()-> new NoSuchElementException(String.format("no instrument found for key: %s",request.getKey())));
        request.setTicker(instrument.getYahooFinanceTicker());
        if(instrument.getInstrumentType().equals(InstrumentType.EQUITY.name()) && Strings.isNullOrEmpty(instrument.getYahooFinanceTicker())){
            request.setTicker(instrument.getSymbol()+"."+ Constants.YAHOO_FINANCE_TICKER_EXTENSION_NS);
        }
        return dataAnalyticsClientService.downloadDailyData(request);
    }


    public int loadToInstrumentMaster(List<Instrument> list) {
        return instrumentRepository.loadToInstrumentMaster(list);
	}

    public Instrument getInstrumentByKey(String instrumentKey){
        return instrumentRepository.findByKey(instrumentKey).orElse(null);
    }
}
