package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.CurativeEffectEntity;
import com.manli.manli_java.model_auto.UserCurativeEffectElectronicRecordEntity;
import com.manli.manli_java.model_auto.UserCurativeEffectEntity;
import com.manli.manli_java.repository.CurativeEffectRepository;
import com.manli.manli_java.repository.UserCurativeEffectElectronicRecordRepository;
import com.manli.manli_java.repository.UserCurativeEffectRepository;
import com.manli.manli_java.util.TupleTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurativeEffectService {

    @Autowired
    private UserCurativeEffectRepository userCurativeEffectRepository;

    @Autowired
    private CurativeEffectRepository curativeEffectRepository;

    @Autowired
    private UserCurativeEffectElectronicRecordRepository userCurativeEffectElectronicRecordRepository;


    //find one
    public UserCurativeEffectEntity getLatestUserCurativeEffect(Integer userId) {
        UserCurativeEffectEntity userCurativeEffectEntity = userCurativeEffectRepository.findTop1ByUserIdAndStatusOrderByCreatedAtDesc(userId, Byte.valueOf("0"));
        return userCurativeEffectEntity;
    }

    public TupleTwo<String, Integer> evaluate(Date startTime, Date bcrAblTime, Integer bcrAblIs) {
        Integer intEvaluateResult;
        String evaluateResult;
        Float m = _getDateDiff(bcrAblTime, startTime);
        Integer month;
        if (m <= 4.5) {
            month = 3;
            intEvaluateResult = _getCurativeEffectThreeMonth(bcrAblIs);
        } else if (m > 4.5 && m <= 8.9) {
            month = 6;
            intEvaluateResult = _getCurativeEffectSixMonth(bcrAblIs);
        } else if (m > 8.9 && m <= 12) {
            month = 12;
            intEvaluateResult = _getCurativeEffectTweleveMonth(bcrAblIs);
        } else {
            month = -1; //-1表示一年以上
            intEvaluateResult = _getCurativeEffectAboveTweleveMonth(bcrAblIs);
        }

        if (intEvaluateResult == 1) {
            evaluateResult = "最佳反应";
        } else if (intEvaluateResult == 2) {
            evaluateResult = "警告";
        } else if (intEvaluateResult == 3) {
            evaluateResult = "治疗失败";
        } else {
            evaluateResult = "治疗效果未知";
        }
        return new TupleTwo<>(evaluateResult, month);

    }

    private Float _getDateDiff(Date bcrAblTime, Date startTime) {
        Long timeStamp1 = startTime.getTime();
        Long timeStamp2 = bcrAblTime.getTime();
        Float cha = Float.valueOf(new BigDecimal(String.valueOf(timeStamp2 - timeStamp1)).toString());
        Float m = cha / 1000 / 86400 / 30;
        return m;
    }

    private Integer _getCurativeEffectThreeMonth(Integer bcrAblIs) {
        Integer res;
        if (bcrAblIs <= 10) {
            res = 1;
        } else {
            res = 2;
        }
        return res;
    }

    private Integer _getCurativeEffectSixMonth(Integer bcrAblIs) {
        Integer res;
        if (bcrAblIs <= 10) {
            res = 1;
        } else {
            res = 3;
        }

        return res;
    }

    private Integer _getCurativeEffectTweleveMonth(Integer bcrAblIs) {
        Integer res;
        if (bcrAblIs <= 1) {
            res = 1;
        } else if (bcrAblIs > 1 && bcrAblIs <= 10) {
            res = 2;
        } else {
            res = 3;
        }
        return res;

    }

    private Integer _getCurativeEffectAboveTweleveMonth(Integer bcrAblIs) {
        Integer res;
        if (bcrAblIs <= 0.1) {
            res = 1;
        } else if (bcrAblIs > 0.1 && bcrAblIs <= 1) {
            res = 2;
        } else {
            res = 3;
        }
        return res;
    }

    public String getEvaluateResultDetail(String evaluateResult) {
        CurativeEffectEntity curativeEffectEntity = curativeEffectRepository.findOneByEvaluateResult(evaluateResult);
        if (null != curativeEffectEntity) {
            return curativeEffectEntity.getEvaluateResultDetail();
        }
        return null;
    }


    //page
    public Page<UserCurativeEffectEntity> getUserHistoryPage(Integer userId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<UserCurativeEffectEntity> pageList = userCurativeEffectRepository.findAllByUserIdAndStatus(userId, Byte.valueOf("0"), pageable);
        return pageList;
    }

    //save
    public void saveUserCurativeEffect(Integer userId, String evaluateResult, String evaluateResultDetail, Integer month, Date startTime, Date bcrAblTime, Integer bcrAblIs, String hashNameArray) {
        UserCurativeEffectEntity userCurativeEffectEntity = new UserCurativeEffectEntity();
        userCurativeEffectEntity.setUserId(userId);
        userCurativeEffectEntity.setEvaluateResult(evaluateResult);
        userCurativeEffectEntity.setEvaluateResultDetail(evaluateResultDetail);
        userCurativeEffectEntity.setMonth(month);
        userCurativeEffectEntity.setStartTime(new java.sql.Date(startTime.getTime()));
        userCurativeEffectEntity.setBcrAblTime(new java.sql.Date(bcrAblTime.getTime()));
        userCurativeEffectEntity.setBcrAblIs(bcrAblIs);
        userCurativeEffectEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userCurativeEffectEntity.setStatus(Byte.valueOf("0"));

        userCurativeEffectRepository.save(userCurativeEffectEntity);

        Integer insertId = userCurativeEffectEntity.getId();

        if (null != hashNameArray) {
            String[] hashNameArr = hashNameArray.split(",");
            if (hashNameArr.length > 0) {
                List<UserCurativeEffectElectronicRecordEntity> entityList = Arrays.asList(hashNameArray.split(",")).stream().map((String hashName) -> {
                    UserCurativeEffectElectronicRecordEntity userCurativeEffectElectronicRecordEntity = new UserCurativeEffectElectronicRecordEntity();
                    userCurativeEffectElectronicRecordEntity.setHashName(hashName);
                    userCurativeEffectElectronicRecordEntity.setUserCurativeEffectId(insertId);
                    userCurativeEffectElectronicRecordEntity.setStatus(Byte.valueOf("0"));
                    return userCurativeEffectElectronicRecordEntity;
                }).collect(Collectors.toList());
                userCurativeEffectElectronicRecordRepository.saveAll(entityList);
            }
        }

    }

    public UserCurativeEffectElectronicRecordEntity saveUserCurativeEffectElectronicRecord(UserCurativeEffectElectronicRecordEntity userCurativeEffectElectronicRecordEntity) {
        return userCurativeEffectElectronicRecordRepository.save(userCurativeEffectElectronicRecordEntity);
    }


}
