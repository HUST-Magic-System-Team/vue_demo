package com.manli.manli_java.repository;

import com.manli.manli_java.model_auto.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Integer> {

}
