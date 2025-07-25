package org.partha.wmservice.repositories;

import org.partha.wmcommon.entities.Holding;
import org.partha.wmcommon.projections.HoldingProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Integer> {

    @Modifying
    @Query(value = "delete from wealthmanager.holdingmaster where username=:username and brokername=:brokername",
            nativeQuery = true)
    int removeByUsernameAndBrokername(String username, String brokername);

    @Query(value = "select h from Holding h where username in (:users)")
    List<Holding> getHoldingsByUsernames(List<String> users);


    @Query(value="select hm.*, im.symbol, im.yahooFinanceTicker from wealthmanager.holdingmaster hm\n" +
            "left outer join wealthmanager.instrumentmaster im\n" +
            "on hm.key = im.key \n" +
            "where hm.username in (:users)",
    nativeQuery = true)
    List<HoldingProjection> getHoldingsProjectionByUsernames(List<String> users);

    @Query(value="select hm.*, im.symbol, im.yahooFinanceTicker from wealthmanager.holdingmaster hm\n" +
            "left outer join wealthmanager.instrumentmaster im\n" +
            "on hm.key = im.key \n" +
            "where hm.username = :username and hm.brokername= :brokername",
            nativeQuery = true)
    List<HoldingProjection> getHoldingsProjectionByUsernameAndBrokername(String username, String brokername);


    @Modifying
    @Query(value = "update  wealthmanager.holdingmaster hm\n" +
            "set key= coalesce(hm.key, im.key)\n" +
            "from wealthmanager.instrumentmaster im\n" +
            "where hm.isin = im.isin\n" +
            "and hm.username = :username\n" +
            "and hm.brokername = :brokername ",
            nativeQuery = true)
    int updateKeyForGiverUserAndBrokerForSameIsin(String username, String brokername);

    @Modifying
    @Query(value = "update  wealthmanager.holdingmaster hm \n" +
            "set key= coalesce(hm.key, im.key) \n" +
            "from wealthmanager.instrumentmaster im \n" +
            "where hm.brokersymbol = im.symbol \n" +
            "and hm.username = :username \n" +
            "and hm.brokername = :brokername",
    nativeQuery = true)
    int updateKeyForGiverUserAndBrokerForSameSymbol(String username,String brokername);


}
