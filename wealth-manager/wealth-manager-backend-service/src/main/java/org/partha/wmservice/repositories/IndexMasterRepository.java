package org.partha.wmservice.repositories;

import org.partha.wmcommon.entities.Dividend;
import org.partha.wmcommon.entities.IndexMaster;
import org.partha.wmcommon.entities.StockMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndexMasterRepository extends JpaRepository<IndexMaster,Integer> {

    Optional<StockMaster> findByKey(String key);
}
