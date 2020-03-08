package com.manli.manli_java.service;


import com.manli.manli_java.model_auto.MedicinePlanEntity;
import com.manli.manli_java.model_auto.MedicineSignEntity;
import com.manli.manli_java.model_impl.MedicinePlanEntityImpl1;
import com.manli.manli_java.repository.MedicinePlanRepository;
import com.manli.manli_java.repository.MedicineSignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MedicinePlanService {

    @Autowired
    private MedicinePlanRepository medicinePlanRepository;

    @Autowired
    private MedicineSignRepository medicineSignRepository;


    public Page<MedicinePlanEntityImpl1> getList(Integer userId, Integer page, Integer size, Date signDate) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<MedicinePlanEntity> pageList = medicinePlanRepository.findAllByUserIdAndStatus(userId, Byte.valueOf("0"), pageable);
        Page<MedicinePlanEntityImpl1> pageList2 = pageList.map((MedicinePlanEntity medicinePlanEntity) -> {
            return getMedicinePlanEntityImpl1(medicinePlanEntity, signDate);
        });

        return pageList2;
    }

    private MedicinePlanEntityImpl1 getMedicinePlanEntityImpl1(MedicinePlanEntity medicinePlanEntity, Date signDate) {
        if (null == medicinePlanEntity) {
            return null;
        }
        Integer medicinePlanId = medicinePlanEntity.getId();
        List<MedicineSignEntity> medicineSignEntityArray = medicineSignRepository.findAllByMedicinePlanIdAndSignDateAndStatus(medicinePlanId, new java.sql.Date(signDate.getTime()), Byte.valueOf("0"));
        if (null == medicineSignEntityArray) {
            return null;
        }
        MedicinePlanEntityImpl1 medicinePlanEntityImpl1 = new MedicinePlanEntityImpl1(medicinePlanEntity);
        medicinePlanEntityImpl1.setMedicineSignEntityArray(medicineSignEntityArray);

        return medicinePlanEntityImpl1;
    }

    public void delOutdated(Integer userId) {
        medicinePlanRepository.deleteOutded(userId);
    }

    public boolean hasMainMedicinePlan(Integer userId) {
        return medicinePlanRepository.existsByUserIdAndIsMainAndStatus(userId, Short.valueOf("1"), Byte.valueOf("0"));
    }

    public Integer randomSetMainMedicinePlan(Integer userId) {
        return 0;
    }

    public MedicinePlanEntity get(Integer medicinePlanId) {
        return medicinePlanRepository.findOneById(medicinePlanId);
    }

    public boolean hasSameMedicinePlan(Integer userId, String medicine) {
        return medicinePlanRepository.existsByUserIdAndMedicineAndStatus(userId, medicine, Byte.valueOf("0"));
    }

    public MedicinePlanEntity add(Integer userId, String medicine, Integer dosage, Integer frequency, String notify,
                                  Date startTime, Integer duration, Short isMain) {
        MedicinePlanEntity medicinePlanEntity = new MedicinePlanEntity();
        medicinePlanEntity.setUserId(userId);
        medicinePlanEntity.setMedicine(medicine);
        medicinePlanEntity.setDosage(dosage);
        medicinePlanEntity.setFrequency(frequency);
        medicinePlanEntity.setNotify(notify);
        medicinePlanEntity.setStartTime(new java.sql.Date(startTime.getTime()));
        medicinePlanEntity.setDuration(duration);
        medicinePlanEntity.setIsMain(isMain);

        return medicinePlanRepository.save(medicinePlanEntity);
    }

    public void clearAllMainMedicinePlan(Integer userId) {
        medicinePlanRepository.deleteAllByUserIdAndStatus(userId, Byte.valueOf("0"));

    }

    public void del(Integer medicinePlanId) {
        medicinePlanRepository.deleteAllById(medicinePlanId);
    }

    public MedicinePlanEntity update(MedicinePlanEntity medicinePlanEntity, Integer userId, String medicine, Integer dosage,
                                     Integer frequency, String notify,
                                     Date startTime, Integer duration, Short isMain) {
        medicinePlanEntity.setUserId(userId);
        medicinePlanEntity.setMedicine(medicine);
        medicinePlanEntity.setDosage(dosage);
        medicinePlanEntity.setFrequency(frequency);
        medicinePlanEntity.setNotify(notify);
        medicinePlanEntity.setStartTime(new java.sql.Date(startTime.getTime()));
        medicinePlanEntity.setDuration(duration);
        medicinePlanEntity.setIsMain(isMain);

        return medicinePlanRepository.save(medicinePlanEntity);
    }

    public MedicinePlanEntity getMainMedicinePlanByUserId(Integer userId) {
        Optional<MedicinePlanEntity> o = medicinePlanRepository.findOneByUserIdAndIsMainAndStatus(userId, Short.valueOf("1"), Byte.valueOf("0"));
        return o.orElse(null);
    }
}
