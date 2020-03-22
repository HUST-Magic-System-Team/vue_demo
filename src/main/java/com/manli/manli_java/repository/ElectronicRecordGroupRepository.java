package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.ElectronicRecordEntity;
import com.manli.manli_java.model_auto.ElectronicRecordGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElectronicRecordGroupRepository extends JpaRepository<ElectronicRecordGroupEntity,Integer> {
        ElectronicRecordGroupEntity findOneById(Integer id);
        List<ElectronicRecordGroupEntity> findAllByStatus(Byte status);

}
