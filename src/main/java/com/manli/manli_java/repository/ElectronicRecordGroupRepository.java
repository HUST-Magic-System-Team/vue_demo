package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.ElectronicRecordEntity;
import com.manli.manli_java.model_auto.ElectronicRecordGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ElectronicRecordGroupRepository extends JpaRepository<ElectronicRecordGroupEntity,Integer> {
        ElectronicRecordGroupEntity findOneById(Integer id);
        List<ElectronicRecordGroupEntity> findAllByStatus(Byte status);

}
