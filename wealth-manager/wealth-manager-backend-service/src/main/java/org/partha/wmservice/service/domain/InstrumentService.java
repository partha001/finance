package org.partha.wmservice.service.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.partha.wmcommon.constants.Constants;
import org.partha.wmcommon.entities.DailyPrice;
import org.partha.wmcommon.entities.Instrument;
import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmcommon.request.ChartDataRequest;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.partha.wmcommon.response.InstrumentDataDownloadResponseDto;
import org.partha.wmcommon.util.DateUtil;
import org.partha.wmservice.repositories.DailyPriceRepository;
import org.partha.wmservice.repositories.InstrumentRepository;
import org.partha.wmservice.service.DataAnalyticsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
public class InstrumentService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    InstrumentRepository instrumentRepository;

    @Autowired
    DataAnalyticsClientService dataAnalyticsClientService;

    @Autowired
    DailyPriceRepository dailyPriceRepository;

    public List<String> getInstrumentKeys(InstrumentType instrumentType) {
        List<String> instrumentKeys = instrumentRepository.findByInstrumentType(instrumentType.name())
                .stream()
                .map(item -> item.getKey())
                .collect(Collectors.toList());
        Collections.sort(instrumentKeys);
        return instrumentKeys;
    }


    public List<Instrument> getInstrumentsByType(InstrumentType instrumentType) {
        List<Instrument> instrumentList = instrumentRepository.findByInstrumentType(instrumentType.name())
                .stream()
                .collect(Collectors.toList());
        return instrumentList;
    }

    public InstrumentDataDownloadResponseDto downloadInstrumentDailyData(DownloadDailyDataRequest request) {
        Instrument instrument = instrumentRepository
                .findByKey(request.getKey())
                .orElseThrow(() -> new NoSuchElementException(String.format("no instrument found for key: %s", request.getKey())));
        request.setTicker(instrument.getYahooFinanceTicker());
        if (instrument.getInstrumentType().equals(InstrumentType.EQUITY.name()) && Strings.isNullOrEmpty(instrument.getYahooFinanceTicker())) {
            request.setTicker(instrument.getSymbol() + "." + Constants.YAHOO_FINANCE_TICKER_EXTENSION_NS);
        }
        return dataAnalyticsClientService.downloadDailyData(request);
    }


