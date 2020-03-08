package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsEntity, Integer> {

    //find one
    NewsEntity findOneByIdAndStatus(int id, Byte status);

    NewsEntity findOneById(int id);

    boolean existsByStatus(Byte status);


    //list
    List<NewsEntity> findAllByStatus(Byte status);


    //page
    Page<NewsEntity> findAllByStatus(Byte status, Pageable pageable);

    Page<NewsEntity> findAllByStatusAndGroupId(Byte status, int groupId, Pageable pageable);


}
