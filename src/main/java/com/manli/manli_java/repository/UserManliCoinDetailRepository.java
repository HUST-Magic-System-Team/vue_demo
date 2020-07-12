package com.manli.manli_java.repository;


import com.manli.manli_java.model_auto.UserManliCoinDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserManliCoinDetailRepository extends JpaRepository<UserManliCoinDetailEntity, Integer> {

    //list
    List<UserManliCoinDetailEntity> findAllByStatus(Byte status);

    List<UserManliCoinDetailEntity> findAllByUserIdAndStatus(Integer userId, Byte status);

    //page
    Page<UserManliCoinDetailEntity> findAllByUserIdAndStatus(Integer userId, Byte status, Pageable pageable);

    //save
    @Modifying
    UserManliCoinDetailEntity save(UserManliCoinDetailEntity userManliCoinDetailEntity);


}