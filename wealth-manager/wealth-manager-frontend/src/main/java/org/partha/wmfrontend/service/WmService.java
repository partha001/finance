package org.partha.wmfrontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.partha.wmclient.client.*;
import org.partha.wmcommon.constants.Constants;
import org.partha.wmcommon.enums.AssetChartType;
import org.partha.wmcommon.enums.DividendChartType;
import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmcommon.request.ChartDataRequest;
import org.partha.wmcommon.request.CreateInstrumentUniverseRequest;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.partha.wmcommon.response.CreateInstrumentUniverseResponse;
import org.partha.wmcommon.response.DividendChartDto;
import org.partha.wmcommon.response.InstrumentDataDownloadResponseDto;
import org.partha.wmcommon.response.ResponseDto;
import org.partha.wmcommon.util.DateUtil;
import org.partha.wmfrontend.util.WmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import static org.partha.wmcommon.constants.Constants.DATE_FORMAT2;

@Log4j2
@Service
public class WmService {

    @Autowired
    DividendControllerClient dividendControllerClient;

    @Autowired
    HoldingControllerClient holdingControllerClient;

    @Autowired
    AssetControllerClient assetControllerClient;

    @Autowired
    InstrumentControllerClient instrumentControllerClient;

    @Autowired
    InstrumentUniverserControllerClient instrumentUniverserControllerClient;

    ObjectMapper mapper = new ObjectMapper();

    public ResponseDto getDividendDetails(String payload) {
        return dividendControllerClient.getAllDividends();
    }


    public ResponseDto getHoldingDetails(String usernames) {
        if (Strings.isNullOrEmpty(usernames)) {
            usernames = "partha,shibani";
        }
        ResponseDto response = holdingControllerClient.getHoldings(usernames);
        return response;
    }


    public void postMarketsUpdateStockUniverse(@RequestParam Map<String, Object> inputMap, ModelMap map) {
        String universeName = inputMap.get("universeName").toString();
        String submitType = inputMap.get("submitType").toString();
        log.info("submitType:{} universeName:{}", submitType, universeName);
        if (submitType.equals("create")) {
            Set<String> allInstrumentUniverseNames = instrumentUniverserControllerClient.getAllInstrumentUniverseNames();
            if (allInstrumentUniverseNames.contains(universeName)) {
                map.put("message", "universeName already exists . please enter a different name.");
            } else {
                CreateInstrumentUniverseRequest createInstrumentUniverseRequest = CreateInstrumentUniverseRequest.builder()
                        .name(universeName)
                        .build();
                CreateInstrumentUniverseResponse instrumentUniverseResponse = instrumentUniverserControllerClient
                        .createInstrumentUniverse(createInstrumentUniverseRequest);
                if (instrumentUniverseResponse.getMessage().equals(Constants.SUCCESS)) {
                    map.put("message", "universe creation successful");
                } else {
                    map.put("message", "universe creation failed");
                }
            }
        } else if (submitType.equals("update")) {

        }
    }

    public void getDidvidendSummary(ModelMap map) {
        map.put("dividendChartTypes", WmUtil.getDividendSummaryTypes());
        DividendChartType defaultDividendCharType = DividendChartType.DividendSummaryByYear;
        map.put("selectedDividendChartType", defaultDividendCharType);
        DividendChartDto dividendChartDetails = dividendControllerClient.getDividendChartDetails(defaultDividendCharType);
        map.put("imageString", dividendChartDetails.getImageString());
    }

    public void postDividendSummary(DividendChartType dividendChartType, ModelMap map) {
        log.info("input params. dividendChartType:{}", dividendChartType);
        map.put("dividendChartTypes", WmUtil.getDividendSummaryTypes());
        map.put("selectedDividendChartType", dividendChartType);
        DividendChartDto dividendChartDetails = dividendControllerClient.getDividendChartDetails(dividendChartType);
        map.put("imageString", dividendChartDetails.getImageString());
        map.put("key", "hello dividend");
    }

    public void getMarketsDatasetup(ModelMap map) {
        map.put("instrumentTypes", WmUtil.getInstrumentTypes());
        map.put("selectedInstrumentType", "");
        map.put("selectedInstrumentName", "");
        map.put("fromHiddenField", "");
        map.put("downloadDataButton_Disabled", false);
        map.put("htmlString", "");
    }

    public void postMarketsDatasetup(Map<String, Object> inputMap, ModelMap map) {
        String selectedInstrumentType = inputMap.get("instrumentType").toString();
        String selectedInstrumentName = inputMap.get("instrumentName").toString();
        map.put("instrumentTypes", WmUtil.getInstrumentTypes());
        map.put("selectedInstrumentType", selectedInstrumentType);
        map.put("selectedInstrumentName", selectedInstrumentName);

        if (!Strings.isNullOrEmpty(selectedInstrumentType))
            map.put("instrumentKeys", instrumentControllerClient.getInstrumenKeysByType(InstrumentType.valueOf(selectedInstrumentType)));


        System.out.println("downloadDataFlag:" + inputMap.get("downloadDataFlag").toString());
        if (!Strings.isNullOrEmpty(inputMap.get("downloadDataFlag").toString())) {
            try {
                DownloadDailyDataRequest downloadDailyDatarequest = DownloadDailyDataRequest.builder()
                        .key(selectedInstrumentName)
                        .startDate("2010-01-01")
                        .endDate(DateUtil.convertUtilDateToFormattedString(new Date(), DATE_FORMAT2))
                        .build();
                InstrumentDataDownloadResponseDto dto = instrumentControllerClient.downloadInstrumentDailyData(downloadDailyDatarequest);

                map.put("downloadResponseMessage", String.format("download successful. records fetched: %s  records saved:%s", dto.getRecordsFetched(), dto.getRecordsInserted()));
                log.info("data download successful");

                ChartDataRequest chartDataRequest = ChartDataRequest.builder()
                        .key(selectedInstrumentName)
                        .build();
                log.info("request payload: {}", mapper.writeValueAsString(chartDataRequest));
                map.put("htmlString", instrumentControllerClient.getTechnicalChartData(chartDataRequest));

            } catch (Exception ex) {
                log.error("exception occurred:", ex);
                map.put("downloadResponseMessage", "error occurred while downloading data");
            }
        }
    }

    public void getHoldingsImportHoldings(ModelMap map) {
        map.put("importFormats", WmUtil.getHoldingImportFormats());
    }

    public void postHoldingsImport(ModelMap map, MultipartFile file, ExportImportFormat importFormat, String holdingOwner) throws IOException {
        holdingControllerClient.importHoldings(file, importFormat, holdingOwner);
        map.put("importFormats", WmUtil.getHoldingImportFormats());
    }

    public void getAssetCharts(ModelMap map) {
        map.put("assetChartTypes", WmUtil.getAssetChartTypes());
        AssetChartType defaultAssetChartType = AssetChartType.Chart_AssetVsTime;
        map.put("imageString", assetControllerClient.getChartData(defaultAssetChartType).getImageString());
        map.put("selectedAssetChartType", defaultAssetChartType);
    }

    public void postAssetCharts(ModelMap map, AssetChartType assetChartType) {
        log.info("input params. assetChartType:{}", assetChartType);
        map.put("assetChartTypes", WmUtil.getAssetChartTypes());
        map.put("selectedAssetChartType", assetChartType);
        map.put("imageString", assetControllerClient.getChartData(assetChartType).getImageString());
    }

    public void getMarketsManageStockUniverse(ModelMap map) {
    }

}
