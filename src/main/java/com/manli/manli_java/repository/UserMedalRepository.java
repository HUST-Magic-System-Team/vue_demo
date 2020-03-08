package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMedalRepository extends JpaRepository<UserMedalEntity, Integer> {
    //list
    List<UserMedalEntity> getAllByIdIn(String[] medals);

    //save
    UserMedalEntity save(UserMedalEntity userMedalEntity);
}
