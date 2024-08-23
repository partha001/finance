package org.partha.wmservice.controllers;

import org.partha.wmservice.service.domain.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    AssetService assetService;

    @GetMapping("/getGraphData")
    public List<List<Object>> getGraphData(@RequestParam("users") String users){
        return assetService.getGraphData(users);
    }
}
