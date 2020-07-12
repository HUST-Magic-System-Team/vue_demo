package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.SuggestionEntity;
import com.manli.manli_java.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestionService {
    @Autowired
    SuggestionRepository suggestionRepository;
    public Integer add(Integer userId,String content){
        SuggestionEntity suggestionEntity = new SuggestionEntity();
        suggestionEntity.setUserId(userId);
        suggestionEntity.setContent(content);
        suggestionEntity.setStatus(Byte.valueOf("0"));
        SuggestionEntity result = suggestionRepository.save(suggestionEntity);
        return result.getId();
    }
    public  void  delete(Integer suggestionId){
        suggestionRepository.deleteById(suggestionId);
    }
    public boolean isSubmitter(Integer userId,Integer suggestionId){
        SuggestionEntity result = suggestionRepository.findOneByIdAndUserId(suggestionId, userId);
        if (result!=null){
            return true;
        }
        return false;
    }
}
