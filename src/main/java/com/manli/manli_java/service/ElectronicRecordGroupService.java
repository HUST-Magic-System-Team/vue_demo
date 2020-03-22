package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.ElectronicRecordEntity;
import com.manli.manli_java.model_auto.ElectronicRecordGroupEntity;
import com.manli.manli_java.repository.ElectronicRecordGroupRepository;
import com.manli.manli_java.repository.ElectronicRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ElectronicRecordGroupService {
    @Autowired
    ElectronicRecordRepository electronicRecordRepository;
    @Autowired
    ElectronicRecordGroupRepository electronicRecordGroupRepository;
    public void updateGroupId(Integer[] recordIds,Integer groupId){
        for (int i = 0; i <recordIds.length ; i++) {
            ElectronicRecordEntity oneById = electronicRecordRepository.findOneById(recordIds[i]);
            oneById.setGroupId(groupId);
        }
    }
    public boolean isValidGroup(Integer groupId){
        ElectronicRecordGroupEntity oneById = electronicRecordGroupRepository.findOneById(groupId);
        if (oneById==null){
            return false;
        }else {
            return true;
        }

    }
    public List<ElectronicRecordGroupEntity> getGroupinfo(){
        List<ElectronicRecordGroupEntity> groupList=electronicRecordGroupRepository.findAllByStatus(Byte.valueOf("0"));
        return groupList;
    }
}
