package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.MedicineSignElectronicRecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface MedicineSignElectronicRecordRepository extends JpaRepository<MedicineSignElectronicRecordEntity, Integer> {
    //list
    List<MedicineSignElectronicRecordEntity> findAllByMedicineSignIdAndStatus(Integer medicineSignId, Byte status);

    //page
    Page<MedicineSignElectronicRecordEntity> findAllByMedicineSignIdAndStatus(Integer medicineSignId, Byte status, Pageable pageable);

    //save
    @Modifying
    @Transactional
    MedicineSignElectronicRecordEntity save(MedicineSignElectronicRecordEntity medicineSignElectronicRecordEntity);

    //delete
    @Modifying
    @Transactional
    void deleteAllByMedicineSignIdAndStatus(Integer medicineSignId, Byte status);


}
