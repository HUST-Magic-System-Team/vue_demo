package com.manli.manli_java.service;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.MemberHistorySummaryEntity;
import com.manli.manli_java.repository.MemberHistorySummaryRepository;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberHistorySummaryService {
    @Autowired
    MemberHistorySummaryRepository memberHistorySummaryRepository;
    @Autowired
    MemberService memberService;
    public ResultBean getList(Integer userId){
        if (memberService.isMember(userId)){

        }else {
            return new ResultBean(ErrorCodeEnum.NOT_MEMBER);
        }

    }
}
