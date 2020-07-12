package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.MedicineDosageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MedicineDosageRepository extends JpaRepository<MedicineDosageEntity, Integer> {
    //list
    List<MedicineDosageEntity> findAllByStatus(Byte status);
}
