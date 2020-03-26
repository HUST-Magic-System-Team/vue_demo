package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.TkiWithMedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TkiWithMedicineRepository extends JpaRepository<TkiWithMedicineEntity, Integer> {

    //find one
    Optional<TkiWithMedicineEntity> findOneByTkiAndMedicineAndStatus(String tki, String medicine, Byte status);

    //native query
    @Query(value = "select distinct concat(t.tki, ',', t.tkiLabel) as tki2Field  from TkiWithMedicineEntity t where t.status = ?1 order by t.tki asc")
    Optional<List<String>> getDistinctTkiList(Byte status);


}


