package org.partha.wmservice.importers;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.dto.HoldingDto;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.partha.wmcommon.util.ExcelUtil;
import org.partha.wmservice.service.domain.HoldingService;
import org.partha.wmservice.service.domain.InstrumentService;

import java.util.List;

@Log4j2
public abstract class AbstractImporter implements Importer {

    protected ExcelUtil excelUtil = new ExcelUtil();
    protected HoldingService holdingService;
    protected InstrumentService instrumentService;

//    protected void downloadDailyHoldingData(String username, String brokername) {
//        log.info("downloading daily data for holdings");
//
//        List<HoldingDto> holdings = holdingService.getHoldingsByUsernameAndBrokername(username, brokername);
////        holdings
////                //.stream()
////                //.filter(item -> !Strings.isNullOrEmpty(item.getKey()) && !Strings.isNullOrEmpty(item.getYahooFinanceTicker()))
////                .forEach(item -> {
////                    if(!Strings.isNullOrEmpty(item.getKey()) && !Strings.isNullOrEmpty(item.getYahooFinanceTicker())){
////                        DownloadDailyDataRequest request = DownloadDailyDataRequest.builder()
////                                .key(item.getKey())
////                                .ticker(item.getYahooFinanceTicker())
////                                .startDate("2010-01-01")
////                                .endDate("2025-01-01")
////                                .build();
////                        instrumentService.downloadInstrumentDailyData(request);
////                    }
////
////                });
//        for (HoldingDto item : holdings) {
//            if (!Strings.isNullOrEmpty(item.getKey()) && !Strings.isNullOrEmpty(item.getYahooFinanceTicker())) {
//                DownloadDailyDataRequest request = DownloadDailyDataRequest.builder()
//                        .key(item.getKey())
//                        .ticker(item.getYahooFinanceTicker())
//                        .startDate("2010-01-01")
//                        .endDate("2025-01-01")
//                        .build();
//                instrumentService.downloadInstrumentDailyData(request);
//            }
//        }
//        log.info("downloading daily data for holdings completed");
//
//
//    }
}
