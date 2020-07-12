package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, Integer> {
    //list
    List<MedicineEntity> findAllByStatus(Byte status);
}
