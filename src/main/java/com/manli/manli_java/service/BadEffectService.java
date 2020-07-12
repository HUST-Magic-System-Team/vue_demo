package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.BadEffectEntity;
import com.manli.manli_java.model_auto.BadEffectTreatmentEntity;
import com.manli.manli_java.model_auto.UserBadEffectEntity;
import com.manli.manli_java.repository.BadEffectRepository;
import com.manli.manli_java.repository.BadEffectTreatmentRepository;
import com.manli.manli_java.repository.UserBadEffectRepository;
import com.manli.manli_java.util.TupleTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
public class BadEffectService {

    @Autowired
    private BadEffectRepository badEffectRepository;

    @Autowired
    private BadEffectTreatmentRepository badEffectTreatmentRepository;

    @Autowired
    private UserBadEffectRepository userBadEffectRepository;

    //find one
    public TupleTwo<UserBadEffectEntity, String> getLatestUserBadEffect(Integer userId) {
        String treatment = "";
        UserBadEffectEntity userBadEffectEntity = userBadEffectRepository.findTop1ByUserIdAndStatusOrderByCreatedAtDesc(userId, Byte.valueOf("0"));
        if (null != userBadEffectEntity) {
            Integer badEffectLevel = userBadEffectEntity.getBadEffectLevel();
            BadEffectTreatmentEntity badEffectTreatmentEntity = badEffectTreatmentRepository.findOneByBadEffectLevel(Byte.valueOf(badEffectLevel.toString()));

            if (null != badEffectTreatmentEntity) {
                treatment = badEffectTreatmentEntity.getTreatment();
            }
        }

        return new TupleTwo<>(userBadEffectEntity, treatment);
    }

    public Integer getBadEffectLevelByBadEffect(String badEffect) {
        BadEffectEntity badEffectEntity = badEffectRepository.findOneByBadEffectAndStatus(badEffect, Byte.valueOf("0"));
        if (null != badEffectEntity) {
            return Integer.valueOf(badEffectEntity.getBadEffectLevel());
        } else {
            return -1; //-1代表未知级别
        }
    }

    public String evaluate(Integer badEffectLevel) {
        String treatment = "";
        BadEffectTreatmentEntity badEffectTreatmentEntity = badEffectTreatmentRepository.findOneByBadEffectLevel(Byte.valueOf(badEffectLevel.toString()));

        if (null != badEffectTreatmentEntity) {
            treatment = badEffectTreatmentEntity.getTreatment();
        }

        return treatment;
    }


    //list
    public List<BadEffectEntity> getDistinctList() {
        List<BadEffectEntity> list = badEffectRepository.findDistinctBadEffectByStatus(Byte.valueOf("0"));
        return list;
    }

    public List<String> getDistinctBadEffectList() {
        List<String> list = badEffectRepository.getDistinctBadEffectList(Byte.valueOf("0"));
        return list;
    }

    public List<BadEffectEntity> getBadEffectList() {
        List<BadEffectEntity> list = badEffectRepository.findAllByStatusOrderByPositionDesc(Byte.valueOf("0"));
        return list;
    }


    public List<BadEffectTreatmentEntity> getBadEffectTreatmentList() {
        List<BadEffectTreatmentEntity> list = badEffectTreatmentRepository.findAllByStatus(Byte.valueOf("0"));
        return list;
    }

    public List<UserBadEffectEntity> getUserHistoryList(Integer userId) {
        List<UserBadEffectEntity> list = userBadEffectRepository.findAllByUserIdAndStatusOrderByCreatedAtDesc(userId, Byte.valueOf("0"));
        return list;
    }

    //page
    public Page<UserBadEffectEntity> getUserHistoryPage(Integer userId, Integer page, Integer size) {

        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<UserBadEffectEntity> pageList = userBadEffectRepository.findAllByUserIdAndStatus(userId, Byte.valueOf("0"), pageable);
        return pageList;
    }

    //save
    public UserBadEffectEntity saveUserBadEffect(Integer userId, String badEffect, Integer badEffectLevel,
                                                 String badEffectDetail) {
        UserBadEffectEntity userBadEffectEntity = new UserBadEffectEntity();
        userBadEffectEntity.setUserId(userId);
        userBadEffectEntity.setBadEffect(badEffect);
        userBadEffectEntity.setBadEffectLevel(badEffectLevel);
        userBadEffectEntity.setBadEffectDetail(badEffectDetail);
        userBadEffectEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        userBadEffectEntity = userBadEffectRepository.save(userBadEffectEntity);
        return userBadEffectEntity;
    }


}
