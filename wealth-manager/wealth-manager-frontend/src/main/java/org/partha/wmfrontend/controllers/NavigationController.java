package org.partha.wmfrontend.controllers;


import org.partha.wmcommon.enums.ExportImportFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.EnumSet;

@Controller
public class NavigationController {

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

    @GetMapping(value = "/importHoldings")
    public ModelAndView importHoldings(){
        EnumSet<ExportImportFormat> importFormats = EnumSet.allOf(ExportImportFormat.class);
        ModelMap map = new ModelMap();
        map.put("importFormats", importFormats);
        return new ModelAndView("importHoldings",map);
    }

}
