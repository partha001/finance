package org.partha.wmservice.loaders;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.partha.wmcommon.constants.Constants;
import org.partha.wmcommon.entities.Stock;
import org.partha.wmcommon.util.CommonUtil;
import org.partha.wmcommon.util.DateUtil;
import org.partha.wmcommon.util.ExcelUtil;
import org.partha.wmservice.service.domain.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Component
public class NSEDataLoader {

    private static final Logger logger = LoggerFactory.getLogger(AssetLoader.class);


    ExcelUtil excelUtil = new ExcelUtil();

    @Value("${nse.equity.list-fileName}")
    private String nseEquity;

    @Value("${nse.equity.list-LoadFlag}")
    private Boolean nseEquityListLoadFlag;


    @Value("${nse.equity.smelist-fileName}")
    private String nseEquitySme;

    @Value("${nse.equity.smelist-LoadFlag}")
    private Boolean nseEquitySmeListLoadFlag;

    @Autowired
    private StockService stockService;

    public void run() {

        if (nseEquityListLoadFlag) {
            logger.info("loading nse-equity-list");
            loadEquityList();
        }else {
            logger.info("skipping nse-data load since load-flag is false");
        }


        if (nseEquitySmeListLoadFlag) {
            logger.info("loading nse-equity-sme-list");
            loadEquitySmeList();
        }else{
            logger.info("skipping nse-sme-data load since load-flag is false");
        }


        stockService.refreshStockMap();

    }


    private void loadEquityList() {
        try (InputStream inputStream = new ClassPathResource(nseEquity, this.getClass().getClassLoader()).getInputStream();
             InputStreamReader isr = new InputStreamReader(inputStream);
             Reader reader = new BufferedReader(isr);
             CSVParser csvParser = new CSVParser(reader,
                     CSVFormat.DEFAULT.withHeader(
                             //SYMBOL	NAME OF COMPANY	 SERIES	 DATE OF LISTING	 PAID UP VALUE	 MARKET LOT	 ISIN NUMBER	 FACE VALUE
                             "SYMBOL",
                             "NAME OF COMPANY",
                             "SERIES",
                             "DATE OF LISTING",
                             "PAID UP VALUE",
                             "MARKET LOT",
                             "ISIN NUMBER",
                             "FACE VALUE").withIgnoreHeaderCase().withTrim())) {
            List<Stock> stockList = new ArrayList<>();
            for (CSVRecord record : csvParser) {
                if (csvParser.getCurrentLineNumber() == 1) {
                    if (!record.isConsistent()) {
                        throw new IllegalArgumentException("Please validate the fomat of the file");
                    }
                    // Ignore header
                    continue;
                }

                //validation can be plugged in here

                Stock stock = new Stock();
                stock.setExchange(Constants.NSE);
                stock.setSymbol(record.get("SYMBOL"));
                stock.setName(record.get("NAME OF COMPANY"));
                stock.setFaceValue(CommonUtil.parseDouble(record.get("FACE VALUE")));
                stock.setIsin(record.get("ISIN NUMBER"));
                stock.setKey(stock.getExchange() + ":" + stock.getSymbol());
                stock.setListingDate(DateUtil.convertStringToUtilDate(record.get("DATE OF LISTING"), Constants.DATE_FORMAT1));
                stockList.add(stock);
            }

            int dataLoaded = stockService.loadToStockmaster(stockList);
            logger.info("records loaded:{}", dataLoaded);

        } catch (Exception e) {
            logger.error("exception occured", e);
        }
    }


    private void loadEquitySmeList() {
        try (InputStream inputStream = new ClassPathResource(nseEquitySme, this.getClass().getClassLoader()).getInputStream();
             InputStreamReader isr = new InputStreamReader(inputStream);
             Reader reader = new BufferedReader(isr);
             CSVParser csvParser = new CSVParser(reader,
                     CSVFormat.DEFAULT.withHeader(
                             //SYMBOL	NAME_OF_COMPANY	SERIES	DATE_OF_LISTING	PAID_UP_VALUE	ISIN_NUMBER	FACE_VALUE
                             //SYMBOL,NAME_OF_COMPANY,SERIES,DATE_OF_LISTING,PAID_UP_VALUE,ISIN_NUMBER,FACE_VALUE,
                             "SYMBOL",
                             "NAME_OF_COMPANY",
                             "SERIES",
                             "DATE_OF_LISTING",
                             "PAID_UP_VALUE",
                             "ISIN_NUMBER",
                             "FACE_VALUE").withIgnoreHeaderCase().withTrim())) {
            List<Stock> stockList = new ArrayList<>();
            for (CSVRecord record : csvParser) {
                if (csvParser.getCurrentLineNumber() == 1) {
                    if (!record.isConsistent()) {
                        throw new IllegalArgumentException("Please validate the fomat of the file");
                    }
                    // Ignore header
                    continue;
                }

                //validation can be plugged in here

                Stock stock = new Stock();
                stock.setExchange(Constants.NSE);
                stock.setSymbol(record.get("SYMBOL"));
                stock.setName(record.get("NAME_OF_COMPANY"));
                stock.setFaceValue(CommonUtil.parseDouble(record.get("FACE_VALUE")));
                stock.setIsin(record.get("ISIN_NUMBER"));
                stock.setKey(stock.getExchange() + ":" + stock.getSymbol());
                stock.setListingDate(DateUtil.convertStringToUtilDate(record.get("DATE_OF_LISTING"), Constants.DATE_FORMAT1));
                stockList.add(stock);
            }

            int dataLoaded = stockService.loadToStockmaster(stockList);
            logger.info("records loaded:{}", dataLoaded);

        } catch (Exception e) {
            logger.error("exception occured", e);
        }
    }

}
