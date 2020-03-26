package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.MedicineSignEntity;
import com.manli.manli_java.model_impl.MedicineSignEntityImpl2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface MedicineSignRepository extends JpaRepository<MedicineSignEntity, Integer> {

    //find one
    MedicineSignEntity findOneByIdAndStatus(Integer medicineSignId, Byte status);
    MedicineSignEntity findOneByUserIdAndSignDateAndStatus(Integer userId,Date signDate,Byte status);
    //list
    List<MedicineSignEntity> findAllByMedicinePlanIdAndSignDateAndStatus(Integer medicinePlanId, Date signDate, Byte status);

    List<MedicineSignEntity> findAllByMedicinePlanIdAndUserIdAndStatusAndSignDateBetween(Integer medicinePlanId,
                                                                                         Integer userId, Byte status, Date startDate, Date endDate);

    //page
    Page<MedicineSignEntity> findAllByUserIdAndStatus(Integer userId, Byte status, Pageable pageable);
    //page
    Page<MedicineSignEntity> findAllByUserIdAndStatusAndMedicinePlanId(Integer userId, Byte status, Pageable pageable,Integer medicinePlanId);
    //save
    @Modifying
    @Transactional
    MedicineSignEntity save(MedicineSignEntity medicineSignEntity);


    //delete
    @Modifying
    @Transactional
    void deleteAllByIdAndStatus(Integer medicineSignId, Byte status);

    //native query
    @Query(nativeQuery = true, value = "select concat(signCount, '%%%', signDate, '%%%', ifnull(notify, 'NULL')) as fieldThree from (" +
            " select  count(s.signDate) as signCount, s.signDate, p.notify from medicine_sign s" +
            " left join medicine_plan p " +
            " on s.medicinePlanId = p.id" +
            " where year(signDate) = :year and month(signDate) = :month and s.userId = :userId or s.medicinePlanId = :mainMedicinePlanId " +
            " group by signDate" +
            " having signCount >= splitTotal(`notify`,',')" +
            ") as tt")
    Optional<List<String>> getSignDaysByMonth(Integer userId, Integer year, Integer month, Integer mainMedicinePlanId);
}
