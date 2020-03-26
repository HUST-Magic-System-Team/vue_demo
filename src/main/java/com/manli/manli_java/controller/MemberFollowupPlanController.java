package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_impl.MemberFollowupPlanEntityImpl1;
import com.manli.manli_java.model_impl.UserFollowupPlanEntityImpl1;
import com.manli.manli_java.service.MemberFollowupPlanService;
import com.manli.manli_java.service.MemberService;
import com.manli.manli_java.service.UserFollowupPlanService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/member_history_summary")
public class MemberFollowupPlanController {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberFollowupPlanService memberFollowupPlanService;
    @Autowired
    UserFollowupPlanService userFollowupPlanService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResultBean list(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (!memberService.isMember(userId)) {
            return new ResultBean(ErrorCodeEnum.NOT_MEMBER);
        }
        List<MemberFollowupPlanEntityImpl1> list = memberFollowupPlanService.getList(userId);
        List<UserFollowupPlanEntityImpl1> commonList = userFollowupPlanService.getList();
        Map<String, Object> data = new HashMap<>();
        data.put("commonList", commonList);
        data.put("list", list);
        return new ResultBean(data);

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResultBean add(@RequestParam("content") String content,
                          @RequestParam("planDate") String planDate,
                          HttpServletRequest request) throws ParseException {
        Integer userId = (Integer) request.getAttribute("userId");
        if (content.isEmpty() || planDate.isEmpty()) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (!memberService.isMember(userId)) {
            return new ResultBean(ErrorCodeEnum.NOT_MEMBER);
        }
        Integer result = memberFollowupPlanService.add(userId, content, planDate);
        Map<String, Object> data = new HashMap<>();
        data.put("planId", result);
        return new ResultBean(data);
    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    public ResultBean del(@RequestParam("planId") Integer planId,
                          HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (planId == null) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (!memberService.isMember(userId)) {
            return new ResultBean(ErrorCodeEnum.NOT_MEMBER);
        }
        if (!memberFollowupPlanService.isForUser(userId, planId)) {
            return new ResultBean(ErrorCodeEnum.MEMEBER_FOLLOWUP_PLAN_ID_ERROR);
        }
        memberFollowupPlanService.delete(planId);
        return new ResultBean(ErrorCodeEnum.OK);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResultBean update(@RequestParam("planId") Integer planId,
                             @RequestParam("content") String content,
                             @RequestParam("plantDate") String plantDate,
                             HttpServletRequest request) throws ParseException {
        Integer userId = (Integer) request.getAttribute("userId");
        if (planId == null || content.isEmpty() || plantDate.isEmpty()) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (!memberService.isMember(userId)) {
            return new ResultBean(ErrorCodeEnum.NOT_MEMBER);
        }
        if (!memberFollowupPlanService.isForUser(userId, planId)) {
            return new ResultBean(ErrorCodeEnum.MEMEBER_FOLLOWUP_PLAN_ID_ERROR);
        }
        memberFollowupPlanService.update(planId, content, plantDate);
        return new ResultBean(ErrorCodeEnum.OK);
    }

    @RequestMapping(value = "read", method = RequestMethod.POST)
    public ResultBean read(@RequestParam("planId") Integer planId,
                           HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (planId == null) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (!memberService.isMember(userId)) {
            return new ResultBean(ErrorCodeEnum.NOT_MEMBER);
        }
        if (!memberFollowupPlanService.isForUser(userId, planId)) {
            return new ResultBean(ErrorCodeEnum.MEMEBER_FOLLOWUP_PLAN_ID_ERROR);
        }
        memberFollowupPlanService.read(planId);
        return new ResultBean(ErrorCodeEnum.OK);
    }

    @RequestMapping(value = "isread")
    public ResultBean isread(@RequestParam("planId") Integer planId) {
        if (planId == null) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        Boolean isread = memberFollowupPlanService.isread(planId);
        Map<String, Object> data = new HashMap<>();
        data.put("isRead", isread);
        return new ResultBean(data);
    }


    @RequestMapping(value = "shouldalert")
    public ResultBean shouldalert(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        List<MemberFollowupPlanEntityImpl1> list = memberFollowupPlanService.getList(userId);
        boolean shouldAlert = false;
        for (MemberFollowupPlanEntityImpl1 impl : list) {
            long planDate = impl.getPlanDate().getTime();
            long now = new Date().getTime();
            if ((planDate >= now) && (planDate - now) <= 60 * 60 * 24 * 1000 * 3) {
                shouldAlert = true;
                break;
            }
        }
        Map<String,Object> data=new HashMap<>();
        data.put("shouldAlert",shouldAlert);
        return new ResultBean(data);
    }


}
