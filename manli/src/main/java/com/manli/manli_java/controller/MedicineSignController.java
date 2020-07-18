package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.MedicinePlanEntity;
import com.manli.manli_java.model_auto.MedicineSignEntity;
import com.manli.manli_java.model_impl.MedicineSignEntityImpl1;
import com.manli.manli_java.model_impl.MedicineSignEntityImpl2;
import com.manli.manli_java.model_impl.PageImp1;
import com.manli.manli_java.service.BadEffectService;
import com.manli.manli_java.service.MedicinePlanService;
import com.manli.manli_java.service.MedicineSignService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "api/medicine_sign")
public class MedicineSignController {
    @Autowired
    MedicineSignService medicineSignService;

    @Autowired
    MedicinePlanService medicinePlanService;

    @Autowired
    BadEffectService badEffectService;

    @RequestMapping(value = "history")
    public ResultBean history(@RequestParam("page") Integer page,
                              @RequestParam("size") Integer size,
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

        Page<MedicineSignEntityImpl1> pageList = medicineSignService.getHistoryList(userId, page, size);
        if (null == pageList) {
            pageList = Page.empty();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("list", PageImp1.fromPage(pageList));

        return new ResultBean(data);
    }

    @RequestMapping(value = "add")
    public ResultBean add(@RequestParam("medicinePlanId") Integer medicinePlanId,
                          @RequestParam("signDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date signDate,
                          @RequestParam("signTime") String signTime,
                          @RequestParam("eatMedicineOnTime") Short eatMedicineOnTime,
                          @RequestParam("dosage") Integer dosage,
                          @RequestParam(value = "badEffect", required = false) String badEffect,
                          @RequestParam(value = "badEffectDetail", required = false) String badEffectDetail,
                          @RequestParam(value = "hashNameArray", required = false) String hashNameArray,
                          HttpServletRequest request
    ) {
        if (null == medicinePlanId ||
                null == signDate ||
                null == signTime ||
                null == eatMedicineOnTime ||
                null == dosage
        ) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        Integer userId = (Integer) request.getAttribute("userId");

        MedicineSignEntity medicineSignEntity = medicineSignService.add(userId, medicinePlanId, signDate, signTime, eatMedicineOnTime,
                dosage, badEffect, badEffectDetail, hashNameArray);

        if (null != badEffect) {
            Integer badEffectLevel = badEffectService.getBadEffectLevelByBadEffect(badEffect);
            badEffectService.saveUserBadEffect(userId, badEffect, badEffectLevel, badEffectDetail);
        }


        Integer medicineSignId = medicineSignEntity.getId();
        Map<String, Object> data = new HashMap<>();
        data.put("medicineSignId", medicineSignId);
        return new ResultBean(data);

    }

    @RequestMapping(value = "get")
    public ResultBean get(@RequestParam("medicineSignId") Integer medicineSignId) {
        if (null == medicineSignId) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        MedicineSignEntity medicineSignEntity = medicineSignService.get(medicineSignId);
        if (null == medicineSignEntity) {
            return new ResultBean(ErrorCodeEnum.MEDICINE_SIGN_NOT_EXIST);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("medicineSign", medicineSignEntity);
        return new ResultBean(data);
    }

    @RequestMapping(value = "del")
    public ResultBean del(@RequestParam("medicineSignId") Integer medicineSignId) {
        if (null == medicineSignId) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        MedicineSignEntity medicineSignEntity = medicineSignService.get(medicineSignId);
        if (null == medicineSignEntity) {
            return new ResultBean(ErrorCodeEnum.MEDICINE_SIGN_NOT_EXIST);
        }

        medicineSignService.del(medicineSignId);
        return ResultBean.OK;
    }

    @RequestMapping(value = "update")
    public ResultBean update(@RequestParam("medicineSignId") Integer medicineSignId,
                             @RequestParam("eatMedicineOnTime") Short eatMedicineOnTime,
                             @RequestParam("dosage") Integer dosage,
                             @RequestParam(value = "badEffect", required = false) String badEffect,
                             @RequestParam(value = "badEffectDetail", required = false) String badEffectDetail,
                             @RequestParam(value = "hashNameArray", required = false) String hashNameArray,
                             HttpServletRequest request) {
        if (null == medicineSignId) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        MedicineSignEntity medicineSignEntity = medicineSignService.get(medicineSignId);
        if (null == medicineSignEntity) {
            return new ResultBean(ErrorCodeEnum.MEDICINE_SIGN_NOT_EXIST);
        }
        medicineSignService.update(medicineSignId, eatMedicineOnTime, dosage, badEffect, badEffectDetail, hashNameArray);

        return ResultBean.OK;
    }


    @RequestMapping(value = "signdaysbymonth")
    public ResultBean signdaysbymonth(@RequestParam("year") Integer year,
                                      @RequestParam("month") Integer month,
                                      HttpServletRequest request
    ) {
        if (null == year || null == month) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        List<MedicineSignEntityImpl2> list = new ArrayList<>();
        Integer userId = (Integer) request.getAttribute("userId");
        MedicinePlanEntity mainMedicinePlanEntity = medicinePlanService.getMainMedicinePlanByUserId(userId);
        if (null != mainMedicinePlanEntity) {
            list = medicineSignService.getSignDaysByMonth(userId, year, month, mainMedicinePlanEntity.getId());
            if (null == list) {
                list = new ArrayList<>();
            }
        }

        Map<String, Object> data = new HashMap<>();
        data.put("list", list);

        return new ResultBean(data);
    }


    @RequestMapping(value = "shouldalert",method = RequestMethod.POST)
    public ResultBean shouldalert(HttpServletRequest request) throws ParseException {
        Integer userId = (Integer) request.getAttribute("userId");
        List<String> notifyArr = medicinePlanService.getMainNotifyArr(userId);
        if (notifyArr.isEmpty()){
            return new ResultBean(ErrorCodeEnum.PRIMARY_MEDICINE_DATE_NOT_SET);
        }
        List<String> todaySignTimeArr = medicineSignService.getTodaySignTimeArr(userId);
        List<String> diffArr = medicineSignService.difference(notifyArr, todaySignTimeArr);
        boolean shouldalert=false;
        if (!diffArr.isEmpty()){
            List<Date> diffArr2=new ArrayList<>();
            for (String data:diffArr){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date= (Date) formatter.parse(data);
                diffArr2.add(date);
            }
            long min = Collections.min(diffArr2).getTime();
            long now = System.currentTimeMillis();
            if (now>min){
                shouldalert=true;
            }
        }
        Map<String,Object> data=new HashMap();
        data.put("shouldalert",shouldalert);
        return new ResultBean(data);
    }


    @RequestMapping(value = "historybyplan",method = RequestMethod.POST)
    public ResultBean historybyplan(@RequestParam("page")Integer page,
                                    @RequestParam("size")Integer size,
                                    @RequestParam("medicinePlanId")Integer medicinePlanId,
                                    HttpServletRequest request) {
        if (null == page || null == size) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        if (page < 1) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }
        //java的page是从第０页开始的,所以要特殊处理下
        page = page - 1;

        Integer userId = (Integer) request.getAttribute("userId");

        Page<MedicineSignEntityImpl1> pageList = medicineSignService.getHistoryList(userId, page, size);
        if (null == pageList) {
            pageList = Page.empty();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("list", PageImp1.fromPage(pageList));

        return new ResultBean(data);
    }


}
