package jpabook.japshop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jpabook.japshop.domian.Member;
import java.util.List;

@Repository //스프링 빈에 등록하는 어노테이션
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceUnit 거의 쓸일은 없지만 팩토리를 주입받는 어노테이션


    //@PersistenceContext //스프링이 EntityManager를 일일히 꺼낼 필요 없이 쓰게 도와주는 어노테이션
    //본래 JPA EntityManager는 위 주석을 달아야 의존성 주입이 되는데 스프링이 Autowired도 주입이 되게 도와준다.

    @Autowired
    private EntityManager em;

    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) { //셋 파라메터
        return em.createQuery("select m from Member m Where m.name = :name", Member.class).setParameter("name", name).getResultList();
    }

}
