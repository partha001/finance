package org.partha.wmfrontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.ListUtils;
import org.partha.wmclient.client.*;
import org.partha.wmcommon.InstrumentNameComparator;
import org.partha.wmcommon.constants.Constants;
import org.partha.wmcommon.entities.Instrument;
import org.partha.wmcommon.enums.AssetChartType;
import org.partha.wmcommon.enums.DividendChartType;
import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmcommon.model.InstrumentUniverseModel;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

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

    private void populateInstrumentListForInstrumentUniverse(Model model) {
        //gettting equity list
        List<Instrument> equityList = instrumentControllerClient.getInstrumentsByType(InstrumentType.EQUITY);
        Collections.sort(equityList, new InstrumentNameComparator());
        List<List<Instrument>> equityPartitions = ListUtils.partition(equityList, 100);
        model.addAttribute("equityPartitions", equityPartitions);

        //getting index list
        List<Instrument> indexList = instrumentControllerClient.getInstrumentsByType(InstrumentType.INDEX);
        Collections.sort(indexList, new InstrumentNameComparator());
        List<List<Instrument>> indexPartitions = ListUtils.partition(indexList, 500);
        model.addAttribute("indexPartitions", indexPartitions);
    }


    public void getMarketsManageInstrumentUniverse(Model model) {
        model.addAttribute("formModel", new InstrumentUniverseModel());
        model.addAttribute("universeNames", instrumentUniverserControllerClient.getAllInstrumentUniverseNames());
        model.addAttribute("selectedSubmitType", "update");
        populateInstrumentListForInstrumentUniverse(model);
    }

    public void postMarketsUpdateInstrumentUniverse(Model model, InstrumentUniverseModel formModel) {
        String newUniverseName = formModel.getNewUniverseName();
        String selectedUniverseName = formModel.getSelectedUniverseName();
        String submitType = formModel.getSubmitType();
        populateInstrumentListForInstrumentUniverse(model);
        log.info("submitType:{} newUniverseName:{} selectedUniverseName:{}", submitType, newUniverseName, selectedUniverseName);
        if (submitType.equals("create")) {
            if (Strings.isNullOrEmpty(newUniverseName)) {
                model.addAttribute("message", "please provide a universe-name");
                return;
            }
            Set<String> allInstrumentUniverseNames = instrumentUniverserControllerClient.getAllInstrumentUniverseNames();
            if (allInstrumentUniverseNames.contains(newUniverseName)) {
                model.addAttribute("message", "universeName already exists . please enter a different name.");
            } else {
                CreateInstrumentUniverseRequest createInstrumentUniverseRequest = CreateInstrumentUniverseRequest.builder()
                        .name(newUniverseName)
                        .build();
                CreateInstrumentUniverseResponse instrumentUniverseResponse = instrumentUniverserControllerClient
                        .createInstrumentUniverse(createInstrumentUniverseRequest);
                if (instrumentUniverseResponse.getMessage().equals(Constants.SUCCESS)) {
                    model.addAttribute("message", "universe creation successful");
                    model.addAttribute("selectedUniverseName", newUniverseName);
                } else {
                    model.addAttribute("message", "universe creation failed");
                }
                model.addAttribute("universeNames", instrumentUniverserControllerClient.getAllInstrumentUniverseNames());
                model.addAttribute("selectedSubmitType", "update");
            }
        } else if (submitType.equals("update")) {
            model.addAttribute("selectedSubmitType", "update");
            model.addAttribute("selectedUniverseName", selectedUniverseName);
            model.addAttribute("universeNames", instrumentUniverserControllerClient.getAllInstrumentUniverseNames());
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


}
