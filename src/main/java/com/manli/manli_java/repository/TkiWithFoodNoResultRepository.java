package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.TkiWithFoodNoresultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TkiWithFoodNoResultRepository extends JpaRepository<TkiWithFoodNoresultEntity, Integer> {

    //save
    TkiWithFoodNoresultEntity save(TkiWithFoodNoresultEntity tkiWithFoodNoresultEntity);


}


