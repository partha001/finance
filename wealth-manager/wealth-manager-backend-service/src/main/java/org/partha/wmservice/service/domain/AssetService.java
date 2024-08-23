package org.partha.wmservice.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.partha.wmcommon.entities.Asset;
import org.partha.wmservice.repositories.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    public List<List<Object>> getGraphData(String users){
        List<String> usernames = Arrays.asList(users.split(","));
        List<Asset> assets = assetRepository.getAsstsByUsernames(usernames);
        Map<Date, List<Asset>> assetsByDate = assets.stream().filter(item -> item.getUsername().equals("partha") &&
                        (item.getAssetName().equals("zerodha_equity") || item.getAssetName().equals("groww_mf")))
                .collect(Collectors.groupingBy(Asset::getValuationDate));
        return null;
    }
}
