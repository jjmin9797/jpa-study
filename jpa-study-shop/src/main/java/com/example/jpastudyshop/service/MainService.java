package com.example.jpastudyshop.service;


import com.example.jpastudyshop.domain.Member;
import com.example.jpastudyshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Service
@RequiredArgsConstructor
public class MainService {

    private final MemberRepository memberRepository;

    public void saveMember(Member member){
        System.out.println("In Controller");
        memberRepository.save(member);
    }
}
