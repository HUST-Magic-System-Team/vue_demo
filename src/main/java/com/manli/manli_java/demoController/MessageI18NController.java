package com.manli.manli_java.demoController;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.manli.manli_java.eenum.ErrorCodeEnum;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping(value = "api/demo/task")
public class MessageI18NController {
    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "hash")
    public Map<String, ?> hash() {
        Map<String, Map<Object, String>> partThree = new HashMap<>(3);

        //入库状态 下拉选框
        Map<Object, String> stockStatus = new HashMap<>(6);
        for (ErrorCodeEnum c : ErrorCodeEnum.values()) {
            stockStatus.put(c.ordinal(), c.name());
        }
        partThree.put("stockStatus", stockStatus);

        //入库类型 下拉选框
        Map<Object, String> stockType = new HashMap<>(6);
        for (ErrorCodeEnum c : ErrorCodeEnum.values()) {
            stockType.put(c.ordinal(), c.name());
        }
        partThree.put("stockType", stockType);
        return partThree;
    }

    @RequestMapping(value = "jsonp")
    @ResponseJSONP(callback = "callback")
    public Map<String, String> jsonp() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        return map;
    }

    @RequestMapping(value = "result")
    public ResultBean result() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        return new ResultBean(0, "123", map);
    }


    //i18n controller
    @RequestMapping(value = "i18n")
    public ResultBean i18n() {
        Map<String, Object> map = new HashMap<>();
        String message = messageSource.getMessage("page.content", null, Locale.SIMPLIFIED_CHINESE);
        map.put("home_welcome", message);
        return new ResultBean(0, "123", map);
    }

}
