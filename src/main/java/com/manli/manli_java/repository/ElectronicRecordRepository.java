package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.ElectronicRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface ElectronicRecordRepository extends JpaRepository<ElectronicRecordEntity, Integer> {
    //find one
    ElectronicRecordEntity findOneByUserIdAndHashName(Integer userId, String hashName);

    //save
    @Transactional
    @Modifying
    ElectronicRecordEntity save(ElectronicRecordEntity electronicRecordEntity);

    //delete
    @Transactional
    @Modifying
    void deleteByUserIdAndHashName(Integer userId, String hashName);


}
