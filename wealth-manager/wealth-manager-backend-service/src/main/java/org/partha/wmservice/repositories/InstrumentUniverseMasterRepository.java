package org.partha.wmservice.repositories;

import org.partha.wmcommon.entities.InstrumentUniverse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentUniverseMasterRepository extends JpaRepository<InstrumentUniverse, Integer> {

}
