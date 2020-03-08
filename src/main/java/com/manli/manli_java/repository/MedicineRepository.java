package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<MedicineEntity, Integer> {
    //list
    List<MedicineEntity> findAllByStatus(Byte status);
}
