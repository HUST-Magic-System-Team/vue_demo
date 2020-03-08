package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.InterestTagEntity;
import com.manli.manli_java.model_auto.UserInfoEntity;
import com.manli.manli_java.repository.InterestTagRepository;
import com.manli.manli_java.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class InterestTagService {

    @Autowired
    private UserInfoRepository    userInfoRepository;
    @Autowired
    private InterestTagRepository interestTagRepository;


    public List<InterestTagEntity> getUserInterestTags(Integer userId) {
        UserInfoEntity userInfoEntity = userInfoRepository.findOneByUserId(userId);
        if (null == userInfoEntity) {
            return new ArrayList<>(0);
        }

        String interestTags = userInfoEntity.getInterestTags();
        String[] interestTagsArr = new String[0];
        if (null != interestTags) {
            interestTagsArr = interestTags.split(",");
        }
        List<InterestTagEntity> list = new ArrayList<>();
        for (String tagId : interestTagsArr) {
            InterestTagEntity InterestTagEntity = getInterestTagMapInfoByTagId(Integer.valueOf(tagId));
            list.add(InterestTagEntity);
        }
        return list;
    }

    public void setUserInterestTags(Integer userId, String interestTagIds) {
        UserInfoEntity userInfoEntity = userInfoRepository.findOneByUserId(userId);
        if (null != userInfoEntity) {
            userInfoEntity.setInterestTags(interestTagIds);
        }
    }


    public List<InterestTagEntity> getAvailableInterestTags() {
        return interestTagRepository.findAllByStatus(Byte.valueOf("0"));
    }


    public InterestTagEntity getInterestTagMapInfoByTagId(Integer tagId) {
        InterestTagEntity interestTagEntity = interestTagRepository.findOneByIdAndStatus(tagId, Byte.valueOf("0"));
        if (null != interestTagEntity) {
            return interestTagEntity;
        }
        return null;
    }

    public void getList() {

    }

}
