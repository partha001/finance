package org.partha.wmfrontend.service;

import lombok.extern.log4j.Log4j2;
import org.partha.wmclient.client.DividendControllerClient;
import org.partha.wmcommon.dto.DividendDto;
import org.partha.wmcommon.entities.Dividend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class WmService {

    @Autowired
    DividendControllerClient dividendControllerClient;


    public List<DividendDto> getDividendDetails(String payload) throws InterruptedException {
        log.info("payload:{}", payload);
        return dividendControllerClient.getDividendDetails().getBody().getList();
    }
}
