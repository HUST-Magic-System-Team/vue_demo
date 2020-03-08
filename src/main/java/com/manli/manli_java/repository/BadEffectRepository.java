package com.manli.manli_java.repository;


import com.manli.manli_java.model_auto.BadEffectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadEffectRepository extends JpaRepository<BadEffectEntity, Integer> {

    //find one
    BadEffectEntity findOneByBadEffectAndStatus(String badEffect, Byte status);

    //list
    List<BadEffectEntity> findDistinctBadEffectByStatus(Byte status);

    List<BadEffectEntity> findAllByStatusOrderByPositionDesc(Byte status);

    //page

    //save

    //saveAll

    //delete

    //native query
    @Query(value = "select distinct b.badEffect from BadEffectEntity b where b.status = ?1 order by b.position asc")
    List<String> getDistinctBadEffectList(Byte status);


}