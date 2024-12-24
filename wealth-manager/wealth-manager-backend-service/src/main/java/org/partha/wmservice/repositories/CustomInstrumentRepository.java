package org.partha.wmservice.repositories;

import org.partha.wmcommon.entities.Instrument;

import java.util.List;

public interface CustomInstrumentRepository {

    public int loadToInstrumentMaster(List<Instrument> list) ;

}
