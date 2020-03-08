package com.manli.manli_java.repository;


import com.manli.manli_java.model_auto.UserCurativeEffectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCurativeEffectRepository extends JpaRepository<UserCurativeEffectEntity, Integer> {

    //find one
    UserCurativeEffectEntity findTop1ByUserIdAndStatusOrderByCreatedAtDesc(Integer userId, Byte status);

    //list
    List<UserCurativeEffectEntity> findAllByUserIdAndStatusOrderByCreatedAtDesc(Integer userId, Byte status);

    //page
    Page<UserCurativeEffectEntity> findAllByUserIdAndStatus(Integer userId, Byte status, Pageable pageable);

    //save
    UserCurativeEffectEntity save(UserCurativeEffectEntity userBadEffectEntity);

}