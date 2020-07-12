package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.*;
import com.manli.manli_java.model_impl.UserTkiRecordInfoImpl1;
import com.manli.manli_java.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserTkiRecordInfoService {
    @Autowired
    UserTkiRecordInfoRepository userTkiRecordInfoRepository;

    public List<UserTkiRecordInfoEntity> getUserTkiRecordInfoAsync(Integer userId) {
        List<UserTkiRecordInfoEntity> tki = userTkiRecordInfoRepository.findAllByUserIdAndStatus(userId, Byte.valueOf("0"));
        return tki;
    }

    public void updateUserTkiRecordInfo(Integer userId, List<UserTkiRecordInfoImpl1> tkiList) {
        userTkiRecordInfoRepository.deleteAllByUserIdAndStatus(userId, Byte.valueOf("0"));

        List<UserTkiRecordInfoEntity> entityList = tkiList.stream().map((UserTkiRecordInfoImpl1 userTkiRecordInfoImpl1) -> {
            UserTkiRecordInfoEntity userTkiRecordInfoEntity = new UserTkiRecordInfoEntity();

            userTkiRecordInfoEntity.setUserId(userTkiRecordInfoImpl1.getUserId());
            userTkiRecordInfoEntity.setTkiName(userTkiRecordInfoImpl1.getTkiName());
            userTkiRecordInfoEntity.setTkiStartDate(userTkiRecordInfoImpl1.getTkiStartDate());
            userTkiRecordInfoEntity.setTkiDosage(userTkiRecordInfoImpl1.getTkiDosage());
            userTkiRecordInfoEntity.setTkiFrequency(userTkiRecordInfoImpl1.getTkiFrequency());
            userTkiRecordInfoEntity.setTkiOrder(userTkiRecordInfoImpl1.getTkiOrder());
            userTkiRecordInfoEntity.setStatus(Byte.valueOf("0"));

            return userTkiRecordInfoEntity;
        }).collect(Collectors.toList());

        userTkiRecordInfoRepository.saveAll(entityList);
    }

}
