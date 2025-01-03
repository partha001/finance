package org.partha.wmfrontend.service;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.partha.wmclient.client.AssetControllerClient;
import org.partha.wmclient.client.DividendControllerClient;
import org.partha.wmclient.client.HoldingControllerClient;
import org.partha.wmclient.client.InstrumentControllerClient;
import org.partha.wmcommon.enums.AssetChartType;
import org.partha.wmcommon.enums.DividendChartType;
import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmcommon.request.DownloadDailyDataRequest;
import org.partha.wmcommon.response.AssetChartDto;
import org.partha.wmcommon.response.DividendChartDto;
import org.partha.wmcommon.response.ResponseDto;
import org.partha.wmcommon.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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

    public List<String> getInstrumentKeys(InstrumentType instrumentType){
        return instrumentControllerClient.getInstrumenKeys(instrumentType);
    }

    public String downloadDailyData(String selectedKey) {
        String downloadStatusMessage = null;
        DownloadDailyDataRequest request = DownloadDailyDataRequest.builder()
                .key(selectedKey)
                .start_date("2010-01-01")
                .end_date(DateUtil.convertUtilDateToFormattedString(new Date(), DATE_FORMAT2))
                .build();
        ResponseEntity<?> responseEntity = instrumentControllerClient.downloadInstrumentDailyData(request);
        if (responseEntity.getStatusCode().value()==200){
            downloadStatusMessage = "message1";
        }else{
            downloadStatusMessage = "message2";
        }
        return downloadStatusMessage;
    }


}
