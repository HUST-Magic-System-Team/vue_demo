package com.manli.manli_java.controller;

import com.alibaba.fastjson.JSON;
import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.*;
import com.manli.manli_java.model_impl.*;
import com.manli.manli_java.service.*;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/userinfo")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserRecordInfoService userRecordInfoService;

    @Autowired
    UserTkiRecordInfoService userTkiRecordInfoService;

    @Autowired
    InterestTagService interestTagService;

    @Autowired
    TaskService taskService;

    @Autowired
    CurativeEffectService curativeEffectService;

    @Autowired
    MedicinePlanService medicinePlanService;

    @Autowired
    SelfManageService selfManageService;


    @RequestMapping(value = "getusertag")
    public ResultBean getUserTag(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute("userId");

        List<InterestTagEntity> interestTag = interestTagService.getUserInterestTags(userId);
        Map<String, Object> takingMedicineTimeMap = userInfoService.getUserTakingMedicineTime(userId);
        List<InterestTagEntity> availableInterestTag = interestTagService.getAvailableInterestTags();

        Map<String, Object> data = new HashMap<>();
        if (null != takingMedicineTimeMap) {
            data.putAll(takingMedicineTimeMap);
        } else {
            data.put("takingMedicineTimeType", null);
            data.put("takingMedicineTime", null);
        }

        data.put("interestTag", interestTag);
        data.put("availableInterestTag", availableInterestTag);

        return new ResultBean(data);
    }

    @RequestMapping(value = "/setusertag")
    public ResultBean setUserTag(@RequestParam("takingMedicineTimeType") Integer takingMedicineTimeType,
                                 @RequestParam("interestTagIds") String interestTagIds,
                                 HttpServletRequest request) {
        if (null == takingMedicineTimeType) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        if (null == interestTagIds) {
            interestTagIds = "";
        }

        Integer userId = (Integer) request.getAttribute("userId");
        userInfoService.setUserTakingMedicineTime(userId, takingMedicineTimeType);
        interestTagService.setUserInterestTags(userId, interestTagIds);

        return new ResultBean(ErrorCodeEnum.OK);
    }

    @RequestMapping(value = "/getmedals")
    public ResultBean getMedals(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        List<UserMedalEntity> list = userInfoService.getUserMedals(userId);
        Map<String, Object> data = new HashMap<>();
        if (list != null) {
            data.put("list", list);
        } else {
            data.put("list", new ArrayList<>());
        }

        return new ResultBean(data);
    }

    @RequestMapping(value = "/index")
    public ResultBean index(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");

        UserInfoEntity base = userInfoService.getUserBaseInfo(userId);
        UserRecordInfoEntity record = userRecordInfoService.getUserRecordInfoAsync(userId);
        List<UserTkiRecordInfoEntity> list = userTkiRecordInfoService.getUserTkiRecordInfoAsync(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("base", base);
        data.put("record", record);
        data.put("list", list);

        return new ResultBean(data);
    }


    @RequestMapping(value = "/update")
    public ResultBean update(@RequestParam("base") String base,
                             @RequestParam("record") String record,
                             @RequestParam("tki") String tki,
                             HttpServletRequest request
    ) {

        Integer userId = (Integer) request.getAttribute("userId");
        if (null == base) {
            base = "{}";
        }
        if (null == record) {
            record = "{}";
        }
        if (null == tki) {
            tki = "[]";
        }

        UserInfoImpl1 userInfoImpl1 = JSON.parseObject(base, UserInfoImpl1.class);
        UserRecordInfoImpl1 userRecordInfoImpl1 = JSON.parseObject(record, UserRecordInfoImpl1.class);
        List<UserTkiRecordInfoImpl1> tkiList = JSON.parseArray(tki, UserTkiRecordInfoImpl1.class);

        if (null == userInfoImpl1 || userInfoImpl1.getSex() == null || userInfoImpl1.getBirthDay() == null
                || userInfoImpl1.getPhone() == null || userInfoImpl1.getName() == null) {
            return new ResultBean(ErrorCodeEnum.MISS_SEX_BIRTHDAY_TEL_NAME);
        }

        if (null == userRecordInfoImpl1
                || userRecordInfoImpl1.getConfirmDate() == null
                || userRecordInfoImpl1.getCml() == null
                || userRecordInfoImpl1.getFirstMedicineDate() == null
                || userRecordInfoImpl1.getCombinedChemotherapy() == null
        ) {
            return new ResultBean(ErrorCodeEnum.MISS_CONFIRM_DATE_CML_FIRST_DATE);
        }

        if (null == tkiList
                || tkiList.size() == 0
                || tkiList.get(0).getTkiName() == null
                || tkiList.get(0).getTkiStartDate() == null
                || tkiList.get(0).getTkiDosage() == null
                || tkiList.get(0).getTkiFrequency() == null
        ) {
            return new ResultBean(ErrorCodeEnum.MISS_TKI_INFO);
        }

        userInfoService.updateUserBaseInfo(userId, userInfoImpl1);
        userRecordInfoService.updateUserRecordInfo(userId, userRecordInfoImpl1);
        userTkiRecordInfoService.updateUserTkiRecordInfo(userId, tkiList);

        return ResultBean.OK;
    }


    @RequestMapping(value = "/indextoppart")
    public ResultBean indextoppart(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute("userId");
        List<UserTaskEntity> task = taskService.getList(userId);
        List<UserTaskImpl1> task2 = new ArrayList<>();

        if (null != task) {
            String taskTemplate = "记得__TASK_DATE_TIME__去医院复查哦。当病人结束医院内诊治疗后，为了证实诊断和观察疗效，需要对出院病人进一步了解。";

            task2 = task.stream().map((UserTaskEntity userTaskEntity) -> {
                UserTaskImpl1 userTaskImpl1 = new UserTaskImpl1(userTaskEntity);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年M月d日");
                String dateString = simpleDateFormat.format(userTaskImpl1.getTaskTime());
                userTaskImpl1.setDateString(dateString);
                userTaskImpl1.setContent(taskTemplate.replace("__TASK_DATE_TIME__", dateString));

                return userTaskImpl1;
            }).collect(Collectors.toList());
        }

        UserCurativeEffectEntity latestCurativeEffect = curativeEffectService.getLatestUserCurativeEffect(userId);
        SelfManageEvaluateImpl selfManageEvaluateImpl = selfManageService.evaluate(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("task", task2);
        data.put("latestCurativeEffect", latestCurativeEffect);
        data.put("latestSelfManageEvaluate", selfManageEvaluateImpl);

        return new ResultBean(data);
    }


    @RequestMapping(value = "/usertoppart")
    public ResultBean usertoppart(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute("userId");

        String name = "";
        Integer totalCoin = 0;
        UserInfoEntity userInfoEntity = userInfoService.getUserBaseInfo(userId);
        if (null != userInfoEntity) {
            name = userInfoEntity.getName();
            totalCoin = userInfoEntity.getManliCoin();
        }

        List<UserMedalEntity> medals = userInfoService.getUserMedals(userId);
        if (medals == null) {
            medals = new ArrayList<>();
        }

        Integer[] rankArr = new Integer[]{399, 1099};
        String rank = "初级会员";
        Integer gap = 0;
        if (totalCoin < rankArr[0]) {
            rank = "初级会员";
            gap = rankArr[0] - totalCoin;
        } else if (totalCoin >= rankArr[0] && totalCoin < rankArr[1]) {
            rank = "中级会员";
            gap = rankArr[1] - totalCoin;
        } else {
            rank = "高级会员";
            gap = -1;
        }

        Integer filledFieldCount = userInfoService.filledFieldCount(userId);
        Integer totalFieldCount = userInfoService.totalFieldCount();

        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("medals", medals);
        data.put("rank", rank);
        data.put("totalCoin", totalCoin);
        data.put("gap", gap);
        data.put("filledFieldCount", filledFieldCount);
        data.put("totalFieldCount", totalFieldCount);

        return new ResultBean(data);
    }


}
