package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NewsGroupRepository extends JpaRepository<NewsGroupEntity, Integer> {
    //list
    List<NewsGroupEntity> findAllByStatus(Byte status);


}
