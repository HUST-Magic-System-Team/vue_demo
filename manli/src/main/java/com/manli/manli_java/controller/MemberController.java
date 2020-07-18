package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_impl.MemberImpl1;
import com.manli.manli_java.service.MemberService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @RequestMapping(value = "get",method = RequestMethod.POST)
    public ResultBean get(HttpServletRequest request) {
        Integer userId=(Integer) request.getAttribute("userId");
        MemberImpl1 memberInfo = memberService.getMemberInfo(userId);
        Map<String, Object> data = new HashMap<>();
        data.put("info",memberInfo);
        return  new ResultBean(data);
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ResultBean save(@RequestParam("name")String name,
                       @RequestParam("phone")String phone,
                       @RequestParam(value = "email",required = false)String email,
                       @RequestParam(value = "location",required = false)String location,
                       @RequestParam(value = "qq",required = false)String qq,
                       @RequestParam(value = "address",required = false)String address) {
        if(name==null||phone==null){
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }else {
            memberService.add(name,phone,email,location,qq,address);
            return new ResultBean(ErrorCodeEnum.OK);
        }


    }

    @RequestMapping(value = "ismember",method = RequestMethod.POST)
    public ResultBean ismember(HttpServletRequest request) {
        Integer userId=(Integer) request.getAttribute("userId");
        Map<String, Object> data = new HashMap<>();
        boolean b = memberService.isMember(userId);
        data.put("isMember",b);
        return new ResultBean(data);
    }
}
