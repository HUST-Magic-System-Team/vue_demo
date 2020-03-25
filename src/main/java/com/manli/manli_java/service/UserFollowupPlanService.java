package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.UserFollowupPlanEntity;
import com.manli.manli_java.model_impl.UserFollowupPlanEntityImpl1;
import com.manli.manli_java.repository.UserFollowupPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFollowupPlanService {
    @Autowired
    UserFollowupPlanRepository userFollowupPlanRepository;
    public List<UserFollowupPlanEntityImpl1> getList(){
        List<UserFollowupPlanEntity> list = userFollowupPlanRepository.findAllByStatus(Byte.valueOf("0"));
        List<UserFollowupPlanEntityImpl1> list1=new ArrayList<>();
        UserFollowupPlanEntityImpl1 entityImpl1=new UserFollowupPlanEntityImpl1();
        int index=1;
        for(UserFollowupPlanEntity entity:list){
            entityImpl1.setPlanId(entity.getId());
            entityImpl1.setContent(entity.getContent());
            entityImpl1.setCreatedAt(entity.getCreatedAt());
            String contentUrl="http://sh5.cml922.com/user/followup-plan?planId="+entity.getId()+"&readonlyToken="+entity.toString().substring(0,7);
            entityImpl1.setContentUrl(contentUrl);
            entityImpl1.setTitle("通用随访计划"+index);
            list1.add(entityImpl1);
            index++;
        }
        return list1;
    }
}
