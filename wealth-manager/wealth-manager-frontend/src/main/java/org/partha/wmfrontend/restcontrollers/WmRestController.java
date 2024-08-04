package org.partha.wmfrontend.restcontrollers;

import org.partha.wmcommon.dto.DividendDto;
import org.partha.wmcommon.entities.Dividend;
import org.partha.wmfrontend.service.WmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WmRestController {

    @Autowired
    WmService wmService;

    @PostMapping("/dividendDetails")
    public List<DividendDto> getDividendDetails(@RequestBody String  payload) throws InterruptedException {
        return wmService.getDividendDetails(payload);
    }
}
