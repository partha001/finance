package org.partha.wmservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.partha.wmcommon.entities.Dividend;

@Repository
public interface DividendRepository extends JpaRepository<Dividend,Integer> {

}
