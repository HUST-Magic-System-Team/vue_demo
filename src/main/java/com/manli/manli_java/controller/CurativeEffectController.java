package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.UserCurativeEffectEntity;
import com.manli.manli_java.model_impl.PageImp1;
import com.manli.manli_java.service.CurativeEffectService;
import com.manli.manli_java.util.ResultBean;
import com.manli.manli_java.util.TupleTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "api/curative_effect")
public class CurativeEffectController {
    @Autowired
    CurativeEffectService curativeEffectService;

    @RequestMapping(value = "history")
    public ResultBean history(@RequestParam("page") Integer page,
                              @RequestParam("size") Integer size,
                              HttpServletRequest request
    ) {

        if (null == page || null == size) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (page < 1) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        //java的page是从第０页开始的,所以要特殊处理下
        page = page - 1;
        Integer userId = (Integer) request.getAttribute("userId");

        Page<UserCurativeEffectEntity> pageList = curativeEffectService.getUserHistoryPage(userId, page, size);
        if (null == pageList) {
            pageList = Page.empty();
        }
        UserCurativeEffectEntity latest = curativeEffectService.getLatestUserCurativeEffect(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("list", PageImp1.fromPage(pageList));
        data.put("latest", latest);

        return new ResultBean(data);
    }


    @RequestMapping(value = "evaluate")
    public ResultBean evaluate(@RequestParam("startTime") String startTime,
                               @RequestParam("bcrAblTime") String bcrAblTime,
                               @RequestParam("bcrAblIs") Integer bcrAblIs,
                               @RequestParam(value = "hashNameArray", required = false) String hashNameArray,

                               HttpServletRequest request) {
        if (null == startTime) {
            return new ResultBean(ErrorCodeEnum.TKI_DATE_FORMAT_ERROR);
        }

        if (null == bcrAblTime) {
            return new ResultBean(ErrorCodeEnum.MISS_BCRABL_IS);
        }

        if (null == bcrAblIs) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        String regex = "^\\d{4}-\\d{2}-\\d{2}$";

        if (!Pattern.matches(regex, startTime)) {
            return new ResultBean(ErrorCodeEnum.TKI_DATE_FORMAT_ERROR);
        }
        if (!Pattern.matches(regex, bcrAblTime)) {
            return new ResultBean(ErrorCodeEnum.BCSABL_FORMAT_ERROR);
        }


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startTimeDate;
        Date bcrAblTimeDate;
        try {
            startTimeDate = simpleDateFormat.parse(startTime);
        } catch (Exception ex) {
            return new ResultBean(ErrorCodeEnum.BCSABL_FORMAT_ERROR);
        }

        try {
            bcrAblTimeDate = simpleDateFormat.parse(bcrAblTime);
        } catch (Exception ex) {
            return new ResultBean(ErrorCodeEnum.TKI_DATE_FORMAT_ERROR);
        }


        Integer userId = (Integer) request.getAttribute("userId");

        TupleTwo<String, Integer> tuple = curativeEffectService.evaluate(startTimeDate, bcrAblTimeDate, bcrAblIs);
        String evaluateResult = tuple.first;
        Integer month = tuple.second;
        String evaluateResultDetail = curativeEffectService.getEvaluateResultDetail(evaluateResult);
        curativeEffectService.saveUserCurativeEffect(userId, evaluateResult, evaluateResultDetail, month, startTimeDate, bcrAblTimeDate, bcrAblIs, hashNameArray);

        Map<String, Object> data = new HashMap<>();
        data.put("evaluateResult", evaluateResult);

        return new ResultBean(data);

    }

}
