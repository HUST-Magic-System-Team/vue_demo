package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.MemberHistorySummaryEntity;
import com.manli.manli_java.service.MemberHistorySummaryService;
import com.manli.manli_java.service.MemberService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.IContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/member_followup_plan")
public class MemberHistorySummaryController {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberHistorySummaryService memberHistorySummaryService;

    @RequestMapping(value = "list")
    public ResultBean list(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (memberService.isMember(userId)) {
            List<MemberHistorySummaryEntity> list = memberHistorySummaryService.getList(userId);
            Map<String, Object> data = new HashMap<>();
            data.put("list", list);
            return new ResultBean(data);
        } else {
            return new ResultBean(ErrorCodeEnum.NOT_MEMBER);
        }
    }

    @RequestMapping(value = "add")
    public ResultBean add(HttpServletRequest request,
                          @RequestParam("content") String content) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (content.isEmpty()) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (!memberService.isMember(userId)) {
            return new ResultBean(ErrorCodeEnum.NOT_MEMBER);
        }
        Integer summaryId = memberHistorySummaryService.add(userId, content);
        Map<String, Object> data = new HashMap<>();
        data.put("summaryId", summaryId);
        return new ResultBean(data);


    }

    @RequestMapping(value = "del")
    public ResultBean del(@RequestParam("summaryId") Integer summaryId,
                          HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (summaryId == null) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (!memberService.isMember(userId)) {
            return new ResultBean(ErrorCodeEnum.NOT_MEMBER);
        }
        boolean forUser = memberHistorySummaryService.isForUser(userId, summaryId);
        if (!forUser){
            return new ResultBean(ErrorCodeEnum.MEMEBER_HISTORY_SUMMARY_ID_ERROR);
        }
        memberHistorySummaryService.delete(summaryId);
        return  new ResultBean(ErrorCodeEnum.OK);


    }

    @RequestMapping(value = "update")
    public ResultBean update(@RequestParam("summaryId")Integer summaryId,
                         @RequestParam("content")String content,
                         HttpServletRequest request) {
        Integer userId =(Integer) request.getAttribute("userId");
        if (summaryId==null||content.isEmpty()){
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (!memberService.isMember(userId)) {
            return new ResultBean(ErrorCodeEnum.NOT_MEMBER);
        }
        boolean forUser = memberHistorySummaryService.isForUser(userId, summaryId);
        if (!forUser){
            return new ResultBean(ErrorCodeEnum.MEMEBER_HISTORY_SUMMARY_ID_ERROR);
        }
        memberHistorySummaryService.update(summaryId,content);
        return new ResultBean(ErrorCodeEnum.OK);


    }

    @RequestMapping(value = "read")
    public ResultBean read(@RequestParam("summaryId")Integer summaryId,
                       HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (summaryId==null){
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (!memberService.isMember(userId)) {
            return new ResultBean(ErrorCodeEnum.NOT_MEMBER);
        }
        boolean forUser = memberHistorySummaryService.isForUser(userId, summaryId);
        if (!forUser){
            return new ResultBean(ErrorCodeEnum.MEMEBER_HISTORY_SUMMARY_ID_ERROR);
        }
        memberHistorySummaryService.read(summaryId);
       return  new ResultBean(ErrorCodeEnum.OK);
    }

    @RequestMapping(value = "isread")
    public String isread(@RequestParam("summaryId")Integer summaryId) {
        if (summaryId==null){

        }
        return "json isread";
    }


}
