package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRecordInfoRepository extends JpaRepository<UserRecordInfoEntity, Integer> {
    //find one
    UserRecordInfoEntity findOneByUserId(int userId);

    UserRecordInfoEntity findOneByUserIdAndStatus(int userId, Byte status);

}
