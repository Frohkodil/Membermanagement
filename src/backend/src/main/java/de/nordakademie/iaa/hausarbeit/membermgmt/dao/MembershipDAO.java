package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MembershipDAO {
    private EntityManager entityManager;

    public List<Membership> listMemberships() {
        return entityManager.createQuery("select * from Membership ").getResultList();
    }


    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Membership loadMembership(Long id) {
        return entityManager.find(Membership.class, id);
    }

    public void persistMembership(Membership membership) {
    }
}
