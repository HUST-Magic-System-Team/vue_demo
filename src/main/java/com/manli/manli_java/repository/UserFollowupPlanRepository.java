package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.UserFollowupPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFollowupPlanRepository extends JpaRepository<UserFollowupPlanEntity,Integer> {
    List<UserFollowupPlanEntity> findAllByStatus(Byte status);
}
