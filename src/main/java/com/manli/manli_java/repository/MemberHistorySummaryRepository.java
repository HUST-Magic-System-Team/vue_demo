package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.MemberHistorySummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHistorySummaryRepository extends JpaRepository<MemberHistorySummaryEntity,Integer> {
    MemberHistorySummaryEntity findOneByUserIdAAndStatus(Integer userId,Byte status);
}
