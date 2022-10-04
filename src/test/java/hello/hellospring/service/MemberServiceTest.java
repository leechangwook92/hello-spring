package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void update() throws Exception {
        //given
        Member member = new Member();
        member.setName("이창욱");
        memberService.join(member);

        Long id = member.getId();
        String name = member.getName();
        String trans = "삼창욱";
        Member findMember = memberRepository.findById(id).get();

        memberService.updateMember(id,trans,findMember);

        //when
        Assertions.assertThat(findMember.getName()).isEqualTo("삼창욱");
        System.out.println("findMember = " + findMember);

        //then
    }
}