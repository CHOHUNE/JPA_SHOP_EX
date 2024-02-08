package jpabook.japshop.service;


import jakarta.persistence.EntityManager;
import jpabook.japshop.Service.MemberService;
import jpabook.japshop.domian.Member;
import jpabook.japshop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest //위 두개 코드는 스프링과 연결해서 실무같은 테스트를 진행하기 위함
@Transactional //데이터를 변경 해야 하니 롤백을 위한 태그, 테스트케이스에서 쓰일 떄는 기본적으로 롤백 ( 레퍼지토리,서비스는 아님)
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Autowired
    EntityManager em;


    @Test
    @Rollback(false) //해당 태그가 있으면 JPA가 롤백하지 않고 insert 문이 나간다. 영속성 컨텍스트 flush commit
    public void 회원가입() throws Exception {


        //give
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
//        em.flush(); 강제로 플러쉬를 해도 인서트문을 확인할 수 있다.
        Assert.assertEquals(member, memberRepository.findOne(saveId));

        // 번외로 이 테스트 코드를 실행 하면 select 쿼리만 나가고 insert문이 안나가는데, 이유는 아래와 같다.
        // join -> save -> em.persist = 결론 persist 만으로는 DB에 insert문이 나가지 않음
    }

    @Test(expected = IllegalStateException.class) // 이 코드를 작성하면 따로 try catch문이 필요 없다.
    public void 중복_회원_예외() throws Exception {

        //give
        Member member = new Member();
        member.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        //when
//
//        memberService.join(member);
//        try {
//
//            memberService.join(member2); // 예외가 발생 해야 한다.
//
//        } catch (IllegalStateException e) {
//            return;
//        }

        //then
        Assert.fail("여기 까지 도달하면 테스트가 실패");
    }
}

//