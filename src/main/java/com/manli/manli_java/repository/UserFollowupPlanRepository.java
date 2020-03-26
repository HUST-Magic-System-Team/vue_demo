package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.UserFollowupPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserFollowupPlanRepository extends JpaRepository<UserFollowupPlanEntity,Integer> {
    List<UserFollowupPlanEntity> findAllByStatus(Byte status);
}
