package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.MembershipType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MembershipTypeDAO implements DAO<MembershipType>{
    private EntityManager entityManager;


    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<MembershipType> get(Long id) {
        return Optional.ofNullable(entityManager.find(MembershipType.class, id));
    }

    @Override
    public List<MembershipType> getAll() {
        return entityManager.createQuery("select membershipType from MembershipType membershipType").getResultList();
    }

    @Override
    public void save(MembershipType membershipType) {
        entityManager.persist(membershipType);
    }

    @Override
    public void update(MembershipType membershipType) {

    }

    @Override
    public void delete(MembershipType membershipType) {

    }
}
