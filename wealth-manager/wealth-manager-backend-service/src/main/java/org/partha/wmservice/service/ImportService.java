package org.partha.wmservice.service;

import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmservice.importers.Importer;
import org.partha.wmservice.importers.impl.AngelHoldingImporter;
import org.partha.wmservice.importers.impl.UpstoxHoldingImporter;
import org.partha.wmservice.importers.impl.ZerodhaHoldingImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@Service
public class ImportService {

    @Autowired
    private ApplicationContext context;

    private Importer getImporter(ExportImportFormat importFormat){
        switch (importFormat){
            case ZerodhaHoldingExport_Import:
                return context.getBean("zerodhaHoldingImporter", ZerodhaHoldingImporter.class);
            case AngelOneHoldingExport_Import:
                return context.getBean("angelHoldingImporter", AngelHoldingImporter.class);
            case UpstoxHoldingExport_Import:
                return context.getBean("upstoxHoldingImporter", UpstoxHoldingImporter.class);
        }
        throw new RuntimeException("no importer found for the given type");
    }

    public void importHoldings(MultipartFile multipartFile, ExportImportFormat inputFormat, String username) throws IOException {
        Importer importer = getImporter(inputFormat);
        importer.importData(multipartFile,username);
    }
}
