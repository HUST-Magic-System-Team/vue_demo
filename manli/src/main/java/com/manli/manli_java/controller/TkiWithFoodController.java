package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_impl.TkiWithFoodImpl1;
import com.manli.manli_java.service.TkiWithFoodNoResultService;
import com.manli.manli_java.service.TkiWithFoodService;
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
@RequestMapping(value = "api/tki_with_food")
public class TkiWithFoodController {
    @Autowired
    TkiWithFoodService tkiWithFoodService;

    @Autowired
    TkiWithFoodNoResultService tkiWithFoodNoResultService;

    @RequestMapping(value = "dropdownlist")
    public ResultBean dropdownlist() {
        List<TkiWithFoodImpl1> list = tkiWithFoodService.getDistinctFoodList();

        Map<String, Object> data = new HashMap<>();
        data.put("list", list);

        return new ResultBean(data);
    }

    @RequestMapping(value = "suggestion")
    public ResultBean suggestion(@RequestParam("food") String food,
                                 HttpServletRequest request
    ) {
        if (null == food) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        Integer userId = (Integer) request.getAttribute("userId");
        String suggestion = tkiWithFoodService.getSuggestion(food);


        Map<String, Object> data = new HashMap<>();
        if (suggestion == null) {
            tkiWithFoodNoResultService.addRecord(userId, food);
            suggestion = "经查询相关医学文献，未发现食物与TKI存在相互作用。您可以再查看相关说明书再次确认，并以您主管医生的意见为准。";
        }
        data.put("suggestion", suggestion);

        return new ResultBean(data);
    }
}
