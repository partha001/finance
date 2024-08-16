package org.partha.wmservice.importers.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.partha.wmcommon.constants.Constants;
import org.partha.wmcommon.entities.Holding;
import org.partha.wmcommon.util.ExcelUtil;
import org.partha.wmservice.importers.Importer;
import org.partha.wmservice.repositories.HoldingRepository;
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
@Component("upstoxHoldingImporter")
public class UpstoxHoldingImporter implements Importer {

    ExcelUtil excelUtil = new ExcelUtil();

    @Autowired
    private HoldingRepository holdingRepository;


    @Override
    public void importData(MultipartFile multipartFile, String username) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet("HOLDING");
        int rowIndex = 8; //start reading rows from this
        Holding holding = null;
        List<Holding> holdings = new ArrayList<>();
        while (true) {
            Row row = sheet.getRow(rowIndex++);
            if(row==null)
                break;

            String isin = excelUtil.getString(row.getCell(0));
            String symbol = excelUtil.getString(row.getCell(1));

            Integer quantity = (int)Double.parseDouble(row.getCell(3).getStringCellValue());
            log.info("isin:{} symbol:{} quantity:{} ",isin, symbol,quantity);
            holding = Holding.builder()
                    .username(username)
                    .brokersymbol(symbol)
                    .quantity(quantity)
                    .brokername(Constants.BROKER_UPSTOX)
                    .isin(isin)
                    .build();
            holdings.add(holding);
        }
        deleteInsertHoldings(holdings,username);
    }

    public void deleteInsertHoldings(List<Holding> holdings,String username){
        int a = holdingRepository.removeByUsernameAndBrokername(username, Constants.BROKER_UPSTOX);
        log.info("deleted record count.{}",a);
        long insertedRecordCount = holdingRepository.saveAll(holdings)
                .stream().count();
        log.info("inserted record count:{}", insertedRecordCount);
    }
}