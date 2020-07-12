package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecordInfoRepository extends JpaRepository<UserRecordInfoEntity, Integer> {
    //find one
    UserRecordInfoEntity findOneByUserId(int userId);

    UserRecordInfoEntity findOneByUserIdAndStatus(int userId, Byte status);

}
