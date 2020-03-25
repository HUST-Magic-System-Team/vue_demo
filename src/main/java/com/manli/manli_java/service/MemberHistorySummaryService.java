package com.manli.manli_java.service;

import com.manli.manli_java.eenum.ErrorCodeEnum;
import com.manli.manli_java.model_auto.MemberHistorySummaryEntity;
import com.manli.manli_java.repository.MemberHistorySummaryRepository;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MemberHistorySummaryService {
    @Autowired
    MemberHistorySummaryRepository memberHistorySummaryRepository;
    public List<MemberHistorySummaryEntity> getList(Integer userId){
        List<MemberHistorySummaryEntity> list= memberHistorySummaryRepository.findAllByUserIdAndStatus(userId, Byte.valueOf("0"));
        return list;
    }
    public MemberHistorySummaryEntity get(Integer summaryId){
        MemberHistorySummaryEntity result = memberHistorySummaryRepository.findOneById(summaryId);
        return result;
    }
    public Integer add(Integer userId,String content){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(date);
        Timestamp createdAt =Timestamp.valueOf(format);
        MemberHistorySummaryEntity memberHistorySummaryEntity=new MemberHistorySummaryEntity();
        memberHistorySummaryEntity.setUserId(userId);
        memberHistorySummaryEntity.setContent(content);
        memberHistorySummaryEntity.setCreatedAt(createdAt);
        memberHistorySummaryEntity.setIsread((byte) 0);
        MemberHistorySummaryEntity save = memberHistorySummaryRepository.save(memberHistorySummaryEntity);
        return save.getId();
    }
    public boolean isForUser (Integer userId,Integer summaryId){
        MemberHistorySummaryEntity result = memberHistorySummaryRepository.findByUserIdAndId(userId, summaryId);
        if (result==null){
            return false;
        }else {
            return true;
        }

    }
    public void delete(Integer summaryId){
        memberHistorySummaryRepository.deleteById(summaryId);
    }
    public void update(Integer summaryId,String content){
        MemberHistorySummaryEntity result=get(summaryId);
        Byte isread=result.getIsread();
        if (isread==1&&result.getContent()!=content){
            isread=0;
        }
        result.setIsread(isread);
        result.setContent(content);
        memberHistorySummaryRepository.save(result);
    }
    public void read(Integer summaryId){
        MemberHistorySummaryEntity result = get(summaryId);
        result.setIsread((byte) 0);
        memberHistorySummaryRepository.save(result);
    }
    public Boolean isread(Integer summaryId){
        MemberHistorySummaryEntity result = memberHistorySummaryRepository.findOneById(summaryId);
        if (result.getIsread()==0){
            return false;
        }else {
            return true;
        }
    }


}
