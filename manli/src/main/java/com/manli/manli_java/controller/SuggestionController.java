package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.service.SuggestionService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/suggestion")
public class SuggestionController {
    @Autowired
    SuggestionService suggestionService;
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public ResultBean add(@RequestParam("content")String content,
                          HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (content.isEmpty()){
            return new ResultBean(ErrorCodeEnum.MISS_SUGGESTION_CONTENT);
        }
        Integer suggestionId = suggestionService.add(userId, content);
        Map<String,Object> data=new HashMap<>();
        data.put("suggestionId",suggestionId);
        return new ResultBean(data);


    }

    @RequestMapping(value = "del",method = RequestMethod.POST)
    public ResultBean del(@RequestParam("suggestionId")Integer suggestionId,
                      HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (suggestionId==null){
            return new ResultBean(ErrorCodeEnum.SUGGESTION_NOT_EXIST);
        }
        boolean isSubmitter = suggestionService.isSubmitter(userId, suggestionId);
        if (!isSubmitter){
            return new ResultBean(ErrorCodeEnum.CANNOT_DEL_SUGGESTION_NOT_FOR_YOU);
        }
        suggestionService.delete(suggestionId);
        return  new ResultBean(ErrorCodeEnum.OK);
    }

}
