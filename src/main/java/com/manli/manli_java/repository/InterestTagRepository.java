package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.InterestTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InterestTagRepository extends JpaRepository<InterestTagEntity, Integer> {

    //find one
    boolean existsByStatus(Byte status);

    InterestTagEntity findOneByIdAndStatus(Integer id, Byte status);

    //list
    List<InterestTagEntity> findAllByStatus(Byte status);

}