//    public int loadToInstrumentMaster(List<Instrument> list) {
//        return instrumentRepository.loadToInstrumentMaster(list);
//	}

    public Instrument getInstrumentByKey(String instrumentKey) {
        return instrumentRepository.findByKey(instrumentKey).orElse(null);
    }

    public List<String> downloadNseEquityDailyData() throws JsonProcessingException {
        List<Instrument> instrumentList = instrumentRepository.findAll().stream()
                .filter(item ->
                        //Strings.isNullOrEmpty(item.getExchange())
                        item.getInstrumentType().equals("EQUITY")
                                //&& Strings.isNullOrEmpty(item.getExchange())
                                && item.getExchange().equals("NSE"))
                .collect(Collectors.toList());
        List<String> result = new ArrayList<>();
        for (Instrument instrument : instrumentList) {
            DownloadDailyDataRequest request = DownloadDailyDataRequest.builder()
                    .key(instrument.getKey())
                    .ticker(instrument.getSymbol() + ".NS")
                    .startDate("2010-01-01")
                    .endDate(DateUtil.convertUtilDateToFormattedString(new Date(), "yyyy-MM-dd"))
                    .build();
            InstrumentDataDownloadResponseDto responseDto = dataAnalyticsClientService.downloadDailyData(request);

            if (responseDto.getRecordsFetched() > 0) {
                log.info("response payload:{}", objectMapper.writeValueAsString(responseDto));
                instrument.setYahooFinanceTicker(request.getTicker());
                instrumentRepository.save(instrument);
                result.add(responseDto.getKey());
            }
        }
        return result;
    }


    public List<InstrumentDataDownloadResponseDto> downloadIndexDailyData() {
        List<Instrument> instrumentList = instrumentRepository.findAll().stream()
                .filter(item -> item.getInstrumentType().equals("INDEX"))
                .collect(Collectors.toList());
        String indexesWithoutTicker = instrumentList.stream()
                .filter(item -> Strings.isNullOrEmpty(item.getYahooFinanceTicker()))
                .map(item -> item.getKey()).collect(Collectors.joining(", "));
        log.info("skipping download for these keys since yfinance tiker not available:{}", indexesWithoutTicker);

        List<Instrument> indexesWithTicker = instrumentList.stream().filter(item -> !Strings.isNullOrEmpty(item.getYahooFinanceTicker())).collect(Collectors.toList());

        List<InstrumentDataDownloadResponseDto> result = new ArrayList<>();
        indexesWithTicker.forEach( item -> {
            DownloadDailyDataRequest request = DownloadDailyDataRequest.builder()
                    .key(item.getKey())
                    .ticker(item.getYahooFinanceTicker())
                    .startDate("2010-01-01")
                    .endDate(DateUtil.convertUtilDateToFormattedString(new Date(), "yyyy-MM-dd"))
                    .build();
            InstrumentDataDownloadResponseDto responseDto = dataAnalyticsClientService.downloadDailyData(request);
            log.info("key:{}   yfinanceTicker:{}   datafetched:{}   dataInserted:{}", responseDto.getKey(), responseDto.getTicker(), responseDto.getRecordsFetched(), responseDto.getRecordsInserted());
            result.add(responseDto);

        });
        return result;
    }

    /**
     * it is to be kept in mind that will reading data page-wise if sorting is not applied on any unique key column
     * that 2 page might pick up same/overlapping data
     * @param filepath
     */
    public void exportInstrumentDailyData(String filepath) {
        log.info("filepath:{}", filepath);
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(filepath));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("pricedate", "key", "adjcloseprice", "closeprice", "openprice", "high", "low", "volume"));
        ) {

            Sort sort = Sort.by("id").ascending();
            int pageSize = 100000;
            Pageable pageRequest = PageRequest.of(0, pageSize, sort);
            Page<DailyPrice> dailyPricePage;
            do {
                dailyPricePage = dailyPriceRepository.findAll(pageRequest);

                //performing work on a page
                for (DailyPrice dailyPrice : dailyPricePage.getContent()) {
                    csvPrinter.printRecord(
                            DateUtil.convertUtilDateToFormattedString(dailyPrice.getPriceDate(), "yyy-MM-dd"),
                            dailyPrice.getKey(),
                            dailyPrice.getAdjClosePrice(),
                            dailyPrice.getClosePrice(),
                            dailyPrice.getOpenPrice(),
                            dailyPrice.getHigh(),
                            dailyPrice.getLow(),
                            dailyPrice.getVolume()
                    );
                }
                log.info("writng page:{} of totalPages:", dailyPricePage.getNumber(), dailyPricePage.getTotalPages());
                pageRequest = pageRequest.next();
                //csvPrinter.flush();
            } while (dailyPricePage.hasNext());


            csvPrinter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void importInstrumentDailyData(String filepath) {

        dailyPriceRepository.deleteAll();
        Date date = new Date();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath), 1048576 * 10);
             CSVParser csvParser = new CSVParser(reader,
                     CSVFormat.DEFAULT.withHeader("pricedate", "key", "adjcloseprice", "closeprice", "openprice", "high", "low", "volume").withIgnoreHeaderCase().withTrim())
        ) {
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(reader);
            List<DailyPrice> dailyPriceList = new ArrayList<>();
            int writeChunkSize = 100000;
            int recordCount = 0;

            for (CSVRecord record : csvParser) {
                if (csvParser.getCurrentLineNumber() == 1) {
                    if (!record.isConsistent()) {
                        throw new IllegalArgumentException("Please validate the fomat of the file");
                    }
                    // Ignore header
                    continue;
                }

                //validation can be plugged in here
                DailyPrice dailyPrice = DailyPrice.builder()
                        .priceDate(DateUtil.convertStringToUtilDate(record.get("pricedate"), "yyyy-MM-dd"))
                        .key(record.get("key"))
                        .adjClosePrice(Double.parseDouble(record.get("adjcloseprice")))
                        .closePrice(Double.parseDouble(record.get("closeprice")))
                        .openPrice(Double.parseDouble(record.get("openprice")))
                        .high(Double.parseDouble(record.get("high")))
                        .low(Double.parseDouble(record.get("low")))
                        .volume(Double.parseDouble(record.get("volume")))
                        .createTime(date)
                        .build();

                dailyPriceList.add(dailyPrice);
                if(dailyPriceList.size() % writeChunkSize==0){
                    dailyPriceRepository.saveAll(dailyPriceList);
                    dailyPriceList.clear();
                }
            }

            if(dailyPriceList.size()>0){
                dailyPriceRepository.saveAll(dailyPriceList);
                dailyPriceList.clear();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public String getTechnicalChartData(ChartDataRequest chartDataRequest) throws JsonProcessingException {
        log.info("request payload: {}", objectMapper.writeValueAsString(chartDataRequest));
        Optional<Instrument> instrumentOptional = instrumentRepository.findByKey(chartDataRequest.getKey());
        chartDataRequest.setTicker(instrumentOptional.get().getYahooFinanceTicker());
        chartDataRequest.setStartDate("2010-01-01");
        chartDataRequest.setEndDate(DateUtil.convertUtilDateToFormattedString(new Date(),"yyyy-MM-dd"));
        return dataAnalyticsClientService.getTechnicalChartData(chartDataRequest);
    }

}
