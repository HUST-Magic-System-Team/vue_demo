package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.MedicinePlanEntity;
import com.manli.manli_java.model_auto.MedicineSignEntity;
import com.manli.manli_java.model_impl.MedicinePlanEntityImpl1;
import com.manli.manli_java.model_impl.PageImp1;
import com.manli.manli_java.service.MedicinePlanService;
import com.manli.manli_java.service.MedicineSignService;
import com.manli.manli_java.service.TaskService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping(value = "api/medicine_plan")
public class MedicinePlanController {
    @Autowired
    MedicinePlanService medicinePlanService;

    @Autowired
    MedicineSignService medicineSignService;

    @Autowired
    TaskService taskService;


    @RequestMapping(value = "list")
    public ResultBean list(@RequestParam("page") Integer page,
                           @RequestParam("size") Integer size,
                           @RequestParam(value = "signDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date signDate,
                           HttpServletRequest request
    ) {
        if (null == signDate) {
            signDate = new Date();
        }
        if (null == page || null == size) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (page < 1) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        //java的page是从第０页开始的,所以要特殊处理下
        page = page - 1;

        Integer userId = (Integer) request.getAttribute("userId");
        //==============================================
        //删除过期的，重新选主服药任务,重新生成用户随访提醒任务
        //==============================================
        medicinePlanService.delOutdated(userId);
        if (!medicinePlanService.hasMainMedicinePlan(userId)) {
            Integer otherMedicinePlanId = medicinePlanService.randomSetMainMedicinePlan(userId);
            if (null != otherMedicinePlanId) {
                MedicinePlanEntity medicinePlanEntity = medicinePlanService.get(otherMedicinePlanId);
                if (medicinePlanEntity != null) {
                    taskService.regenUserTask(userId, medicinePlanEntity.getStartTime());
                }
            }
        }
        //==============================================
        //读取List
        //==============================================

        Page<MedicinePlanEntityImpl1> pageList = medicinePlanService.getList(userId, page, size, signDate);
        if (null == pageList) {
            pageList = Page.empty();
        }

        Map<String, Object> data = new HashMap<>();
        data.put("list", PageImp1.fromPage(pageList));
        return new ResultBean(data);
    }


    @RequestMapping(value = "add")
    public ResultBean add(@RequestParam("medicine") String medicine,
                          @RequestParam("dosage") Integer dosage,
                          @RequestParam("frequency") Integer frequency,
                          @RequestParam("notify") String notify,
                          @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
                          @RequestParam("duration") Integer duration,
                          @RequestParam("isMain") Short isMain,
                          HttpServletRequest request
    ) {
        if (null == medicine ||
                null == dosage ||
                null == frequency ||
                null == startTime ||
                null == duration ||
                null == notify
        ) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        Integer userId = (Integer) request.getAttribute("userId");
        String regex = "(^,*)|(,*$)";
        notify = notify.replaceAll(regex, "");
        if (null == notify) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        if (notify.split(",").length != frequency) {
            return new ResultBean(ErrorCodeEnum.MEDICINE_PLAN_DATE_ERROR);
        }

        boolean hasSameMedicinePlan = medicinePlanService.hasSameMedicinePlan(userId, medicine);
        if (hasSameMedicinePlan) {
            return new ResultBean(ErrorCodeEnum.ONLY_ONE_MEDICINE_PLANT_PER_MEDICINE);
        }

        if (isMain == 0) {
            boolean hasMainMedicinePlan = medicinePlanService.hasMainMedicinePlan(userId);
            if (hasMainMedicinePlan) {
                return new ResultBean(ErrorCodeEnum.REQUIRE_MAIN_MEDINCE_PLAN);
            }
        } else if (isMain == 1) {
            medicinePlanService.clearAllMainMedicinePlan(userId);
            taskService.regenUserTask(userId, startTime);
        }

        MedicinePlanEntity medicinePlanEntity = medicinePlanService.add(userId, medicine, dosage, frequency, notify, startTime, duration, isMain);

        Map<String, Object> data = new HashMap<>();
        data.put("medicinePlanId", medicinePlanEntity.getId());
        return new ResultBean(data);
    }

    @RequestMapping(value = "get")
    public ResultBean get(@RequestParam("medicinePlanId") Integer medicinePlanId,
                          HttpServletRequest request
    ) {
        if (null == medicinePlanId) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        MedicinePlanEntity medicinePlanEntity = medicinePlanService.get(medicinePlanId);
        if (null == medicinePlanEntity) {
            return new ResultBean(ErrorCodeEnum.MEDICINDE_PLANT_NOT_EXIST);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("medicinePlan", medicinePlanEntity);

        return new ResultBean(data);
    }

    @RequestMapping(value = "del")
    public ResultBean del(@RequestParam("medicinePlanId") Integer medicinePlanId,
                          HttpServletRequest request) {
        if (null == medicinePlanId) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        Integer userId = (Integer) request.getAttribute("userId");

        MedicinePlanEntity medicinePlanEntity = medicinePlanService.get(medicinePlanId);
        if (null == medicinePlanEntity) {
            return new ResultBean(ErrorCodeEnum.MEDICINDE_PLANT_NOT_EXIST);
        }

        Short isMain = medicinePlanEntity.getIsMain();

        medicinePlanService.del(medicinePlanId);
        if (isMain == 1) {
            Integer otherMedicinePlanId = medicinePlanService.randomSetMainMedicinePlan(userId);
            if (null != otherMedicinePlanId) {
                MedicinePlanEntity otherMedicinePlanEntity = medicinePlanService.get(otherMedicinePlanId);
                if (otherMedicinePlanEntity != null) {
                    //重新产生“随访提醒”任务
                    taskService.regenUserTask(userId, otherMedicinePlanEntity.getStartTime());
                }
            }
        }

        return new ResultBean(ErrorCodeEnum.OK);
    }

    @RequestMapping(value = "update")
    public ResultBean update(@RequestParam("medicinePlanId") Integer medicinePlanId,
                             @RequestParam("medicine") String medicine,
                             @RequestParam("dosage") Integer dosage,
                             @RequestParam("frequency") Integer frequency,
                             @RequestParam("notify") String notify,
                             @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
                             @RequestParam("duration") Integer duration,
                             @RequestParam("isMain") Short isMain,
                             HttpServletRequest request
    ) {
        if (null == medicinePlanId ||
                null == medicine ||
                null == dosage ||
                null == frequency ||
                null == startTime ||
                null == duration ||
                null == notify
        ) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }


        Integer userId = (Integer) request.getAttribute("userId");
        String regex = "(^,*)|(,*$)";
        notify = notify.replaceAll(regex, "");
        if (null == notify) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        if (notify.split(",").length != frequency) {
            return new ResultBean(ErrorCodeEnum.MEDICINE_PLAN_DATE_ERROR);
        }
        MedicinePlanEntity medicinePlanEntity = medicinePlanService.get(medicinePlanId);
        if (null == medicinePlanEntity) {
            return new ResultBean(ErrorCodeEnum.MEDICINDE_PLANT_NOT_EXIST);
        }
        if (isMain == 1) {
            medicinePlanService.clearAllMainMedicinePlan(userId);
            //重新产生“随访提醒”任务
            taskService.regenUserTask(userId, startTime);
        }

        medicinePlanEntity = medicinePlanService.update(medicinePlanEntity, userId, medicine, dosage, frequency, notify, startTime, duration, isMain);

        return new ResultBean(ErrorCodeEnum.OK);
    }

    @RequestMapping(value = "deloutdated")
    public ResultBean deloutdated(HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute("userId");
        //==============================================
        //删除过期的，重新选主服药任务,重新生成用户随访提醒任务
        //==============================================
        medicinePlanService.delOutdated(userId);

        return new ResultBean(ErrorCodeEnum.OK);
    }


}
