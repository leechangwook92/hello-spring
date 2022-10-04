package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     *
     * 회원 가입
     */
    public Long join(Member member){
        validateDuplicateMember(member);// 같은 이름이 있는 중복 회원x
        memberRepository.save(member);
        return member.getId();
    }

    /**
     *
     * 중복기능 메소드
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 아이디로 확인
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    /**
     * 삭제
     */
    public void deleteOne(Long id){
        memberRepository.deleteById(id);
    }

    /**
     *  업데이트
     */
    public void updateMember(Long id, String title, Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    member.setTitle(title);
                });
        memberRepository.save(member);
    }
}
