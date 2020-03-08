package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.UserTokenEntity;
import com.manli.manli_java.repository.UserTokenRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class LoginService {
    @Autowired
    private UserTokenRepository userTokenRepository;

    public boolean isLogin(String token) {
        return userTokenRepository.existsByToken(token);
    }

    public boolean isRegister(String phone) {
        return userTokenRepository.existsByPhone(phone);
    }

    @Transactional
    public String newToken(String phone) {
        String token = DigestUtils.md5Hex(phone + String.valueOf(System.currentTimeMillis()));
        UserTokenEntity userTokenEntity = userTokenRepository.findOneByPhone(phone);
        Timestamp ts = new Timestamp(System.currentTimeMillis());

        if (null == userTokenEntity) {
            //第一次设置createAt
            userTokenEntity = new UserTokenEntity();
            userTokenEntity.setPhone(phone);
            userTokenEntity.setCreatedAt(ts);

        }
        userTokenEntity.setToken(token);
        userTokenEntity.setUpdatedAt(ts);

        userTokenRepository.save(userTokenEntity);
        return token;
    }

    public int getUserIdByToken(String token) {
        UserTokenEntity userTokenEntity = userTokenRepository.findOneByToken(token);
        return (null != userTokenEntity) ? userTokenEntity.getId() : -1;
    }

    @Transactional
    public void clearToken(String phone) {
        UserTokenEntity userTokenEntity = userTokenRepository.findOneByPhone(phone);
        if (null != userTokenEntity) {
            userTokenEntity.setToken(null);
            userTokenRepository.save(userTokenEntity);
        }
    }

}
