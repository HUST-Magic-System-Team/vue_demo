package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_impl.TkiWithMedicineImpl1;
import com.manli.manli_java.service.TkiWithMedicineNoResultService;
import com.manli.manli_java.service.TkiWithMedicineService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "api/tki_with_medicine")
public class TkiWithMedicineController {
    @Autowired
    TkiWithMedicineService tkiWithMedicineService;

    @Autowired
    TkiWithMedicineNoResultService tkiWithMedicineNoResultService;


    @RequestMapping(value = "dropdownlist")
    public ResultBean dropdownlist(HttpServletRequest request) {
        List<TkiWithMedicineImpl1> list = tkiWithMedicineService.getDistinctTkiList();

        Map<String, Object> data = new HashMap<>();
        data.put("list", list);

        return new ResultBean(data);
    }

    @RequestMapping(value = "suggestion")
    public ResultBean suggestion(@RequestParam("tki") String tki,
                                 @RequestParam("medicine") String medicine,
                                 HttpServletRequest request
    ) {
        if (null == tki || null == medicine) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        Integer userId = (Integer) request.getAttribute("userId");
        String tkiLabel = tkiWithMedicineService.getTkiLabelByTki(tki);
        String suggestion = tkiWithMedicineService.getSuggestion(tki, medicine);


        Map<String, Object> data = new HashMap<>();
        if (suggestion == null) {
            tkiWithMedicineNoResultService.addRecord(userId, tki, tkiLabel, medicine);
            suggestion = "没有找到相关建议！\n\n我们为您整理了400余种与TKI可能存在相互作用的药物及一些常见药物，如您输入的药物没有查到，则可能提示不存在相互作用，您可以再查看相关药物说明书再次确认，并以您主管医生的意见为准。";
        }
        data.put("suggestion", suggestion);

        return new ResultBean(data);
    }
}
