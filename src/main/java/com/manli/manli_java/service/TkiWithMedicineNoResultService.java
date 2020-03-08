package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.TkiWithMedicineNoresultEntity;
import com.manli.manli_java.repository.TkiWithMedicineNoResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TkiWithMedicineNoResultService {

    @Autowired
    private TkiWithMedicineNoResultRepository tkiWithMedicineNoResultRepository;

    public TkiWithMedicineNoresultEntity addRecord(
            Integer userId,
            String tki,
            String tkiLabel,
            String medicine
    ) {
        TkiWithMedicineNoresultEntity tkiWithMedicineNoresultEntity = new TkiWithMedicineNoresultEntity();
        tkiWithMedicineNoresultEntity.setUserId(userId);
        tkiWithMedicineNoresultEntity.setTki(tki);
        tkiWithMedicineNoresultEntity.setTkiLabel(tkiLabel);
        tkiWithMedicineNoresultEntity.setMedicine(medicine);
        tkiWithMedicineNoresultEntity.setStatus(Byte.valueOf("0"));
        tkiWithMedicineNoresultEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        tkiWithMedicineNoResultRepository.save(tkiWithMedicineNoresultEntity);

        return tkiWithMedicineNoresultEntity;
    }

}
