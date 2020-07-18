package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.MedicineEntity;
import com.manli.manli_java.service.MedicineDosageService;
import com.manli.manli_java.service.MedicineService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/medicine")
public class MedicineController {
    @Autowired
    MedicineService medicineService;

    @Autowired
    MedicineDosageService medicineDosageService;

    @RequestMapping(value = "list")
    public ResultBean list(HttpServletRequest request) {
        List<MedicineEntity> list = medicineService.getList();
        Map<String, Object> data = new HashMap<>();
        if (null != list && list.size() > 0) {
            data.put("data", list);
        } else {
            data.put("list", new ArrayList<>());
        }
        return new ResultBean(data);
    }


    @RequestMapping(value = "dosagelist")
    public ResultBean dosagelist(HttpServletRequest request) {
        List<String> list = medicineDosageService.getDosageList();
        Map<String, Object> data = new HashMap<>();
        if (null != list && list.size() > 0) {
            data.put("list", list);
        } else {
            data.put("list", new ArrayList<>());
        }
        return new ResultBean(data);
    }

}
