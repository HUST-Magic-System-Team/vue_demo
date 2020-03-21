package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.MemberEntity;
import com.manli.manli_java.model_impl.MemberImpl1;
import com.manli.manli_java.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public MemberImpl1 getMemberInfo(Integer userId) {
        MemberEntity memberEntity = memberRepository.findById(userId).orElse(null);
        MemberImpl1 memberImpl1 = new MemberImpl1();
        memberImpl1.setUserId(memberEntity.getUserId());
        memberImpl1.setName(memberEntity.getName());
        memberImpl1.setPhone(memberEntity.getPhone());
        memberImpl1.setEmail(memberEntity.getEmail());
        memberImpl1.setLocation(memberEntity.getLocation());
        memberImpl1.setQq(memberEntity.getQq());
        memberImpl1.setAddress(memberEntity.getAddress());
        memberImpl1.setIsMember(isMember(userId));
        return memberImpl1;
    }

    public boolean isMember(Integer userId) {
        if (memberRepository.existsById(userId)) {
            return true;
        } else {
            return false;
        }

    }

    public void add(String name,String phone,String email,String location,String qq,String address){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setName(name);
        memberEntity.setPhone(phone);
        memberEntity.setEmail(email);
        memberEntity.setLocation(location);
        memberEntity.setQq(qq);
        memberEntity.setAddress(address);
        memberRepository.save(memberEntity);
    }

}
