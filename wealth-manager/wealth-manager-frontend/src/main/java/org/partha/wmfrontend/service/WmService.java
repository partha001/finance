package org.partha.wmfrontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.ListUtils;
import org.partha.wmclient.client.*;
import org.partha.wmcommon.InstrumentNameComparator;
import org.partha.wmcommon.constants.Constants;
import org.partha.wmcommon.entities.Instrument;
import org.partha.wmcommon.entities.InstrumentUniverseDetail;
import org.partha.wmcommon.enums.AssetChartType;
import org.partha.wmcommon.enums.DividendChartType;
import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmcommon.model.DataSetupModel;
import org.partha.wmcommon.model.InstrumentUniverseModel;
import org.partha.wmcommon.request.ChartDataRequest;
import org.partha.wmcommon.request.CreateInstrumentUniverseRequest;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.partha.wmcommon.request.UpdateInstrumentUniverseRequest;
import org.partha.wmcommon.response.*;
import org.partha.wmcommon.util.DateUtil;
import org.partha.wmfrontend.util.WmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    public void getMarketsManageInstrumentUniverse(Model model) {
        model.addAttribute("equitySet", new HashSet<String>());
        model.addAttribute("formModel", new InstrumentUniverseModel());
        model.addAttribute("universeNames", instrumentUniverserControllerClient.getAllInstrumentUniverseNames());
        populateInstrumentListForInstrumentUniverse(model);
    }

    public void postMarketsUpdateInstrumentUniverse(Model model, InstrumentUniverseModel formModel) {
        String newUniverseName = formModel.getNewUniverseName();
        String selectedUniverseName = formModel.getSelectedUniverseName();
        if (formModel.getSwitchRequest().equals("true")) {
            model.addAttribute("selectedSubmitType", "update");
            model.addAttribute("selectedUniverseName", selectedUniverseName);
            model.addAttribute("universeNames", instrumentUniverserControllerClient.getAllInstrumentUniverseNames());
            List<InstrumentUniverseDetail> instrumentDetails = instrumentUniverserControllerClient.getInstrumentUniverseDetailsByUniverseName(selectedUniverseName);
            Set<String> equitySet = instrumentDetails.stream().map(item -> item.getInstrumentKey()).collect(Collectors.toSet());
            model.addAttribute("equitySet", equitySet);
            populateInstrumentListForInstrumentUniverse(model, equitySet, new HashSet<String>());
            return;
        } else if (formModel.getDeleteRequest().equals("true")) {
            DeleteInstrumentUniverseResponse response = instrumentUniverserControllerClient.deleteInstrumentUniverseByName(selectedUniverseName);
            model.addAttribute("message", response.getMessage());
            model.addAttribute("equitySet", new HashSet<String>());
            populateInstrumentListForInstrumentUniverse(model);
            if (response.getOperationStatus().equals(Constants.SUCCESS)) {
                model.addAttribute("universeNames", instrumentUniverserControllerClient.getAllInstrumentUniverseNames());
                model.addAttribute("formModel", new InstrumentUniverseModel());
            } else {
                model.addAttribute("formModel", formModel);
                model.addAttribute("selectedUniverseName", selectedUniverseName);
                model.addAttribute("universeNames", instrumentUniverserControllerClient.getAllInstrumentUniverseNames());
            }
            return;
        } else if (formModel.getCreateRequest().equals("true")) {
            CreateInstrumentUniverseRequest request = CreateInstrumentUniverseRequest.builder()
                    .name(newUniverseName)
                    .build();
            CreateInstrumentUniverseResponse response = instrumentUniverserControllerClient
                    .createInstrumentUniverse(request);
            if (response.getOperationStatus().equals(Constants.SUCCESS)) {
                model.addAttribute("selectedUniverseName", newUniverseName);
            } else {
                model.addAttribute("selectedUniverseName", "");
            }
            model.addAttribute("equitySet", new HashSet<String>());
            populateInstrumentListForInstrumentUniverse(model);
            model.addAttribute("message", response.getMessage());
            model.addAttribute("universeNames", instrumentUniverserControllerClient.getAllInstrumentUniverseNames());
            return;
        } else if (formModel.getUpdateRequest().equals("true")) {
            if (Strings.isNullOrEmpty(formModel.getSelectedUniverseName())) {
                model.addAttribute("message", "select a universe-name to save");
            } else if (formModel.getSelectedEquities() != null && formModel.getSelectedEquities().length > 0) {
                UpdateInstrumentUniverseRequest request = UpdateInstrumentUniverseRequest.builder()
                        .name(selectedUniverseName)
                        .instrumentKeyList(Set.of(formModel.getSelectedEquities()))
                        .build();
                UpdateInstrumentUniverseResponse response = instrumentUniverserControllerClient.updateInstrumentUniverse(request);
                model.addAttribute("message", "saved");
            }

            model.addAttribute("selectedUniverseName", selectedUniverseName);
            Set<String> equitySet = null;
            if (formModel.getSelectedEquities() != null && formModel.getSelectedEquities().length > 0) {
                equitySet = Set.of(formModel.getSelectedEquities());
            } else {
                equitySet = new HashSet<String>();
            }
            model.addAttribute("equitySet", equitySet);
            populateInstrumentListForInstrumentUniverse(model, equitySet, new HashSet<String>());
            model.addAttribute("universeNames", instrumentUniverserControllerClient.getAllInstrumentUniverseNames());
            return;
        }
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
        List<List<Instrument>> indexPartitions = ListUtils.partition(indexList, 100);
        model.addAttribute("indexPartitions", indexPartitions);
    }


    private void populateInstrumentListForInstrumentUniverse(Model model, Set<String> equitySet, Set<String> indexSet) {
        //gettting equity list
        List<Instrument> equityList = instrumentControllerClient.getInstrumentsByType(InstrumentType.EQUITY);
        Collections.sort(equityList, new InstrumentNameComparator());
        List<Instrument> equityInUniverseList = new LinkedList<>();
        List<Instrument> equityNotInUniverseList = new LinkedList<>();
        equityList.stream().forEach(item -> {
            if (equitySet.contains(item.getKey())) {
                equityInUniverseList.add(item);
            } else {
                equityNotInUniverseList.add(item);
            }
        });
        List<Instrument> finalEquityList = new LinkedList<>();
        finalEquityList.addAll(equityInUniverseList);
        finalEquityList.addAll(equityNotInUniverseList);
        List<List<Instrument>> equityPartitions = ListUtils.partition(finalEquityList, 100);
        model.addAttribute("equityPartitions", equityPartitions);

        //indexlist
        List<Instrument> indexList = instrumentControllerClient.getInstrumentsByType(InstrumentType.INDEX);
        Collections.sort(indexList, new InstrumentNameComparator());
        List<List<Instrument>> indexPartitions = ListUtils.partition(indexList, 100);
        model.addAttribute("indexPartitions", indexPartitions);
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
        map.put("htmlString", "");
        map.put("universeList", instrumentUniverserControllerClient.getAllInstrumentUniverseNames());
        map.put("selectedUniverse", "");
        map.put("dataSetupModel", new DataSetupModel());
    }

    public void postMarketsDatasetup(DataSetupModel modelInput, Model modelOutput) throws JsonProcessingException {
        modelOutput.addAttribute("instrumentTypes", WmUtil.getInstrumentTypes());
        modelOutput.addAttribute("selectedInstrumentType", modelInput.getInstrumentType());
        modelOutput.addAttribute("selectedInstrumentName", modelInput.getInstrumentName());
        modelOutput.addAttribute("selectedUniverse", modelInput.getUniverseName());
        modelOutput.addAttribute("universeList", instrumentUniverserControllerClient.getAllInstrumentUniverseNames());
        modelOutput.addAttribute("dataSetupModel", modelInput);
        if (modelInput.getRequestType().equals("instrumentTypeChange")) {
            modelOutput.addAttribute("instrumentKeys", instrumentControllerClient.getInstrumenKeysByType(InstrumentType.valueOf(modelInput.getInstrumentType())));
        } else if (modelInput.getRequestType().equals("downloadInstrumentDailyData")) {
            modelOutput.addAttribute("instrumentKeys", instrumentControllerClient.getInstrumenKeysByType(InstrumentType.valueOf(modelInput.getInstrumentType())));
            DownloadDailyDataRequest downloadDailyDatarequest = DownloadDailyDataRequest.builder()
                    .key(modelInput.getInstrumentName())
                    .startDate("2010-01-01")
                    .endDate(DateUtil.convertUtilDateToFormattedString(new Date(), DATE_FORMAT2))
                    .build();
            InstrumentDataDownloadResponseDto dto = instrumentControllerClient.downloadInstrumentDailyData(downloadDailyDatarequest);

            modelOutput.addAttribute("downloadResponseMessage", String.format("download successful. records fetched: %s  records saved:%s", dto.getRecordsFetched(), dto.getRecordsInserted()));
            log.info("data download successful");
            ChartDataRequest chartDataRequest = ChartDataRequest.builder()
                    .key(modelInput.getInstrumentName())
                    .build();
            log.info("request payload: {}", mapper.writeValueAsString(chartDataRequest));
            modelOutput.addAttribute("htmlString", instrumentControllerClient.getTechnicalChartData(chartDataRequest));
        } else if (modelInput.getRequestType().equals("downloadUniverseDailyData")) {
            log.info("universeDailyData download requested for : {}", modelInput.getUniverseName());
            modelOutput.addAttribute("selectedUniverse",modelInput.getUniverseName());
            modelOutput.addAttribute("downloadResponseMessage", "select universe to get data");
        }
    }

    public void getHoldingsImportHoldings(ModelMap map) {
        map.put("importFormats", WmUtil.getHoldingImportFormats());
    }

    public void postHoldingsImport(ModelMap map, MultipartFile file, ExportImportFormat importFormat, String holdingOwner, String filePassword) throws IOException {
        holdingControllerClient.importHoldings(file, importFormat, holdingOwner,filePassword);
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
