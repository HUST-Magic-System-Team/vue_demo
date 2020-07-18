package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.ElectronicRecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface ElectronicRecordRepository extends JpaRepository<ElectronicRecordEntity, Integer> {
    //find one
    ElectronicRecordEntity findOneByUserIdAndHashName(Integer userId, String hashName);
    //find one by id
    ElectronicRecordEntity findOneById(Integer id);
    //save
    @Transactional
    @Modifying
    ElectronicRecordEntity save(ElectronicRecordEntity electronicRecordEntity);

    //delete
    @Transactional
    @Modifying
    void deleteByUserIdAndHashName(Integer userId, String hashName);
    //page
    Page<ElectronicRecordEntity> findAllByUserIdAndStatus(Integer userId, Byte status, Pageable pageable);

}