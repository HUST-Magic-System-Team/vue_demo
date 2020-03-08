package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.MedicineDosageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineDosageRepository extends JpaRepository<MedicineDosageEntity, Integer> {
    //list
    List<MedicineDosageEntity> findAllByStatus(Byte status);
}
