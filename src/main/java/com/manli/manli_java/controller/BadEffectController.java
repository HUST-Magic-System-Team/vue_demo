package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.BadEffectEntity;
import com.manli.manli_java.model_auto.BadEffectTreatmentEntity;
import com.manli.manli_java.model_auto.UserBadEffectEntity;
import com.manli.manli_java.model_impl.PageImp1;
import com.manli.manli_java.model_impl.UserBadEffectEntityImpl1;
import com.manli.manli_java.service.BadEffectService;
import com.manli.manli_java.util.ResultBean;
import com.manli.manli_java.util.TupleTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/bad_effect")
public class BadEffectController {
    @Autowired
    BadEffectService badEffectService;

    @RequestMapping(value = "list")
    public ResultBean list() {
        List<String> list = badEffectService.getDistinctBadEffectList();
        Map<String, Object> data = new HashMap<>();
        if (null != list) {
            data.put("list", list);
        } else {
            data.put("list", new ArrayList<>());
        }

        return new ResultBean(data);
    }

    @RequestMapping(value = "dict")
    public ResultBean dict() {

        List<BadEffectEntity> badEffect = badEffectService.getBadEffectList();
        List<BadEffectTreatmentEntity> treatment = badEffectService.getBadEffectTreatmentList();

        Map<String, Object> data = new HashMap<>();
        data.put("badEffect", badEffect);
        data.put("treatment", treatment);

        return new ResultBean(data);
    }

    @RequestMapping(value = "history")
    public ResultBean history(@RequestParam("page") Integer page,
                              @RequestParam("size") Integer size,
                              HttpServletRequest request) {
        if (null == page || null == size) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (page < 1) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        //java的page是从第０页开始的,所以要特殊处理下
        page = page - 1;

        Integer userId = (Integer) request.getAttribute("userId");
        Page<UserBadEffectEntity> pageList = badEffectService.getUserHistoryPage(userId, page, size);
        if (null == pageList) {
            pageList = Page.empty();
        }
        TupleTwo<UserBadEffectEntity, String> tupleTwo = badEffectService.getLatestUserBadEffect(userId);

        UserBadEffectEntityImpl1 userBadEffectEntityImpl1 = new UserBadEffectEntityImpl1(tupleTwo.first);
        userBadEffectEntityImpl1.setTreatment(tupleTwo.second);

        Map<String, Object> data = new HashMap<>();
        data.put("list", PageImp1.fromPage(pageList));
        data.put("latest", userBadEffectEntityImpl1);

        return new ResultBean(data);
    }

    @RequestMapping(value = "evaluate")
    public ResultBean evaluate(@RequestParam("badEffect") String badEffect,
                               @RequestParam("badEffectLevel") Integer badEffectLevel,
                               @RequestParam("badEffectDetail") String badEffectDetail,
                               HttpServletRequest request) {
        if (null == badEffect || null == badEffectLevel || null == badEffectDetail) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        Integer userId = (Integer) request.getAttribute("userId");

        String evaluateResult = badEffectService.evaluate(badEffectLevel);
        badEffectService.saveUserBadEffect(userId, badEffect, badEffectLevel, badEffectDetail);

        Map<String, Object> data = new HashMap<>();
        data.put("evaluateResult", evaluateResult);

        return new ResultBean(data);

    }


}
