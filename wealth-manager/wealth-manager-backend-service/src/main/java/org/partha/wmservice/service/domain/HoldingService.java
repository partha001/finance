package org.partha.wmservice.service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.dto.HoldingDto;
import org.partha.wmcommon.entities.Holding;
import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmcommon.response.ResponseDto;
import org.partha.wmservice.repositories.HoldingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
                    .build();
        }).collect(Collectors.toList());
        return ResponseDto.builder()
                .data(holdings)
                .build();
    }
}
