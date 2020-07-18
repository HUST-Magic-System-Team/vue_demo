package com.manli.manli_java.controller;

import com.alibaba.fastjson.JSONObject;
import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.NewsEntity;
import com.manli.manli_java.model_impl.PageImp1;
import com.manli.manli_java.service.LoginService;
import com.manli.manli_java.service.NewsService;
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
@RequestMapping(value = "api/news")
public class NewsController {
    @Autowired
    NewsService  newsService;
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResultBean list(@RequestParam("page") Integer page,
                           @RequestParam("size") Integer size) {
        if (null == page || null == size) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        if (page < 1) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        //java的page是从第０页开始的,所以要特殊处理下
        page = page - 1;


        Page<NewsEntity> pageList = newsService.getList(page, size);
        if (null == pageList) {
            pageList = Page.empty();
        }

        Map<String, Object> data = new HashMap<>();
        data.put("list", PageImp1.fromPage(pageList));
        return new ResultBean(data);
    }

    @RequestMapping(value = "list2", method = RequestMethod.POST)
    public ResultBean list2(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam(value = "groupId", required = false) Integer groupId,
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
        JSONObject list = newsService.getList2(page, size, userId, groupId);
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        return new ResultBean(data);
    }

    @RequestMapping(value = "read", method = RequestMethod.POST)
    public ResultBean read(@RequestParam("newsId") Integer newsId) {
        if (null == newsId) {
            return new ResultBean(ErrorCodeEnum.INVALID_NEWS_ID);
        }

        boolean isValid = newsService.isValid(newsId);
        if (isValid) {
            newsService.read(newsId);
        }
        return new ResultBean(ErrorCodeEnum.OK);
    }

}
