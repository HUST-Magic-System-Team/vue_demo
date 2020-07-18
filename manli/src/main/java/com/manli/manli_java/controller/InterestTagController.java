package com.manli.manli_java.controller;

import com.manli.manli_java.model_auto.InterestTagEntity;
import com.manli.manli_java.service.InterestTagService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/interest_tag")
public class InterestTagController {
    @Autowired
    InterestTagService interestTagService;

    @RequestMapping(value = "list")
    public ResultBean list() {
        List<InterestTagEntity> list = interestTagService.getAvailableInterestTags();

        Map<String, Object> data = new HashMap<>();
        data.put("list", list);

        return new ResultBean(data);
    }
}
