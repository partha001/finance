package org.partha.wmservice.config;

import org.partha.wmcommon.enums.ExportImportFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WealthManagerConfig {


//    @Bean
//    public Map<String, ExportImportFormat> exportImportFormatMapMap(){
//        Map<String,ExportImportFormat> exportImportFormatMap = new HashMap<>();
//        EnumSet.allOf(ExportImportFormat.class).stream().forEach(item -> {
//            exportImportFormatMap.put(item.name(), item);
//        });
//        return exportImportFormatMap;
//    }

}
