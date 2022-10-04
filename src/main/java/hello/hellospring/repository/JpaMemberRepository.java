package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository {

    // 모든게 EntityManager에서 동작함 데이터베이스랑 다 연동해서 만들어줌 DataSource같은걸 다 들고있음
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        em.createQuery("delete from Member m where m.id = :id ")
                .setParameter("id",id)
                .executeUpdate();
    }

//    @Override
//    @Transactional
//    public void updateByIdByName(Long id, String name) {
//        em.createQuery("UPDATE Member m SET m.name = :name WHERE m.id = :id")
//                .setParameter("id",id)
//                .setParameter("name",name)
//                .executeUpdate();
//    }
}