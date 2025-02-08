package org.partha.wmservice.repositories;

import jakarta.transaction.Transactional;
import org.partha.wmcommon.entities.InstrumentStaging;
import org.partha.wmservice.repositories.custom.CustomInstrumentStagingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstrumentStagingRepository extends JpaRepository<InstrumentStaging,Integer>, CustomInstrumentStagingRepository {

    @Transactional
    @Modifying
    @Query(value="delete from InstrumentStaging where sourceName=:sourceName")
    int deleteAllBySourceName(@Param("sourceName") String sourceName);

    @Query(value="SELECT key FROM wealthmanager.instrumentmaster WHERE sourcename =:sourceName " +
            "EXCEPT " +
            "SELECT key from wealthmanager.instrumentmasterstaging " +
            "WHERE sourcename =:sourceName ", nativeQuery = true)
    List<String> additionalKeysInInstrumentMaster(@Param("sourceName") String sourceName);


    @Query(value="SELECT key FROM wealthmanager.instrumentmasterstaging WHERE sourcename =:sourceName " +
            "EXCEPT " +
            "SELECT key from wealthmanager.instrumentmaster " +
            "WHERE sourcename =:sourceName ", nativeQuery = true)
    List<String> additionalKeysInInstrumentMasterStaging(@Param("sourceName") String sourceName);

}
