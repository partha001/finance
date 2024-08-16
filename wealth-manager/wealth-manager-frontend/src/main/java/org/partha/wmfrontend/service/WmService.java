package org.partha.wmfrontend.service;

import lombok.extern.log4j.Log4j2;
import org.partha.wmclient.client.DividendControllerClient;
import org.partha.wmclient.client.HoldingControllerClient;
import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmcommon.response.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@Service
public class WmService {

    @Autowired
    DividendControllerClient dividendControllerClient;

    @Autowired
    HoldingControllerClient holdingControllerClient;


    public ResponseDto getDividendDetails(String payload) {
        return dividendControllerClient.getAllDividends();
    }

    public String importFile(MultipartFile file) throws IOException {
        log.info("file:{}", file.getBytes().length);
        holdingControllerClient.importHoldings(file, ExportImportFormat.ZerodhaHoldingExport_Import, "partha");
        return "upload successfull";
    }
}
