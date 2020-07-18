package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.MedicineSignEntity;
import com.manli.manli_java.model_impl.MemberFollowupPlanEntityImpl1;
import com.manli.manli_java.service.MedicinePlanService;
import com.manli.manli_java.service.MedicineSignService;
import com.manli.manli_java.service.MemberFollowupPlanService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.soap.Addressing;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "api/shouldalert")
public class ShouldAlertController {
    @Addressing
    MedicineSignService medicineSignService;
    @Autowired
    MedicinePlanService medicinePlanService;
    @Autowired
    MemberFollowupPlanService memberFollowupPlanService;

    @RequestMapping(value = "shouldalert")
    public ResultBean shouldalert(HttpServletRequest request) throws ParseException {
        //===================
        //检查签到
        Integer userId = (Integer) request.getAttribute("userId");
        List<String> notifyArr = medicinePlanService.getMainNotifyArr(userId);
        if (notifyArr.isEmpty()) {
            return new ResultBean(ErrorCodeEnum.PRIMARY_MEDICINE_DATE_NOT_SET);
        }
        List<String> todaySignTimeArr = medicineSignService.getTodaySignTimeArr(userId);
        List<String> diffArr = medicineSignService.difference(notifyArr, todaySignTimeArr);
        boolean medicineSignShouldAlert = false;
        if (!diffArr.isEmpty()) {
            List<Date> diffArr2 = new ArrayList<>();
            for (String data : diffArr) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = (Date) formatter.parse(data);
                diffArr2.add(date);
            }
            long min = Collections.min(diffArr2).getTime();
            long now = System.currentTimeMillis();
            if (now > min) {
                medicineSignShouldAlert = true;
            }
        }
        //===================
        //检查用户随访计划
        List<MemberFollowupPlanEntityImpl1> list = memberFollowupPlanService.getList(userId);
        boolean memberFollowupPlanShouldAlert = false;
        for (MemberFollowupPlanEntityImpl1 impl : list) {
            long planDate = impl.getPlanDate().getTime();
            long now = new Date().getTime();
            if ((planDate >= now) && (planDate - now) <= 60 * 60 * 24 * 1000 * 3) {
                memberFollowupPlanShouldAlert = true;
                break;
            }
        }
        Map<String, Object> data = new HashMap<>();
        data.put("medicineSignShouldAlert", medicineSignShouldAlert);
        data.put("memberFollowupPlanShouldAlert", medicineSignShouldAlert);
        return new ResultBean(data);
    }
}
