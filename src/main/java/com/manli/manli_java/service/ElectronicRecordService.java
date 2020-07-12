package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.*;
import com.manli.manli_java.model_impl.ElectronicRecordEntityImpl1;
import com.manli.manli_java.model_impl.ElectronicRecordEntityImpl1;
import com.manli.manli_java.repository.ElectronicRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    public Page<ElectronicRecordEntityImpl1> getElectronicRecordList(Integer userId, Integer page, Integer size){
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "signDate"));
        orders.add(new Sort.Order(Sort.Direction.DESC, "signTime"));
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ElectronicRecordEntity> pageList1=electronicRecordRepository.findAllByUserIdAndStatus(userId, Byte.valueOf("0"), pageable);
        Page<ElectronicRecordEntityImpl1> pageList2 = pageList1.map((ElectronicRecordEntity electronicRecordEntity) -> {
                return getElectronicRecordEntityImpl1(electronicRecordEntity);
        });
        return pageList2;

    }
    private ElectronicRecordEntityImpl1 getElectronicRecordEntityImpl1(ElectronicRecordEntity electronicRecordEntity) {
        if (null == electronicRecordEntity) {
            return null;
        }
        ElectronicRecordEntityImpl1 impl1 = new ElectronicRecordEntityImpl1(electronicRecordEntity);
        return impl1;
    }
}
