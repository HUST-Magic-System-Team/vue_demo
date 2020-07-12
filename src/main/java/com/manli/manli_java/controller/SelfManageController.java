package com.manli.manli_java.controller;

import com.manli.manli_java.model_impl.SelfManageEvaluateImpl;
import com.manli.manli_java.service.SelfManageService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/self_manage")
public class SelfManageController {

    @Autowired
    SelfManageService selfManageService;


    @RequestMapping(value = "evaluate")
    public ResultBean evaluate(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");

        SelfManageEvaluateImpl selfManageEvaluateImpl = selfManageService.evaluate(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("percent", selfManageEvaluateImpl.getPercent());
        data.put("evaluateResult", selfManageEvaluateImpl.getEvaluateResult());
        data.put("evaluateResultDetail", selfManageEvaluateImpl.getEvaluateResultDetail());


        return new ResultBean(data);
    }
}
