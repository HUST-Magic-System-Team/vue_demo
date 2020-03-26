package com.manli.manli_java.controller;

import com.manli.manli_java.service.HdfService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/hdf")
public class HdfController {
    @Autowired
    HdfService hdfService;
    @RequestMapping(value = "hdfurl",method = RequestMethod.POST)
    public ResultBean hdfurl(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        String hdfUrl = hdfService.getHdfUrl(userId);
        Map<String,Object> data=new HashMap<>();
        data.put("hdfUrl",hdfUrl);
        return new ResultBean(data);
    }
}
