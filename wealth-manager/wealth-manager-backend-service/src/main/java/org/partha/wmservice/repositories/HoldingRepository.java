package org.partha.wmservice.repositories;

import org.partha.wmcommon.entities.Holding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Integer> {

    @Modifying
    @Query(value = "delete from wealthmanager.holdingmaster where username=:username and brokername=:brokername",
    nativeQuery = true)
    int removeByUsernameAndBrokername(String username, String brokername);

}
