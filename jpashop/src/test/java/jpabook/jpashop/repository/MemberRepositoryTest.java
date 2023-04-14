package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @DisplayName("")
    @Transactional
    void testMember() {
        //given
        Member member = new Member();
        member.setUsername("JM");

        //when
        Long savedId = memberRepository.save(member);

        //then
        Member findMember = memberRepository.find(savedId);
        assertThat(findMember.getId()).isEqualTo(savedId);
    }

}