package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.MedicinePlanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MedicinePlanRepository extends JpaRepository<MedicinePlanEntity, Integer> {

    //find one
    boolean existsByUserIdAndIsMainAndStatus(Integer userId, Short isMain, Byte status);

    boolean existsByUserIdAndMedicineAndStatus(Integer userId, String medicine, Byte status);

    MedicinePlanEntity findOneById(Integer medicinePlanId);

    Optional<MedicinePlanEntity> findOneByUserIdAndIsMainAndStatus(Integer userId, Short isMain, Byte status);

    //page
    Page<MedicinePlanEntity> findAllByStatus(Byte status, Pageable pageable);

    Page<MedicinePlanEntity> findAllByUserIdAndStatus(Integer userId, Byte status, Pageable pageable);

    //save
    @Modifying
    @Transactional
    MedicinePlanEntity save(MedicinePlanEntity medicinePlanEntity);

    //delete
    @Modifying
    @Transactional
    void deleteAllByUserIdAndStatus(Integer userId, Byte status);

    @Modifying
    @Transactional
    void deleteAllById(Integer medicinePlanId);

    //native query
    @Modifying
    @Transactional
    @Query(value = "delete from medicine_plan where `userId` = ?1 and `status` = 0 and `duration` <> -1 and date_add(`startTime`, interval duration day) < current_date()", nativeQuery = true)
    void deleteOutded(Integer userId);
}
