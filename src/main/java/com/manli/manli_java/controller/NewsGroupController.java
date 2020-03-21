package com.manli.manli_java.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_impl.NewsGroupImpl1;
import com.manli.manli_java.repository.NewsGroupRepository;
import com.manli.manli_java.service.LoginService;
import com.manli.manli_java.service.NewsGroupService;
import com.manli.manli_java.service.NewsReadService;
import com.manli.manli_java.service.NewsService;
import com.manli.manli_java.util.ResultBean;
import com.manli.manli_java.util.TupleThree;
import io.netty.util.concurrent.Promise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/news_group")
public class NewsGroupController {
    @Autowired
    NewsGroupService newsGroupService;

    @Autowired
    NewsReadService newsReadService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResultBean list(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        Map<String, Object> data = new HashMap<>();
        List<NewsGroupImpl1> list = newsGroupService.getList(userId);
        data.put("list", list);

        return new ResultBean(data);
    }

}
