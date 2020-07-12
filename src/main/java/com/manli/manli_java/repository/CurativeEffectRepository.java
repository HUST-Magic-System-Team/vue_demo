package com.manli.manli_java.repository;


import com.manli.manli_java.model_auto.CurativeEffectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurativeEffectRepository extends JpaRepository<CurativeEffectEntity, Integer> {

    //find one
    CurativeEffectEntity findOneByEvaluateResult(String evaluateResult);

    //list
    List<CurativeEffectEntity> findAllByStatus(Byte status);


}