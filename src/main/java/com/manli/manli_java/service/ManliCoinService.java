package com.manli.manli_java.service;


import com.manli.manli_java.model_auto.UserInfoEntity;
import com.manli.manli_java.model_auto.UserManliCoinDetailEntity;
import com.manli.manli_java.repository.UserInfoRepository;
import com.manli.manli_java.repository.UserManliCoinDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ManliCoinService {

    @Autowired
    private UserManliCoinDetailRepository userManliCoinDetailRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public void add(Integer userId, String action, Timestamp actionTime, Integer coin) {
        UserManliCoinDetailEntity userManliCoinDetailEntity = new UserManliCoinDetailEntity();
        userManliCoinDetailEntity.setUserId(userId);
        userManliCoinDetailEntity.setAction(action);
        userManliCoinDetailEntity.setActionTime(actionTime);
        userManliCoinDetailEntity.setCoin(coin);

        userManliCoinDetailRepository.save(userManliCoinDetailEntity);


        UserInfoEntity userInfoEntity = userInfoRepository.findOneByUserId(userId);
        if (userInfoEntity != null) {
            Integer manliCoin = userInfoEntity.getManliCoin();
            userInfoEntity.setManliCoin(manliCoin + coin);
            userInfoRepository.save(userInfoEntity);
        }

    }

    public Integer getManliCoin(Integer userId) {
        UserInfoEntity userInfoEntity = userInfoRepository.findOneByUserId(userId);
        if (userInfoEntity != null) {
            Integer manliCoin = userInfoEntity.getManliCoin();
            return manliCoin;
        }
        return 0;
    }

    public Page getList(Integer userId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "actionTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<UserManliCoinDetailEntity> pageList = userManliCoinDetailRepository.findAllByUserIdAndStatus(userId, Byte.valueOf("0"), pageable);
        return pageList;
    }


}
