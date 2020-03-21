package com.manli.manli_java.service;

import com.manli.manli_java.repository.ElectronicRecordGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectronicRecordGroupService {
    @Autowired
    ElectronicRecordGroupRepository electronicRecordGroupRepository;
    public void updateGroupId(Integer[] recordIds,Integer groupId){
        for (int i = 0; i <recordIds.length ; i++) {
            electronicRecordGroupRepository.findById(i);
        }
    }
}
