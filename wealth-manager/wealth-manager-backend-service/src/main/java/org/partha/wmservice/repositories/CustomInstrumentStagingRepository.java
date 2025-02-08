package org.partha.wmservice.repositories;

import org.partha.wmcommon.entities.Instrument;
import org.partha.wmcommon.entities.InstrumentStaging;

import java.util.List;

public interface CustomInstrumentStagingRepository {

    public int loadToInstrumentMasterStaging(List<InstrumentStaging> list) ;

}
