package org.partha.wmfrontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String importHoldings(){
        return "importHoldings";
    }

}
