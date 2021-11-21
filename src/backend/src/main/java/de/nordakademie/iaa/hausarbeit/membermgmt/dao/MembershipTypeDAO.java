package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.MembershipType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MembershipTypeDAO {
    private EntityManager entityManager;


    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<MembershipType> get(Long id) {
        return Optional.ofNullable(entityManager.find(MembershipType.class, id));
    }

    public List<MembershipType> getAll() {
        return entityManager.createQuery("from MembershipType").getResultList();
    }

    public void save(MembershipType membershipType) {
        entityManager.persist(membershipType);
    }

    public void update(MembershipType membershipType) {

    }

    public void delete(MembershipType membershipType) {

    }
}
