package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
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
