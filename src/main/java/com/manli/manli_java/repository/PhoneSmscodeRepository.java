package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneSmscodeRepository extends JpaRepository<PhoneSmscodeEntity, Integer> {

    //find one
    boolean existsByPhone(String phone);

    PhoneSmscodeEntity findByPhone(String phone);

    PhoneSmscodeEntity findOneByPhoneAndSmscode(String phone, String smscode);

    //save
    PhoneSmscodeEntity save(PhoneSmscodeEntity phoneSmscodeEntity);


}
