package com.manli.manli_java.repository;


import com.manli.manli_java.model_auto.UserBadEffectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBadEffectRepository extends JpaRepository<UserBadEffectEntity, Integer> {

    //find one
    UserBadEffectEntity findTop1ByUserIdAndStatusOrderByCreatedAtDesc(Integer userId, Byte status);

    //list
    List<UserBadEffectEntity> findAllByUserIdAndStatusOrderByCreatedAtDesc(Integer userId, Byte status);

    //page
    Page<UserBadEffectEntity> findAllByUserIdAndStatus(Integer userId, Byte status, Pageable pageable);

    //save
    UserBadEffectEntity save(UserBadEffectEntity userBadEffectEntity);

}