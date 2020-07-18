package com.manli.manli_java.controller;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.TkiInstructionEntity;
import com.manli.manli_java.model_impl.PageImp1;
import com.manli.manli_java.service.TkiInstructionService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/tki_instruction")
public class TkiInstructionController {
    @Autowired
    TkiInstructionService tkiInstructionService;

    @RequestMapping(value = "list")
    public ResultBean list(@RequestParam("page") Integer page,
                           @RequestParam("size") Integer size) {
        if (null == page || null == size) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        if (page < 1) {
            return new ResultBean(ErrorCodeEnum.MISS_PARAMETER);
        }

        //java的page是从第０页开始的,所以要特殊处理下
        page = page - 1;


        Page<TkiInstructionEntity> pageList = tkiInstructionService.getList(page, size);
        if (null == pageList) {
            pageList = Page.empty();
        }

        Map<String, Object> data = new HashMap<>();
        data.put("list", PageImp1.fromPage(pageList));

        return new ResultBean(data);
    }


}
