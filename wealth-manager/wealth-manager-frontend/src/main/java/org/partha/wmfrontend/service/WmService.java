package org.partha.wmfrontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.partha.wmcommon.response.*;
import org.partha.wmcommon.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;
import java.util.List;
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

    public void importFile(MultipartFile file, ExportImportFormat importFormat, String holdingOwner) throws IOException {
        log.info("file:{} importFormat:{} holdingOnwer:{}", file.getBytes().length, importFormat, holdingOwner);
        holdingControllerClient.importHoldings(file, importFormat, holdingOwner);
    }

    public ResponseDto getHoldingDetails(String usernames) {
        if (Strings.isNullOrEmpty(usernames)) {
            usernames = "partha,shibani";
        }
        ResponseDto response = holdingControllerClient.getHoldings(usernames);
        return response;
    }

    public AssetChartDto getAssetChart(AssetChartType assetChartType) {
        return assetControllerClient.getChartData(assetChartType);
    }

    public DividendChartDto getDividendChartDetails(DividendChartType dividendChartType) {
        return dividendControllerClient.getDividendChartDetails(dividendChartType);
    }

    public List<String> getInstrumentKeys(InstrumentType instrumentType) {
        return instrumentControllerClient.getInstrumenKeysByType(instrumentType);
    }

    public InstrumentDataDownloadResponseDto downloadDailyData(String selectedKey) {
        String downloadStatusMessage = null;
        DownloadDailyDataRequest request = DownloadDailyDataRequest.builder()
                .key(selectedKey)
                .startDate("2010-01-01")
                .endDate(DateUtil.convertUtilDateToFormattedString(new Date(), DATE_FORMAT2))
                .build();
        return instrumentControllerClient.downloadInstrumentDailyData(request);
    }


    public String getInstrumentTechnicalChart(String selectedKey) throws JsonProcessingException {
        ChartDataRequest request = ChartDataRequest.builder()
                .key(selectedKey)
                .build();
        log.info("request payload: {}", mapper.writeValueAsString(request));
        return instrumentControllerClient.getTechnicalChartData(request);
    }

    public void updateStockUniverse(@RequestParam Map<String, Object> inputMap, ModelMap map) {
        String universeName = inputMap.get("universeName").toString();
        String submitType = inputMap.get("submitType").toString();
        log.info("submitType:{} universeName:{}", submitType, universeName);
        if (submitType.equals("create")) {
            Set<String> allInstrumentUniverseNames = instrumentUniverserControllerClient.getAllInstrumentUniverseNames();
            if(allInstrumentUniverseNames.contains(universeName)){
                map.put("message","universeName already exists . please enter a different name.");
            }else{
                CreateInstrumentUniverseRequest createInstrumentUniverseRequest = CreateInstrumentUniverseRequest.builder()
                        .name(universeName)
                        .build();
                CreateInstrumentUniverseResponse instrumentUniverseResponse = instrumentUniverserControllerClient
                        .createInstrumentUniverse(createInstrumentUniverseRequest);
                if(instrumentUniverseResponse.getMessage().equals(Constants.SUCCESS)){
                    map.put("message","universe creation successful");
                }else{
                    map.put("message","universe creation failed");
                }
            }
        } else if (submitType.equals("update")) {

        }
    }
}
