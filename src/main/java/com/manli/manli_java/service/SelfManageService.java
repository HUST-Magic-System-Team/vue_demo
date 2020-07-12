package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.MedicinePlanEntity;
import com.manli.manli_java.model_auto.MedicineSignEntity;
import com.manli.manli_java.model_impl.SelfManageEvaluateImpl;
import com.manli.manli_java.repository.MedicinePlanRepository;
import com.manli.manli_java.repository.MedicineRepository;
import com.manli.manli_java.repository.MedicineSignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SelfManageService {

    @Autowired
    MedicineSignRepository medicineSignRepository;

    @Autowired
    MedicinePlanRepository medicinePlanRepository;


    public SelfManageEvaluateImpl evaluate(Integer userId) {
        Optional<MedicinePlanEntity> o = medicinePlanRepository.findOneByUserIdAndIsMainAndStatus(userId, Short.valueOf("1"), Byte.valueOf("0"));
        MedicinePlanEntity mainMedicinePlanEntity = o.orElse(null);

        SelfManageEvaluateImpl selfManageEvaluateImpl = new SelfManageEvaluateImpl();
        if (null == mainMedicinePlanEntity) {
            selfManageEvaluateImpl.setPercent(0.0);
            selfManageEvaluateImpl.setEvaluateResult("未评级");
            selfManageEvaluateImpl.setEvaluateResultDetail("快去添加服药计划哦");
            return selfManageEvaluateImpl;
        }

        Date startDate = new Date(mainMedicinePlanEntity.getStartTime().getTime());
        Date endDate = new Date(System.currentTimeMillis());
        Integer mainMedicinePlanId = mainMedicinePlanEntity.getId();


        List<MedicineSignEntity> list = medicineSignRepository
                .findAllByMedicinePlanIdAndUserIdAndStatusAndSignDateBetween(mainMedicinePlanId, userId, Byte.valueOf("0"),
                        new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));
        Integer signCount = 0;
        if (null != list) {
            signCount = list.size();
        }
        Long days = endDate.getTime() - startDate.getTime() / (1000 * 60 * 60 * 24) + 1;
        DecimalFormat df = new DecimalFormat("#.##");
        Double percent = Double.valueOf(df.format((signCount * 1.0 / (days * 3))));

        String evaluateResult;

        if (percent < 0.6) {
            evaluateResult = "差";
        } else if (percent < 0.8) {
            evaluateResult = "中";
        } else {
            evaluateResult = "优";
        }

        String evaluateResultDetail = "您已击败__PERCENT__%的用户，记得按时服药哦".replace("__PERCENT__", Double.toString((percent * 100)));

        selfManageEvaluateImpl.setPercent(percent);
        selfManageEvaluateImpl.setEvaluateResult(evaluateResult);
        selfManageEvaluateImpl.setEvaluateResultDetail(evaluateResultDetail);

        return selfManageEvaluateImpl;

    }

}
