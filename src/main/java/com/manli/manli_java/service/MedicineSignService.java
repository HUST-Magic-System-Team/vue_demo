package com.manli.manli_java.service;


import com.manli.manli_java.model_auto.MedicinePlanEntity;
import com.manli.manli_java.model_auto.MedicineSignElectronicRecordEntity;
import com.manli.manli_java.model_auto.MedicineSignEntity;
import com.manli.manli_java.model_impl.MedicineSignEntityImpl1;
import com.manli.manli_java.model_impl.MedicineSignEntityImpl2;
import com.manli.manli_java.repository.MedicinePlanRepository;
import com.manli.manli_java.repository.MedicineSignElectronicRecordRepository;
import com.manli.manli_java.repository.MedicineSignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MedicineSignService {

    @Autowired
    MedicineSignRepository medicineSignRepository;

    @Autowired
    MedicinePlanRepository medicinePlanRepository;

    @Autowired
    MedicineSignElectronicRecordRepository medicineSignElectronicRecordRepository;


    public List<MedicineSignEntity> getMedicineSignByDateAsync(Integer medicinePlanId, Date signDate) {
        List<MedicineSignEntity> list = medicineSignRepository.findAllByMedicinePlanIdAndSignDateAndStatus(medicinePlanId, new java.sql.Date(signDate.getTime()), Byte.valueOf("0"));
        return list;
    }

    public Page<MedicineSignEntityImpl1> getHistoryList(Integer userId, Integer page, Integer size) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "signDate"));
        orders.add(new Sort.Order(Sort.Direction.DESC, "signTime"));
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<MedicineSignEntity> pageList = medicineSignRepository.findAllByUserIdAndStatus(userId, Byte.valueOf("0"), pageable);
        Page<MedicineSignEntityImpl1> pageList2 = pageList.map((MedicineSignEntity medicineSignEntity) -> {
            return getMedicineSignEntityImpl1(medicineSignEntity);
        });
        return pageList2;
    }

    private MedicineSignEntityImpl1 getMedicineSignEntityImpl1(MedicineSignEntity medicineSignEntity) {
        if (null == medicineSignEntity) {
            return null;
        }
        MedicineSignEntityImpl1 impl1 = new MedicineSignEntityImpl1(medicineSignEntity);

        MedicinePlanEntity medicinePlanEntity = medicinePlanRepository.findOneById(medicineSignEntity.getMedicinePlanId());
        if (null != medicinePlanEntity) {
            impl1.setMedicine(medicinePlanEntity.getMedicine());
            impl1.setFrequency(medicinePlanEntity.getFrequency());
            impl1.setIsMain(medicinePlanEntity.getIsMain());
        } else {
            impl1.setMedicine("未知");
            impl1.setFrequency(3);
            impl1.setIsMain(Short.valueOf("0"));
        }
        return impl1;
    }


    public MedicineSignEntity add(Integer userId, Integer medicinePlanId, Date signDate, String signTime,
                                  Short eatMedicineOnTime, Integer dosage, String badEffect, String badEffectDetail,
                                  String hashNameArray) {
        MedicineSignEntity medicineSignEntity = new MedicineSignEntity();
        medicineSignEntity.setUserId(userId);
        medicineSignEntity.setUserId(medicinePlanId);
        medicineSignEntity.setSignDate(new java.sql.Date(signDate.getTime()));
        medicineSignEntity.setSignTime(signTime);
        medicineSignEntity.setEatMedicineOnTime(eatMedicineOnTime);
        medicineSignEntity.setDosage(dosage);
        medicineSignEntity.setBadEffect(badEffect);
        medicineSignEntity.setBadEffectDetail(badEffectDetail);

        medicineSignRepository.save(medicineSignEntity);

        Integer medicineSignId = medicineSignEntity.getId();

        //添加关联的电子检查单
        if (null != hashNameArray) {
            List<MedicineSignElectronicRecordEntity> recordEntityList = Arrays.asList(hashNameArray.split(",")).stream()
                    .map((String hashName) -> {
                        MedicineSignElectronicRecordEntity medicineSignElectronicRecordEntity = new MedicineSignElectronicRecordEntity();
                        medicineSignElectronicRecordEntity.setMedicineSignId(medicineSignId);
                        medicineSignElectronicRecordEntity.setHashName(hashName);
                        medicineSignElectronicRecordEntity.setStatus(Byte.valueOf("0"));
                        return medicineSignElectronicRecordEntity;
                    }).collect(Collectors.toList());

            medicineSignElectronicRecordRepository.saveAll(recordEntityList);
        }

        return medicineSignEntity;

    }

    public MedicineSignEntity get(Integer medicineSignId) {
        MedicineSignEntity medicineSignEntity = medicineSignRepository.findOneByIdAndStatus(medicineSignId, Byte.valueOf("0"));
        return medicineSignEntity;
    }

    public void del(Integer medicineSignId) {
        medicineSignRepository.deleteAllByIdAndStatus(medicineSignId, Byte.valueOf("0"));
    }

    public void update(Integer medicineSignId, Short eatMedicineOnTime, Integer dosage, String badEffect,
                       String badEffectDetail, String hashNameArray) {
        MedicineSignEntity medicineSignEntity = medicineSignRepository.findOneByIdAndStatus(medicineSignId, Byte.valueOf("0"));
        if (null == medicineSignEntity) {
            return;
        }

        medicineSignEntity.setEatMedicineOnTime(eatMedicineOnTime);
        medicineSignEntity.setDosage(dosage);
        medicineSignEntity.setBadEffect(badEffect);
        medicineSignEntity.setBadEffectDetail(badEffectDetail);
        medicineSignEntity.setEatMedicineOnTime(eatMedicineOnTime);


        medicineSignElectronicRecordRepository.deleteAllByMedicineSignIdAndStatus(medicineSignId, Byte.valueOf("0"));
        //添加关联的电子检查单
        if (null != hashNameArray) {
            List<MedicineSignElectronicRecordEntity> recordEntityList = Arrays.asList(hashNameArray.split(",")).stream()
                    .map((String hashName) -> {
                        MedicineSignElectronicRecordEntity medicineSignElectronicRecordEntity = new MedicineSignElectronicRecordEntity();
                        medicineSignElectronicRecordEntity.setMedicineSignId(medicineSignId);
                        medicineSignElectronicRecordEntity.setHashName(hashName);
                        medicineSignElectronicRecordEntity.setStatus(Byte.valueOf("0"));
                        return medicineSignElectronicRecordEntity;
                    }).collect(Collectors.toList());

            medicineSignElectronicRecordRepository.saveAll(recordEntityList);
        }
    }

    public List<MedicineSignEntityImpl2> getSignDaysByMonth(Integer userId, Integer year, Integer month, Integer mainMedicinePlanId) {
        Optional<List<String>> oList = medicineSignRepository.getSignDaysByMonth(userId, year, month, mainMedicinePlanId);
        if (oList.isPresent()) {
            List<MedicineSignEntityImpl2> list = oList.get().stream().map((String fieldThree) -> {
                MedicineSignEntityImpl2 medicineSignEntityImpl2 = new MedicineSignEntityImpl2();
                String[] arr = fieldThree.split("%%%");
                medicineSignEntityImpl2.setSignCount(Integer.valueOf(arr[0]));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date signDate = null;
                try {
                    signDate = simpleDateFormat.parse(arr[1]);
                } catch (ParseException ex) {

                }
                medicineSignEntityImpl2.setSignDate(new java.sql.Date(signDate.getTime()));
                String notify = "";
                if (arr[2] != null && !arr[2].equals("NULL")) {
                    notify = arr[2].toString();
                }
                medicineSignEntityImpl2.setNotify(notify);

                return medicineSignEntityImpl2;

            }).collect(Collectors.toList());
            return list;
        }

        return null;
    }


}
