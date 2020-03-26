package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Integer> {
    //find one
    UserInfoEntity findOneByUserId(int userId);

    UserInfoEntity findOneByUserIdAndStatus(int userId, Byte status);

}

