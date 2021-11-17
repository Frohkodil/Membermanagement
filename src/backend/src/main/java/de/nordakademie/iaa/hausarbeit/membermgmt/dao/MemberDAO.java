package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.Member;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberDAO implements DAO<Member>{
    private EntityManager entityManager;


    @Override
    public Optional<Member> get(Long id) {
        return Optional.ofNullable(entityManager.find(Member.class, id));
    }

    @Override
    public List<Member> getAll() {
        return entityManager.createQuery("select member from Member member").getResultList();
    }

    @Override
    public void save(Member member) throws ConstraintViolationException{
        entityManager.persist(member);
    }

    @Override
    public void update(Member member) {

    }

    @Override
    public void delete(Member member) {
        entityManager.remove(member);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
