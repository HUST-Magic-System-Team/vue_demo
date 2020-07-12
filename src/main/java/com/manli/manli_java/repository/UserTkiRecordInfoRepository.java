package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.UserTkiRecordInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
import java.util.List;
@Repository
public interface UserTkiRecordInfoRepository extends JpaRepository<UserTkiRecordInfoEntity, Integer> {
    //list
    List<UserTkiRecordInfoEntity> findAllByUserId(int userId);

    List<UserTkiRecordInfoEntity> findAllByUserIdAndStatus(int userId, Byte status);

    //delete
    @Transactional
    @Modifying
    void deleteAllByUserIdAndStatus(Integer userId, Byte status);
}
