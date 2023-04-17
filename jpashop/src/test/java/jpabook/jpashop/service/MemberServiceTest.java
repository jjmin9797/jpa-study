package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 가입 테스트")
    public void givenMemberInfoWhenSaveMemberThenSaveMember() throws Exception {
        // given
        Member member = new Member();
        member.setName("kimaa");

        // when
        Long savedId = memberService.join(member);

        // then
        assertThat(savedId).isEqualTo(member.getId());
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    @DisplayName("중복 회원 가입 오류 발생")
    public void givenDuplicateMemberNameWhenSaveMemberThenException() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
        //방법1
//        try {
//            memberService.join(member2);
//        } catch (IllegalStateException e) {
//            return;
//        }
//        // then
//        fail();
    }
}