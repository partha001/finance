package org.partha.wmservice.service.domain;

import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.dto.HoldingDto;
import org.partha.wmcommon.entities.Holding;
import org.partha.wmcommon.response.ResponseDto;
import org.partha.wmservice.repositories.HoldingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class HoldingService {

    @Autowired
    HoldingRepository holdingRepository;


    public ResponseDto getHoldings(String users) {
        List<String> usernames = Arrays.asList(users.split(","));
        List<Holding> holdingList = holdingRepository.getHoldingsByUsernames(usernames);
        List<HoldingDto> holdings = holdingList.stream().map(item -> {
            return HoldingDto.builder()
                    .id(item.getId())
                    .username(item.getUsername())
                    .brokername(item.getBrokername())
                    .brokersymbol(item.getBrokersymbol())
                    .quantity(item.getQuantity())
                    .averagePrice(item.getAveragePrice())
                    .build();
        }).collect(Collectors.toList());

        //populating screener hyperlink
        holdings.forEach(holding -> {
            holding.setScreenerLink("https://www.screener.in/company/" + holding.getBrokersymbol());
//            if(holding.getBrokername().equals("zerodha")) {
//                holding.setInvestmentAmount(holding.getQuantity() * holding.getAveragePrice());
//            }
        });

        return ResponseDto.builder()
                .data(holdings)
                .build();
    }


    public int deleteHoldingsByUserByBroker(String username, String brokername) {
        int count = holdingRepository.removeByUsernameAndBrokername(username, brokername);
        log.info("deleted record count.{}", count);
        return count;
    }

    public int insertHolding(List<Holding> holdings) {
        int insertedRecordCount = holdingRepository.saveAll(holdings)
                .size();
        log.info("inserted record count:{}", insertedRecordCount);
        return insertedRecordCount;
    }


}
