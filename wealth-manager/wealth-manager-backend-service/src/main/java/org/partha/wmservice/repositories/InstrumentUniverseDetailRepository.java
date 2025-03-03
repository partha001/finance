package org.partha.wmservice.repositories;

import jakarta.transaction.Transactional;
import org.partha.wmcommon.entities.InstrumentUniverse;
import org.partha.wmcommon.entities.InstrumentUniverseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentUniverseDetailRepository extends JpaRepository<InstrumentUniverseDetail, Integer> {

    @Modifying
    @Transactional
    int deleteByInstrumentUniverseMasterId(int id);

    List<InstrumentUniverseDetail> findByInstrumentUniverseMasterId(int id);

}
