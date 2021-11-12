package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.Member;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MemberDAO {
    private EntityManager entityManager;



    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void persistMember(Member member) throws ConstraintViolationException {
        entityManager.persist(member);
    }

    public void deleteMember(Member member) {
        entityManager.remove(member);
    }

    public Member loadMember(Long id) {
        return entityManager.find(Member.class, id);
    }

    public List<Member> listMembers() {
        return entityManager.createQuery("select member from Member member").getResultList();
    }
}
