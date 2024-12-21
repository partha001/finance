package org.partha.wmservice.repositories;

import org.partha.wmcommon.entities.Dividend;
import org.partha.wmcommon.entities.IndexMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexMasterRepository extends JpaRepository<IndexMaster,Integer> {

}
