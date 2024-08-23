package org.partha.wmservice.repositories;

import org.partha.wmcommon.entities.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository  extends JpaRepository<Asset,Integer> {

    @Query(value="select a from Asset a where username in (:users)")
    List<Asset> getAsstsByUsernames(List<String> users);

}
