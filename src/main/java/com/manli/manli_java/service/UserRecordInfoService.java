package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.*;
import com.manli.manli_java.model_impl.UserRecordInfoImpl1;
import com.manli.manli_java.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRecordInfoService {
    @Autowired
    UserRecordInfoRepository userRecordInfoRepository;

    public UserRecordInfoEntity getUserRecordInfoAsync(Integer userId) {
        UserRecordInfoEntity userRecordInfoEntity = userRecordInfoRepository.findOneByUserIdAndStatus(userId, Byte.valueOf("0"));
        return userRecordInfoEntity;
    }

    public void updateUserRecordInfo(Integer userId, UserRecordInfoImpl1 userRecordInfoImpl1) {
        UserRecordInfoEntity userRecordInfoEntity = userRecordInfoRepository.findOneByUserIdAndStatus(userId, Byte.valueOf("0"));
        if (userRecordInfoImpl1.getHospital() != null) {
            userRecordInfoEntity.setHospital(userRecordInfoImpl1.getHospital());
        }
        if (userRecordInfoImpl1.getDoctor() != null) {
            userRecordInfoEntity.setDoctor(userRecordInfoImpl1.getDoctor());
        }
        if (userRecordInfoImpl1.getProfession() != null) {
            userRecordInfoEntity.setProfession(userRecordInfoImpl1.getProfession());
        }
        if (userRecordInfoImpl1.getWeight() != null) {
            userRecordInfoEntity.setWeight(userRecordInfoImpl1.getWeight());
        }
        if (userRecordInfoImpl1.getCompl() != null) {
            userRecordInfoEntity.setCompl(userRecordInfoImpl1.getCompl());
        }
        if (userRecordInfoImpl1.getbChaoDown() != null) {
            userRecordInfoEntity.setbChaoDown(userRecordInfoImpl1.getbChaoDown());
        }
        if (userRecordInfoImpl1.getbChaoLength() != null) {
            userRecordInfoEntity.setbChaoLength(userRecordInfoImpl1.getbChaoLength());
        }
        if (userRecordInfoImpl1.getbChaoDepth() != null) {
            userRecordInfoEntity.setbChaoDepth(userRecordInfoImpl1.getbChaoDepth());
        }

        if (userRecordInfoImpl1.getBloodTestWhite() != null) {
            userRecordInfoEntity.setBloodTestWhite(userRecordInfoImpl1.getBloodTestWhite());
        }
        if (userRecordInfoImpl1.getBloodTestRed() != null) {
            userRecordInfoEntity.setBloodTestRed(userRecordInfoImpl1.getBloodTestRed());
        }
        if (userRecordInfoImpl1.getBloodTestPlatelet() != null) {
            userRecordInfoEntity.setBloodTestPlatelet(userRecordInfoImpl1.getBloodTestPlatelet());
        }

        if (userRecordInfoImpl1.getMarrowCells() != null) {
            userRecordInfoEntity.setMarrowCells(userRecordInfoImpl1.getMarrowCells());
        }
        if (userRecordInfoImpl1.getGene() != null) {
            userRecordInfoEntity.setGene(userRecordInfoImpl1.getGene());
        }
        if (userRecordInfoImpl1.getChromosome() != null) {
            userRecordInfoEntity.setChromosome(userRecordInfoImpl1.getChromosome());
        }
        if (userRecordInfoImpl1.getConfirmDate() != null) {
            userRecordInfoEntity.setConfirmDate(userRecordInfoImpl1.getConfirmDate());
        }

        if (userRecordInfoImpl1.getFirstMedicineDate() != null) {
            userRecordInfoEntity.setFirstMedicineDate(userRecordInfoImpl1.getFirstMedicineDate());
        }
        if (userRecordInfoImpl1.getCml() != null) {
            userRecordInfoEntity.setCml(userRecordInfoImpl1.getCml());
        }
        if (userRecordInfoImpl1.getCombinedChemotherapy() != null) {
            userRecordInfoEntity.setCombinedChemotherapy(userRecordInfoImpl1.getCombinedChemotherapy());
        }

        userRecordInfoRepository.save(userRecordInfoEntity);
    }

}
