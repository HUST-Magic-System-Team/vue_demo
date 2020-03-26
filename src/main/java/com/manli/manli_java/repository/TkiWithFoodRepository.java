package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.TkiWithFoodEntity;
import com.manli.manli_java.model_auto.TkiWithMedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TkiWithFoodRepository extends JpaRepository<TkiWithFoodEntity, Integer> {

    //find one
    Optional<TkiWithFoodEntity> findOneByFoodAndStatus(String food, Byte status);

    //native query
    @Query(value = "select distinct t.food  from TkiWithFoodEntity t where t.status = ?1 order by t.food asc")
    Optional<List<String>> getDistinctTkiList(Byte status);


}


