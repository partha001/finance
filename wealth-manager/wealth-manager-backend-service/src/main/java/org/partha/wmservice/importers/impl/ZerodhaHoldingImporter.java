package org.partha.wmservice.importers.impl;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.partha.wmcommon.constants.Constants;
import org.partha.wmcommon.entities.Holding;
import org.partha.wmservice.importers.Importer;
import org.partha.wmservice.repositories.HoldingRepository;
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
public class ZerodhaHoldingImporter implements Importer {

    @Autowired
    HoldingRepository holdingRepository;


    @Override
    public void importData(MultipartFile multipartFile, String username) throws IOException {
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
                    .build();
            holdings.add(holding);
            //System.out.println(csvRecord);
        }
        deleteInsertHoldings(holdings,username);



    }


    public void deleteInsertHoldings(List<Holding> holdings,String username){
        int a = holdingRepository.removeByUsernameAndBrokername(username, Constants.BROKER_ZERODHA);
        log.info("deleted record count.{}",a);
        long insertedRecordCount = holdingRepository.saveAll(holdings)
                .stream().count();
        log.info("inserted record count:{}", insertedRecordCount);
    }
}
