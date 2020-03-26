package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.SuggestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface SuggestionRepository extends JpaRepository<SuggestionEntity,Integer> {

    @Modifying
    @Transactional
    SuggestionEntity save(SuggestionEntity suggestionEntity);
    SuggestionEntity findOneByIdAndUserId(Integer id,Integer userId);
}
