package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.TkiWithFoodNoresultEntity;
import com.manli.manli_java.repository.TkiWithFoodNoResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TkiWithFoodNoResultService {

    @Autowired
    private TkiWithFoodNoResultRepository tkiWithFoodNoResultRepository;

    public TkiWithFoodNoresultEntity addRecord(
            Integer userId,
            String food
    ) {
        TkiWithFoodNoresultEntity tkiWithFoodNoresultEntity = new TkiWithFoodNoresultEntity();
        tkiWithFoodNoresultEntity.setUserId(userId);
        tkiWithFoodNoresultEntity.setFood(food);
        tkiWithFoodNoresultEntity.setStatus(Byte.valueOf("0"));
        tkiWithFoodNoresultEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        tkiWithFoodNoResultRepository.save(tkiWithFoodNoresultEntity);

        return tkiWithFoodNoresultEntity;
    }

}
