package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.UserTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTaskEntity, Integer> {

    //list
    List<UserTaskEntity> findAllByUserIdAndStatusOrderByTaskTimeAsc(Integer userId, Byte status);

    //delete
    @Modifying
    @Transactional
    void deleteAllByUserIdAndStatus(Integer userId, Byte status);

}