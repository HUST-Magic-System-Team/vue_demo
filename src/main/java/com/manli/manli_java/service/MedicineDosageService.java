package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.MedicineDosageEntity;
import com.manli.manli_java.repository.MedicineDosageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineDosageService {

    @Autowired
    private MedicineDosageRepository medicineDosageRepository;

    public List<String> getDosageList() {
        List<MedicineDosageEntity> list = medicineDosageRepository.findAllByStatus(Byte.valueOf("0"));
        List<String> result = new ArrayList<>();
        if (null != list && list.size() > 0) {
            result = list.stream().map((MedicineDosageEntity medicineDosageEntity) -> {
                return medicineDosageEntity.getDosage();
            }).collect(Collectors.toList());
        }
        return result;
    }

}
