package org.partha.wmservice.importers.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.partha.wmcommon.constants.Constants;
import org.partha.wmcommon.entities.Holding;
import org.partha.wmcommon.util.ExcelUtil;
import org.partha.wmservice.importers.AbstractImporter;
import org.partha.wmservice.importers.Importer;
import org.partha.wmservice.service.domain.HoldingService;
import org.partha.wmservice.service.domain.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Transactional
@Component("angelHoldingImporter")
public class AngelHoldingImporter extends AbstractImporter {


    @Autowired
    public AngelHoldingImporter(HoldingService holdingService,
                                InstrumentService instrumentService) {
        this.holdingService = holdingService;
        this.instrumentService = instrumentService;
    }

    @Override
    public void importData(MultipartFile multipartFile, String username, String filePassword) throws IOException {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream, filePassword);
            Sheet sheet = workbook.getSheet("Equity");
            Row row = null;

            int rowIndex = 15; //start reading rows from this
            Holding holding = null;
            List<Holding> holdings = new ArrayList<>();
            while (true) {
                row = sheet.getRow(rowIndex++);

                /** exit condition **/
                if (excelUtil.getString(row.getCell(0)).trim().equals("Total"))
                    break;

                String symbol = excelUtil.getString(row.getCell(1));
                Integer quantity = excelUtil.getInt(row.getCell(5));
                Double averagePrice = excelUtil.getDouble(row.getCell(12));
                String isin = excelUtil.getString(row.getCell(2));

                //Integer quantity = Integer.parseInt(excelUtil.getString(row.getCell(5)));
                log.info("symbol:{} quantity:{} averagePrice:{}", symbol, quantity,averagePrice);
                holding = Holding.builder()
                        .username(username)
                        .brokersymbol(symbol)
                        .averagePrice(averagePrice)
                        .isin(isin)
                        .quantity(quantity)
                        .brokername(Constants.BROKER_ANGELONE)
                        .build();
                holdings.add(holding);
            }

            holdingService.deleteHoldingsByUserByBroker(username, Constants.BROKER_ANGELONE);
            holdingService.insertHolding(holdings);

            //enriching now
            holdingService.updateKeyForGiverUserAndBrokerForSameIsin(username, Constants.BROKER_ANGELONE);
            //fetch daily data
            //downloadDailyHoldingData(username, Constants.BROKER_ANGELONE);

        } catch (Exception ex) {
            log.error("error occurred while importing holding", ex);
        }
    }
}

/**
 *
 * older version of the method when the import format was different and didnt required any filepassword to open
    @Override
    public void importData(MultipartFile multipartFile, String username) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet("Portfolio");
        int rowIndex = 11; //start reading rows from this
        Holding holding = null;
        List<Holding> holdings = new ArrayList<>();
        while (true) {
            Row row = sheet.getRow(rowIndex++);
            if(row==null)
                break;

            String symbol = excelUtil.getString(row.getCell(0));
            String isin = excelUtil.getString(row.getCell(2));

            Integer quantity = Integer.parseInt(excelUtil.getString(row.getCell(5)));
            //String quantity = excelUtil.getString(row.getCell(5));
            log.info("symbol:{} isin:{} quantity:{}",symbol, isin, quantity);
            holding = Holding.builder()
                    .username(username)
                    .brokersymbol(symbol)
                    .isin(isin)
                    .quantity(quantity)
                    .brokername(Constants.BROKER_ANGELONE)
                    .build();
            holdings.add(holding);
        }
        deleteInsertHoldings(holdings,username);
    }

 **/
