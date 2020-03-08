package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Integer> {

    //find one
    boolean existsByPhone(String phone);

    boolean existsByToken(String token);

    UserTokenEntity findOneByPhone(String phone);

    UserTokenEntity findOneByToken(String token);

    //save
    UserTokenEntity save(UserTokenEntity userTokenEntity);

}
