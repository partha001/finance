package org.partha.wmclient.client;

import org.partha.wmcommon.enums.AssetChartType;
import org.partha.wmcommon.response.AssetChartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "AssetControllerClient", url = "${feign.url}/assets")
public interface AssetControllerClient {


    @GetMapping("/getGraphData")
    public List<List<Object>> getGraphData(@RequestParam("users") String users);

    @GetMapping("/getChartData")
    public AssetChartDto getChartData(@RequestParam(name = "assetChartType") AssetChartType assetChartType);

}
