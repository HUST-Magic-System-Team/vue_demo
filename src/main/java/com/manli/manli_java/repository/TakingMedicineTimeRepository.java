package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakingMedicineTimeRepository extends JpaRepository<TakingMedicineTimeEntity, Integer> {
    //find one
    TakingMedicineTimeEntity findOneByType(Short type);

}
