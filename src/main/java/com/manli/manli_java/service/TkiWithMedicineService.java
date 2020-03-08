package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.TkiWithMedicineEntity;
import com.manli.manli_java.model_impl.TkiWithMedicineImpl1;
import com.manli.manli_java.repository.TkiWithMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TkiWithMedicineService {

    @Autowired
    private TkiWithMedicineRepository tkiWithMedicineRepository;

    public List<TkiWithMedicineImpl1> getDistinctTkiList() {
        List<TkiWithMedicineImpl1> list = new ArrayList<>();
        Optional<List<String>> o = tkiWithMedicineRepository.getDistinctTkiList(Byte.valueOf("0"));

        if (o.isPresent()) {
            list = o.get().stream().filter((String tki2Field) -> {
                return null != tki2Field && tki2Field.split(",").length == 2;
            }).map((String tki2Field) -> {
                TkiWithMedicineImpl1 tkiWithMedicineImpl1 = new TkiWithMedicineImpl1();

                tkiWithMedicineImpl1.setTki(tki2Field.split(",")[0]);
                tkiWithMedicineImpl1.setTkiLabel(tki2Field.split(",")[1]);

                return tkiWithMedicineImpl1;

            }).collect(Collectors.toList());
        }
        return list;
    }

    public String getTkiLabelByTki(String tki) {
        if (null == tki) {
            return null;
        }

        Optional<List<String>> oList = tkiWithMedicineRepository.getDistinctTkiList(Byte.valueOf("0"));
        if (oList.isPresent()) {
            Optional<String> o = oList.get().stream().filter((String tki2Field) -> {
                return null != tki2Field && tki2Field.split(",").length == 2 && tki2Field.split(",")[0].equals(tki);
            }).findFirst();

            if (o.isPresent()) {
                return o.get().split(",")[1];
            }
        }
        return null;
    }

    public String getSuggestion(String tki, String medicine) {
        Optional<TkiWithMedicineEntity> o = tkiWithMedicineRepository.findOneByTkiAndMedicineAndStatus(tki, medicine, Byte.valueOf("0"));
        if (o.isPresent()) {
            return o.get().getSuggestion();
        }
        return null;
    }

}
