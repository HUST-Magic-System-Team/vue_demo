package com.manli.manli_java.repository;


import com.manli.manli_java.model_auto.UserCurativeEffectElectronicRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCurativeEffectElectronicRecordRepository extends JpaRepository<UserCurativeEffectElectronicRecordEntity, Integer> {

    //list
    List<UserCurativeEffectElectronicRecordEntity> findAllByStatus(Byte status);

    //save
    @Modifying
    UserCurativeEffectElectronicRecordEntity save(UserCurativeEffectElectronicRecordEntity userCurativeEffectElectronicRecordEntity);

}