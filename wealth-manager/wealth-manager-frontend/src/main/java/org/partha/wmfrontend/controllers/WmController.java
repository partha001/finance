package org.partha.wmfrontend.controllers;


import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.enums.AssetChartType;
import org.partha.wmcommon.enums.DividendChartType;
import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmcommon.model.InstrumentUniverseModel;
import org.partha.wmfrontend.service.WmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;


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
    public ModelAndView getDividendSummary() {
        ModelMap map = new ModelMap();
        wmService.getDidvidendSummary(map);
        return new ModelAndView("dividendSummary", map);
    }

    @PostMapping(value = "/dividendSummary")
    public ModelAndView postDividendSummary(@RequestParam("dividendChartType") DividendChartType dividendChartType,
                                            ModelMap map) {
        wmService.postDividendSummary(dividendChartType, map);
        return new ModelAndView("dividendSummary", map);
    }

    /***************************  Markets related endpoints **********************************************************/
    @GetMapping(value = "/markets/dataSetup")
    public ModelAndView getMarketsDatasetup() {
        ModelMap map = new ModelMap();
        wmService.getMarketsDatasetup(map);
        return new ModelAndView("marketsDatasetup", map);
    }

    @PostMapping(value = "/markets/dataSetup")
    public ModelAndView postMarketsDatasetup(@RequestParam Map<String, Object> inputMap, ModelMap map) {
        wmService.postMarketsDatasetup(inputMap, map);
        return new ModelAndView("marketsDatasetup", map);
    }

    @GetMapping(value = "/markets/analyseData")
    public ModelAndView analyseData() {
        ModelMap map = new ModelMap();
        return new ModelAndView("marketsAnalyseData", map);
    }

    @GetMapping(value = "/markets/manageStockUniverse")
    public String manageInstrumentUniverse(Model model) {
        wmService.getMarketsManageInstrumentUniverse(model);
        return "manageStockUniverse";
    }

    @PostMapping(value = "/markets/updateStockUniverse")
    public String updateInstrumentUniverse(Model model, @ModelAttribute InstrumentUniverseModel formModel) {
        wmService.postMarketsUpdateInstrumentUniverse(model,formModel);
        return "manageStockUniverse";
    }

    /***************************  holding related endpoints ******************************************************/

    @GetMapping(value = "/holdings/importHoldings")
    public ModelAndView importHoldings() {
        ModelMap map = new ModelMap();
        wmService.getHoldingsImportHoldings(map);
        return new ModelAndView("importHoldings", map);
    }

    @PostMapping(value = "/holdings/import"
            ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView importFile(@RequestParam("importFormat") ExportImportFormat importFormat,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam("holdingOwner") String holdingOwner) throws IOException {
        ModelMap map = new ModelMap();
        wmService.postHoldingsImport(map, file, importFormat, holdingOwner);
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
        wmService.getAssetCharts(map);
        return new ModelAndView("assetCharts", map);
    }

    @PostMapping(value = "/assetCharts")
    public ModelAndView assetCharts(@RequestParam("assetChartType") AssetChartType assetChartType,
                                    ModelMap map) {
        wmService.postAssetCharts(map, assetChartType);
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
