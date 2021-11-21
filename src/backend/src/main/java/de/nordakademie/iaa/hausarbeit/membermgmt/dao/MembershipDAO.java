package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MembershipDAO{
    private EntityManager entityManager;

    public Optional<Membership> get(Long id) {
        return Optional.ofNullable(entityManager.find(Membership.class, id));
    }

    public List<Membership> getAll() {
        return entityManager.createQuery("from Membership").getResultList();
    }

    public void save(Membership membership) {
        entityManager.persist(membership);
    }

    public void update(Membership membership) {

    }

    public void delete(Membership membership) {

    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
