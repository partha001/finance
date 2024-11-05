package org.partha.wmfrontend.controllers;


import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmfrontend.service.WmService;
import org.partha.wmfrontend.util.WmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@Log4j2
@Controller
public class WmController {

    @Autowired
    WmService wmService;

    @GetMapping(value = "/home")
    public String home(){
        return "home";
    }

    @GetMapping(value = "/stocks")
    public String stocks(){
        return "stocks";
    }

    @GetMapping(value = "/dividends")
    public String dividends(){
        return "dividends";
    }

    @GetMapping(value = "/dividendSummary")
    public String dividendSummary(){
        return "dividendSummary";
    }

    @GetMapping(value = "/about")
    public String about(){
        return "about";
    }


    /** holding related endpoints **/

    @GetMapping(value = "/holdings/importHoldings")
    public ModelAndView importHoldings(){
        ModelMap map = new ModelMap();
        map.put("importFormats", WmUtil.getHoldingImportFormats());
        return new ModelAndView("importHoldings",map);
    }

    @PostMapping(value = "/holdings/import"
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ModelAndView importFile(@RequestParam("importFormat") ExportImportFormat importFormat,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam("holdingOwner") String holdingOwner) throws IOException {
        log.info("importFormat:{}", importFormat);
        wmService.importFile( file, importFormat, holdingOwner);
        ModelMap map = new ModelMap();
        map.put("importFormats", WmUtil.getHoldingImportFormats());
        return new ModelAndView("importHoldings", map);
    }
    /** asset related endpoints **/
    @GetMapping(value = "/assets")
    public String assets(){
        return "assets";
    }


    @GetMapping(value = "/assetCharts")
    public String assetCharts(Model model){
        model.addAttribute("graphTitle", "special graph");
        String imageString = wmService.getCharData();
        model.addAttribute("imageString",imageString);
        return "assetCharts";
    }






}
