package jpabook.japshop.Service;

import jpabook.japshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jpabook.japshop.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
//@Transactional // JPA의 데이터가 변경되는 모든 일련의 과정들은 가급적 트랜잭션 단위 안에서 일어나야 한다.
@Transactional(readOnly = true) //JPA가 최적화 함. 성능 향상, 충돌 방지, 가독성 등
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원 가입
    @Transactional // 만약 클래스에 읽는 메서드들이 대다수 일 경우 클래스 전체에 readonly를 하고 이 처럼 쓰는 메서드에만 false를 주는 게 효율적이다
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);

        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원 입니다. ");
        }
    }

    //    회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //  단건 조회
    public Member findMember(Long id) {
        return memberRepository.findOne(id);
    }
}
