package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.ElectronicRecordGroupEntity;
import com.manli.manli_java.service.ElectronicRecordGroupService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/electronic_record_group")
public class ElectronicRecordGroupController {
    @Autowired
    ElectronicRecordGroupService electronicRecordGroupService;
    @RequestMapping(value = "bulksetgroup")
    public ResultBean bulksetgroup(@RequestParam("recordIds")String recordIds,
                               @RequestParam("groupId")Integer groupId) {
        String[] str=recordIds.split(",");
        Integer[] recordIns1=new Integer[str.length];
        for (int i = 0; i <str.length; i++) {
            recordIns1[i]=Integer.parseInt(str[i]);
        }
        electronicRecordGroupService.updateGroupId(recordIns1,groupId);
        return new ResultBean(ErrorCodeEnum.OK);
    }

    @RequestMapping(value = "isvalidgroup")
    public ResultBean isvalidgroup(@RequestParam("groupId")Integer groupId) {
        if (groupId==null){
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        boolean validGroup = electronicRecordGroupService.isValidGroup(groupId);
        Map<String,Object> data=new HashMap<>();
        data.put("isValidGroup",validGroup);
        return new ResultBean(data);
    }

    @RequestMapping(value = "groupinfo")
    public ResultBean groupinfo() {
        List<ElectronicRecordGroupEntity> groupList = electronicRecordGroupService.getGroupinfo();
        Map<Integer,Object> groupInfo=new HashMap<>();
        for (ElectronicRecordGroupEntity info:groupList){
            groupInfo.put(info.getId(),info.getGroup());
        }
        Map<String,Object> data=new HashMap<>();
        data.put("groupList",groupList);
        data.put("groupInfo",groupInfo);
        return new ResultBean(data);
    }

}
