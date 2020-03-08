package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.TkiWithMedicineNoresultEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TkiWithMedicineNoResultRepository extends JpaRepository<TkiWithMedicineNoresultEntity, Integer> {

    //save
    TkiWithMedicineNoresultEntity save(TkiWithMedicineNoresultEntity tkiWithMedicineNoresultEntity);


}


