package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.service.ElectronicRecordGroupService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String isvalidgroup() {
        return "json isvalidgroup";
    }

    @RequestMapping(value = "groupinfo")
    public String groupinfo() {
        return "json groupinfo";
    }

}
