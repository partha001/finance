package org.partha.wmservice.service.domain;

import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.dto.HoldingDto;
import org.partha.wmcommon.entities.Holding;
import org.partha.wmcommon.projections.HoldingProjection;
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
        List<HoldingProjection> holdingList = holdingRepository.getHoldingsProjectionByUsernames(usernames);
        List<HoldingDto> holdings = holdingList.stream().map(item -> {
            return HoldingDto.builder()
                    .id(item.getId())
                    .username(item.getUsername())
                    .brokername(item.getBrokername())
                    .brokersymbol(item.getBrokersymbol())
                    .quantity(item.getQuantity())
                    .averagePrice(item.getAveragePrice())
                    .screenerLink("https://www.screener.in/company/" + item.getSymbol())
                    .build();
        }).collect(Collectors.toList());
        return ResponseDto.builder()
                .data(holdings)
                .build();
    }


    public List<HoldingDto> getHoldingsByUsernameAndBrokername(String username, String brokername){
        List<HoldingProjection> holdingList = holdingRepository.getHoldingsProjectionByUsernameAndBrokername(username,brokername);
        return holdingList.stream().map(item -> {
            return HoldingDto.builder()
                    .id(item.getId())
                    .username(item.getUsername())
                    .brokername(item.getBrokername())
                    .brokersymbol(item.getBrokersymbol())
                    .quantity(item.getQuantity())
                    .averagePrice(item.getAveragePrice())
                    .key(item.getKey())
                    .yahooFinanceTicker(item.getYahooFinanceTicker())
                    .screenerLink("https://www.screener.in/company/" + item.getSymbol())
                    .build();
        }).collect(Collectors.toList());
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

    public int updateKeyForGiverUserAndBrokerForSameIsin(String username, String brokername) {
        int recordsUpdated = holdingRepository.updateKeyForGiverUserAndBrokerForSameIsin(username, brokername);
        log.info("username:{}  brokername:{}   key-update-count:{}", username, brokername, recordsUpdated);
        return recordsUpdated;
    }


    public int updateKeyForGiverUserAndBrokerForSameSymbol(String username, String brokername) {
        int recordsUpdated = holdingRepository.updateKeyForGiverUserAndBrokerForSameSymbol(username, brokername);
        log.info("username:{}  brokername:{}   key-update-count:{}", username, brokername, recordsUpdated);
        return recordsUpdated;
    }
}
