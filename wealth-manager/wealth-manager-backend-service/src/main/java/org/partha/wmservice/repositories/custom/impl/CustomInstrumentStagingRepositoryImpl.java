package org.partha.wmservice.repositories.custom.impl;

import org.partha.wmcommon.constants.SqlConstant;
import org.partha.wmcommon.entities.InstrumentStaging;
import org.partha.wmcommon.enums.InstrumentType;
import org.partha.wmservice.repositories.custom.CustomInstrumentStagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public class CustomInstrumentStagingRepositoryImpl implements CustomInstrumentStagingRepository {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public int loadToInstrumentMasterStaging(List<InstrumentStaging> list) {
        MapSqlParameterSource[] map = new MapSqlParameterSource[list.size()];
        for(int i=0;i<list.size();i++) {
            InstrumentStaging dto = list.get(i);
            MapSqlParameterSource param = new MapSqlParameterSource();
            //public static final String LOAD_NSE_DATA = "insert into InstrumentMaster(instrumentType,name, exchange, key, symbol, isin, faceValue,  listingDate, yahooFinanceTicker, sourceName, createdDate) values (:instrumentType,:name, :exchange, :key, :symbol, :isin, :faceValue,  :listingDate, :yahooFinanceTicker, :sourceName, :createdDate) on conflict (key) do nothing";

            param.addValue("instrumentType",dto.getInstrumentType());
            param.addValue("name",dto.getName());
            param.addValue("exchange",dto.getExchange());

            String key = "";
            if(dto.getInstrumentType().equals(InstrumentType.EQUITY.name())){
                key = dto.getInstrumentType() +":"+ dto.getExchange() + ":"+ dto.getSymbol();
            }

            param.addValue("key",key);
            param.addValue("symbol",dto.getSymbol());
            param.addValue("isin",dto.getIsin());
            param.addValue("faceValue",dto.getFaceValue());
            param.addValue("listingDate", dto.getListingDate());
            param.addValue("yahooFinanceTicker",dto.getYahooFinanceTicker());
            param.addValue("sourceName",dto.getSourceName());
            param.addValue("createdDate", new Date());

            map[i] = param;
        }
        int[] batchUpdate = jdbcTemplate.batchUpdate(SqlConstant.LOAD_NSE_DATA, map);
        return IntStream.of(batchUpdate).sum();
    }

}
