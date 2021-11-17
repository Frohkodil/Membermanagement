package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MembershipDAO implements DAO<Membership>{
    private EntityManager entityManager;

    @Override
    public Optional<Membership> get(Long id) {
        return Optional.ofNullable(entityManager.find(Membership.class, id));
    }

    @Override
    public List<Membership> getAll() {
        return entityManager.createQuery("select * from Membership ").getResultList();
    }

    @Override
    public void save(Membership membership) {

    }

    @Override
    public void update(Membership membership) {

    }

    @Override
    public void delete(Membership membership) {

    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
