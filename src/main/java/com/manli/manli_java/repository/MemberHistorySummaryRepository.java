package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.MemberHistorySummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberHistorySummaryRepository extends JpaRepository<MemberHistorySummaryEntity,Integer> {
    List<MemberHistorySummaryEntity> findAllByUserIdAndStatus(Integer userId, Byte status);
    MemberHistorySummaryEntity findOneById(Integer id);
    MemberHistorySummaryEntity findByUserIdAndId(Integer userId,Integer id);
    @Modifying
    @Transactional
    MemberHistorySummaryEntity save(MemberHistorySummaryEntity memberHistorySummaryEntity);
    @Modifying
    @Transactional
    void deleteById(Integer id);


}
