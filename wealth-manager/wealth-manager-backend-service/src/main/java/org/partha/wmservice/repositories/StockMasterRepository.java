package org.partha.wmservice.repositories;

import org.partha.wmcommon.entities.StockMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMasterRepository extends JpaRepository<StockMaster,Integer> {

}
