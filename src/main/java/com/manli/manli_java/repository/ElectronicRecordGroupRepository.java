package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.ElectronicRecordGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectronicRecordGroupRepository extends JpaRepository<ElectronicRecordGroupEntity,Integer> {
   ElectronicRecordGroupEntity findOneById(Integer id);
}
