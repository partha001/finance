package org.partha.wmservice.importers.impl;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.partha.wmcommon.constants.Constants;
import org.partha.wmcommon.entities.Holding;
import org.partha.wmservice.importers.AbstractImporter;
import org.partha.wmservice.importers.Importer;
import org.partha.wmservice.service.domain.HoldingService;
import org.partha.wmservice.service.domain.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Transactional
@Component("zerodhaHoldingImporter")
public class ZerodhaHoldingImporter extends AbstractImporter {

    @Autowired
    public ZerodhaHoldingImporter(HoldingService holdingService,
                                  InstrumentService instrumentService) {
        this.holdingService = holdingService;
        this.instrumentService = instrumentService;
    }

    @Override
    public void importData(MultipartFile multipartFile, String username,String filePassword) throws IOException {
        BufferedReader fileReader = new BufferedReader(new
                InputStreamReader(multipartFile.getInputStream(), "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withSkipHeaderRecord(true).withFirstRecordAsHeader());

        List<String> headerNames = csvParser.getHeaderNames();
        log.info("columnsHeaders:{}", headerNames);

        Iterable<CSVRecord> csvRecords = csvParser.getRecords();

        List<Holding> holdings = new ArrayList<>();
        Holding holding = null;
        for (CSVRecord csvRecord : csvRecords) {
            holding = Holding.builder()
                    .username(username)
                    .brokername(Constants.BROKER_ZERODHA)
                    .brokersymbol(csvRecord.get("Instrument"))
                    .quantity(Strings.isNullOrEmpty(csvRecord.get("Qty.")) ? null : Integer.parseInt(csvRecord.get("Qty.")))
                    .averagePrice(Strings.isNullOrEmpty(csvRecord.get("Avg. cost"))? null: Double.parseDouble(csvRecord.get("Avg. cost")))
                    .build();
            holdings.add(holding);
            //System.out.println(csvRecord);
        }
        holdingService.deleteHoldingsByUserByBroker(username,Constants.BROKER_ZERODHA);
        holdingService.insertHolding(holdings);

        //enriching now. updating key and isin
        holdingService.updateKeyForGiverUserAndBrokerForSameSymbol(username,Constants.BROKER_ZERODHA);
        //fetch daily data for instruments
        //downloadDailyHoldingData(username, Constants.BROKER_ZERODHA);

    }
}
