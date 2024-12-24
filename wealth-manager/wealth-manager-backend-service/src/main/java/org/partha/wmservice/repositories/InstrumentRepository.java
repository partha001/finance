package org.partha.wmservice.repositories;

import org.partha.wmcommon.entities.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument,Integer>, CustomInstrumentRepository {

    Optional<Instrument> findByKey(String key);

    List<Instrument> findByInstrumentType(String instrumentType);
}
