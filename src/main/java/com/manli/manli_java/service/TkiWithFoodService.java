package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.TkiWithFoodEntity;
import com.manli.manli_java.model_auto.TkiWithMedicineEntity;
import com.manli.manli_java.model_impl.TkiWithFoodImpl1;
import com.manli.manli_java.repository.TkiWithFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TkiWithFoodService {

    @Autowired
    private TkiWithFoodRepository tkiWithFoodRepository;

    public List<TkiWithFoodImpl1> getDistinctFoodList() {
        List<TkiWithFoodImpl1> list = new ArrayList<>();
        Optional<List<String>> o = tkiWithFoodRepository.getDistinctTkiList(Byte.valueOf("0"));

        if (o.isPresent()) {
            list = o.get().stream().filter((String food) -> {
                return null != food;
            }).map((String food) -> {
                TkiWithFoodImpl1 tkiWithMedicineImpl1 = new TkiWithFoodImpl1();
                tkiWithMedicineImpl1.setFood(food);
                return tkiWithMedicineImpl1;

            }).collect(Collectors.toList());
        }
        return list;
    }

    public String getSuggestion(String food) {
        Optional<TkiWithFoodEntity> o = tkiWithFoodRepository.findOneByFoodAndStatus(food, Byte.valueOf("0"));
        if (o.isPresent()) {
            return o.get().getSuggestion();
        }
        return null;
    }

}
