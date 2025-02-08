package org.partha.wmservice.repositories;

import org.partha.wmcommon.entities.DailyPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DailyPriceRepository  extends JpaRepository<DailyPrice,Integer> {
}
