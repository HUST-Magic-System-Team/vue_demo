package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.MedicineEntity;
import com.manli.manli_java.repository.MedicineRepository;
import com.manli.manli_java.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    public List<MedicineEntity> getList() {
        List<MedicineEntity> list = medicineRepository.findAllByStatus(Byte.valueOf("0"));
        return list;
    }


}
