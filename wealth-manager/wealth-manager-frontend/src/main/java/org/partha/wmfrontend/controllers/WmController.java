package org.partha.wmfrontend.controllers;


import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.enums.AssetChartType;
import org.partha.wmcommon.enums.DividendChartType;
import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmcommon.response.DividendChartDto;
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

    /***************************  dividend related endpoints *****************************************************/

    @GetMapping(value = "/dividends")
    public String dividends() {
        return "dividends";
    }

    @GetMapping(value = "/dividendSummary")
    public ModelAndView dividendSummary() {
        ModelMap map = new ModelMap();
        map.put("dividendChartTypes", WmUtil.getDividendSummaryTypes());
        DividendChartType defaultDividendCharType = DividendChartType.DividendSummaryByYear;
        map.put("selectedDividendChartType", defaultDividendCharType);
        DividendChartDto dividendChartDetails = wmService.getDividendChartDetails(defaultDividendCharType);
        map.put("imageString", dividendChartDetails.getImageString());
        return new ModelAndView("dividendSummary", map);
    }

    @PostMapping(value = "/dividendSummary")
    public ModelAndView getdividendSummary(@RequestParam("dividendChartType") DividendChartType dividendChartType,
                                           ModelMap map) {
        log.info("input params. dividendChartType:{}", dividendChartType);
        map.put("dividendChartTypes", WmUtil.getDividendSummaryTypes());
        map.put("selectedDividendChartType", dividendChartType);
        DividendChartDto dividendChartDetails = wmService.getDividendChartDetails(dividendChartType);
        map.put("imageString", dividendChartDetails.getImageString());
        map.put("key", "hello dividend");
        return new ModelAndView("dividendSummary", map);
    }


    /***************************  holding related endpoints ******************************************************/

    @GetMapping(value = "/holdings/importHoldings")
    public ModelAndView importHoldings() {
        ModelMap map = new ModelMap();
        map.put("importFormats", WmUtil.getHoldingImportFormats());
        return new ModelAndView("importHoldings", map);
    }

    @PostMapping(value = "/holdings/import"
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ModelAndView importFile(@RequestParam("importFormat") ExportImportFormat importFormat,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam("holdingOwner") String holdingOwner) throws IOException {
        log.info("importFormat:{}", importFormat);
        wmService.importFile(file, importFormat, holdingOwner);
        ModelMap map = new ModelMap();
        map.put("importFormats", WmUtil.getHoldingImportFormats());
        return new ModelAndView("importHoldings", map);
    }


    /***************************  asset related endpoints **********************************************************/
    @GetMapping(value = "/assets")
    public String assets() {
        return "assets";
    }


    @GetMapping(value = "/assetCharts")
    public ModelAndView assetCharts(Model model) {
        ModelMap map = new ModelMap();
        map.put("assetChartTypes", WmUtil.getAssetChartTypes());
        AssetChartType defaultAssetChartType = AssetChartType.Chart_AssetVsTime;
        map.put("imageString", wmService.getAssetChart(defaultAssetChartType).getImageString());
        map.put("selectedAssetChartType", defaultAssetChartType);
        return new ModelAndView("assetCharts", map);
    }

    @PostMapping(value = "/assetCharts")
    public ModelAndView assetCharts(@RequestParam("assetChartType") AssetChartType assetChartType,
                                    ModelMap map) {
        log.info("input params. assetChartType:{}", assetChartType);
        map.put("assetChartTypes", WmUtil.getAssetChartTypes());
        map.put("selectedAssetChartType", assetChartType);
        map.put("imageString", wmService.getAssetChart(assetChartType).getImageString());
        return new ModelAndView("assetCharts", map);
    }


    /***************************  others  **************************************************************************/

    @GetMapping(value = "/home")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/stocks")
    public String stocks() {
        return "stocks";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }


}
