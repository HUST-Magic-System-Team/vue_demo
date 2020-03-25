package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.MemberFollowupPlanEntity;
import com.manli.manli_java.model_impl.MemberFollowupPlanEntityImpl1;
import com.manli.manli_java.repository.MemberFollowupPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemberFollowupPlanService {
    @Autowired
    MemberFollowupPlanRepository memberFollowupPlanRepository;
   public List<MemberFollowupPlanEntityImpl1> getList(Integer userId){
       List<MemberFollowupPlanEntity> list = memberFollowupPlanRepository.findAllByUserIdAndStatus(userId, Byte.valueOf("0"));
       List<MemberFollowupPlanEntityImpl1> list1=new ArrayList<>();
       MemberFollowupPlanEntityImpl1 entityImpl1=new MemberFollowupPlanEntityImpl1();
       int index=1;
       for (MemberFollowupPlanEntity entity:list){
           entityImpl1.setTitle("随访计划"+index);
           entityImpl1.setPlanId(entity.getId());
           entityImpl1.setUserId(entity.getUserId());
           entityImpl1.setContent(entity.getContent());
           entityImpl1.setPlanDate(entity.getPlanDate());
           entityImpl1.setIsread(entity.getIsread());
           entityImpl1.setCreatedAt(entity.getCreatedAt());
           String contentUrl="http://sh5.cml922.com/user/followup-plan?planId="+entity.getId()+"&readonlyToken="+entity.toString().substring(0,7);
           entityImpl1.setContentUrl(contentUrl);
           list1.add(entityImpl1);

       }
       return list1;
   }
   public Integer add(Integer userId,String content,String planDate) throws ParseException {
       MemberFollowupPlanEntity entity=new MemberFollowupPlanEntity();
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       Date date= (Date) formatter.parse(planDate);
       java.sql.Date date1=new java.sql.Date(date.getTime());
       entity.setUserId(userId);
       entity.setContent(content);
       entity.setPlanDate(date1);
       entity.setIsread(Byte.valueOf("0"));
       MemberFollowupPlanEntity result = memberFollowupPlanRepository.save(entity);
       return result.getId();
   }
   public boolean isForUser(Integer userId,Integer planId){
       MemberFollowupPlanEntity result = memberFollowupPlanRepository.findOneByUserIdAndPlanAndId(userId, planId);
       if (result==null){
           return false;
       }
       return true;
   }
   public void delete(Integer planId){
       memberFollowupPlanRepository.deleteById(planId);
   }
   public void update(Integer planId,String content,String planDate) throws ParseException {
       MemberFollowupPlanEntity entity = memberFollowupPlanRepository.findOneById(planId);
       Byte isread = entity.getIsread();
       if (isread==1&&entity.getContent()!=content){
           isread=0;
       }
       entity.setIsread(isread);
       entity.setContent(content);
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       Date date= (Date) formatter.parse(planDate);
       java.sql.Date date1=new java.sql.Date(date.getTime());
       entity.setPlanDate(date1);
       memberFollowupPlanRepository.save(entity);
   }
   public  void read(Integer planId){
       MemberFollowupPlanEntity entity = memberFollowupPlanRepository.findOneById(planId);
       entity.setIsread(Byte.valueOf("0"));
       memberFollowupPlanRepository.save(entity);

   }

}
