package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_impl.ElectronicRecordEntityImpl1;
import com.manli.manli_java.model_impl.PageImp1;
import com.manli.manli_java.service.ElectronicRecordService;
import com.manli.manli_java.service.MemberService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/electronic_record")
public class ElectronicRecordController {
    @Autowired
    ElectronicRecordService electronicRecordService;
    @Autowired
    MemberService memberService;
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public ResultBean list(@RequestParam("page")Integer page,
                       @RequestParam("size")Integer size,
                       @RequestParam(value = "displayMode",required = false)String displayMode,
                       HttpServletRequest request) {
        if (null == page || null == size) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (page < 1) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        //java的page是从第０页开始的,所以要特殊处理下
        page = page - 1;
        Integer userId=(Integer) request.getAttribute("userId");

        Page<ElectronicRecordEntityImpl1> electronicRecordList = electronicRecordService.getElectronicRecordList(userId, page, size);
        if (null == electronicRecordList) {
            electronicRecordList = Page.empty();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("list", PageImp1.fromPage(electronicRecordList));
        data.put("isMember",memberService.isMember(userId));
        data.put("displayMode",displayMode);
        return  new ResultBean(data);
    }

}
