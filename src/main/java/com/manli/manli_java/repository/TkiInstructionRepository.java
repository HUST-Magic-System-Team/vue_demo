package com.manli.manli_java.repository;


import com.manli.manli_java.model_auto.TkiInstructionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TkiInstructionRepository extends JpaRepository<TkiInstructionEntity, Integer> {
    //page
    Page<TkiInstructionEntity> findAllByStatus(Byte status, Pageable pageable);

}