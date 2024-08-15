package org.partha.wmservice.service.domain;

import lombok.extern.log4j.Log4j2;
import org.partha.wmcommon.entities.Holding;
import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmservice.repositories.HoldingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class HoldingService {

    @Autowired
    HoldingRepository holdingRepository;



}
