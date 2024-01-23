package jpabook.japshop.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jpabook.japshop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Spring Bean으로 등록시켜줌 (Component를 상속 받음)
@RequiredArgsConstructor
public class MemberRepository {

     // Spring이 JPA EntityManager를 주입 시켜줌
//    @Autowired @PersistenceContext 스프링 부트가 부개의 어노테이션을 알아서 지원 해줌 ( 생상자 빈 주입, 엔티티 매니저 주입 )
    private final EntityManager entityManager;

    public void save(Member member) {
        entityManager.persist(member);
    }

    public Member findOne(Long id) {
        return entityManager.find(Member.class, id); //첫 번째 타입, 두번째 pk
    }

    public List<Member> findAll() {
        return entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();

        //createQuery는 JPQL을 생성하는데 mySQL과 유사함, 다만 대상이 table이 아니라 Entity
    }

    public List<Member> findByNames(String name) {
        return entityManager.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name", name)
                .getResultList();

    }

}
