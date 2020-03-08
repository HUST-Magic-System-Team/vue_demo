package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.*;
import com.manli.manli_java.repository.ElectronicRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;


@Service
public class ElectronicRecordService {

    @Autowired
    private ElectronicRecordRepository electronicRecordRepository;

    public ElectronicRecordEntity add(Integer userId, String hashName, Date checkTime) {
        ElectronicRecordEntity electronicRecordEntity = new ElectronicRecordEntity();
        electronicRecordEntity.setUserId(userId);
        electronicRecordEntity.setHashName(hashName);
        electronicRecordEntity.setCheckTime(new java.sql.Date(checkTime.getTime()));
        electronicRecordEntity.setUploadTime(new Timestamp(System.currentTimeMillis()));
        electronicRecordEntity.setStatus(Byte.valueOf("0"));

        return electronicRecordRepository.save(electronicRecordEntity);
    }

    public void remove(Integer userId, String hashName) {
        electronicRecordRepository.deleteByUserIdAndHashName(userId, hashName);
    }

}
