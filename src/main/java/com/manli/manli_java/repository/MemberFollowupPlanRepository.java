package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.MemberFollowupPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberFollowupPlanRepository extends JpaRepository<MemberFollowupPlanEntity,Integer> {
  List<MemberFollowupPlanEntity> findAllByUserIdAndStatus(Integer userId,Byte status);
  MemberFollowupPlanEntity findOneByUserIdAndPlanAndId(Integer userId,Integer id);
  MemberFollowupPlanEntity findOneById(Integer id);
  @Modifying
  @Transactional
  MemberFollowupPlanEntity save(MemberFollowupPlanEntity memberFollowupPlanEntity);
  @Modifying
  @Transactional
  void  deleteById(Integer id);

}
