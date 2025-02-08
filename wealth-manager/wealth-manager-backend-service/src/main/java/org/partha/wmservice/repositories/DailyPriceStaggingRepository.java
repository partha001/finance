package org.partha.wmservice.repositories;

import org.partha.wmcommon.entities.DailyPriceStaging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyPriceStaggingRepository extends JpaRepository<DailyPriceStaging,Integer> {
}
