package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.BadEffectTreatmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadEffectTreatmentRepository extends JpaRepository<BadEffectTreatmentEntity, Integer> {

    //find one
    BadEffectTreatmentEntity findOneByBadEffectLevel(Byte badEffectLevel);

    //list
    List<BadEffectTreatmentEntity> findAllByStatus(Byte status);


}