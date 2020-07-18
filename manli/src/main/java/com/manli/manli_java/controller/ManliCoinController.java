package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.UserManliCoinDetailEntity;
import com.manli.manli_java.model_impl.PageImp1;
import com.manli.manli_java.service.InterestTagService;
import com.manli.manli_java.service.ManliCoinService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/manli_coin")
public class ManliCoinController {
    @Autowired
    ManliCoinService manliCoinService;

    @RequestMapping(value = "addcoin")
    public ResultBean addcoin(@RequestParam("action") String action,
                              @RequestParam("actionTime") Timestamp actionTime,
                              @RequestParam("coin") Integer coin,
                              HttpServletRequest request
    ) {
        if (null == action || null == actionTime || null == coin) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        Integer userId = (Integer) request.getAttribute("userId");
        manliCoinService.add(userId, action, actionTime, coin);
        return ResultBean.OK;
    }

    @RequestMapping(value = "totalcoin")
    public ResultBean totalcoin(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        Integer manliCoin = manliCoinService.getManliCoin(userId);
        Map<String, Object> data = new HashMap<>();
        data.put("total", manliCoin);
        return new ResultBean(data);
    }

    @RequestMapping(value = "detail")
    public ResultBean detail(@RequestParam("page") Integer page,
                             @RequestParam("size") Integer size,
                             HttpServletRequest request
    ) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (null == page || null == size) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (page < 1) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        //java的page是从第０页开始的,所以要特殊处理下
        page = page - 1;

        Page<UserManliCoinDetailEntity> pageList = manliCoinService.getList(userId, page, size);
        if (null == pageList) {
            pageList = Page.empty();
        }
        Integer totalCoin = manliCoinService.getManliCoin(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("list", PageImp1.fromPage(pageList));
        data.put("totalCoin", totalCoin);

        return new ResultBean(data);
    }


}
