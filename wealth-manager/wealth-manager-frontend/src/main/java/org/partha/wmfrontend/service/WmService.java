package org.partha.wmfrontend.service;

import lombok.extern.log4j.Log4j2;
import org.partha.wmclient.client.DividendControllerClient;
import org.partha.wmcommon.response.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class WmService {

    @Autowired
    DividendControllerClient dividendControllerClient;


    public ResponseDto getDividendDetails(String payload) {
        return dividendControllerClient.getAllDividends();
    }
}
