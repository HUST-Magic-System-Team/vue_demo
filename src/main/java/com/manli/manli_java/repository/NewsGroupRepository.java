package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsGroupRepository extends JpaRepository<NewsGroupEntity, Integer> {
    //list
    List<NewsGroupEntity> findAllByStatus(Byte status);


}
